package spring.neotodobackend.presentation.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserSignOutRequest {
    @NotNull(message = "아이디가 누락되었습니다.")
    @Size(min = 5, message = "최소 5자리 이상입니다.")
    private String id;
    @NotNull(message = "비밀번호가 누락되었습니다.")
    @Size(min = 8, message = "최소 8자리 이상입니다.")
    private String password;
    @NotNull(message = "닉네임이 누락되었습니다.")
    private String nickname;
}
