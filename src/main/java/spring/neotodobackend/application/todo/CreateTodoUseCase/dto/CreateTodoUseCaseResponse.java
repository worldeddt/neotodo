package spring.neotodobackend.application.todo.CreateTodoUseCase.dto;


import lombok.AccessLevel;
import lombok.Builder;
import spring.neotodobackend.application.todo.CreateTodoUseCase.vo.CreateTodoUseCaseResponseBody;
import spring.neotodobackend.interfaces.IResponse;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public class CreateTodoUseCaseResponse implements IResponse<CreateTodoUseCaseResponseBody> {
    private Integer index;

    public static CreateTodoUseCaseResponse init(Integer index) {
        return CreateTodoUseCaseResponse.builder()
                .index(index)
                .build();
    }

    @Override
    public List<CreateTodoUseCaseResponseBody> getResponses() {
        return null;
    }

    @Override
    public CreateTodoUseCaseResponseBody getResponse() {
        return CreateTodoUseCaseResponseBody.init(
                this.index
        );
    }
}
