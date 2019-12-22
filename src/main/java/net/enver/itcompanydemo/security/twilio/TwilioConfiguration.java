package net.enver.itcompanydemo.security.twilio;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@NoArgsConstructor
public class TwilioConfiguration {

    @Value("${twilio.ACCOUNT_SID}")
    private String accountSid;

    @Value("${twilio.AUTH_TOKEN}")
    private String authToken;

    @Value("${twilio.PHONE_NUMBER}")
    private String phoneNumber;
}
