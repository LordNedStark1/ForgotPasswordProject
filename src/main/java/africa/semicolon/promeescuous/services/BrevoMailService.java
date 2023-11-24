package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.EmailNotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BrevoMailService implements MailService{
    //TODO: Remove hardcoded values
    @Override
    public String send(EmailNotificationRequest emailNotificationRequest) {
//        String brevoMailAddress = "https://api.brevo.com/v3/smtp/email";
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("api-key", appConfig.getMailApiKey());
//        headers.set("Content-Type", "application/json");
//        HttpEntity<EmailNotificationRequest> request =
//                new HttpEntity<>(emailNotificationRequest, headers);
//
//        ResponseEntity<EmailNotificationResponse> response =
//                restTemplate.postForEntity(brevoMailAddress, request, EmailNotificationResponse.class);
//        EmailNotificationResponse emailNotificationResponse = response.getBody();
        return "emailNotificationResponse";
    }
}
