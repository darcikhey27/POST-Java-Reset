package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Controller implements Initializable  {

	
	// GUI components
	@FXML private Label statusLabel;
	@FXML private ImageView imgpic;
	@FXML private Button disabledButton;
	@FXML private Button disabledButton2;
	@FXML private Button disabledButton3;
	
	private Model modelTest;
	
	@FXML 
	public void buttonClick(ActionEvent event) {
		printConsole("buttonClick()");
		Button btn = (Button) event.getSource();
		int roomExtension = Integer.parseInt(btn.getText());
		showExtension(roomExtension);
		
		if(isSuite(roomExtension)) {
			statusLabel.setText("Resseting suite "+roomExtension);
			System.out.println("Resseting suite "+ roomExtension);
			//TODO: put a sleep(1) here so we can see status for suites
			modelTest.handleSuite(roomExtension);
			statusLabel.setText("Status..");
			return;
		}
//		statusLabel.setText("Deleting missed Calls for "+roomExtension);
//		sleep(2);
//		modelTest.testingCallSequence();
//		sleep(2);
//		statusLabel.setText("Finished");
		
		modelTest.resetDevice(roomExtension, "missed");
		
		modelTest.resetDevice(roomExtension, "dialed");
		
		modelTest.resetDevice(roomExtension, "recieved");
//		
		modelTest.resetDevice(roomExtension, "reboot");
	}
	
	
	private void sleep(int i) {
		try {
			Thread.sleep(i * 1000);
		} 
		catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private boolean isSuite(int roomExtension) {
		// check if the roomExtension is 106 or 206 or 306
		if(roomExtension == 106 || roomExtension == 206 || roomExtension == 306) {
			statusLabel.setText("is suite "+ roomExtension);
			return true;
		}
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
		Image img = null;
		img = new Image("file:src/application/gamble-sands-white.png");
		if(img != null) {
			this.imgpic.setImage(img);
		}
		this.disabledButton.setDisable(true);
		this.disabledButton2.setDisable(true);
		this.disabledButton3.setDisable(true);
		
	}
	
}
