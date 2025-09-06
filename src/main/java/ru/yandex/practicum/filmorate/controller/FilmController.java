package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api/v1/")
public class FilmController {
    private List<Film> films = new ArrayList<>();

    @GetMapping("/films")
    public List<Film> getFilms() {
        return films;
    }

    @PostMapping("/films")
    public Film createFilm(@RequestBody Film film) throws Exception{
        if (film.getName() == "" || film.getName().equals(null)) {
            throw new ValidationException("Film name can't be empty or blank.");
        }
        if (film.getDescription().length() > 200) {
            throw new ValidationException("Film description can't be more than 200 characters.");
        }
        if (film.getReleaseDate().isBefore(LocalDateTime.of(1895, 12, 28, 0,0))) {
            throw new ValidationException("Film's date can't be earlier than 28-12-1895");
        }
        if (film.getDuration() < 0) {
            throw new ValidationException("Film's duration should be positive!");
        }
        films.add(film);
        return film;
    }

    @PutMapping("/films")
    public Film putFilm(@RequestBody Film film) throws Exception{
        if (film.getName() == "" || film.getName().equals(null)) {
            throw new ValidationException("Film name can't be empty or blank.");
        }
        if (film.getDescription().length() > 200) {
            throw new ValidationException("Film description can't be more than 200 characters.");
        }
        if (film.getReleaseDate().isBefore(LocalDateTime.of(1895, 12, 28, 0,0))) {
            throw new ValidationException("Film's date can't be earlier than 28-12-1895");
        }
        if (film.getDuration() < 0) {
            throw new ValidationException("Film's duration should be positive!");
        }
        for (int i = 0; i < films.size(); i++) {
            if (films.get(i).equals(film)) {
                films.set(i, film);
                return film;
            }
        }
        films.add(film);
        return film;
    }
}
