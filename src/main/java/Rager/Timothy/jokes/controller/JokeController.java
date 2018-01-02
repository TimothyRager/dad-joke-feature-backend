package Rager.Timothy.jokes.controller;

import Rager.Timothy.jokes.model.Joke;
import Rager.Timothy.jokes.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
public class JokeController {

    @Autowired
    private JokeService jokeService;

    @GetMapping("/jokes/{jokeId}")
    public Joke getJokeById(@PathVariable String jokeId) {
        return jokeService.getJokeById(jokeId);
    }

    @GetMapping("/jokes/all")
    public List<Joke> getAllCachedJokes() {
        return jokeService.getAllJokes();
    }

    @GetMapping("/jokes")
    public Joke getRandomJoke() {
        return jokeService.getRandomJoke();
    }

}