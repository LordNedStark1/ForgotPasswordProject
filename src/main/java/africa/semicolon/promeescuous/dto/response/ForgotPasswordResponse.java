package africa.semicolon.promeescuous.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForgotPasswordResponse {
    private String message;
    private String email;

    //otpForTest was added just to use in testing. Ugrade the test later and delete this attribute if need be.
    private String otpForTest;
}
