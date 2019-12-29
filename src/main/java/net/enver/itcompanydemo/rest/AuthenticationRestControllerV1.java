package net.enver.itcompanydemo.rest;

import net.enver.itcompanydemo.dto.UserVerifyDto;
import net.enver.itcompanydemo.exception.PhoneVerificationException;
import net.enver.itcompanydemo.model.User;
import net.enver.itcompanydemo.security.jwt.JwtUtil;
import net.enver.itcompanydemo.security.twilio.PhoneVerificationService;
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
    private final PhoneVerificationService phoneVerificationService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, PhoneVerificationService phoneVerificationService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.phoneVerificationService = phoneVerificationService;
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

            String token = jwtUtil.createToken(user);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Username or password is incorrect.");
        }
    }

    @PostMapping("verify")
    public ResponseEntity<Map<Object, Object>> verifyPhoneNumber(@RequestBody UserVerifyDto verifyDto) {
        Map<Object, Object> response = new HashMap<>();
        try {
            String phoneNumber = verifyDto.getPhoneNumber();
            User user = userService.findByPhoneNumber(phoneNumber);

            if (user == null) {
                throw new UsernameNotFoundException("User with phone number: " + phoneNumber + " not found.");
            }

            phoneVerificationService.sendSmsCode(phoneNumber);

            response.put("phoneNumber", phoneNumber);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new PhoneVerificationException("Error sending sms message to verify.");
        }
    }

    @PostMapping("checksmscode")
    public ResponseEntity<Map<Object, Object>> checkSmsCode(@RequestBody UserVerifyDto verifyDto) {
        Map<Object, Object> response = new HashMap<>();
        try {
            String phoneNumber = verifyDto.getPhoneNumber();
            User user = userService.findByPhoneNumber(phoneNumber);

            if (user == null) {
                throw new UsernameNotFoundException("User with phone number: " + phoneNumber + " not found.");
            }
            if (phoneVerificationService.verifySmsCode(phoneNumber, verifyDto.getSmsCode())) {
                userService.activate(user);
                response.put("message", "User with username " + user.getUsername() + " is activated.");
            } else {
                response.put("message", "The entered code is not correct.");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new PhoneVerificationException("Error checking sms code.");
        }
    }
}
