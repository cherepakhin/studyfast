package ru.perm.v.sburrestdemo.ch4;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffees")
public class RestApiDemoController {

    ObjectMapper mapper = new ObjectMapper();
    private CoffeeRepository coffeeRepository;

    public RestApiDemoController(CoffeeRepository repository) {
        this.coffeeRepository =repository;
    }

    @GetMapping("/")
    public Iterable<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Coffee> getById(@PathVariable String id) {
        for (Coffee coffee : coffeeRepository.findAll()) {
            if (coffee.getId().equals(id)) {
                return Optional.of(coffee);
            }
        }
        return Optional.empty();
    }

    @PostMapping("")
    public Coffee postCoffee(@RequestBody Coffee coffee) {
        boolean isNew = true;
        for (Coffee c : coffeeRepository.findAll()) {
            if (c.getId().equals(coffee.getId())) {
                c.setName(coffee.getName());
                isNew = false;
            }
        }
        if (isNew) {
            System.out.println("Сохранение");
            coffeeRepository.save(coffee);
        }
        System.out.println("===============Список после");
        System.out.println(coffeeRepository.findAll());
        return coffee;
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffeeRepository.deleteById(id);
    }


}
