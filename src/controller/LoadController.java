package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoadController implements Initializable {

	@FXML private TextField lvl;
	@FXML private Label alert;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public TextField getLvl() {
		return lvl;
	}



	public void setLvl(TextField lvl) {
		this.lvl = lvl;
	}



	public void fileChoosed(ActionEvent event) {
		if(!new File(lvl.getText()+".txt").exists()) {
			alert.setVisible(true);
		}
		else {
			alert.setVisible(false);
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getClassLoader().getResource("view/Circle.fxml"));
				loader.load();
				CircleController controller = loader.getController();
				controller.generateLevel(lvl.getText()+".txt");
				Parent p = loader.getRoot();
				Stage s = (Stage)lvl.getScene().getWindow();
				s.setScene(new Scene(p));
				s.show();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
