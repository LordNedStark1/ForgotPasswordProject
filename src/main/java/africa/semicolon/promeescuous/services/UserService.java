package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.response.RegistrationResponse;
import africa.semicolon.promeescuous.models.User;

public interface UserService {

    RegistrationResponse register(String email, String password);

    String forgetPassword(String email);

    String activateNewPassword(String token);

    User findUserById(Long id);

    String getUserById(Long id) ;

}
