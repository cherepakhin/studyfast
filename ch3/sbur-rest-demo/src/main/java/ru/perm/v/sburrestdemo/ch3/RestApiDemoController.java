package ru.perm.v.sburrestdemo.ch3;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffees")
public class RestApiDemoController {

    ObjectMapper mapper = new ObjectMapper();
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController() {
        coffees.add(new Coffee("Coffee0"));
        coffees.add(new Coffee("Coffee1"));
        coffees.add(new Coffee("Coffee2"));
    }

    @GetMapping("/")
    public List<Coffee> getCoffees() {
        return coffees;
    }

    @GetMapping("/{id}")
    public Optional<Coffee> getById(@PathVariable String id) {
        for (Coffee coffee : coffees) {
            if (coffee.getId().equals(id)) {
                return Optional.of(coffee);
            }
        }
        return Optional.empty();
    }

    @PostMapping("")
    ResponseEntity<Coffee> postCoffee(@RequestBody Coffee coffee) {
        boolean isNew = true;
        for (Coffee c : coffees) {
            if (c.getId().equals(coffee.getId())) {
                c.setName(coffee.getName());
                isNew = false;
            }
        }
        if (isNew) {
            System.out.println(coffee);
            System.out.println("===============Добавляю в список");
            System.out.println(coffees);
            coffees.add(coffee);
        }
        System.out.println("===============Список после");
        System.out.println(coffees);
        return new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Coffee> putCoffee(@RequestBody Coffee coffee) {
        int coffeeIndex = -1;
        for (Coffee c : coffees) {
            if (c.getId().equals(coffee.getId())) {
                coffeeIndex = coffees.indexOf(coffee);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ? postCoffee(coffee)
            : new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }

}
