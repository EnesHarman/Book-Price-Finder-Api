package com.book.price.founder.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.price.founder.Entity.concretes.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
