module br.edu {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.edu.view to javafx.fxml;
    opens br.edu.controller to javafx.fxml;
    opens br.edu.entities to javafx.base;

    exports br.edu.view;

}