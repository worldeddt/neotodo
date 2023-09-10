package spring.neotodobackend.application.user.FindUseCase;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.user.FindUseCase.dto.FindUseCaseResponse;
import spring.neotodobackend.application.user.FindUseCase.vo.FindUseCaseRequestBody;
import spring.neotodobackend.application.user.FindUseCase.vo.FindUseCaseResponseBody;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.domain.entity.User;
import spring.neotodobackend.domain.enums.UserStatus;
import spring.neotodobackend.infra.UserRepository;
import spring.neotodobackend.interfaces.IRequest;
import spring.neotodobackend.interfaces.IResponse;

import static spring.neotodobackend.core.error.enums.NotFoundCode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class FindUseCase {
    private UserRepository userRepository;

    public IResponse<FindUseCaseResponseBody> login(IRequest<FindUseCaseRequestBody> iRequest) {

        User user = this.userRepository.findByIdAndStatus(iRequest.getConditions().getId(), UserStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(USER_NOT_FOUND));

        //todo password 점검

        return FindUseCaseResponse.init(user.getId(), user.getNickname());
    }

}
