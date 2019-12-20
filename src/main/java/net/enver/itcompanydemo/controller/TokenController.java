package net.enver.itcompanydemo.controller;

import lombok.AllArgsConstructor;
import net.enver.itcompanydemo.model.JwtUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class TokenController {

//    private JwtGenerator jwtGenerator;
//
//    @PostMapping
//    public String generate(@RequestBody final JwtUser jwtUser) {
//        return jwtGenerator.generate(jwtUser);
//    }
}
