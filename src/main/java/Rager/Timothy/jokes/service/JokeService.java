package Rager.Timothy.jokes.service;

import Rager.Timothy.jokes.model.Joke;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class JokeService {

    private String API_URL = "https://icanhazdadjoke.com";

    private static List<Joke> jokes = new ArrayList<>();

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    public List<Joke> getAllJokes() {
        return jokes;
    }

    public Joke getJokeById(String jokeId) {
        for (Joke j : jokes) {
            if (jokeId.equals(j.getJokeId())) {
                return j;
            }
        }
        headers.set(HttpHeaders.USER_AGENT, "Nyan-Chat, Zip Code Wilmington");
        HttpEntity<String> entity = new HttpEntity<>("",headers);
        ResponseEntity<Joke> responseEntity = restTemplate.exchange(API_URL+"/j/"+jokeId, HttpMethod.GET, entity, Joke.class);

        Joke returnJoke = responseEntity.getBody();
        if (returnJoke.getJokeId()==null || returnJoke.getJokeText()==null) {
            returnJoke = new Joke(jokeId, "No Joke with ID "+jokeId+" found.");
        }
        addJoke(returnJoke);
        return returnJoke;
    }

    public Joke getRandomJoke(){
        headers.set(HttpHeaders.USER_AGENT, "Nyan-Chat, Zip Code Wilmington");
        HttpEntity<String> entity = new HttpEntity<>("",headers);
        ResponseEntity<Joke> responseEntity = restTemplate.exchange(API_URL, HttpMethod.GET, entity, Joke.class);
        Joke returnJoke = responseEntity.getBody();
        addJoke(returnJoke);
        return returnJoke;
    }

    private boolean jokesContainsId(String passedId){
        for (Joke j : jokes){
            if (j.getJokeId().equals(passedId)){
                return true;
            }
        }
        return false;
    }

    private void addJoke(Joke joke) {
        if (joke == null || jokesContainsId(joke.getJokeId())) {
            return;
        }
        jokes.add(joke);
    }

}
