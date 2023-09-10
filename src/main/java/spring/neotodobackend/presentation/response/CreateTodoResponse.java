package spring.neotodobackend.presentation.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.neotodobackend.core.BaseResponse;
import spring.neotodobackend.domain.TodoDomain;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CreateTodoResponse extends BaseResponse {
    private Integer index;

    public static CreateTodoResponse init(Integer index) {
        return CreateTodoResponse.builder()
                .index(index)
                .build();
    }
}
