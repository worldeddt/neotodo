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

    public static FindUserResponse init(String id, String nickname) {
        return FindUserResponse.builder()
                .id(id)
                .nickname(nickname)
                .build();
    }
}
