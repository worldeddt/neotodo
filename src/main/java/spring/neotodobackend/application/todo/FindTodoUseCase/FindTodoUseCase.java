package spring.neotodobackend.application.todo.FindTodoUseCase;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.neotodobackend.application.todo.FindTodoUseCase.dto.FindTodoLastOneUseCaseResponse;
import spring.neotodobackend.application.todo.FindTodoUseCase.dto.FindTodoUseCaseResponse;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.core.utils.TokenExtractor;
import spring.neotodobackend.domain.TodoDomain;
import spring.neotodobackend.domain.entity.Todo;
import spring.neotodobackend.domain.enums.TodoStatus;
import spring.neotodobackend.domain.enums.UserStatus;
import spring.neotodobackend.infra.TodoRepository;
import spring.neotodobackend.infra.UserRepository;
import spring.neotodobackend.interfaces.IResponse;
import spring.neotodobackend.security.provider.JwtProvider;

import java.util.ArrayList;
import java.util.List;

import static spring.neotodobackend.core.error.enums.NotFoundCode.TODO_NOT_FOUND;


@Service
@AllArgsConstructor
@Slf4j
public class FindTodoUseCase {
    private TodoRepository todoRepository;
    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public IResponse<TodoDomain> execute(HttpServletRequest request) {

        String resolveToken = TokenExtractor.resolveToken(request);

        String userId = jwtProvider.getUserId(resolveToken);

        List<Todo> todos = this.todoRepository.findAllByUserAndStatus(
                userRepository.findByIdAndStatus(userId, UserStatus.ACTIVE.name())
                        .orElseThrow(() -> CommonException.init(TODO_NOT_FOUND)),
                TodoStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(TODO_NOT_FOUND));

        List<TodoDomain> todoDomains = new ArrayList<>();
        todos.stream().map(TodoDomain::init).forEach(todoDomains::add);

        return FindTodoUseCaseResponse.init(todoDomains);
    }

    public IResponse<TodoDomain> executeLastOne(HttpServletRequest request) {

        String resolveToken = TokenExtractor.resolveToken(request);

        String userId = jwtProvider.getUserId(resolveToken);

        List<Todo> todos = this.todoRepository.findAllByUserAndStatusOrderByIndexDesc(
                        userRepository.findByIdAndStatus(userId, UserStatus.ACTIVE.name())
                                .orElseThrow(() -> CommonException.init(TODO_NOT_FOUND)),
                        TodoStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(TODO_NOT_FOUND));

        return FindTodoLastOneUseCaseResponse.init(TodoDomain.init(todos.get(0)));
    }
}
