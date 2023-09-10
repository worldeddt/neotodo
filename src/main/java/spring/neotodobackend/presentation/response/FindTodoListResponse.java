package spring.neotodobackend.presentation.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import spring.neotodobackend.core.BaseResponse;
import spring.neotodobackend.domain.TodoDomain;

import java.util.List;


@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FindTodoListResponse extends BaseResponse {

    private List<TodoDomain> list;

    public static FindTodoListResponse init(List<TodoDomain> todoDomains) {
        return FindTodoListResponse.builder()
                .list(todoDomains)
                .build();
    }
}
