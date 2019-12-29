package net.enver.itcompanydemo.security.twilio;

public interface PhoneVerificationService {

    void sendSmsCode(String phoneNumber);

    boolean verifySmsCode(String phoneNumber, String smsCode);
}
