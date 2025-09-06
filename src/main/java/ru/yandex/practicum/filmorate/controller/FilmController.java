package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

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
    public Film createFilm(@RequestBody Film film) {
        films.add(film);
        return film;
    }

    @PutMapping("/films")
    public Film putFilm(@RequestBody Film film) {
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
