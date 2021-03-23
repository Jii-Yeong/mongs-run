package img;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Person extends JPanel {
	private Image im;
	private ImageIcon img;
	
	// 달리는 캐릭터
	private Image person_run = new ImageIcon(".\\img\\Person.gif").getImage();
	private Image chick_run = new ImageIcon(".\\img\\chick_run.gif").getImage();
	private Image totoro_run = new ImageIcon(".\\img\\totoro_run.gif").getImage();
	private Image beemo_run = new ImageIcon(".\\img\\beemo_run.gif").getImage();
	
	// 점프 캐릭터
	private Image person_jump = new ImageIcon(".\\img\\person_jump.png").getImage();
	private Image chick_jump = new ImageIcon(".\\img\\chick_jump.png").getImage();
	private Image totoro_jump = new ImageIcon(".\\img\\totoro_jump.png").getImage();
	private Image beemo_jump = new ImageIcon(".\\img\\beemo_jump.png").getImage();
	
	// 슬라이딩 캐릭터
	private Image person_sliding = new ImageIcon(".\\img\\Person_sliding.png").getImage();
	private Image chick_sliding = new ImageIcon(".\\img\\chick_sliding.png").getImage();
	private Image totoro_sliding = new ImageIcon(".\\img\\totoro_sliding.png").getImage();
	private Image beemo_sliding = new ImageIcon(".\\img\\beemo_sliding.png").getImage();
	
	private Image run;
	private Image slide;
	private Image jump;
	
	private int width;
	private int height;
	
	private int alpha = 255;
	private AlphaComposite alphaComposite;

	public Person(int selectedNum) {
		switch (selectedNum) {
		case 1:
			setIm(person_run);
			break;
		case 2:
			setIm(chick_run);
			break;
		case 3:
			setIm(totoro_run);
			break;
		case 4:
			setIm(beemo_run);
			break;
		}
		switchSize(selectedNum);
		switchRun(selectedNum);
		switchSlid(selectedNum);
		switchJump(selectedNum);
	}
	
	public void switchSlid(int selectedNum) {
		switch (selectedNum) {
		case 1:
			setSlide(person_sliding);
			break;
		case 2:
			setSlide(chick_sliding);
			break;
		case 3:
			setSlide(totoro_sliding);
			break;
		case 4:
			setSlide(beemo_sliding);
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
		case 3:
			setRun(totoro_run);
			break;
		case 4:
			setRun(beemo_run);
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
		case 3:
			setJump(totoro_jump);
			break;
		case 4:
			setJump(beemo_jump);
			break;
		}
	}
	
	public void switchSize(int selectedNum) {
		switch (selectedNum) {
		case 1:
			setWidth(im.getWidth(null));
			setHeight(im.getHeight(null));
			break;
		case 2:
			setWidth(im.getWidth(null));
			setHeight(im.getHeight(null));
			break;
		case 3:
			setWidth(im.getWidth(null));
			setHeight(im.getHeight(null));
			break;
		case 4:
			setWidth(im.getWidth(null));
			setHeight(im.getHeight(null));
			break;
		}
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

	public int getAlpha() {
		return alpha;
	}
	
	public void setAlpha(int alpha) {
		this.alpha = alpha;
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
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	private void setHeight(int height) {
		this.height = height;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) getAlpha()/255);
		g2.setComposite(alphaComposite);
		g.drawImage(im, 0, 0, this);
	}
}
