package br.edu.model;

import java.util.ArrayList;
import java.util.List;

public class Olimpíada {
    String country;
    Integer year;
    Sede sede;
    List<Atleta> atletas = new ArrayList<>();

    public Olimpíada(String country, Integer year, Sede sede) {
        this.country = country;
        this.year = year;
        this.sede = sede;
    }


    public void addAtleta(Atleta atleta){
        atletas.add(atleta);
    }

    public void remove(Atleta atleta){
        atletas.remove(atleta);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    @Override
    public String toString() {
        return "Olimpíada{" +
                "country='" + country + '\'' +
                ", year=" + year +
                ", sede=" + sede +
                ", atletas=" + atletas +
                '}';
    }
}
