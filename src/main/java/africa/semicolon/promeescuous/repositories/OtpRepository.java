package africa.semicolon.promeescuous.repositories;

import africa.semicolon.promeescuous.models.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtpRepository extends MongoRepository<Otp, Long> {
}
