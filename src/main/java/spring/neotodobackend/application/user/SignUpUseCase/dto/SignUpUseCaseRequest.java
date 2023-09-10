package spring.neotodobackend.application.user.SignUpUseCase.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.user.SignUpUseCase.vo.SignUpUseCaseRequestBody;
import spring.neotodobackend.infra.UserRepository;
import spring.neotodobackend.interfaces.IRequest;

@Builder(access = AccessLevel.PRIVATE)
public class SignUpUseCaseRequest implements IRequest<SignUpUseCaseRequestBody> {
    private String id;
    private String password;
    private String nickname;


    public static SignUpUseCaseRequest init(String id, String password, String nickname) {
        return SignUpUseCaseRequest.builder()
                .id(id)
                .password(password)
                .nickname(nickname)
                .build();
    }

    @Override
    public SignUpUseCaseRequestBody getConditions() {
        return SignUpUseCaseRequestBody
                .init(
                        this.id,
                        this.password,
                        this.nickname
                );
    }
}
