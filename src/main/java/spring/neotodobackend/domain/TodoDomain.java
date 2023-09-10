package spring.neotodobackend.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import spring.neotodobackend.domain.entity.Todo;



@Getter
@Builder(access = AccessLevel.PRIVATE)
public class TodoDomain {
    private Integer index;
    private String title;
    private String status;
    private String progressStatue;
    private String registerDatetime;
    private String updateDatetime;

    public static TodoDomain init(Todo todo) {
        return TodoDomain.builder()
                .index(todo.getIndex())
                .title(todo.getTitle())
                .status(todo.getStatus())
                .progressStatue(todo.getProgressStatus())
                .registerDatetime(todo.getRegisterDatetime() == null ? "0000-00-00 00:00:00" :todo.getRegisterDatetime().toString())
                .updateDatetime(todo.getUpdateDatetime() == null ? "0000-00-00 00:00:00" :todo.getUpdateDatetime().toString())
                .build();
    }
}
