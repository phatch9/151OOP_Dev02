package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DefineNewCategoryController {
    @FXML
    private TextField categoryNameField;

    @FXML
    private void saveCategory() {
        String categoryName = categoryNameField.getText();

    }

    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene homeScene = new Scene(homePage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }
}