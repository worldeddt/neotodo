package spring.neotodobackend.security.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.core.http.HttpStatusCategories;
import spring.neotodobackend.security.provider.JwtProvider;

import java.io.IOException;
import java.util.ArrayList;

import static spring.neotodobackend.core.error.enums.BadRequestCode.CANNOT_FIND_AUTH_INFO;
import static spring.neotodobackend.security.config.SecurityConfig.whiteListUris;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (isWhiteListURI(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtProvider.validateToken(resolveToken(request));
        ArrayList<Integer> normalHttpStatus = HttpStatusCategories.getNormalHttpStatus();

        log.info("httpstatus at filter :"+response.getStatus());
        if (!normalHttpStatus.contains(response.getStatus())) throw CommonException.init(CANNOT_FIND_AUTH_INFO);

        filterChain.doFilter(request, response);
    }

    private boolean isWhiteListURI(HttpServletRequest servletRequest) {
        return (servletRequest.getRequestURI() != null) && whiteListCheck(servletRequest.getRequestURI());
    }

    private boolean whiteListCheck(String uri){
        return PatternMatchUtils.simpleMatch(whiteListUris,uri);
    }

    // Request Header 에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization");
        if (itHasToken(bearerToken)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private static boolean itHasToken(String bearerToken) {
        return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer");
    }
}
