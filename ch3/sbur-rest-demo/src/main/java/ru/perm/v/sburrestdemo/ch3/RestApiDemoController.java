package ru.perm.v.sburrestdemo.ch3;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiDemoController {

    ObjectMapper mapper = new ObjectMapper();
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController() {
        coffees.add(new Coffee("Coffee0"));
        coffees.add(new Coffee("Coffee1"));
        coffees.add(new Coffee("Coffee2"));
    }

    @GetMapping("/coffees")
    public List<Coffee> getCoffees() {
        return coffees;
    }

    @GetMapping("/coffees/{id}")
    public Optional<Coffee> getById(@PathVariable String id) {
        for (Coffee coffee : coffees) {
            if (coffee.getId().equals(id)) {
                return Optional.of(coffee);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/coffees/{id}")
    ResponseEntity<Coffee> postCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        boolean isNew = true;
        for (Coffee c : coffees) {
//            System.out.println(c);
            if (c.getId().equals(coffee.getId())) {
                c.setName(coffee.getName());
                isNew = false;
            }
        }
        if (isNew) {
            System.out.println(coffee);
            System.out.println("===============");
            System.out.println(coffees);
            System.out.println("---------------");
            coffees.add(coffee);
        }
        System.out.println("**************");
        System.out.println(coffees);
        return new ResponseEntity<Coffee>(coffee, HttpStatus.OK);
    }
}
