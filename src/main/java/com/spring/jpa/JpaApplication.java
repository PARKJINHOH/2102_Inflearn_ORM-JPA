package com.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    private MovieRepository movieRepository;

    public JpaApplication(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;

        Movie movie = new Movie();
        movie.setDirector("디렉터");
        movie.setActor("엑터");
        movie.setName("영화이름");
        movie.setPrice(10000);

        movieRepository.save(movie);



        // 조회
        System.out.println("=======================");
        movieRepository.findById(movie.getId());



    }



}
