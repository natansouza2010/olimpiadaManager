package br.edu.model;

import java.time.LocalDate;

public class Atleta {
    private Integer cod;
    private String name;
    private String modality;
    private String country;
    private LocalDate birthDate;

    public Atleta(Integer cod, String name, String modality, String country, LocalDate birthDate) {
        this.cod = cod;
        this.name = name;
        this.modality = modality;
        this.country = country;
        this.birthDate = birthDate;
        isParaOlímpico();
    }

    public Integer getCod() {
        return cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


    public Boolean isParaOlímpico (){
        return false;
    }


    @Override
    public String toString() {
        return "Atleta{" +
                "cod=" + getCod() +
                ", name='" + getName() + '\'' +
                ", modality='" + getModality() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", birthDate=" + getBirthDate() +
                '}';
    }
}
