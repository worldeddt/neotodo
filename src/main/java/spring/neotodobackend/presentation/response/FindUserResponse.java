package spring.neotodobackend.presentation.response;

import jakarta.persistence.Access;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import spring.neotodobackend.core.BaseResponse;


@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class FindUserResponse extends BaseResponse {
    private String id;
    private String nickname;
    private TokenInfo tokenInfo;

    public static FindUserResponse init(String id, String nickname, TokenInfo tokenInfo) {
        return FindUserResponse.builder()
                .id(id)
                .nickname(nickname)
                .tokenInfo(tokenInfo)
                .build();
    }
}
