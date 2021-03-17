package result;

import javax.swing.JPanel;

import ranking.RankData;
import ranking.RankPanel;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ResultPanel extends JPanel implements ActionListener {
	private RankPanel rankPanel;
	private Font font;
	private ImageIcon button_image;
	private ImageIcon background_image;
	/**
	 * Create the panel.
	 */
	public ResultPanel(RankPanel rankPanel) {
		// 이미지 변경 작업필요 **************************************************************************************
		// 배경 1000 * 700
		// 버튼 230 * 70
		button_image = new ImageIcon(".\\img\\button\\btn_main.png");
		background_image = new ImageIcon(".\\img\\ScoreBackground.jpg");
		this.rankPanel = rankPanel;
		setLayout(null);
		font = new Font("맑은 고딕", Font.BOLD, 30);
		
		JLabel score = new JLabel(rankPanel.getScore().getName() + " : " + rankPanel.getScore().getScore() + "점");
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
		mainButton.setActionCommand("처음으로");
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
		if (e.getActionCommand().equals("처음으로")) {
			System.out.println("처음으로");
		}
	}
}
