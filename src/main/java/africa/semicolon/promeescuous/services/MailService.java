package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.EmailNotificationRequest;

public interface MailService {
    String  send(EmailNotificationRequest emailNotificationRequest);
}
