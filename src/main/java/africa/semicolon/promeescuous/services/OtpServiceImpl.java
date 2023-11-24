package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.response.OtpVerificationResponse;
import africa.semicolon.promeescuous.exceptions.InvalidOtp;
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
        //This is returned for test sake. Implement your own generator method.
        return "1234";
    }

    @Override
    public OtpVerificationResponse verifyOtp(String otpToVerify) {
     Otp foundOtp=otpRepository.findByOtp(otpToVerify)
                                .orElseThrow(()->
                                        new InvalidOtp("Otp might be invalid or expired. Please try again, or request another otp"));

     foundOtp.setActive(false);

        return OtpVerificationResponse.builder()
                .isVerified(true)
                .email(foundOtp.getEmail())
                .build();
    }
}
