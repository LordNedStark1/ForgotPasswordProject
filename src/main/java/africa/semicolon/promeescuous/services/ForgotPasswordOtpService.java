package africa.semicolon.promeescuous.services;

import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordOtpService implements OtpService{


    @Override
    public String generateOtp(String email) {
        return "1234";
    }
}
