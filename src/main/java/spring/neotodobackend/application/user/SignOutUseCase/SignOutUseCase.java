package spring.neotodobackend.application.user.SignOutUseCase;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.user.SignOutUseCase.vo.SignOutUseCaseRequestBody;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.domain.entity.User;
import spring.neotodobackend.domain.enums.UserStatus;
import spring.neotodobackend.infra.UserRepository;
import spring.neotodobackend.interfaces.IRequest;

import java.time.LocalDateTime;

import static spring.neotodobackend.core.error.enums.NotFoundCode.USER_NOT_FOUND;


@Service
@AllArgsConstructor
public class SignOutUseCase {
    private UserRepository userRepository;

    @Transactional(rollbackOn=Exception.class)
    public void signOut(IRequest<SignOutUseCaseRequestBody> iRequest) {
        User user = this.userRepository.findByIdAndStatus(iRequest.getConditions().getId(), UserStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(USER_NOT_FOUND));

        user.setStatus(UserStatus.DELETED.name());
        user.setUpdateDatetime(LocalDateTime.now());
    }
}
