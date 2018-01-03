package Rager.Timothy.jokes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@Component
public class SearchResult {

    @JsonProperty("results")
    private List<Joke> searchResults;

    public SearchResult(){}
    public SearchResult (List<Joke> results){
        searchResults = results;
    }

    public List<Joke> getSearchResults(){
        return searchResults;
    }

}
