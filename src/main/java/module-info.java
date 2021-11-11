module br.edu {


    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens br.edu.controller to javafx.fxml;
    opens br.edu.model to javafx.base;

    exports br.edu.view;

}