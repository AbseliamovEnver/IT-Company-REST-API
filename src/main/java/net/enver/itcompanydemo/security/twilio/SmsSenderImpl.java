package net.enver.itcompanydemo.security.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("twilio")
@Slf4j
public class SmsSenderImpl implements SmsSender {

    @Value("${twilio.PHONE_NUMBER}")
    private String trialPhoneNumber;

    @PostConstruct
    public void init() {
        Twilio.init(
                System.getenv("TWILIO_ACCOUNT_SID"),
                System.getenv("TWILIO_AUTH_TOKEN"));
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        String phoneNumber = smsRequest.getPhoneNumber();

        if (isPhoneNumberValid(phoneNumber)) {
            MessageCreator creator = Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(trialPhoneNumber),
                    smsRequest.getMessage());
            creator.create();
            log.info("Send sms {}", smsRequest);
        } else {
            throw new IllegalArgumentException("Phone number: " + phoneNumber + " is not a valid.");
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
//        Pattern pattern = Pattern.compile("\\+\\d{2}-\\d{3}-\\d{7}");
        Pattern pattern = Pattern.compile("\\+\\d{12}");
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
