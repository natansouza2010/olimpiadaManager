package br.edu.model;

import br.edu.model.utils.Deficiência;

import java.time.LocalDate;

public class AtletaParaOlimpico extends Atleta{

    private String deficiency;

    public AtletaParaOlimpico(Integer cod, String name, String modality, String country, LocalDate birthDate) {
        super(cod, name, modality, country, birthDate);
    }

    public AtletaParaOlimpico(Integer cod, String name, String modality, String country, LocalDate birthDate, String deficiency) {
        super(cod, name, modality, country, birthDate);
        this.deficiency = deficiency;
        isParaOlímpico();
    }

    public Boolean isParaOlímpico (){
        return true;
    }

    public String getDeficiency() {
        return deficiency;
    }

    public void setDeficiency(String deficiency) {
        this.deficiency = deficiency;
    }

    @Override
    public String toString() {
        return "AtletaParaOlimpico{" +
                "cod=" + getCod() +
                ", name='" + getName() + '\'' +
                ", modality='" + getModality() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", birthDate=" + getBirthDate() +
                ", deficiênciaFísica=" + getDeficiency() +
                '}';
    }
}
