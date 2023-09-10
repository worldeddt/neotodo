package spring.neotodobackend.core.error.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import spring.neotodobackend.core.BaseCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum BadRequestCode implements BaseCode {
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    VALUE_IS_NULL("값이 비어있습니다.", BAD_REQUEST),
    CANNOT_REMOVE("항목을 삭제할 수 없습니다.", BAD_REQUEST),
    CANNOT_UPDATE("항목 수정에 실패했습니다.", BAD_REQUEST),
    DUPLICATE_NICKNAME("닉네임이 중복되었습니다.", BAD_REQUEST),
    DUPLICATE_ID("아이디가 중복되었습니다.", BAD_REQUEST),
    ID_FORMAT("id 형식이 맞지 않습니다.", BAD_REQUEST),
    EXPIRED_JWT_TOKEN("토큰이 만료되었습니다.", HttpStatus.BAD_REQUEST),
    MALFORMAT_JWT_TOKEN("형식에 맞지 않는 토큰입니다.", HttpStatus.BAD_REQUEST),
    UNSUPPORT_JWT_TOKEN("지원하지 않는 토큰입이다.", HttpStatus.BAD_REQUEST),
    CANNOT_FIND_TOKEN("토큰 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    ILLEGALARGUMENT_TOKEN("형식에 맞지 않는 토큰(claim 정보 이슈)", HttpStatus.BAD_REQUEST),
    CANNOT_FIND_AUTH_INFO("인증정보를 찾을 수 없습니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("인증되지 않은 사용자입니다.", HttpStatus.UNAUTHORIZED),
    NOT_FOUND_USER("인가된 사용자를 찾을 수 없습니다.", HttpStatus.UNAUTHORIZED);
    private final String message;
    private final HttpStatus httpStatus;
}