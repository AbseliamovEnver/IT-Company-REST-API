package net.enver.itcompanydemo.rest;

import net.enver.itcompanydemo.model.User;
import net.enver.itcompanydemo.security.jwt.JwtUtil;
import net.enver.itcompanydemo.security.twilio.SmsRequest;
import net.enver.itcompanydemo.security.twilio.TwilioService;
import net.enver.itcompanydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserService userService;

    private final TwilioService twilioService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, TwilioService twilioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.twilioService = twilioService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationUtil authenticationUtil) {
        try {
            String username = authenticationUtil.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationUtil.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            String token = jwtUtil.createToken(user.getUsername());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Username or password is incorrect.");
        }
    }

    @PostMapping("twilio")
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        twilioService.sendSms(smsRequest);
    }
}
