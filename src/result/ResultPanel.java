package result;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.MainFrame;
import panel.StartPanel;
import score.ScorePanel;

public class ResultPanel extends JPanel implements ActionListener {
	private Font font;
	private ImageIcon button_image;
	private ImageIcon background_image;
	private MainFrame frame;
	private StartPanel startPanel;
	private ScorePanel scorePanel;
	/**
	 * Create the panel.
	 */
	public ResultPanel(StartPanel startPanel, ScorePanel scorePanel, MainFrame frame) {
		this.frame = frame;
		this.startPanel = startPanel;
		this.scorePanel = scorePanel;
		button_image = new ImageIcon(".\\img\\button\\btn_main.png");
		background_image = new ImageIcon(".\\img\\background\\resultBg.png");
		font = new Font("맑은 고딕", Font.BOLD, 30);
		setLayout(null);
		
		JLabel score = new JLabel(startPanel.getTfdName().getText() + " : " + String.format("%,d", scorePanel.getScore()) + "점");
		score.setBounds(479, 66, 439, 113);
		score.setFont(font);
		score.setForeground(new Color(255, 255, 255));
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
	
	public StartPanel getStartPanel() {
		return startPanel;
	}

	public void setStartPanel(StartPanel startPanel) {
		this.startPanel = startPanel;
	}

	public ScorePanel getScorePanel() {
		return scorePanel;
	}

	public void setScorePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("메인화면")) {
			frame.changeStartPanel();
		}
	}
}
