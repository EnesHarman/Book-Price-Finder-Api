package com.book.price.founder.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.price.founder.Core.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long>{

}
