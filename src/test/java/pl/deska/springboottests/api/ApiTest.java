package pl.deska.springboottests.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.deska.springboottests.model.Animal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_return_animals_list() throws Exception {
        mockMvc.perform(get("/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void should_return_animal_by_id() throws Exception {
        mockMvc.perform(get("/get/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty());
    }


    @Test
    public void should_add_animal() throws Exception {
        Animal animal = new Animal("Elephant");
        mockMvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(animal)))
                .andExpect(jsonPath("$.name", Is.is(animal.getName())));
    }


    @Test
    public void should_update_animal() throws Exception {
        Animal newAnimal = new Animal("Horse");
        mockMvc.perform(put("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(newAnimal)))
                .andExpect(jsonPath("$.name", Is.is(newAnimal.getName())));
    }

    @Test
    public void should_delete_animal_by_id() throws Exception {
        mockMvc.perform(delete("/delete/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String asJsonString(Animal elephant) {
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(elephant);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
