package com.book.price.founder.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.price.founder.Entity.concretes.ReadBook;

public interface ReadBookRepository extends JpaRepository<ReadBook, Long>{

}
