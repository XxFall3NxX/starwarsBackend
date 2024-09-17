package com.example.star_wars_backend;

import java.util.List;

public class Character {
    private String name;
    private List<Film> films;
    private List<Vehicle> vehicles;

    public Character(String name, List<Film> films, List<Vehicle> vehicles) {
        this.name = name;
        this.films = films;
        this.vehicles = vehicles;
    }

    public String getName() {
        return name;
    }

    public List<Film> getFilms() {
        return films;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
