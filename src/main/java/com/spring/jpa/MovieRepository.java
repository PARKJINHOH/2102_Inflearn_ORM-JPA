package com.spring.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

//public interface MovieRepository extends JpaRepository<Item, Long> {
//}
