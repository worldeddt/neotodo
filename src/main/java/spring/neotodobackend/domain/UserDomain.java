package spring.neotodobackend.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import spring.neotodobackend.domain.entity.Todo;
import spring.neotodobackend.domain.entity.User;


@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UserDomain {
    private Integer index;
    private String id;
    private String password;
    private String nickname;
    private String status;
    private String refreshToken;
    private String registerDatetime;
    private String updateDatetime;

    public static UserDomain init(User user) {
        return UserDomain.builder()
                .index(user.getIndex())
                .id(user.getId())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .status(user.getStatus())
                .refreshToken(user.getRefreshToken())
                .registerDatetime(user.getUpdateDatetime() == null ? "0000-00-00 00:00:00" :user.getUpdateDatetime().toString())
                .registerDatetime(user.getUpdateDatetime() == null ? "0000-00-00 00:00:00" :user.getUpdateDatetime().toString())
                .build();
    }
}
