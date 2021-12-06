package com.book.price.founder.Entity.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Books")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","likes", "readBooks"})

public class Book {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="image", nullable = false)
	private String image;
	
	@Column(name="summary",columnDefinition = "text")
	private String summary;
	
	@Column(name="ISBN", nullable = false)
	private String ISBN;
	
	@Column(name="publisher", nullable = false)
	private String publisher;
	
	@Column(name="click_rate")
	public long clickRate;
	
	@ManyToOne
	@JoinColumn( name = "author_id", nullable = false)
	private Author author;
	
	@OneToMany(mappedBy = "book")
	private List<Like> likes;
	
	@OneToMany(mappedBy = "book")
	private List<ReadBook> readBooks;
	
}
