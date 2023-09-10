package spring.neotodobackend.application.todo.UpdateTodoUseCase;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.todo.UpdateTodoUseCase.dto.UpdateTodoUseCaseRequest;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.domain.entity.Todo;
import spring.neotodobackend.infra.TodoRepository;
import spring.neotodobackend.interfaces.IRequest;

import java.time.LocalDateTime;

import static spring.neotodobackend.core.error.enums.NotFoundCode.TODO_NOT_FOUND;

@Service
@AllArgsConstructor
public class UpdateTodoUseCase {
    private TodoRepository todoRepository;
    private EntityManager entityManager;

    @Transactional(rollbackOn=Exception.class)
    public void update(IRequest<UpdateTodoUseCaseRequest> iRequest) {
        Todo todo = this.todoRepository.findByIndex(iRequest.getConditions().getIndex())
                .orElseThrow(() -> CommonException.init(TODO_NOT_FOUND));

        todo.setTitle(iRequest.getConditions().getTitle());
        todo.setIsChecked(iRequest.getConditions().getIsChecked());
        todo.setUpdateDatetime(LocalDateTime.now());
//        entityManager.flush();
    }
}
