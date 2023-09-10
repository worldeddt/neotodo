package spring.neotodobackend.application.user.SignOutUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import spring.neotodobackend.application.user.SignOutUseCase.vo.SignOutUseCaseRequestBody;
import spring.neotodobackend.interfaces.IRequest;



@Builder(access = AccessLevel.PRIVATE)
public class SignOutUseCaseRequest implements IRequest<SignOutUseCaseRequestBody> {
    private String id;

    public static SignOutUseCaseRequest init(String id) {
        return SignOutUseCaseRequest.builder()
                .id(id)
                .build();
    }

    @Override
    public SignOutUseCaseRequestBody getConditions() {
        return SignOutUseCaseRequestBody.init(this.id);
    }
}
