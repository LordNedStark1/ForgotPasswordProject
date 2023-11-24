package africa.semicolon.promeescuous.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponse {
    String message;
    String email;
}
