package spring.neotodobackend.application.todo.FindTodoUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import spring.neotodobackend.domain.TodoDomain;
import spring.neotodobackend.interfaces.IResponse;

import java.util.List;


@Builder(access = AccessLevel.PRIVATE)
public class FindTodoLastOneUseCaseResponse implements IResponse<TodoDomain> {

    private TodoDomain todoDomain;

    public static FindTodoLastOneUseCaseResponse init(TodoDomain todoDomain) {
        return FindTodoLastOneUseCaseResponse.builder()
                .todoDomain(todoDomain)
                .build();
    }

    @Override
    public TodoDomain getResponse() {
        return todoDomain;
    }

    @Override
    public List<TodoDomain> getResponses() {
        return null;
    }
}
