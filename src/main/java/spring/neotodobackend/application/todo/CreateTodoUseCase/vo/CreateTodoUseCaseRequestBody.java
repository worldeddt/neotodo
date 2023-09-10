package spring.neotodobackend.application.todo.CreateTodoUseCase.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CreateTodoUseCaseRequestBody {
    private String title;

    public static CreateTodoUseCaseRequestBody init(String title) {
        return CreateTodoUseCaseRequestBody.builder()
                .title(title)
                .build();
    }
}
