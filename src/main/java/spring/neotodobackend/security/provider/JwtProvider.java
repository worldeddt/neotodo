package spring.neotodobackend.security.provider;



import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import spring.neotodobackend.config.TokenConfiguration;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.domain.UserDomain;
import spring.neotodobackend.domain.entity.User;
import spring.neotodobackend.presentation.response.TokenInfo;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.time.LocalDateTime.now;
import static spring.neotodobackend.core.error.enums.BadRequestCode.*;

@Slf4j
@Component
public class JwtProvider {
    private final Key key;
    private final TokenConfiguration tokenConfiguration;
    private final CustomDetailService customDetailService;

    public JwtProvider(TokenConfiguration tokenConfiguration, CustomDetailService customDetailService) throws WeakKeyException {
        this.tokenConfiguration = tokenConfiguration;
        this.customDetailService = customDetailService;
        byte[] keyBytes = Decoders.BASE64.decode(this.tokenConfiguration.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Authentication getAuthentication(String jwtToken) {
        UserDetails userDetails = customDetailService.loadUserByUsername(getUserEmail(jwtToken));
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    private String getUserEmail(String token) {
        Claims claims = parseClaims(token);
        if (claims.get("id") == null) throw CommonException.init(CANNOT_FIND_AUTH_INFO);

        return claims.get("id").toString();
    }

    public String getUserId(String token) {
        Claims claims = parseClaims(token);
        if (claims.get("id") == null) throw CommonException.init(CANNOT_FIND_AUTH_INFO);

        return claims.get("id").toString();
    }


    public Date getExpireDateAccessToken() {
        long expireTimeMils = 1000 * 60 * 60 * 3;
        log.info("System.currentTimeMillis() : "+System.currentTimeMillis());
        log.info("expireTimeMils : "+new Date(System.currentTimeMillis() + expireTimeMils));
        return new Date(System.currentTimeMillis() + expireTimeMils);
    }

    public Date getExpireDateRefreshToken() {
        long expireTimeMils = 1000L * 60 * 60 * 24 * 60;
        return new Date(System.currentTimeMillis() + expireTimeMils);
    }

    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public TokenInfo generateToken(UserDomain userDomain) {
        String accessToken = getAccessToken(userDomain);
        String refreshToken = getRefreshToken(userDomain);

        return TokenInfo.init("Bearer", accessToken, refreshToken);
    }

    private String getRefreshToken(UserDomain userDomain) {
        return Jwts.builder()
                .setExpiration(getExpireDateRefreshToken())
                .claim("id", userDomain.getId())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    private String getAccessToken(UserDomain userDomain) {
        long now = (new Date()).getTime();
        String accessToken = Jwts.builder()
                .claim("id", userDomain.getId())
                .setIssuedAt(new Date())
                .setExpiration(getExpireDateAccessToken())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        return accessToken;
    }

    public void validateToken(String token) {
        try {
            if (token == null) throw CommonException.init(CANNOT_FIND_TOKEN);

            Claims claims = parseClaims(token);
            LocalDateTime localDateTime = getLocalDateTime(claims);

            if (claims.get("id") == null) throw CommonException.init(CANNOT_FIND_AUTH_INFO);
            if (now().isAfter(localDateTime)) throw CommonException.init(EXPIRED_JWT_TOKEN);

            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
            throw CommonException.init(MALFORMAT_JWT_TOKEN);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            throw CommonException.init(EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            throw CommonException.init(UNSUPPORT_JWT_TOKEN);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
            throw CommonException.init(ILLEGALARGUMENT_TOKEN);
        }
    }

    private static LocalDateTime getLocalDateTime(Claims claims) {
        return claims.getExpiration()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public  Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder().
                    setSigningKey(key).
                    build().
                    parseClaimsJws(token).
                    getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
