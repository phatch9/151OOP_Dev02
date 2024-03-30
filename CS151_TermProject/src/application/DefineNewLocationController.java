package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class DefineNewLocationController {
    @FXML
    private TextField locationNameField;

    @FXML
    private TextField locationDescriptionField;
    
    @FXML Label errorLabel;
    
    @FXML
    private void resetErrorLabel() {
    	errorLabel.setVisible(false);
    }

    // Method to handle the logic when the "Save Location" button is clicked
    @FXML
    private void saveLocation(ActionEvent event) {
    	// Strips whitespace from both ends of inputs
        String name = locationNameField.getText().replaceAll("^[ \t]+|[ \t]+$", "");;
        String description = locationDescriptionField.getText().replaceAll("^[ \t]+|[ \t]+$", "");;


        // Rejects input if location name is an empty string
        if (name.equals("")) {
        	errorLabel.setTextFill(Paint.valueOf("#ff0000"));
        	errorLabel.setText("Invalid Input: Please enter a valid location name");
        	errorLabel.setVisible(true);
        }
        else {
        	Location userInput = new Location(name, description);
        	try {
        		DataBaseAccessor db = DataBaseAccessor.getSingleInstance();
        		boolean success = db.addEntry(userInput);
        		
        		// Notifies the user if the DB was successful, rejects input if location name is already in the DB
        		if (success) {
                	errorLabel.setTextFill(Paint.valueOf("#01c505"));
                	errorLabel.setText("New Location Successfully Added");
                	errorLabel.setVisible(true);
        		}
        		else {
        			errorLabel.setTextFill(Paint.valueOf("#ff0000"));
                	errorLabel.setText("Invalid Input: Location has already been defined");
                	errorLabel.setVisible(true);
        		}
        	}
        	catch (Exception e) {
        		System.err.println(e);
        	}
        }
    }

    // Method to return to the home page when the "Return to Home" button is clicked
    @FXML
    private void returnToHome(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getClassLoader().getResource("view/HomePage.fxml"));
            Scene homeScene = new Scene(homePage);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homeScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
