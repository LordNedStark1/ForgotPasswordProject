package africa.semicolon.promeescuous.repositories;

import africa.semicolon.promeescuous.models.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpRepository extends MongoRepository<Otp, String> {
    Optional<Otp> findByOtp(String otpToVerify);

    Otp findByEmail(String email);
}
