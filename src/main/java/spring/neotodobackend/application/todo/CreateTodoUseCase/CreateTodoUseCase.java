package spring.neotodobackend.application.todo.CreateTodoUseCase;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.todo.CreateTodoUseCase.dto.CreateTodoUseCaseResponse;
import spring.neotodobackend.application.todo.CreateTodoUseCase.vo.CreateTodoUseCaseRequestBody;
import spring.neotodobackend.application.todo.CreateTodoUseCase.vo.CreateTodoUseCaseResponseBody;
import spring.neotodobackend.domain.entity.Todo;
import spring.neotodobackend.domain.enums.TodoProgressStatus;
import spring.neotodobackend.domain.enums.TodoStatus;
import spring.neotodobackend.interfaces.IRequest;
import spring.neotodobackend.interfaces.IResponse;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class CreateTodoUseCase {
    private EntityManager entityManager;

    @Transactional(rollbackOn = Exception.class)
    public IResponse<CreateTodoUseCaseResponseBody> create(IRequest<CreateTodoUseCaseRequestBody> iRequest) {
        log.info("iRequest.getConditions().getTitle() :"+iRequest.getConditions().getTitle());
        Todo todo = new Todo();
        todo.setStatus(TodoStatus.ACTIVE.name());
        todo.setRegisterDatetime(LocalDateTime.now());
        todo.setProgressStatus(TodoProgressStatus.TODO.name());
        todo.setTitle(iRequest.getConditions().getTitle());
        entityManager.persist(todo);

        return CreateTodoUseCaseResponse.init(todo.getIndex());
    }
}
