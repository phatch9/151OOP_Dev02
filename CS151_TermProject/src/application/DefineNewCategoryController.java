package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.control.Label;

import java.io.IOException;

public class DefineNewCategoryController {
    @FXML
    private TextField categoryNameField;
    
    @FXML Label errorLabel;
    
    @FXML
    private void resetErrorLabel() {
    	errorLabel.setVisible(false);
    }

    @FXML
    private void saveCategory() {
    	String input = categoryNameField.getText().replaceAll("^[ \t]+|[ \t]+$", "");
    	
    	// Strips whitespace from both ends of the string and sets errorLabel if input is only whitespace
        if (input.equals("")) {
        	errorLabel.setTextFill(Paint.valueOf("#ff0000"));
        	errorLabel.setText("Invalid Input: Please enter a category name");
        	errorLabel.setVisible(true);
        }
        else {
        	Category userInput = new Category(input);
        	try {
        		DataBaseAccessor db = DataBaseAccessor.getSingleInstance();
        		boolean success = db.addEntry(userInput);
        		
        		// give user a success label if success is true, else give error label
        		if (success) {
                	errorLabel.setTextFill(Paint.valueOf("#01c505"));
                	errorLabel.setText("New Category Successfully Added");
                	errorLabel.setVisible(true);
        		}
        		else {
        			errorLabel.setTextFill(Paint.valueOf("#ff0000"));
                	errorLabel.setText("Invalid Input: Category has already been defined");
                	errorLabel.setVisible(true);
        		}
        	}
        	catch (Exception e) {
        		System.err.println(e);
        	}
        }
    }

    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getClassLoader().getResource("view/HomePage.fxml"));
        Scene homeScene = new Scene(homePage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }
}