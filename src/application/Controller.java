package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Controller implements Initializable  {

	
	// GUI components
	@FXML private Label statusLabel;
	
	
	
	
	@FXML
	private void testButtonCommand(ActionEvent event) {
		// execute a linux command here and show the output in the 
		// statusLabel
		System.out.println("Button was clicked");
		Model modelTest = new Model();
		try {
			
			//modelTest.executePost3();
			modelTest.executePost();
			//modelTest.executeClearDialed();
			//modelTest.executeClearMissed();
			//modelTest.executeClearRecieved();
			//modelTeset.executeReboot();
			
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("--- Error executing POST message ---");
			//e.printStackTrace();
		}
		System.out.println("POST request was executed on ip address");
		statusLabel.setText("POST request was sent");
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}
