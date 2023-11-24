package africa.semicolon.promeescuous.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpVerificationResponse {
    private boolean isVerified;
    private String email;
}
