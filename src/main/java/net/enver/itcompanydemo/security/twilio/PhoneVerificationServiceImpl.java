package net.enver.itcompanydemo.security.twilio;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class PhoneVerificationServiceImpl implements PhoneVerificationService {

    private static final String TWILIO_ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String TWILIO_AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    private static final String TWILIO_SERVICE_SID = System.getenv("TWILIO_SERVICE_SID");

//    @Value("${twilio.PHONE_NUMBER}")
//    private String trialPhoneNumber;

    @Value("${twilio.message}")
    private String messageTwilio;

    //    @PostConstruct
    public void init() {
        Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
    }

    @Override
    public void sendSmsCode(String phoneNumber) {
        Verification verification = Verification
                .creator(TWILIO_SERVICE_SID, phoneNumber, messageTwilio).create();
        log.info("Send sms to number {}", phoneNumber);
        log.info("Verification information: {}", verification);
    }

    @Override
    public boolean verifySmsCode(String phoneNumber, String smsCode) {
        VerificationCheck verificationCheck = VerificationCheck
                .creator(TWILIO_SERVICE_SID, smsCode).setTo(phoneNumber).create();
        log.info("Verification check information: {}", verificationCheck);
        return verificationCheck.getValid();
    }

//    private boolean isPhoneNumberValid(String phoneNumber) {
////        Pattern pattern = Pattern.compile("\\+\\d{2}-\\d{3}-\\d{7}");
//        Pattern pattern = Pattern.compile("\\+\\d{12}");
//        Matcher matcher = pattern.matcher(phoneNumber);
//
//        if (matcher.matches()) {
//            log.info("Phone number is valid");
//            return true;
//        } else {
//            log.info("Phone Number must be in the form +XX-XXX-XXXXXXX");
//            return false;
//        }
//    }
}
