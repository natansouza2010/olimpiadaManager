package br.edu.controller;

import br.edu.entities.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class ProductManagementController {
    @FXML
    TableView<Product> table;
    @FXML
    TableColumn<Product, String> cDesc;
    @FXML
    TableColumn<Product, Double> cValue;
    @FXML
    TableColumn<Product, String> cCategory;
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
    public void initialize() {
        addCategoryInComboBox();
        bindTableToModel();
    }

    private void bindTableToModel() {
        cDesc.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        cValue.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        cCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        cDate.setCellValueFactory(new PropertyValueFactory<Product, LocalDate>("data"));
        table.setItems(loadValues());
    }

    private ObservableList<Product> loadValues() {
        ObservableList<Product> list = findAll();

        return list;
    }



    private void addCategoryInComboBox() {

        cbCategory.getItems().addAll("Móveis", "Eletrônico", "Livros");
    }


    public void addProduct(ActionEvent actionEvent) {
        String desc = String.valueOf(txtDesc.getText());
        Double valor = Double.parseDouble(txtValue.getText());
        String categoria = String.valueOf(cbCategory.getValue());
        LocalDate data = txtDate.getValue();
        Product d1 = new Product(desc, valor, categoria, data);
        expenses.add(d1);
        save(d1);
    }

    public void removeProduct(ActionEvent actionEvent) {
        Product selectedItem = table.getSelectionModel().getSelectedItem();
        expenses.remove(selectedItem);
        if(selectedItem !=null){
            delete(selectedItem);
        }
    }



    public static void save(Product p) {
        PreparedStatement stmt = null;
        Connection conn = null;

        try {

            conn = DriverManager.getConnection("jdbc:sqlite:db_products.db");

            String sql = "INSERT INTO product (description, price, category, date) values (?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, p.getDescription());
            stmt.setDouble(2, p.getPrice());
            stmt.setString(3, p.getCategory());
            stmt.setString(4, String.valueOf(p.getData()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally { 
        if(stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException e) { e.printStackTrace();}
        }
        if(conn != null) {
            try {conn.close();}
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        }

    }
    public ObservableList<Product> findAll(){
        expenses = FXCollections.observableArrayList();
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String sql = "SELECT * FROM product";
            conn = DriverManager.getConnection("jdbc:sqlite:db_products.db");
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("description"),
                                                rs.getDouble("price"),
                                                rs.getString("category"),
                                                LocalDate.parse(rs.getString("date"))
                );
                expenses.add(product);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expenses;

    }

    public static void delete(Product p){
        String sql = "DELETE FROM product WHERE description = ? AND price = ? AND category = ? AND date = ?" ;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db_products.db");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getDescription());
            stmt.setDouble(2, p.getPrice());
            stmt.setString(3, p.getCategory());
            stmt.setString(4, String.valueOf(p.getData()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
