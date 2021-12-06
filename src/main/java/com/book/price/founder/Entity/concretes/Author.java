package com.book.price.founder.Entity.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Authors")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","books"})
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long authorId;

	@Column(name="name")
	private String authorName;
	
	@Column(name="image")
	private String authorImage;
	
	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	private List<Book> books;
	
}
