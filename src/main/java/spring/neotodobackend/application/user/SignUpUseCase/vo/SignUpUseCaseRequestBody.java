package spring.neotodobackend.application.user.SignUpUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SignUpUseCaseRequestBody {
    private String id;
    private String password;
    private String nickname;

    public static SignUpUseCaseRequestBody init(String id, String password, String nickname) {
        return SignUpUseCaseRequestBody.builder()
                .id(id)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
