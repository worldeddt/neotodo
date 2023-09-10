package spring.neotodobackend.application.todo.CreateTodoUseCase;


import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.todo.CreateTodoUseCase.dto.CreateTodoUseCaseResponse;
import spring.neotodobackend.application.todo.CreateTodoUseCase.vo.CreateTodoUseCaseRequestBody;
import spring.neotodobackend.application.todo.CreateTodoUseCase.vo.CreateTodoUseCaseResponseBody;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.core.utils.TokenExtractor;
import spring.neotodobackend.domain.entity.Todo;
import spring.neotodobackend.domain.entity.User;
import spring.neotodobackend.domain.enums.TodoProgressStatus;
import spring.neotodobackend.domain.enums.TodoStatus;
import spring.neotodobackend.domain.enums.UserStatus;
import spring.neotodobackend.infra.UserRepository;
import spring.neotodobackend.interfaces.IRequest;
import spring.neotodobackend.interfaces.IResponse;
import spring.neotodobackend.security.provider.JwtProvider;

import java.time.LocalDateTime;

import static spring.neotodobackend.core.error.enums.NotFoundCode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class CreateTodoUseCase {
    private EntityManager entityManager;
    private JwtProvider jwtProvider;
    private UserRepository userRepository;

    @Transactional(rollbackOn = Exception.class)
    public IResponse<CreateTodoUseCaseResponseBody> create(
            IRequest<CreateTodoUseCaseRequestBody> iRequest, HttpServletRequest request) {
        String resolveToken = TokenExtractor.resolveToken(request);

        String userId = jwtProvider.getUserId(resolveToken);

        User user = userRepository.findByIdAndStatus(userId, UserStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(USER_NOT_FOUND));

        log.info("iRequest.getConditions().getTitle() :"+iRequest.getConditions().getTitle());
        Todo todo = new Todo();
        todo.setStatus(TodoStatus.ACTIVE.name());
        todo.setUser(user);
        todo.setRegisterDatetime(LocalDateTime.now());
        todo.setProgressStatus(TodoProgressStatus.TODO.name());
        todo.setTitle(iRequest.getConditions().getTitle());
        entityManager.persist(todo);

        return CreateTodoUseCaseResponse.init(todo.getIndex());
    }
}
