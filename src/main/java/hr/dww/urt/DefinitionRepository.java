package hr.dww.urt;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import hr.dww.urt.Definition;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DefinitionRepository extends JpaRepository<Definition, Integer> {

	//public Optional<Definition> findByWord(String word);
	
	
}