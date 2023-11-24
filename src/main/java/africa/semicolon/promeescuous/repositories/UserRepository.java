package africa.semicolon.promeescuous.repositories;


import africa.semicolon.promeescuous.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
