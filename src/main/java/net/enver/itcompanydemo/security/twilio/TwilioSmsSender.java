package net.enver.itcompanydemo.security.twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("twilio")
@Slf4j
public class TwilioSmsSender implements SmsSender {

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        String phoneNumber = smsRequest.getPhoneNumber();

        if (isPhoneNumberValid(phoneNumber)) {
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getPhoneNumber());
            PhoneNumber to = new PhoneNumber(phoneNumber);
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            log.info("Send sms {}", smsRequest);
        } else {
            throw new IllegalArgumentException("Phone number: " + phoneNumber + " is not a valid.");
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        Pattern pattern = Pattern.compile("\\+\\d{2}-\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            log.info("Phone number is valid");
            return true;
        } else {
            log.info("Phone Number must be in the form +XX-XXX-XXXXXXX");
            return false;
        }
    }
}
