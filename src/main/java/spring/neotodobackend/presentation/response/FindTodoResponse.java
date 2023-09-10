package spring.neotodobackend.presentation.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import spring.neotodobackend.core.BaseResponse;
import spring.neotodobackend.domain.TodoDomain;

import java.util.List;


@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FindTodoResponse extends BaseResponse {

    private TodoDomain data;

    public static FindTodoResponse init(TodoDomain todoDomain) {
        return FindTodoResponse.builder()
                .data(todoDomain)
                .build();
    }
}
