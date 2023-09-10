package spring.neotodobackend.application.user.SignOutUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SignOutUseCaseRequestBody {
    private String id;

    public static SignOutUseCaseRequestBody init(String id) {
        return SignOutUseCaseRequestBody.builder()
                .id(id)
                .build();
    }
}
