package Rager.Timothy.jokes.controller;

import Rager.Timothy.jokes.model.Joke;
import Rager.Timothy.jokes.service.JokeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(value = JokeController.class, secure = false)
public class JokeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JokeService jokeService;

    Joke mockJoke = new Joke("abc123", "Troll-lolol");
    Joke mockJoke2 = new Joke("xyz123", "Troll-ercopters");
    Joke failedToFindJoke = new Joke("abc123", "No Joke with ID abc123 found");
    ArrayList<Joke> mockJokes = new ArrayList<>(Arrays.asList(mockJoke, mockJoke2));

    String exampleJokeJson = "{\"id\": \"abc123\", \"joke\": \"Troll-lolol\"}";

    @Test
    public void testGetJokeByIdExisting() throws Exception {

        Mockito.when(
                jokeService.getJokeById(Mockito.anyString())).thenReturn(mockJoke);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/jokes/abc123").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();


        String expected = exampleJokeJson;



        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    @Test
    public void testGetJokeByIdNotExisting() throws Exception {

        Mockito.when(
                jokeService.getJokeById(Mockito.anyString())).thenReturn(failedToFindJoke);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/jokes/abc123").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();


        String expected = "{\"id\": \"abc123\", \"joke\": \"No Joke with ID abc123 found\"}";



        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetRandomJoke() throws Exception {

        Mockito.when(
                jokeService.getRandomJoke()).thenReturn(mockJoke);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/jokes").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = exampleJokeJson;

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void testGetAllCachedJokes() throws Exception {

        Mockito.when(
                jokeService.getAllJokes()).thenReturn(mockJokes);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/jokes/all").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"id\": \"abc123\", \"joke\": \"Troll-lolol\"},{\"id\": \"xyz123\", \"joke\": \"Troll-ercopters\"}]";


        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

}