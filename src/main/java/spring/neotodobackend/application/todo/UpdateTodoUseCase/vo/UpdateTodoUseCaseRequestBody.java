package spring.neotodobackend.application.todo.UpdateTodoUseCase.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateTodoUseCaseRequestBody {
    private Integer index;
    private String title;
    private String progressStatus;

    public static UpdateTodoUseCaseRequestBody init(Integer index, String title, String progressStatus) {
        return UpdateTodoUseCaseRequestBody.builder()
                .index(index)
                .title(title)
                .progressStatus(progressStatus)
                .build();
    }
}
