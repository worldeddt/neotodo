package spring.neotodobackend.application.user.FindUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FindUseCaseRequestBody {
    private String id;
    private String password;

    public static FindUseCaseRequestBody init(String id, String password) {
        return FindUseCaseRequestBody.builder()
                .id(id)
                .password(password)
                .build();
    }
}
