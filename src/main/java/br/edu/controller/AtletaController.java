package br.edu.controller;

import br.edu.model.Atleta;
import br.edu.model.AtletaParaOlimpico;
import br.edu.model.dao.AtletaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class AtletaController {
    @FXML
    ChoiceBox<String> cbAtleta;
    @FXML
    TextField txtCod;
    @FXML
    TextField txtName;
    @FXML
    TextField txtModality;
    @FXML
    TextField txtCountry;

    @FXML
    DatePicker dpBirthday;

    @FXML
    ComboBox<String> cbDeficiency;

    Atleta atleta;
    AtletaParaOlimpico atletaParaOlimpico;


    public void setAtleta(Atleta atleta){
        this.atleta = atleta;
        if(atleta.getClass().getSimpleName() == "Atleta"){
            cbAtleta.setValue(atleta.getClass().getSimpleName());
        }else{
            cbAtleta.setValue("Atleta Paraolímpico");
        }
        txtCod.setText(String.valueOf(atleta.getCod()));
        txtName.setText(String.valueOf(atleta.getName()));
        txtModality.setText(String.valueOf(atleta.getModality()));
        txtCountry.setText(String.valueOf(atleta.getCountry()));
        dpBirthday.setValue(atleta.getBirthDate());
        if(atleta instanceof AtletaParaOlimpico){
            cbDeficiency.setValue(((AtletaParaOlimpico) atleta).getDeficiency());
        }else{
            cbDeficiency.setValue("NÃO POSSUI");
        }
    }


    private Atleta getAtletaFromView(){
        Integer cod = Integer.valueOf(txtCod.getText());
        String name = String.valueOf(txtName.getText());
        String modality = String.valueOf(txtModality.getText());
        String country = String.valueOf(txtCountry.getText());
        LocalDate birthday = dpBirthday.getValue();
        if(cbAtleta.getValue() == "Atleta Paraolímpico"){
            String deficiency = cbDeficiency.getValue();
            Atleta a1 = new AtletaParaOlimpico(cod,name,modality,country,birthday,deficiency);
            return a1;
        }else{
            Atleta a1 = new Atleta(cod,name,modality,country,birthday);
            return a1;
        }
    }



    private void addTypeInComboBox() {
        cbAtleta.setValue("Atleta");
        cbAtleta.getItems().addAll("Atleta", "Atleta Paraolímpico");
    }

    private void addDeficiencyInComboBox() {
        cbDeficiency.getItems().addAll("FÍSICA", "AUDITIVA", "VISUAL", "MÚLTIPLA");

    }


    public void initialize() {
        addTypeInComboBox();
        addDeficiencyInComboBox();
        System.out.println(cbAtleta.getValue());
    }

    public void saveOrUpdate(ActionEvent action){
          AtletaDAO dao = new AtletaDAO();

        if(atleta == null){
            dao.insert(getAtletaFromView());
        }else{
            dao.update(getAtletaFromView());
        }


    }
}
