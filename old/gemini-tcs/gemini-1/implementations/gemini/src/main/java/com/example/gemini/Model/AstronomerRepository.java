package com.example.gemini.Model;

import org.springframework.data.repository.CrudRepository;

public interface AstronomerRepository extends CrudRepository<Astronomer, Integer> {
    Astronomer findByUsername(String username);
    Astronomer findByPassword(String password);
}