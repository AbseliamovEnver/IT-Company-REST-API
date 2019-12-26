package net.enver.itcompanydemo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestRestController {

    @GetMapping("")
    public String helloHeroku(){
        return "This is simple test string for heroku.";
    }
}
