package hr.dww.urt;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/definitions")
public class DefinitonController {
	@Autowired
	private DefinitionRepository definitionRepository;
	@Autowired
	private WordRepository wordRepository;
	
	private String[] sorts = {"upvotes","downvotes"};
	private boolean validSort(String sort) {
		return List.of(sorts).contains(sort);
	}
	
	// TODO: figure out when to use @ResponseBody
	// if it is omitted, an error not found is returned, regardless of the success
	// of th eoperation

	@PostMapping(path = "")
	public void addNewDefiniton(@RequestParam(required = false) Integer wordId, @RequestParam String word,
			@RequestParam String definition, @RequestParam(required = false) String conversation, @RequestParam String submittedByUser,
			@RequestParam(required = false, defaultValue = "0") Integer page) {

		Word wordO = new Word();

		// TODO: make custom exceptions and standardize form

		// could be written really cleanly in one line iw I figured out how to use
		// optionals as a return type in repositories

		if (wordId != null) {
			try {
				wordO = wordRepository.findById(wordId).orElseThrow();
			} catch (Exception e) {
				// TODO: throw some error idk
				return;
			}
		} else {
			try {
				wordO = wordRepository.findByWordIgnoreCase(word, PageRequest.of(0, 1)).get(0);
				if (wordO == null) {
					throw new NoSuchElementException();
				}
			} catch (Exception e) {

				wordO.setWord(word);
				wordRepository.save(wordO);
			}
		}

		Definition n = new Definition();
		n.setWord(wordO);
		n.setDefinition(definition);
		if(conversation != null) n.setConversation(conversation);
		n.setSubmitted_by_user(submittedByUser);

		definitionRepository.save(n);
		return;
	}

	@PostMapping(path = "/test")
	public void testF(@RequestBody Definition def) {
		System.out.println(def.getDefinition());

	}

	@PutMapping(path = "/{id}/upvote")
	public void upvote(@PathVariable Integer id) {
		Definition d = definitionRepository.findById(id).orElseThrow();
		d.upvote();
		definitionRepository.save(d);
	}
	
	@PutMapping(path = "/{id}/downvote")
	public void downvote(@PathVariable Integer id) {
		Definition d = definitionRepository.findById(id).orElseThrow();
		d.downvote();
		definitionRepository.save(d);
	}

	@GetMapping(path = "")
	public Iterable<Definition> getAllDefinitions( 
			@RequestParam(required=false, defaultValue="0") Integer pageIndex,
			@RequestParam(required=false, defaultValue="10") Integer pageSize,
			@RequestParam(required=false, defaultValue="upvotes") String sortBy) {

		if(!validSort(sortBy)) {
			// TODO: throw error
			return null;
		}
		
		PageRequest pageR = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
		return definitionRepository.findAll(pageR).getContent();
	}
	
	// ne znam zasto bi ovo bilo koristeno
	@GetMapping(path = "/{id}")
	public Definition getDefinitionById(@PathVariable Integer id) {
		return definitionRepository.findById(id).orElse(null);
	}
}