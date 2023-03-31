package com.crud.react.film;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film ,Integer> {
    @Override
    Optional<Film> findById(Integer integer);
}
