package pl.deska.springboottests.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.deska.springboottests.model.Animal;

@Repository
public interface AnimalRepo extends CrudRepository<Animal, Long> {
}
