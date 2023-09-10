package spring.neotodobackend.application.todo.FindTodoUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import spring.neotodobackend.domain.TodoDomain;
import spring.neotodobackend.interfaces.IResponse;

import java.util.List;


@Builder(access = AccessLevel.PRIVATE)
public class FindTodoUseCaseResponse implements IResponse<TodoDomain> {

    private List<TodoDomain> inventory;

    public static FindTodoUseCaseResponse init(List<TodoDomain> todoDomains) {
        return FindTodoUseCaseResponse.builder()
                .inventory(todoDomains)
                .build();
    }
    @Override
    public List<TodoDomain> getResponses() {
        return inventory;
    }

    @Override
    public TodoDomain getResponse() {
        return null;
    }
}

