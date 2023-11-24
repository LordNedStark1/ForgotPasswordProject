package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.response.OtpVerificationResponse;
import africa.semicolon.promeescuous.exceptions.OtpNotFoundException;
import africa.semicolon.promeescuous.models.Otp;
import africa.semicolon.promeescuous.repositories.OtpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService{
    private final OtpRepository otpRepository;

    @Override
    public String generateOtp(String email) {
        Otp foundOtp = otpRepository.findByEmail(email);

        if (foundOtp != null){
            otpRepository.delete(foundOtp);
        }
        // this is where you will call your own otp generating method. Maybe UUID or you own built generator method.
        String generatedOtp = generateNewOtp();
        Otp otp = new Otp();

        otp.setOtp(generatedOtp);
        otp.setEmail(email);
        otp.setActive(true);

        otpRepository.save(otp);

        return generatedOtp;
    }

    private String generateNewOtp() {
        return "1234";
    }

    @Override
    public OtpVerificationResponse verifyOtp(String otpToVerify) {
     Otp foundOtp=otpRepository.findByOtp(otpToVerify)
                                .orElseThrow(()-> new OtpNotFoundException("otp not found"));

     foundOtp.setActive(false);

        return OtpVerificationResponse.builder()
                .isVerified(true)
                .email(foundOtp.getEmail())
                .build();
    }
}
