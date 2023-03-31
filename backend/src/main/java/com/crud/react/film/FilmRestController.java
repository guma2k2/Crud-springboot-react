package com.crud.react.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/films")
@CrossOrigin("*")
public class FilmRestController {


    @Autowired
    private FilmRepository filmRepository;

    public FilmRestController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping
    public List<Film> findAll(){
        return filmRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Film> getById(@PathVariable("id")Integer id) throws FilmNotFoundRestException {
        Film filmCreated = filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundRestException("Not found film with id " + id));
        return  ResponseEntity.ok().body(filmCreated);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Film film) {
        film.setCreatedTime(LocalDateTime.now());
        filmRepository.save(film);
        return ResponseEntity.ok().body("Save succesful");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id")Integer id, @RequestBody Film film ) throws FilmNotFoundRestException {
        Film filmCreated = filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundRestException("Not found film with id " + id));
        filmCreated.setName(film.getName());
        filmCreated.setPhotos(film.getPhotos());
        filmCreated.setCreatedTime(filmCreated.getCreatedTime());
        filmRepository.save(filmCreated);
        return ResponseEntity.ok().body("Update success");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id ) throws FilmNotFoundRestException {
        Film filmCreated = filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundRestException("Not found film with id " + id));
        filmRepository.delete(filmCreated);
        return ResponseEntity.ok().body("Delete successful");
    }
}
