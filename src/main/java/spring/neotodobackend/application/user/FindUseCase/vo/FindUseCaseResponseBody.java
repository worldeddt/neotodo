package spring.neotodobackend.application.user.FindUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FindUseCaseResponseBody {
    private String id;
    private String nickname;

    public static FindUseCaseResponseBody init(String id, String nickname) {
        return FindUseCaseResponseBody.builder()
                .id(id)
                .nickname(nickname)
                .build();
    }
}
