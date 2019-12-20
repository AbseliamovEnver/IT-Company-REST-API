package net.enver.itcompanydemo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeRestApiControllerV1 {
//    @GetMapping("index")
//    public String index(){
//        return "index";
//    }

    @GetMapping("login")
    public String login(){
        return "login";
    }
}
