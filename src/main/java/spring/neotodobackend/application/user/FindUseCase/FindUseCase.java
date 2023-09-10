package spring.neotodobackend.application.user.FindUseCase;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.user.FindUseCase.dto.FindUseCaseResponse;
import spring.neotodobackend.application.user.FindUseCase.vo.FindUseCaseRequestBody;
import spring.neotodobackend.application.user.FindUseCase.vo.FindUseCaseResponseBody;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.domain.UserDomain;
import spring.neotodobackend.domain.entity.User;
import spring.neotodobackend.domain.enums.UserStatus;
import spring.neotodobackend.infra.UserRepository;
import spring.neotodobackend.interfaces.IRequest;
import spring.neotodobackend.interfaces.IResponse;
import spring.neotodobackend.presentation.response.TokenInfo;
import spring.neotodobackend.security.provider.JwtProvider;

import static spring.neotodobackend.core.error.enums.BadRequestCode.INVALID_PASSWORD;
import static spring.neotodobackend.core.error.enums.NotFoundCode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class FindUseCase {
    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private final static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(rollbackOn = Exception.class)
    public IResponse<FindUseCaseResponseBody> login(IRequest<FindUseCaseRequestBody> iRequest) {

        User user = this.userRepository.findByIdAndStatus(iRequest.getConditions().getId(), UserStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(USER_NOT_FOUND));

        if (!passwordEncoder.matches(iRequest.getConditions().getPassword(), user.getPassword()))
            throw CommonException.init(INVALID_PASSWORD);

        UserDomain userDomain = UserDomain.init(user);

        TokenInfo tokenInfo = jwtProvider.generateToken(userDomain);

        user.setRefreshToken(tokenInfo.getRefreshToken());

        return FindUseCaseResponse.init(user.getId(), user.getNickname(), tokenInfo);
    }

}
