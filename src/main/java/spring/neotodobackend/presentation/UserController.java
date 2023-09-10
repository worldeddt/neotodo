package spring.neotodobackend.presentation;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.neotodobackend.application.user.SignUpUseCase.SignUpUseCase;
import spring.neotodobackend.application.user.SignUpUseCase.dto.SignUpUseCaseRequest;
import spring.neotodobackend.application.user.SignUpUseCase.vo.SignUpUseCaseResponseBody;
import spring.neotodobackend.config.ApiErrorCodeExample;
import spring.neotodobackend.core.error.enums.BadRequestCode;
import spring.neotodobackend.core.error.enums.NotFoundCode;
import spring.neotodobackend.interfaces.IResponse;
import spring.neotodobackend.presentation.request.UserSignUpRequest;
import spring.neotodobackend.presentation.response.CreateTodoResponse;
import spring.neotodobackend.presentation.response.SignUpUserResponse;

@RequestMapping(value = "/v1/user")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final SignUpUseCase signUpUseCase;


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
    public ResponseEntity<Void> login(@RequestBody @Valid UserSignUpRequest userSignUpRequest) {

        return null;
    }

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @RequestMapping(value="/signOut")
    public ResponseEntity<Void> signOut(@RequestBody @Valid UserSignUpRequest userSignUpRequest) {
        return null;
    }
}
