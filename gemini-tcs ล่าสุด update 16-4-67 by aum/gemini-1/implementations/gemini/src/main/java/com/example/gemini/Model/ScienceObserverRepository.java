package com.example.gemini.Model;

import org.springframework.data.repository.CrudRepository;

public interface ScienceObserverRepository extends CrudRepository<ScienceObserver, Integer> {
    ScienceObserver findByUsername(String username);
    ScienceObserver findByPassword(String password);
}