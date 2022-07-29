package ru.perm.v.studyfast;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class Greeting {
    @Value("${greeting-name: Mirage}")
    private String name;
    @Value("${greeting-coffee: ${greeting-name} coffee is null}")
    private String coffee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoffee() {
        return coffee;
    }

    public void setCoffee(String coffee) {
        this.coffee = coffee;
    }
}
