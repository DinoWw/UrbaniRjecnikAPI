package hr.dww.urt;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/words")
public class WordController {
	@Autowired
	private WordRepository wordRepository;
	

	@PostMapping(path = "")
	public void addNewWord(@RequestBody String word) {

		Word n = new Word();
		n.setWord(word);
		wordRepository.save(n);
		return;
	}

	
	
	// TODO: init ban_datetime
	@PutMapping(path = "/ban")
	public void banWord(@RequestBody Integer id) {
		// TODO: change the type of the exception according to some convention or return
		// a HTTP status code through spring and return;
		Word w = wordRepository.findById(id).orElseThrow(() -> new RuntimeException("no such id"));
		w.setBanned(true);

		// this save is probably not needed,
		// (https://www.baeldung.com/spring-data-crud-repository-save)
		wordRepository.save(w);
	}

	@GetMapping(path = "")
	public Iterable<Word> getAllWords(
			@RequestParam(required=false, defaultValue="0") Integer pageIndex,
			@RequestParam(required=false, defaultValue="10") Integer pageSize) {
		
		PageRequest pageR = PageRequest.of(pageIndex, pageSize);
		return wordRepository.findAll(pageR).getContent();
	}

	@GetMapping(path = "/{id}")
	public Word getWordById(@PathVariable Integer id) {
		return wordRepository.findById(id).orElse(null);
	}

	@GetMapping(path = "/search")
	public Iterable<Word> findContaining(
			@RequestParam String word, 
			@RequestParam(required=false, defaultValue="0") Integer pageIndex,
			@RequestParam(required=false, defaultValue="10") Integer pageSize
			) {

		
		PageRequest pageR = PageRequest.of(pageIndex, pageSize);
		
		return wordRepository.findByWordContainingIgnoreCase(word, pageR);
	}

	@GetMapping(path = "/random")
	public Word getRandomWord() {
		Long qty = wordRepository.count();
		int idx = (int) (Math.random() * qty);
		Page<Word> questionPage = wordRepository.findAll(PageRequest.of(idx, 1));
		Word q = null;
		if (questionPage.hasContent()) {
			q = questionPage.getContent().get(0);
		}
		return q;

	}
}