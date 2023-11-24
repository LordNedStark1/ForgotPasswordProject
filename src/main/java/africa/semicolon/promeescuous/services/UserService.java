package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.ForgotPasswordRequest;
import africa.semicolon.promeescuous.dto.response.ForgotPasswordResponse;
import africa.semicolon.promeescuous.dto.response.OtpVerificationResponse;
import africa.semicolon.promeescuous.dto.response.RegistrationResponse;
import africa.semicolon.promeescuous.dto.response.ResetPasswordResponse;
import africa.semicolon.promeescuous.models.User;

public interface UserService {

    RegistrationResponse register(String email, String password);

    ForgotPasswordResponse forgetPassword(String email);

    User findUserById(Long id);


    OtpVerificationResponse verifyOtp(String otpToVerify);

    ResetPasswordResponse resetPassword(ForgotPasswordRequest forgotPasswordRequest);
}
