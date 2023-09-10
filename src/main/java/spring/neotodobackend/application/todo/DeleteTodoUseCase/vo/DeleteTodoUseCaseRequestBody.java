package spring.neotodobackend.application.todo.DeleteTodoUseCase.vo;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class DeleteTodoUseCaseRequestBody {
    private Integer index;

    public static DeleteTodoUseCaseRequestBody init(Integer index) {
        return DeleteTodoUseCaseRequestBody.builder()
                .index(index)
                .build();
    }
}
