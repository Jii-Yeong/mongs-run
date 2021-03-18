package score;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class ScorePanel extends JPanel implements Runnable {
	private int score;
	private JLabel scoreLabel;
	private Font font;
	
	public ScorePanel() {
		setLayout(null);
		Thread thread = new Thread(this);
		font = new Font("맑은 고딕", Font.BOLD, 30);
		scoreLabel = new JLabel("점수 : " + score);
		scoreLabel.setBounds(0, 0, 300, 100);
		scoreLabel.setFont(font);
		scoreLabel.setForeground(Color.white);
		add(scoreLabel);
		setSize(300, 100);
		
		thread.start();
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public static void main(String[] args) {
		ScorePanel s = new ScorePanel();
	}
	
	@Override
	public void run() {
		try {
			int i = 0;
			while (getScore() <= 1000000000) {
				int temp;
				Thread.sleep(1000);
				temp = getScore();
				temp += 10;
				setScore(temp);
				scoreLabel.setText("점수 : " + String.format("%,d", getScore()));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
