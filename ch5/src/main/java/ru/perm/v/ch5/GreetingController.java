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
    @Value("${greeting-coffee: Mirage is good}")
    private String greetingCoffee;

    @GetMapping("/")
    public String getGreeting() {
        return name;
    }

    @GetMapping("/coffee")
    public String getGreetingCoffee() {
        return greetingCoffee;
    }
}
