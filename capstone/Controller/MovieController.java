package com.example.capstone.Controller;


import com.example.capstone.Model.Movie;
import com.example.capstone.Model.Product;
import com.example.capstone.Services.MovieService;
import com.example.capstone.Services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/movie")
@AllArgsConstructor
public class MovieController {


    private final MovieService movieService;


    @GetMapping("get")
    public ResponseEntity getAllMovies() {

        ArrayList<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.status(200).body(movies);
    }

    @PostMapping("post")
    public ResponseEntity addMovie(@RequestBody @Valid Movie movie, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        movieService.addMovie(movie);
        return ResponseEntity.status(200).body("Movie added successfully");
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateMovie(@PathVariable String id, @RequestBody Movie movie, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = movieService.updateMovie(id, movie);
        if (isUpdated) {
            return ResponseEntity.status(400).body("movie successfully updated");

        } else {
            return ResponseEntity.status(200).body("movie not found");
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteMovie(@PathVariable String id) {

        boolean isDeleted = movieService.deleteMovie(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("movie deleted");

        } else {
            return ResponseEntity.status(400).body("movie not found");

        }
    }

    @PostMapping("watch/{userId}/{movieId}")
    public ResponseEntity watchMovie(@PathVariable String userId, @PathVariable String movieId){
        boolean isWatched = movieService.watchMovie(userId, movieId);
        if (isWatched) {
            return ResponseEntity.status(200).body("add to watch list");

        } else {
            return ResponseEntity.status(400).body("only prime user can watch");

        }
    }


}
