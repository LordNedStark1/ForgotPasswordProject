package africa.semicolon.promeescuous.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class User {
    @Id
    private Long id;


    private String firstName;

    private String lastName;

    private String email;

    private String password;


}
