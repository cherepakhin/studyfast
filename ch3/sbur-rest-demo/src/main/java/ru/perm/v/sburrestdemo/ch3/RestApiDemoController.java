package ru.perm.v.sburrestdemo.ch3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiDemoController {

    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController() {
        this.coffees = List.of(
            new Coffee("Coffee0"),
            new Coffee("Coffee1"),
            new Coffee("Coffee2"),
            new Coffee("Coffee3")
        );
    }

    @GetMapping("/coffees")
    public List<Coffee> getCoffees() {
        return coffees;
    }

    @GetMapping("/coffees/{id}")
    public Optional<Coffee> getById(@PathVariable String id) {
        for(Coffee coffee: coffees) {
            if(coffee.getId().equals(id)) {
                return Optional.of(coffee);
            }
        }
        return Optional.empty();
    }
}
