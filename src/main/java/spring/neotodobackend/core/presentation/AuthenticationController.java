package spring.neotodobackend.core.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.neotodobackend.config.ApiErrorCodeExample;
import spring.neotodobackend.core.application.AuthenticationService;
import spring.neotodobackend.core.error.enums.BadRequestCode;
import spring.neotodobackend.core.error.enums.NotFoundCode;
import spring.neotodobackend.presentation.request.RefreshToken;
import spring.neotodobackend.presentation.response.TokenInfo;


@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @ApiErrorCodeExample({BadRequestCode.class, NotFoundCode.class})
    @PostMapping("/token/refresh")
    public ResponseEntity<TokenInfo> refresh(@RequestBody RefreshToken refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken.getRefreshToken()));
    }
}
