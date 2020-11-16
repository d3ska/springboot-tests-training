package pl.deska.springboottests.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.deska.springboottests.controller.AnimalController;
import pl.deska.springboottests.model.Animal;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class AnimalApi {

    private AnimalController animalController;

    @Autowired
    public AnimalApi(AnimalController animalController) {
        this.animalController = animalController;
    }


    @GetMapping("/all")
    public List<Animal> getAnimals(){
        return animalController.getAnimals();
    }

    @GetMapping("/get/{id}")
    public Animal getById(@PathVariable long id)  {
        Optional<Animal> optionalAnimal = animalController.getById(id);
        if(optionalAnimal.isPresent()){
            return optionalAnimal.get();
        }else{
            throw new IllegalArgumentException();
        }
    }

    @PostMapping("/add")
    public Animal addAnimal(@RequestBody Animal animal){
        return animalController.addAnimal(animal);
    }

    @PutMapping("/update")
    public Animal updateAnimal(@RequestBody Animal animal){
        return animalController.update(animal);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        animalController.deleteById(id);
    }

}
