package africa.semicolon.promeescuous.dto.request;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String newPassword;
    private String email;
}
