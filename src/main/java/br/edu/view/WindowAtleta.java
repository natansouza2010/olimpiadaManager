package br.edu.view;

import br.edu.controller.AtletaController;
import br.edu.model.Atleta;
import br.edu.model.AtletaParaOlimpico;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowAtleta {

    private Atleta atleta;

    private AtletaController controller;

    public void show(Atleta atleta) throws IOException {
        this.atleta = atleta;

        FXMLLoader loader = new FXMLLoader();

        Pane graph = loader.load(getClass().getResource("windowatleta.fxml").openStream());
        controller = loader.getController();


        Scene scene = new Scene(graph, 262, 448);
        Stage stage = new Stage();

        if (atleta != null) {
            controller.setAtleta(atleta);

            stage.setTitle("Alterar Atleta");
        }else{
            stage.setTitle("Adicionar Atleta");
        }

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }
}
