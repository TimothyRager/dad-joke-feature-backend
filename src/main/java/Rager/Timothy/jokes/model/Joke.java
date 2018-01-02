package Rager.Timothy.jokes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown=true)
@Component
public class Joke {

    @Id
    @JsonProperty("id")
    private String jokeId;
    @JsonProperty("joke")
    private String jokeText;

    Joke(){}
    public Joke (String passedJokeId, String passedJokeText){
        jokeId=passedJokeId;
        jokeText=passedJokeText;
    }

    public String getJokeId() {
        return jokeId;
    }

    public void setJokeId(String jokeId) {
        this.jokeId = jokeId;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

    @Override
    public String toString(){
        return ("ID: "+jokeId+"\nJoke: "+jokeText);
    }
}
