package pl.deska.springboottests.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import pl.deska.springboottests.model.Animal;
import pl.deska.springboottests.repo.AnimalRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AnimalController {

    private AnimalRepo animalRepo;

    @Autowired
    public AnimalController(AnimalRepo animalRepo) {
        this.animalRepo = animalRepo;
    }

    public List<Animal> getAnimals(){
        List<Animal> animals = new ArrayList<>();
        animalRepo.findAll().iterator().forEachRemaining(animals::add);
        return animals;
    }

    public Optional<Animal> getById(long id){
        return animalRepo.findById(id);
    }


    public Animal addAnimal(Animal animal){
        return animalRepo.save(animal);
    }

    public Animal update(Animal newAnimal){
        return animalRepo.save(newAnimal);
    }

    public void deleteById(long id){
        animalRepo.deleteById(id);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        animalRepo.save(new Animal("Dog"));
        animalRepo.save(new Animal("Cat"));
        animalRepo.save(new Animal("Horse"));
    }
}
