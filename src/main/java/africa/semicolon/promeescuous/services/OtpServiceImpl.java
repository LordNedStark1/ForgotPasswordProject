package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.response.OtpVerificationResponse;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService{

    @Override
    public String generateOtp(String email) {
        return "1234";
    }

    @Override
    public OtpVerificationResponse verifyOtp(String otpToVerify) {
        return OtpVerificationResponse.builder()
                .isVerified(true)
                .email("")
                .build();
    }
}
