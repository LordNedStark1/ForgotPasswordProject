package africa.semicolon.promeescuous.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class EmailNotificationRequest {
    private final Sender sender = new Sender("APP_NAME", "APP_EMAIL");
    //to
    @JsonProperty("to")
    private Recipient recipients;
    //cc
    @JsonProperty("cc")
    private List<String> copiedEmails;

    //htmlContent
    @JsonProperty("htmlContent")
    private String mailContent;

    private String textContent;

    private String subject;
}
