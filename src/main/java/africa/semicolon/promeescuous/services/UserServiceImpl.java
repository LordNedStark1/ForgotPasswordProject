package africa.semicolon.promeescuous.services;


import africa.semicolon.promeescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promeescuous.dto.request.ForgotPasswordRequest;
import africa.semicolon.promeescuous.dto.request.Recipient;
import africa.semicolon.promeescuous.dto.response.ForgotPasswordResponse;
import africa.semicolon.promeescuous.dto.response.OtpVerificationResponse;
import africa.semicolon.promeescuous.dto.response.RegistrationResponse;
import africa.semicolon.promeescuous.dto.response.ResetPasswordResponse;
import africa.semicolon.promeescuous.exceptions.UserNotFoundException;
import africa.semicolon.promeescuous.models.User;
import africa.semicolon.promeescuous.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import static africa.semicolon.promeescuous.utils.AppUtil.*;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MailService mailService;

    private final OtpService otpService;

    @Override
    public RegistrationResponse register(String email, String password) {
        User foundUser = userRepository.getUserByEmail(email);

        if(foundUser == null) {
            foundUser = new User();
            foundUser.setEmail(email);
            foundUser.setPassword(password);


            User savedUser = userRepository.save(foundUser);

            return RegistrationResponse.builder()
                    .email(savedUser.getEmail())
                    .message( "Registration successful")
                    .build();
        }
        return RegistrationResponse.builder()
                .email(foundUser.getEmail())
                .message( "Registration successful")
                .build();
    }

    @Override
    public ForgotPasswordResponse forgetPassword(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User not found"));

        String otp = otpService.generateOtp(user.getEmail());
        EmailNotificationRequest request = buildEmailRequest(user, otp);
        mailService.send(request);

        return ForgotPasswordResponse.builder()
                .email(user.getEmail())
                .message("Check your mail for a verification link.")
                .otpForTest(otp)
                .build();

    }

    public User findUserById(String  id){
        Optional<User> foundUser = userRepository.findById(id);
      return foundUser.orElseThrow(()->new UserNotFoundException("USER_NOT_FOUND_EXCEPTION"));
    }
    @Override
    public OtpVerificationResponse verifyOtp(String otpToVerify) {
       return otpService.verifyOtp(otpToVerify);
    }

    @Override
    public ResetPasswordResponse resetPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = findUserByEmail(forgotPasswordRequest.getEmail());
        String password = forgotPasswordRequest.getNewPassword();
        user.setPassword( password);

        userRepository.save(user);

        ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
        resetPasswordResponse.setEmail(user.getEmail());
        resetPasswordResponse.setMessage("Password reset successful");

        return resetPasswordResponse;
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found"));
    }

    private EmailNotificationRequest buildEmailRequest(User savedUser, String otp){
        EmailNotificationRequest request =new EmailNotificationRequest();

        Recipient recipient = new Recipient(savedUser.getEmail());

        request.setRecipients(recipient);
        request.setSubject(WELCOME_MAIL_SUBJECT);

        String emailTemplate = "" ;//getMailTemplate();
        String mailContent = String.format(emailTemplate, otp);
        request.setMailContent(mailContent);
        return request;
    }
    @Override
    public String deleteAllUsers(){
        userRepository.deleteAll();
        return "Every user deleted";
    }
}
