package br.edu.controller;

import br.edu.model.Atleta;
import br.edu.model.AtletaParaOlimpico;
import br.edu.model.Olimpíada;
import br.edu.model.Sede;
import br.edu.model.dao.AtletaDAO;
import br.edu.view.WindowAtleta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class OlimpiadaController {
    @FXML
    TextField txtName;
    @FXML
    TextField txtCountry;
    @FXML
    TextField txtYear ;

    @FXML
    Button btnAddOlimpiada;

    @FXML
    Label lbOlimpíada;

    @FXML
    TableView<Atleta> table;

    @FXML
    TableColumn<Atleta, Integer> cId;
    @FXML
    TableColumn<Atleta, String> cName;
    @FXML
    TableColumn<Atleta, String> cModality;
    @FXML
    TableColumn<Atleta, String> cCountry;
    @FXML
    TableColumn<Atleta, LocalDate> cBirthday;

    @FXML
    TableColumn<AtletaParaOlimpico, String> cDeficiency;

    @FXML
    ObservableList<Atleta> atletas;

    List<Atleta> atletaspara;


    public void initialize() {
        cId.setCellValueFactory(new PropertyValueFactory<Atleta, Integer>("cod"));
        cName.setCellValueFactory(new PropertyValueFactory<Atleta, String>("name"));
        cModality.setCellValueFactory(new PropertyValueFactory<Atleta, String>("modality"));
        cCountry.setCellValueFactory(new PropertyValueFactory<Atleta, String>("country"));
        cBirthday.setCellValueFactory(new PropertyValueFactory<Atleta, LocalDate>("birthDate"));
        cDeficiency.setCellValueFactory(new PropertyValueFactory<AtletaParaOlimpico, String>("deficiency"));
        atletas = FXCollections.observableArrayList();
        loadTable();

        table.setItems(atletas);
//
    }





    private void loadTable() {
        AtletaDAO dao = new AtletaDAO();
        List<Atleta> atl = dao.listAll();
        atletas = FXCollections.observableArrayList(atl);
        atletaspara =  atletas.stream().filter(a-> a instanceof AtletaParaOlimpico).collect(Collectors.toList());
        System.out.println(atletaspara);

    }

    private void reloadTable(){
        atletas.clear();
        loadTable();
        table.setItems(atletas);
    }

    public void createOlimpiada(ActionEvent actionEvent) {
        String name = String.valueOf(txtName.getText());
        String country = String.valueOf(txtCountry.getText());
        Integer year = Integer.valueOf(txtYear.getText());
        Sede s1 = new Sede(name,country);
        Olimpíada o = new Olimpíada(country,year,s1);
        lbOlimpíada.setText("Olímpiada em " + name + "(" + country + ")" + ", " + year);

    }

    public void addAtleta(ActionEvent actionEvent){
        WindowAtleta windowAtleta = new WindowAtleta();
        try{
            windowAtleta.show(null);
            reloadTable();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void updateAtleta(ActionEvent actionEvent){
        WindowAtleta windowAtleta = new WindowAtleta();
        try{
            Atleta a1 = table.getSelectionModel().getSelectedItem();
            if(a1 != null){
                windowAtleta.show(a1);
            }
            reloadTable();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void removeAtleta(ActionEvent actionEvent){
        AtletaDAO dao = new AtletaDAO();
        Atleta selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            dao.delete(selectedItem.getCod());
            reloadTable();
        }

    }


}
