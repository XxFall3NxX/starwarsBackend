package com.example.star_wars_backend;

import java.util.List;

public class CharacterData {
    private int count;
    private List<CharacterResult> results;

    // Getters and Setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CharacterResult> getResults() {
        return results;
    }

    public void setResults(List<CharacterResult> results) {
        this.results = results;
    }

    // Nested class for character result
    public static class CharacterResult {
        private String name;
        private List<String> films;
        private List<String> vehicles;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getFilms() {
            return films;
        }

        public void setFilms(List<String> films) {
            this.films = films;
        }

        public List<String> getVehicles() {
            return vehicles;
        }

        public void setVehicles(List<String> vehicles) {
            this.vehicles = vehicles;
        }
    }
}
