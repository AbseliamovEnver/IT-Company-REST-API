package net.enver.itcompanydemo.security.twilio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import java.util.Random;

@Data
@ToString
public class SmsRequest {

    @NotBlank
    private final String phoneNumber;

    @NotBlank
    private final String message;

//    @Value("${twilio.message}")
//    private final String message;

    public SmsRequest(@JsonProperty("phoneNumber") String phoneNumber,
                      @JsonProperty("message") String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String createMessage(){
        return message + " " + new Random().ints(90_000) + 10_000;
    }
}
