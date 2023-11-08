package hr.dww.urt;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity // This tells Hibernate to make a table out of this class
public class Word {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  @Column(unique = true, columnDefinition = "varchar(255) not null")
  private String word;
  
  @OneToMany(mappedBy="word")
  @JsonIgnoreProperties("word")
  private Set<Definition> definitions;

  @Column(nullable = false)
  private Integer flags = 0;
  
  private LocalDateTime ban_datetime;

  @Column(nullable = false)
  private Boolean banned = false;

public Set<Definition> getDefinitions() {
	return definitions;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getWord() {
	return word;
}

public void setWord(String word) {
	this.word = word.toLowerCase();
}

public Integer getFlags() {
	return flags;
}

public void setFlags(Integer flags) {
	this.flags = flags;
}

public LocalDateTime getBan_datetime() {
	return ban_datetime;
}

public void setBan_datetime(LocalDateTime ban_datetime) {
	this.ban_datetime = ban_datetime;
}

public Boolean getBanned() {
	return banned;
}

public void setBanned(Boolean banned) {
	this.banned = banned;
}

  
  
  

  
  
}