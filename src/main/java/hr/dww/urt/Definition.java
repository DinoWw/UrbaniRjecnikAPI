package hr.dww.urt;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


// TODO: change getters and setters to make more sense, add dates autoamtically etc.

@Entity 
public class Definition {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  
  // insted of wordId it is somehow possible to use @ManyToOne and Word word
  @ManyToOne
  @JoinColumn(name="word_id", nullable=false)
  @JsonIgnoreProperties("definitions")
  private Word word;

  @Column(nullable=false)
  private String definition;
  
  private String conversation;

  @Column(nullable=false)
  private Integer flags = 0;

  @Column(nullable=false)
  private LocalDateTime suggested_datetime = LocalDateTime.now();
  
  private LocalDateTime approved_datetime;

  @Column(nullable=false)
  private Boolean approved = false;
  
  private LocalDateTime ban_datetime;

  @Column(nullable=false)
  private Boolean banned = false;

  @Column(nullable=false)
  private Integer upvotes = 0;

  @Column(nullable=false)
  private Integer downvotes = 0;
  
  // TODO: change to id form users table with onetomany
  @Column(nullable=false)
  private String submitted_by_user = "";

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Word getWord() {
	return word;
}

public void setWord(Word word) {
	this.word = word;
}
// TODO: implement setWordById i ByWordString

public String getDefinition() {
	return definition;
}

public void setDefinition(String deifnition) {
	this.definition = deifnition;
}

public String getConversation() {
	return conversation;
}

public void setConversation(String conversation) {
	this.conversation = conversation;
}

public Integer getFlags() {
	return flags;
}

public void setFlags(Integer flags) {
	this.flags = flags;
}

public LocalDateTime getSuggested_datetime() {
	return suggested_datetime;
}

public void setSubmitted_by_user(String submitted_by_user) {
	this.submitted_by_user = submitted_by_user;
}

public LocalDateTime getApproved_datetime() {
	return approved_datetime;
}

public void setApproved_datetime(LocalDateTime approved_datetime) {
	this.approved_datetime = approved_datetime;
}

public Boolean getApproved() {
	return approved;
}

public void setApproved(Boolean approved) {
	this.approved = approved;
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

public void upvote() {
	this.upvotes ++;
}

public Integer getUpvotes() {
	return this.upvotes;
}

public Integer getDownvotes() {
	return downvotes;
}

public void downvote() {
	downvotes ++;
}

public String getSubmitted_by_user() {
	return submitted_by_user;
}
 
  
}