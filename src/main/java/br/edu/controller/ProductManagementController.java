package br.edu.controller;

import br.edu.entities.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ProductManagementController {
    @FXML
    TableView<Product> table;
    @FXML
    TableColumn<Product,String> cDesc;
    @FXML
    TableColumn<Product,Double> cValue;
    @FXML
    TableColumn<Product,String> cCategory;
    @FXML
    TableColumn<Product, LocalDate> cDate;

    @FXML
    TextField txtDesc;
    @FXML
    TextField txtValue;
    @FXML
    DatePicker txtDate;
    @FXML
    ComboBox cbCategory;

    @FXML
    Button btnAdd;

    @FXML
    Button btnRemove;

    @FXML
    ObservableList<Product> expenses;

    @FXML
    public void initialize(){
        addCategoryInComboBox();
        bindTableToModel();
    }

    private void bindTableToModel(){
        cDesc.setCellValueFactory(new PropertyValueFactory<Product,String>("description"));
        cValue.setCellValueFactory(new PropertyValueFactory<Product,Double>("price"));
        cCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        cDate.setCellValueFactory(new PropertyValueFactory<Product,LocalDate>("data"));
        table.setItems(loadValues());
    }

    private ObservableList<Product> loadValues() {
        expenses = FXCollections.observableArrayList();
        expenses.add((new Product("Oi",120.0,"Eletrônico", LocalDate.now())));
        return expenses;
    }

    private void addCategoryInComboBox() {
        cbCategory.getItems().addAll("Móveis","Eletrônico","Livros");
    }


    public void addProduct(ActionEvent actionEvent) {
        String desc = String.valueOf(txtDesc.getText());
        Double valor  = Double.parseDouble(txtValue.getText());
        String categoria  = String.valueOf(cbCategory.getValue());
        LocalDate data = txtDate.getValue();
        Product d1 = new Product(desc,valor,categoria,data);
        expenses.add(d1);
    }

    public void removeProduct(ActionEvent actionEvent) {
        Product selectedItem = table.getSelectionModel().getSelectedItem();
        expenses.remove(selectedItem);
    }
}
