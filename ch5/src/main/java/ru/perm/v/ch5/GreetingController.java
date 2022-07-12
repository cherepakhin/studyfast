package ru.perm.v.ch5;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    @Value("${greeting-name: Mirage}") // greeting-name: Mirage работает, если не задано в application.properties
    private String name;

    @GetMapping("/")
    public String getGreeting() {
        return name;
    }
}
