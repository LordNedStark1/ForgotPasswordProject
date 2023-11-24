package africa.semicolon.promeescuous.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {
    private String email;
    private String password;
}
