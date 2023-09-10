package spring.neotodobackend.application.todo.CreateTodoUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import spring.neotodobackend.application.todo.CreateTodoUseCase.vo.CreateTodoUseCaseRequestBody;
import spring.neotodobackend.interfaces.IRequest;


@Builder(access = AccessLevel.PRIVATE)
public class CreateTodoUseCaseRequest implements IRequest<CreateTodoUseCaseRequestBody> {
    private String title;

    public static CreateTodoUseCaseRequest init(String title) {
        return CreateTodoUseCaseRequest.builder()
                .title(title)
                .build();
    }
    @Override
    public CreateTodoUseCaseRequestBody getConditions() {
        return CreateTodoUseCaseRequestBody.init(
                this.title
        );
    }
}
