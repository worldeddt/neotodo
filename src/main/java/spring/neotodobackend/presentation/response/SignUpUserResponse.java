package spring.neotodobackend.presentation.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.neotodobackend.core.BaseResponse;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SignUpUserResponse extends BaseResponse {
    private Integer index;

    public static SignUpUserResponse init(Integer index) {
        return SignUpUserResponse.builder()
                .index(index)
                .build();
    }
}
