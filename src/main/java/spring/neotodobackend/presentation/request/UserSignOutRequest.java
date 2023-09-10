package spring.neotodobackend.presentation.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserSignOutRequest {
    @NotBlank(message = "아이디가 누락되었습니다.")
    private String id;
}
