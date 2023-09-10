package spring.neotodobackend.application.todo.CreateTodoUseCase.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CreateTodoUseCaseResponseBody {
    private Integer index;

    public static CreateTodoUseCaseResponseBody init(Integer index) {
        return CreateTodoUseCaseResponseBody.builder()
                .index(index)
                .build();
    }
}
