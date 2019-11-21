package model;

public class Ball {
	
	private int radio;
	private int posX;
	private int posY;
	private int wait;
	private String dir;
	private int bounces;
	private boolean stop;
	/**
	 * @param radio
	 * @param posX
	 * @param posY
	 * @param wait
	 * @param direction
	 * @param bounces
	 * @param stop
	 */
	public Ball(int radio, int posX, int posY, int wait, String dir, int bounces, boolean stop) {
		this.radio = radio;
		this.posX = posX;
		this.posY = posY;
		this.wait = wait;
		this.dir = dir;
		this.bounces = bounces;
		this.stop = stop;
	}
	public int getRadio() {
		return radio;
	}
	public void setRadio(int radio) {
		this.radio = radio;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getWait() {
		return wait;
	}
	public void setWait(int wait) {
		this.wait = wait;
	}
	public String getDirn() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public int getBounces() {
		return bounces;
	}
	public void setBounces(int bounces) {
		this.bounces = bounces;
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	public void move() {
		if(dir.equals("DERECHA")) {
			posX++;
			if(posX == 600-radio) {
				dir = "IZQUIERDA";
				bounces++;
			}
		}else if(dir.equals("IZQUIERDA")){
			posX--;
			if(posX == radio) {
				dir = "DERECHA";
				bounces++;
			}
		}else if(dir.equals("ARRIBA")){
			posY--;
			if(posY == 25+radio) {
				dir = "ABAJO";
				bounces++;
			}
		}else if(dir.equals("ABAJO")){
			posY++;
			if(posY == 400-radio) {
				dir = "ARRIBA";
				bounces++;
			}
		}
	}
	
}
