package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
    @FXML
    Button startButton;
    @FXML
    ChoiceBox<Integer> ruleChoiceBox;
    @FXML
    Canvas canvas;
    @FXML
    TextField textFieldGridSize;
    @FXML
    TextField textFieldNumberOfIterations;
    @FXML
    CheckBox checkBoxPeriodicBoundaryConditions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ruleChoiceBox.setItems(FXCollections.observableArrayList(30, 60, 90, 120, 225));
        ruleChoiceBox.setValue(90);
        textFieldGridSize.appendText("100");
        textFieldNumberOfIterations.appendText("90");
    }

    public void clickStartButton() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int rule = ruleChoiceBox.getValue();
        int numberOfIterations = Integer.parseInt(textFieldNumberOfIterations.getText());
        int gridSize = Integer.parseInt(textFieldGridSize.getText());
        boolean ifPeriodicBoundaryConditions = checkBoxPeriodicBoundaryConditions.isSelected();

        boolean[] currentGeneration = new boolean[gridSize];
        if (gridSize % 2 == 0) {
            currentGeneration[gridSize / 2] = true;
        } else {
            currentGeneration[(gridSize - 1) / 2] = true;
        }

        for (int i = 0; i < numberOfIterations; i++) {
            Drawing.draw(currentGeneration, canvas, i);
            currentGeneration = Calculations.calculateNextTimeStep(currentGeneration, rule, ifPeriodicBoundaryConditions);
        }
    }

    public static void closeProgram() {
        Platform.exit();
        System.exit(0);
    }
}
