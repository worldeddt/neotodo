package spring.neotodobackend.application.user.FindUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import spring.neotodobackend.application.user.FindUseCase.vo.FindUseCaseRequestBody;
import spring.neotodobackend.interfaces.IRequest;


@Builder(access = AccessLevel.PRIVATE)
public class FindUseCaseRequest implements IRequest<FindUseCaseRequestBody> {
    private String id;
    private String password;

    public static FindUseCaseRequest init(String id, String password) {
        return FindUseCaseRequest.builder()
                .id(id)
                .password(password)
                .build();
    }

    @Override
    public FindUseCaseRequestBody getConditions() {
        return FindUseCaseRequestBody.init(
                this.id, this.password
        );
    }
}
