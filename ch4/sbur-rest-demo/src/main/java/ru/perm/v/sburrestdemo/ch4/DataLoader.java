package ru.perm.v.sburrestdemo.ch4;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataLoader {
    private final CoffeeRepository coffeeRepository;

    public DataLoader(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @PostConstruct
    private void loadData() {
        this.coffeeRepository.saveAll(List.of(
                new Coffee("Coffee0"),
                new Coffee("Coffee1"),
                new Coffee("Coffee2")
        ));

    }
}
