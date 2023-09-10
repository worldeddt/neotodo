package spring.neotodobackend.presentation;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.neotodobackend.application.todo.CreateTodoUseCase.CreateTodoUseCase;
import spring.neotodobackend.application.todo.CreateTodoUseCase.dto.CreateTodoUseCaseRequest;
import spring.neotodobackend.application.todo.CreateTodoUseCase.vo.CreateTodoUseCaseResponseBody;
import spring.neotodobackend.application.todo.DeleteTodoUseCase.DeleteTodoUseCase;
import spring.neotodobackend.application.todo.DeleteTodoUseCase.dto.DeleteTodoUseCaseRequest;
import spring.neotodobackend.application.todo.FindTodoUseCase.FindTodoUseCase;
import spring.neotodobackend.application.todo.UpdateTodoUseCase.UpdateTodoUseCase;
import spring.neotodobackend.application.todo.UpdateTodoUseCase.dto.UpdateTodoUseCaseRequest;
import spring.neotodobackend.config.ApiErrorCodeExample;
import spring.neotodobackend.core.BaseResponse;
import spring.neotodobackend.core.error.enums.BadRequestCode;
import spring.neotodobackend.core.error.enums.NotFoundCode;
import spring.neotodobackend.domain.TodoDomain;
import spring.neotodobackend.interfaces.IResponse;
import spring.neotodobackend.presentation.request.CreateTodoRequest;
import spring.neotodobackend.presentation.request.DeleteTodoRequest;
import spring.neotodobackend.presentation.request.UpdateTodoRequest;
import spring.neotodobackend.presentation.response.CreateTodoResponse;
import spring.neotodobackend.presentation.response.FindTodoListResponse;


@RequestMapping(value = "/v1/todo")
@RestController
@Slf4j
@RequiredArgsConstructor
public class TodoController {
    private final FindTodoUseCase findTodoUseCase;
    private final UpdateTodoUseCase updateTodoUseCase;
    private final CreateTodoUseCase createTodoUseCase;
    private final DeleteTodoUseCase deleteTodoUseCase;

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @GetMapping(value = "/")
    public ResponseEntity<FindTodoListResponse> get() {
        IResponse<TodoDomain> todoDomainIResponse = this.findTodoUseCase.execute();

        return ResponseEntity.ok(FindTodoListResponse.init(todoDomainIResponse.getResponses()));
    }

    @GetMapping(value = "/last")
    public ResponseEntity<FindTodoListResponse> getLastOne() {
        IResponse<TodoDomain> todoDomainIResponse = this.findTodoUseCase.execute();

        return ResponseEntity.ok(FindTodoListResponse.init(todoDomainIResponse.getResponses()));
    }

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> update(@RequestBody @Valid UpdateTodoRequest updateTodoRequest) {
        this.updateTodoUseCase.update(
                UpdateTodoUseCaseRequest.init(
                        updateTodoRequest.getIndex(),
                        updateTodoRequest.getTitle(),
                        updateTodoRequest.getIsChecked()));

        return ResponseEntity.ok(new BaseResponse());
    }

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @PostMapping(value = "/create")
    public ResponseEntity<CreateTodoResponse> create(@RequestBody @Valid CreateTodoRequest createTodoRequest) {
        IResponse<CreateTodoUseCaseResponseBody> createTodoUseCaseResponseBodyIResponse =
                this.createTodoUseCase.create(CreateTodoUseCaseRequest.init(createTodoRequest.getTitle()));

        return ResponseEntity.ok(CreateTodoResponse.init(createTodoUseCaseResponseBodyIResponse.getResponse().getIndex()));
    }

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @PostMapping(value = "/delete")
    public ResponseEntity<BaseResponse> delete(@RequestBody @Valid DeleteTodoRequest deleteTodoRequest) {

        this.deleteTodoUseCase.execute(DeleteTodoUseCaseRequest.init(deleteTodoRequest.getIndex()));

        return ResponseEntity.ok(new BaseResponse());
    }
}
