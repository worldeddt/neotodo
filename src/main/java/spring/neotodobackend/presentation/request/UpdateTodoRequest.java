package spring.neotodobackend.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateTodoRequest {
    @NotNull(message = "필수값(index) 이 누락되었습니다.")
    private Integer index;
    @NotBlank(message = "필수값(체크여부) 이 누락되었습니다.")
    private String isChecked;

    private String title;
}
