package spring.neotodobackend.application.user.SignUpUseCase;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.user.SignUpUseCase.dto.SignUpUseCaseResponse;
import spring.neotodobackend.application.user.SignUpUseCase.vo.SignUpUseCaseRequestBody;
import spring.neotodobackend.application.user.SignUpUseCase.vo.SignUpUseCaseResponseBody;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.domain.entity.User;
import spring.neotodobackend.domain.enums.UserStatus;
import spring.neotodobackend.infra.UserRepository;
import spring.neotodobackend.interfaces.IRequest;
import spring.neotodobackend.interfaces.IResponse;

import java.time.LocalDateTime;
import java.util.Optional;

import static spring.neotodobackend.core.error.enums.BadRequestCode.DUPLICATE_ID;
import static spring.neotodobackend.core.error.enums.BadRequestCode.DUPLICATE_NICKNAME;

@Service
@AllArgsConstructor
public class SignUpUseCase {
    private UserRepository userRepository;
//    private final static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private EntityManager entityManager;

    @Transactional(rollbackOn = Exception.class)
    public IResponse<SignUpUseCaseResponseBody> signUp(IRequest<SignUpUseCaseRequestBody> iRequest) {

        SignUpUseCaseRequestBody conditions = iRequest.getConditions();

        Optional<User> checkNickName = userRepository.findByNicknameAndStatus(conditions.getNickname(),
                UserStatus.ACTIVE.name());

        Optional<User> checkId = userRepository.findByIdAndStatus(conditions.getId(),
                UserStatus.ACTIVE.name());

        if (checkNickName.isPresent()) throw CommonException.init(DUPLICATE_NICKNAME);
        if (checkId.isPresent()) throw CommonException.init(DUPLICATE_ID);

        User user = new User();

        user.setNickname(conditions.getNickname());
        user.setId(conditions.getId());
        user.setPassword(conditions.getPassword());
        user.setRegisterDatetime(LocalDateTime.now());
        user.setStatus(UserStatus.ACTIVE.name());

        entityManager.persist(user);

        return SignUpUseCaseResponse.init(user.getIndex());
    }


}
