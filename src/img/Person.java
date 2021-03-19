package img;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Person extends JPanel {
	private Image im;
	ImageIcon img;
	
	// 달리는 캐릭터
	public Image person_run = new ImageIcon(".\\img\\Person.gif").getImage();
	public Image chick_run = new ImageIcon(".\\img\\chick_run.gif").getImage();
	
	// 점프 캐릭터
	public Image person_jump = new ImageIcon(".\\img\\person_jumping.png").getImage();
	public Image chick_jump = new ImageIcon(".\\img\\chick_jump.png").getImage();
	
	// 슬라이딩 캐릭터
	public Image person_sliding = new ImageIcon(".\\img\\Person_sliding.png").getImage();
	public Image chick_sliding = new ImageIcon(".\\img\\chick_jump.png").getImage();
	
	
	public Image run;
	public Image slide;
	public Image jump;
	
	
	public Person(int selectedNum) {
		
		switch (selectedNum) {
		case 1:
			setIm(person_run);
			break;
		case 2:
			setIm(chick_run);
			break;
		}
		switchRun(selectedNum);
		switchSlid(selectedNum);
		switchJump(selectedNum);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0, this);
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public Image getIm() {
		return im;
	}

	public void setIm(Image im) {
		this.im = im;
	}

	public Image getSlide() {
		return slide;
	}

	public void setSlide(Image slide) {
		this.slide = slide;
	}
	public Image getRun() {
		return run;
	}

	public void setRun(Image run) {
		this.run = run;
	}

	public Image getJump() {
		return jump;
	}

	public void setJump(Image jump) {
		this.jump = jump;
	}

	public void switchSlid(int selectedNum) {
		switch (selectedNum) {
		case 1:
			setSlide(person_sliding);
			break;
		case 2:
			setSlide(chick_sliding);
			break;
		}
	}
	public void switchRun(int selectedNum) {
		switch (selectedNum) {
		case 1:
			setRun(person_run);
			break;
		case 2:
			setRun(chick_run);
			break;
		}
	}
	public void switchJump(int selectedNum) {
		switch (selectedNum) {
		case 1:
			setJump(person_jump);
			break;
		case 2:
			setJump(chick_jump);
			break;
		}
	}
	
}
