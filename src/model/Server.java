package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Server {

	public final static String LEVEL0 = "Level0.txt";
	public final static String LEVEL1 = "Level1.txt";
	public final static String LEVEL2 = "Level2.txt";
	
	private ArrayList<Ball> balls;
	private ArrayList<Player> p0;
	private ArrayList<Player> p1;
	private ArrayList<Player> p2;
	private String file;
	private int level;
	/**
	 * @param balls
	 * @param players0
	 * @param players1
	 * @param players2
	 * @param file
	 * @param level
	 * @throws IOException 
	 */
	public Server(String file) throws IOException {
		balls = new ArrayList<>();
		p0 = new ArrayList<>();
		p1 = new ArrayList<>();
		p2 = new ArrayList<>();
		this.file = file;
		loadBalls();
	}
	
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public ArrayList<Ball> getBalls() {
		return balls;
	}
	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}
	public ArrayList<Player> getP0() {
		return p0;
	}
	public void setP0(ArrayList<Player> p0) {
		this.p0 = p0;
	}
	public ArrayList<Player> getP1() {
		return p1;
	}
	public void setP1(ArrayList<Player> p1) {
		this.p1 = p1;
	}
	public ArrayList<Player> getP2() {
		return p2;
	}
	public void setP2(ArrayList<Player> p2) {
		this.p2 = p2;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void saveGame() throws IOException {
		
		String msg = "#nivel"+"\n";
		msg += level + "\n";
		msg += "#diametro posX posY espera direccion rebotes" + "\n";
		
		for(int i = 0; i < balls.size(); i++) {
			msg += balls.get(i).toString() + "\n";
		}
		
		File f = new File(file);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write(msg);
		bw.close();
		
	}
	
	public void loadBalls() throws IOException {
		File f = new File(file);
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String text = "";
		
		while((text = reader.readLine()) != null) {
			if(!(text.charAt(0) == '#')) {
				if(text.length() == 1) {
					level = Integer.parseInt(text);
				}
				else {
					String[] separator = text.split("\t");
					Ball ball = new Ball(Integer.parseInt(separator[0]), Integer.parseInt(separator[1]), Integer.parseInt(separator[2]), Integer.parseInt(separator[3]), separator[4], Integer.parseInt(separator[5]), Boolean.parseBoolean(separator[6]));
					addBalls(ball);
				}
			}
		}
		
		reader.close();
		
	}
	
	public void addBalls(Ball ball) {
		balls.add(ball);
	}
	
	public void loadPlayers0() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(LEVEL0);
		ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
		ArrayList<Player> readObject = (ArrayList<Player>) o.readObject();
		p0 = readObject;
		o.close();
	}
	
	public void writePlayers0() throws FileNotFoundException, IOException {
		File f = new File(LEVEL0);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(p0);
		oos.close();
	}
	
}
