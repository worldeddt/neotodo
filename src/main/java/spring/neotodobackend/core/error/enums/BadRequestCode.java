package spring.neotodobackend.core.error.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import spring.neotodobackend.core.BaseCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum BadRequestCode implements BaseCode {
    VALUE_IS_NULL("값이 비어있습니다.", BAD_REQUEST),
    CANNOT_REMOVE("항목을 삭제할 수 없습니다.", BAD_REQUEST),
    CANNOT_UPDATE("항목 수정에 실패했습니다.", BAD_REQUEST),
    DUPLICATE_NICKNAME("닉네임이 중복되었습니다.", BAD_REQUEST),
    DUPLICATE_ID("아이디가 중복되었습니다.", BAD_REQUEST),
    ID_FORMAT("id 형식이 맞지 않습니다.", BAD_REQUEST);
    private final String message;
    private final HttpStatus httpStatus;
}