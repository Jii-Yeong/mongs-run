package Jelly;

import javax.swing.ImageIcon;

public class Jelly {
	private ImageIcon image;
	private int x;
	private int y;
	private int xWeight;
	private int yWeight;
	private int score;
	public Jelly(ImageIcon image, int x, int y, int xWeight, int yWeight, int score) {
		super();
		this.image = image;
		this.x = x;
		this.y = y;
		this.xWeight = xWeight;
		this.yWeight = yWeight;
		this.score = score;
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getxWeight() {
		return xWeight;
	}
	public void setxWeight(int xWeight) {
		this.xWeight = xWeight;
	}
	public int getyWeight() {
		return yWeight;
	}
	public void setyWeight(int yWeight) {
		this.yWeight = yWeight;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
