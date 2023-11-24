package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.response.OtpVerificationResponse;

public interface OtpService {

    public String generateOtp (String email);

    OtpVerificationResponse verifyOtp(String otpToVerify);
}
