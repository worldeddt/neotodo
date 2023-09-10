package spring.neotodobackend.application.user.SignUpUseCase.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.user.SignUpUseCase.vo.SignUpUseCaseResponseBody;
import spring.neotodobackend.infra.UserRepository;
import spring.neotodobackend.interfaces.IResponse;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public class SignUpUseCaseResponse implements IResponse<SignUpUseCaseResponseBody> {
    private Integer index;

    public static SignUpUseCaseResponse init(Integer index) {
        return SignUpUseCaseResponse.builder()
                .index(index)
                .build();
    }

    @Override
    public List<SignUpUseCaseResponseBody> getResponses() {
        return null;
    }

    @Override
    public SignUpUseCaseResponseBody getResponse() {
        return SignUpUseCaseResponseBody.init(
                this.index
        );
    }
}
