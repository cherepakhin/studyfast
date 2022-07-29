package ru.perm.v.sburrestdemo.ch4;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(RestApiDemoController.class)
class RestApiDemoControllerTest {

    private final static String COFFEES = "/coffees";
    private static final MediaType MEDIA_TYPE_JSON = new MediaType(
        MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        StandardCharsets.UTF_8);
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CoffeeRepository coffeeRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getCoffees() throws Exception {
        when(coffeeRepository.findAll()).thenReturn(List.of(
            new Coffee("Coffee0"),
            new Coffee("Coffee1"),
            new Coffee("Coffee2")
        ));
        this.mockMvc.perform(get("/coffees/")).andDo(print()).andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].name", is("Coffee0")));
    }

    @Test
    void getById() throws Exception {
        Coffee coffee = new Coffee("Coffee0");
        when(coffeeRepository.findById(coffee.getId())).thenReturn(Optional.of(coffee));
        this.mockMvc.perform(get("/coffees/" + coffee.getId())).andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("Coffee0")));
    }

    @Test
    void postCoffee() throws Exception {
        List<Coffee> coffees = List.of(
            new Coffee("Coffee0"),
            new Coffee("Coffee1"),
            new Coffee("Coffee2")
        );
        Coffee coffeeNew = new Coffee("Coffee3");
        System.out.println("------------------");
        System.out.println(objectMapper.writeValueAsString(coffeeNew));
        System.out.println("------------------");
        when(coffeeRepository.findAll()).thenReturn(coffees);
        this.mockMvc.perform(
            post("/coffees/")
                .contentType(MEDIA_TYPE_JSON)
                .content(objectMapper.writeValueAsString(coffeeNew)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("Coffee3")));
    }
}
