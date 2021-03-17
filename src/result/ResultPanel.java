package result;

import javax.swing.JPanel;

import main.MainFrame;
import panel.StartPanel;
import score.ScorePanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ResultPanel extends JPanel implements ActionListener {
	private StartPanel name;
	private ScorePanel currentScore;
	private Font font;
	private ImageIcon button_image;
	private ImageIcon background_image;
	private MainFrame frame;
	/**
	 * Create the panel.
	 */
	public ResultPanel(StartPanel name, ScorePanel currentScore, MainFrame frame) {
		// 이미지 변경 작업필요 **************************************************************************************
		// 배경 1000 * 700
		// 버튼 230 * 70
		this.name = name;
		this.currentScore = currentScore;
		this.frame = frame;
		button_image = new ImageIcon(".\\img\\button\\btn_main.png");
		background_image = new ImageIcon(".\\img\\ScoreBackground.jpg");
		setLayout(null);
		font = new Font("맑은 고딕", Font.BOLD, 30);
		
		JLabel score = new JLabel(name.getTfdName().getText() + " : " + currentScore.getScore() + "점");
		score.setBounds(479, 66, 439, 113);
		score.setFont(font);
		add(score);
		
		JButton mainButton = new JButton();
		mainButton.setBounds(675, 508, 230, 70);
		mainButton.addActionListener(this);
		mainButton.setBorderPainted(false);
		mainButton.setFocusPainted(false);
		mainButton.setContentAreaFilled(false);
		mainButton.setIcon(button_image);
		mainButton.setActionCommand("메인화면");
		add(mainButton);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, 1000, 700);
		lblNewLabel.setIcon(background_image);
		
		add(lblNewLabel);
		setSize(1000, 700);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("메인화면")) {
			frame.changeStartPanel();
		}
	}
}
