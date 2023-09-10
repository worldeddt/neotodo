package spring.neotodobackend.application.todo.FindTodoUseCase;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.todo.FindTodoUseCase.dto.FindTodoUseCaseResponse;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.domain.TodoDomain;
import spring.neotodobackend.domain.entity.Todo;
import spring.neotodobackend.domain.enums.TodoStatus;
import spring.neotodobackend.infra.TodoRepository;
import spring.neotodobackend.interfaces.IResponse;

import java.util.ArrayList;
import java.util.List;

import static spring.neotodobackend.core.error.enums.NotFoundCode.TODO_NOT_FOUND;


@Service
@AllArgsConstructor
public class FindTodoUseCase {
    private TodoRepository todoRepository;

    public IResponse<TodoDomain> execute() {
        List<Todo> todos = this.todoRepository.findAllByStatus(TodoStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(TODO_NOT_FOUND));

//        if (todos != null) throw CommonException.init(TODO_NOT_FOUND);

        List<TodoDomain> todoDomains = new ArrayList<>();
        todos.stream().map(TodoDomain::init).forEach(todoDomains::add);

        return FindTodoUseCaseResponse.init(todoDomains);
    }
}
