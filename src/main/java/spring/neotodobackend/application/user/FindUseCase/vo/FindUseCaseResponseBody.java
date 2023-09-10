package spring.neotodobackend.application.user.FindUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import spring.neotodobackend.presentation.response.TokenInfo;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FindUseCaseResponseBody {
    private String id;
    private String nickname;
    private TokenInfo tokenInfo;

    public static FindUseCaseResponseBody init(String id, String nickname, TokenInfo tokenInfo) {
        return FindUseCaseResponseBody.builder()
                .id(id)
                .nickname(nickname)
                .tokenInfo(tokenInfo)
                .build();
    }
}
