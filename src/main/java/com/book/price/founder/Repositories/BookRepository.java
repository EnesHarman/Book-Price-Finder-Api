package com.book.price.founder.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.price.founder.Entity.concretes.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByISBN(String ISBN);
}
