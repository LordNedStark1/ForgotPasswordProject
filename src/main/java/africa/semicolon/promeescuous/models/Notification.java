package africa.semicolon.promeescuous.models;


import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Document
public class Notification {
    @Id
    private Long id;

    private String content;
    private Long sender;

    private User user;
}
