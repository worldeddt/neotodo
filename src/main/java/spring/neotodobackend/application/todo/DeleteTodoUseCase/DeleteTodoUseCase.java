package spring.neotodobackend.application.todo.DeleteTodoUseCase;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.todo.DeleteTodoUseCase.vo.DeleteTodoUseCaseRequestBody;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.domain.entity.Todo;
import spring.neotodobackend.domain.enums.TodoStatus;
import spring.neotodobackend.infra.TodoRepository;
import spring.neotodobackend.interfaces.IRequest;

import java.time.LocalDateTime;

import static spring.neotodobackend.core.error.enums.NotFoundCode.TODO_NOT_FOUND;

@Service
@AllArgsConstructor
public class DeleteTodoUseCase {
    private TodoRepository todoRepository;


    @Transactional(rollbackOn = Exception.class)
    public void execute(IRequest<DeleteTodoUseCaseRequestBody> iRequest) {
        Todo todo = todoRepository.findByIndex(iRequest.getConditions().getIndex())
                .orElseThrow(() -> CommonException.init(TODO_NOT_FOUND));

        todo.setStatus(TodoStatus.DELETED.name());
        todo.setUpdateDatetime(LocalDateTime.now());
    }
}
