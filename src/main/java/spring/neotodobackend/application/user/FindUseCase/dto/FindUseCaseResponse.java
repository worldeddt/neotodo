package spring.neotodobackend.application.user.FindUseCase.dto;


import lombok.AccessLevel;
import lombok.Builder;
import spring.neotodobackend.application.user.FindUseCase.vo.FindUseCaseResponseBody;
import spring.neotodobackend.interfaces.IResponse;
import spring.neotodobackend.presentation.response.TokenInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public class FindUseCaseResponse implements IResponse<FindUseCaseResponseBody> {
    private String id;
    private String nickname;
    private TokenInfo tokenInfo;

    public static FindUseCaseResponse init(String id, String nickname, TokenInfo tokenInfo) {
        return FindUseCaseResponse.builder()
                .id(id)
                .nickname(nickname)
                .tokenInfo(tokenInfo)
                .build();
    }

    @Override
    public List<FindUseCaseResponseBody> getResponses() {
        return null;
    }

    @Override
    public FindUseCaseResponseBody getResponse() {
        return FindUseCaseResponseBody.init(
                this.id,
                this.nickname,
                this.tokenInfo
        );
    }
}
