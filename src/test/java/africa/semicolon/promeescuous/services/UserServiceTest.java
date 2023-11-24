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
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void deleteAllUsers(){
        //once you run this test it will delete all users in the database
        log.info(userService.deleteAllUsers());
    }
    @Test
    public void testThatUserCanRegister(){
        RegistrationResponse response = userService.register("ned@gmail.com", "12345");
        assertEquals("Registration successful", response.getMessage());
    }

    @Test
    public void testForgotPasswordWithCorrectOtpWorks(){
        RegistrationResponse regResponse = userService.register("ned@gmail.com", "12345");
        assertEquals("Registration successful", regResponse.getMessage());

        // an otp is sent to the users email.
        // For this test purpose the otp is also sent through ForgotPasswordResponse for automated testing
        ForgotPasswordResponse forgotPasswordResponse = userService.forgetPassword(regResponse.getEmail());

        //it's expected that user verifies the otp first
        OtpVerificationResponse verificationResponse = userService.verifyOtp(forgotPasswordResponse.getOtpForTest());

        assertTrue(verificationResponse.isVerified());
        assertEquals(regResponse.getEmail(), verificationResponse.getEmail());

        ForgotPasswordRequest forgotPasswordRequest = buildForgotPasswordRequest("changedPassword1", verificationResponse.getEmail());

        //after otp confirmation, the user proceeds to reset their password
        ResetPasswordResponse resetPasswordResponse = userService.resetPassword(forgotPasswordRequest);

        assertEquals("Password reset successful",resetPasswordResponse.getMessage());
        assertEquals(regResponse.getEmail(), resetPasswordResponse.getEmail());

    }
    @Test
    public void testForgotPasswordWithCorrectOtpDoesNotWork(){
        RegistrationResponse regResponse = userService.register("ned@gmail.com", "12345");
        assertEquals("Registration successful", regResponse.getMessage());

        ForgotPasswordResponse forgotPasswordResponse = userService.forgetPassword(regResponse.getEmail());

    }


    private ForgotPasswordRequest buildForgotPasswordRequest(String password, String email) {
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setNewPassword(password);
        forgotPasswordRequest.setEmail(email);

        return forgotPasswordRequest;
    }


}



