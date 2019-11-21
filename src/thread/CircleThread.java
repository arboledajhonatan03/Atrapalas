package thread;

import javafx.scene.shape.Circle;
import model.Ball;
import controller.CircleController;

public class CircleThread extends Thread {
	
	private Circle circle;
	private Ball ball;



	/**
	 * @param circle
	 * @param ball
	 */
	public CircleThread(Circle circle, Ball ball) {
		this.circle = circle;
		this.ball = ball;
	}



	public void run() {
		while(!ball.isStop()) {
			ball.move();
			circle.setLayoutX(ball.getPosX());
			circle.setLayoutY(ball.getPosY());
			try {
				sleep(ball.getWait());
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}