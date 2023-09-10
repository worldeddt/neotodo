package spring.neotodobackend.core.application;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.user.FindUseCase.FindUseCase;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.domain.UserDomain;
import spring.neotodobackend.domain.entity.User;
import spring.neotodobackend.infra.UserRepository;
import spring.neotodobackend.presentation.response.TokenInfo;
import spring.neotodobackend.security.provider.JwtProvider;

import static spring.neotodobackend.core.error.enums.NotFoundCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Transactional(rollbackOn = Exception.class)
    public TokenInfo refreshToken(String refreshToken) {
        jwtProvider.validateToken(refreshToken);

        User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> CommonException.init(USER_NOT_FOUND));

        TokenInfo tokenInfo = jwtProvider.generateToken(UserDomain.init(user));

        user.setRefreshToken(tokenInfo.getRefreshToken());

        return tokenInfo;
    }
}
