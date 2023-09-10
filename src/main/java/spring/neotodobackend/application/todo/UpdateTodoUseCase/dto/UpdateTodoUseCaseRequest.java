package spring.neotodobackend.application.todo.UpdateTodoUseCase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import spring.neotodobackend.interfaces.IRequest;



@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateTodoUseCaseRequest implements IRequest<UpdateTodoUseCaseRequest> {
    private Integer index;
    private String title;
    private String isChecked;

    public static UpdateTodoUseCaseRequest init(Integer index, String title, String isChecked) {
        return UpdateTodoUseCaseRequest.builder()
                .index(index)
                .title(title)
                .isChecked(isChecked)
                .build();
    }

    @Override
    public UpdateTodoUseCaseRequest getConditions() {
        return this;
    }
}
