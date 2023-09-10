package spring.neotodobackend.application.user.SignUpUseCase.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SignUpUseCaseResponseBody {
    private Integer index;

    public static SignUpUseCaseResponseBody init(Integer index) {
        return SignUpUseCaseResponseBody.builder()
                .index(index)
                .build();
    }
}
