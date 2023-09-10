package spring.neotodobackend.application.todo.UpdateTodoUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import spring.neotodobackend.application.todo.UpdateTodoUseCase.vo.UpdateTodoUseCaseRequestBody;
import spring.neotodobackend.interfaces.IRequest;



@Builder(access = AccessLevel.PRIVATE)
public class UpdateTodoUseCaseRequest implements IRequest<UpdateTodoUseCaseRequestBody> {
    private Integer index;
    private String title;
    private String progressStatus;

    public static UpdateTodoUseCaseRequest init(Integer index, String title, String progressStatus) {
        return UpdateTodoUseCaseRequest.builder()
                .index(index)
                .title(title)
                .progressStatus(progressStatus)
                .build();
    }

    @Override
    public UpdateTodoUseCaseRequestBody getConditions() {
        return UpdateTodoUseCaseRequestBody.init(
                this.index,
                this.title,
                this.progressStatus
        );
    }
}
