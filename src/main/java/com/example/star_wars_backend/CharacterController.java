package com.example.star_wars_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CharacterController {

    private final RestTemplate restTemplate;

    @Autowired
    private SearchResultRepository searchResultRepository;

    public CharacterController() {
        this.restTemplate = new RestTemplate();
    }

    @QueryMapping
    public Character character(@Argument String name) {
        try {
            // Fetch character data from SWAPI
            String url = "https://swapi.dev/api/people/?search=" + name;
            CharacterData characterData = restTemplate.getForObject(url, CharacterData.class);

            // Check if results contain any characters
            if (characterData == null || characterData.getResults().isEmpty()) {
                throw new RuntimeException("No data found for character: " + name);
            }

            CharacterData.CharacterResult result = characterData.getResults().get(0);

            // Fetch films and vehicles
            List<Film> films = result.getFilms().stream()
                .map(filmUrl -> restTemplate.getForObject(filmUrl, Film.class))
                .collect(Collectors.toList());

            List<Vehicle> vehicles = result.getVehicles().stream()
                .map(vehicleUrl -> restTemplate.getForObject(vehicleUrl, Vehicle.class))
                .collect(Collectors.toList());

            return new Character(result.getName(), films, vehicles);
        } catch (Exception e) {
            // Log the error and return a meaningful GraphQL error message
            System.err.println("Error occurred: " + e.getMessage());
            throw new RuntimeException("Failed to fetch data from SWAPI: " + e.getMessage());
        }
    }

    @MutationMapping
    public SearchResult saveSearchResult(
            @Argument String characterName,
            @Argument List<String> films,
            @Argument List<String> vehicles) {

        // Create a new SearchResult entity
        SearchResult searchResult = new SearchResult();
        searchResult.setCharacterName(characterName);
        searchResult.setFilms(films);
        searchResult.setVehicles(vehicles);

        // Save the SearchResult entity to the database
        return searchResultRepository.save(searchResult);
    }
}
