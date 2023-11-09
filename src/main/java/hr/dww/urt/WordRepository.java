package hr.dww.urt;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import hr.dww.urt.Word;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface WordRepository extends JpaRepository<Word, Integer> {
	//Word findById(Integer id);

	public Page<Word> findByWordIgnoreCase(String word, Pageable pageable);
	public Page<Word > findByWordContainingIgnoreCase(String wordPart, Pageable pageable);
}