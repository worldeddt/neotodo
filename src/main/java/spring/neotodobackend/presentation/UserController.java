package spring.neotodobackend.presentation;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.neotodobackend.application.todo.FindTodoUseCase.FindTodoUseCase;
import spring.neotodobackend.application.user.FindUseCase.FindUseCase;
import spring.neotodobackend.application.user.FindUseCase.dto.FindUseCaseRequest;
import spring.neotodobackend.application.user.FindUseCase.vo.FindUseCaseResponseBody;
import spring.neotodobackend.application.user.SignOutUseCase.SignOutUseCase;
import spring.neotodobackend.application.user.SignOutUseCase.dto.SignOutUseCaseRequest;
import spring.neotodobackend.application.user.SignUpUseCase.SignUpUseCase;
import spring.neotodobackend.application.user.SignUpUseCase.dto.SignUpUseCaseRequest;
import spring.neotodobackend.application.user.SignUpUseCase.vo.SignUpUseCaseResponseBody;
import spring.neotodobackend.config.ApiErrorCodeExample;
import spring.neotodobackend.core.BaseResponse;
import spring.neotodobackend.core.error.enums.BadRequestCode;
import spring.neotodobackend.core.error.enums.NotFoundCode;
import spring.neotodobackend.interfaces.IResponse;
import spring.neotodobackend.presentation.request.UserLoginRequest;
import spring.neotodobackend.presentation.request.UserSignOutRequest;
import spring.neotodobackend.presentation.request.UserSignUpRequest;
import spring.neotodobackend.presentation.response.FindUserResponse;
import spring.neotodobackend.presentation.response.SignUpUserResponse;

@RequestMapping(value = "/v1/user")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final SignUpUseCase signUpUseCase;
    private final SignOutUseCase signOutUseCase;
    private final FindUseCase findUseCase;

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @RequestMapping(value="/signUp")
    public ResponseEntity<SignUpUserResponse> signUp(@RequestBody @Valid UserSignUpRequest userSignUpRequest) {

        IResponse<SignUpUseCaseResponseBody> signUpUseCaseResponseBodyIResponse = this.signUpUseCase.signUp(
                SignUpUseCaseRequest.init(
                        userSignUpRequest.getId(),
                        userSignUpRequest.getPassword(),
                        userSignUpRequest.getNickname()
                )
        );

        return ResponseEntity.ok(SignUpUserResponse.init(signUpUseCaseResponseBodyIResponse.getResponse().getIndex()));
    }

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @RequestMapping(value="/login")
    public ResponseEntity<FindUserResponse> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {

        IResponse<FindUseCaseResponseBody> login = this.findUseCase.login(
                FindUseCaseRequest.init(
                        userLoginRequest.getId(), userLoginRequest.getPassword()
                )
        );

        return ResponseEntity.ok(FindUserResponse.init(
                login.getResponse().getId(),
                login.getResponse().getNickname(),
                login.getResponse().getTokenInfo()));
    }

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @RequestMapping(value="/signOut")
    public ResponseEntity<BaseResponse> signOut(@RequestBody @Valid UserSignOutRequest userSignOutRequest) {
        signOutUseCase.signOut(SignOutUseCaseRequest.init(userSignOutRequest.getId()));

        return ResponseEntity.ok(new BaseResponse());
    }
}
