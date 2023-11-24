package africa.semicolon.promeescuous.services;


import africa.semicolon.promeescuous.dto.request.ForgotPasswordRequest;
import africa.semicolon.promeescuous.dto.response.ForgotPasswordResponse;
import africa.semicolon.promeescuous.dto.response.OtpVerificationResponse;
import africa.semicolon.promeescuous.dto.response.RegistrationResponse;

import africa.semicolon.promeescuous.dto.response.ResetPasswordResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testThatUserCanRegister(){
        RegistrationResponse response = userService.register("ned@gmail.com", "12345");
        assertEquals("Registration successful", response.getMessage());
    }
    @Test
    public void testForgotPasswordWithCorrectOtp(){
        RegistrationResponse regResponse = userService.register("ned@gmail.com", "12345");
        assertEquals("Registration successful", regResponse.getMessage());

        ForgotPasswordResponse forgotPasswordResponse = userService.forgetPassword(regResponse.getEmail());

        OtpVerificationResponse verificationResponse = userService.verifyOtp(forgotPasswordResponse.getOtpForTest());

        assertTrue(verificationResponse.isVerified());
        assertEquals(verificationResponse.getEmail(), regResponse.getEmail());

        ForgotPasswordRequest forgotPasswordRequest = buildForgotPasswordRequest("password", verificationResponse.getEmail());

        ResetPasswordResponse resetPasswordResponse = userService.resetPassword(forgotPasswordRequest);

        assertEquals("Password reset successful",resetPasswordResponse.getMessage());
        assertEquals(regResponse.getEmail(), resetPasswordResponse.getEmail());

    }

    private ForgotPasswordRequest buildForgotPasswordRequest(String password, String email) {
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setNewPassword(password);
        forgotPasswordRequest.setEmail(email);

        return forgotPasswordRequest;
    }


}



