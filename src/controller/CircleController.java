package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Ball;
import model.Server;
import thread.CircleThread;

public class CircleController implements Initializable {
	@FXML private AnchorPane anchor;
	private Server s;
	private CircleThread thread;
	private ArrayList<Ball> balls;
	private int stop;

	public void initialize() {
		
	}	
	
	public void generateLevel(String file) {
		s = new Server(file);
		generateBalls();
	}
	
	public void generateBalls() {
		stop = 0;
		balls = s.getBalls();
		for (int i = 0; i < balls.size(); i++) {
			if(balls.get(i).isStop()) {
				stop++;
			}
			Ball b = balls.get(i);
			Circle c = new Circle(b.getRadio());
			c.setLayoutX(b.getPosX());
			c.setLayoutY(b.getPosY());
			anchor.getChildren().add(c);	
			thread = new CircleThread(c,b);
			thread.start();
			
		}
	}
	
	public void aboutTheGame(ActionEvent event) {
		Stage stage = new Stage();
		AnchorPane anchor2 = new AnchorPane();
		Label label = new Label();
		String msg = "En el juego aparecen unas esferas en la pantalla moviéndose, algunas horizontal y otras verticalmente. "
				+ "Durante su movimiento, si la esfera alcanza un extremo de la ventana de juego, ésta rebotará  y "
				+ "se moverá ahora en sentido contrario. "
				+ "El jugador debe detenerlas haciendo clic sobre cada una de las esferas que aparecen en la pantalla, "
				+ "lo más rápido posible y antes de que reboten. Por cada rebote, el contador de rebotes aumentará. "
				+ "El mejor jugador es aquel que detenga todas las esferas con la menor cantidad de rebotes.\r\n";
		label.setText(msg);
		label.setMaxSize(400, 400);
		label.setWrapText(true);
		anchor2.getChildren().add(label);
		Scene scene = new Scene(anchor2, 400, 200);
		stage.setScene(scene);
		stage.setTitle("About the game");
		stage.show();
	}
	
	public void loadGame(ActionEvent event) {
		try {
			Stage s = (Stage)anchor.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/loadWindow.fxml"));
			Scene scene = new Scene(root,600,400);
			s.setScene(scene);
			s.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopBall(MouseEvent event) {
		ArrayList<Ball> balls = s.getBalls();
		for(int i = 0; i < balls.size(); i++) {
			if(!balls.get(i).isStop()) {
				double x = event.getSceneX()-(balls.get(i).getPosX());
				double y = event.getSceneY()-(balls.get(i).getPosY());
				double total = x+y;
				double radio = balls.get(i).getRadio();
				if(total <= radio) {
					balls.get(i).setStop(true);
					stop++;
				}
			}
		}
		if(stop == balls.size()) {
			finish();
		}
	}
	
	public void finish() {
		int bounce = 0;
		ArrayList<Ball> balls = s.getBalls();
		for(int i = 0; i < balls.size(); i++) {
			bounce += balls.get(i).getBounces();
		}
	}
	
	
	
	
	
	public void exit(ActionEvent event) {
		Stage s = (Stage)anchor.getScene().getWindow();
		s.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
