package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Controller implements Initializable  {

	
	// GUI components
	@FXML private Label statusLabel;
	
	
	private Model modelTest;
	
	
	@FXML
	private void testButtonCommand(ActionEvent event) {
		// execute a linux command here and show the output in the 
		// statusLabel
		System.out.println("Button was clicked");
		//
		try {
			
			//modelTest.executePost3();
			//modelTest.executePost();
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
	
	@FXML 
	public void buttonClick(ActionEvent event) {
		printConsole("buttonClick()");
		Button btn = (Button) event.getSource();
		int roomExtension = Integer.parseInt(btn.getText());
		showExtension(roomExtension);
		
		if(isSuite(roomExtension)) {
			modelTest.handleSuite(roomExtension);
			return;
			
		}
		modelTest.testingCallSequence();
		
//		modelTest.resetMissedCalls(roomExtension);
//		
//		modelTest.restDialedCalls(roomExtension);
//		
//		modelTest.resetRecievedCalls(roomExtension);
//		
//		modelTest.rebootNode(roomExtension);
	}
	
	
	private boolean isSuite(int roomExtension) {
		return false;
	}

	private void printConsole(String string) {
		System.out.println(string);
		
	}

	private void showExtension(int roomExtension) {
		statusLabel.setText(roomExtension+"");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// Initiazlie our data structure here
		// have a hashamp that contains button.text to ip.address 
		// and go from theree
		this.modelTest = new Model();
		
	}
	
}
