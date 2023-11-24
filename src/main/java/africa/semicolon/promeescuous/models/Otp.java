package africa.semicolon.promeescuous.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@Document
public class Otp {
    @Id
    private String id;

    private String email;
    private String otp;
    private boolean isActive;

}
