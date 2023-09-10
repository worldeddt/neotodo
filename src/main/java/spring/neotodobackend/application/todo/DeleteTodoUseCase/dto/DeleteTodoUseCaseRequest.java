package spring.neotodobackend.application.todo.DeleteTodoUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import spring.neotodobackend.application.todo.DeleteTodoUseCase.vo.DeleteTodoUseCaseRequestBody;
import spring.neotodobackend.interfaces.IRequest;


@Builder(access = AccessLevel.PRIVATE)
public class DeleteTodoUseCaseRequest implements IRequest<DeleteTodoUseCaseRequestBody> {
    private Integer index;

    public static DeleteTodoUseCaseRequest init(Integer index) {
        return DeleteTodoUseCaseRequest.builder().index(index).build();
    }

    @Override
    public DeleteTodoUseCaseRequestBody getConditions() {
        return DeleteTodoUseCaseRequestBody.init(
                this.index
        );
    }
}
