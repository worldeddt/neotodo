package spring.neotodobackend.core.error.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import spring.neotodobackend.core.BaseCode;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum NotFoundCode implements BaseCode {

    TODO_NOT_FOUND("리스트 정보를 불러올 수 없습니다.", NOT_FOUND);
    private final String message;
    private final HttpStatus httpStatus;
}
