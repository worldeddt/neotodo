package spring.neotodobackend.application.todo.FindTodoUseCase.dto;

import spring.neotodobackend.interfaces.IRequest;

public class FindTodoUseCaseRequest implements IRequest<FindTodoUseCaseRequest> {

    @Override
    public FindTodoUseCaseRequest getConditions() {
        return null;
    }
}
