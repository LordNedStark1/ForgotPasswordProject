package africa.semicolon.promeescuous.services;


import africa.semicolon.promeescuous.config.AppConfig;
import africa.semicolon.promeescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promeescuous.dto.request.Recipient;
import africa.semicolon.promeescuous.dto.response.RegistrationResponse;
import africa.semicolon.promeescuous.exceptions.UserNotFoundException;
import africa.semicolon.promeescuous.models.User;
import africa.semicolon.promeescuous.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.promeescuous.utils.AppUtil.*;
import static africa.semicolon.promeescuous.utils.JwtUtil.extractEmailFrom;
import static africa.semicolon.promeescuous.utils.JwtUtil.isValidJwt;

@Service
@Slf4j
@AllArgsConstructor
public class ForgotPasswordUserService implements UserService {
    private final UserRepository userRepository;
    private final MailService mailService;
    private final AppConfig appConfig;
    private final OtpService otpService;

    @Override
    public RegistrationResponse register(String email, String password) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = userRepository.save(user);

        return RegistrationResponse.builder()
                .email(savedUser.getEmail())
                .message( "Registration successful")
                .build();
    }

    @Override
    public String forgetPassword(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User not found"));

        EmailNotificationRequest request = buildEmailRequest(user);
        mailService.send(request);

        return "Check your mail for a verification link.";
    }

    @Override
    public String activateNewPassword(String token) {
        boolean isTestToken = token.equals(appConfig.getTestToken());
        if (isTestToken) return  "testToken";
        boolean isValidJwt = isValidJwt(token);
        if (isValidJwt) return activateAccount(token);
        throw new UserNotFoundException(
                "ACCOUNT_ACTIVATION_FAILED_EXCEPTION");
    }

    @Override
    public String getUserById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        User user = foundUser.orElseThrow(
                ()->new UserNotFoundException("USER_NOT_FOUND_EXCEPTION")
        );
        User getUserResponse = buildUserResponse(user);
        return "activated";
    }

    public User findUserById(Long id){
        Optional<User> foundUser = userRepository.findById(id);
        User user = foundUser.orElseThrow(()->new UserNotFoundException("USER_NOT_FOUND_EXCEPTION"));
        return user;
    }


    private String activateAccount(String token) {
        String email = extractEmailFrom(token);
        Optional<User> user = userRepository.findByEmail(email);
        User foundUser = user.orElseThrow(()->new UserNotFoundException(
                String.format("USER_WITH_EMAIL_NOT_FOUND_EXCEPTION")
        ));

        User savedUser = userRepository.save(foundUser);
        User userResponse = buildUserResponse(savedUser);
        return "userResponse";
    }

    private static User buildUserResponse(User savedUser) {
        return User.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .build();
    }


    private EmailNotificationRequest buildEmailRequest(User savedUser){
        EmailNotificationRequest request =new EmailNotificationRequest();

        Recipient recipient = new Recipient(savedUser.getEmail());

        request.setRecipients(recipient);
        request.setSubject(WELCOME_MAIL_SUBJECT);
        String otp = otpService.generateOtp(savedUser.getEmail());

        String emailTemplate = getMailTemplate();
        String mailContent = String.format(emailTemplate, otp);
        request.setMailContent(mailContent);
        return request;
    }



}
