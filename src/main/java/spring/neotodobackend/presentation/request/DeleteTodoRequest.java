package spring.neotodobackend.presentation.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeleteTodoRequest {
    @NotNull(message = "필수값(index) 이 누락되었습니다.")
    private Integer index;
}
