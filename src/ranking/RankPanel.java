package ranking;

import javax.swing.JPanel;

import main.MainFrame;
import panel.StartPanel;
import result.ResultPanel;
import score.ScorePanel;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RankPanel extends JPanel implements ActionListener, Serializable {
	private static final long serialVersionUID = -4444137678317535870L;
	private File file;
	private Font font;
	private List<RankData> scoreList;
	private MainFrame frame;
	private ResultPanel resultPanel;
	private StartPanel startPanel;
	private ImageIcon background_image;
	private ImageIcon mainButton_imgae;
	/**
	 * Create the panel.
	 */
	
	public RankPanel(ResultPanel resultPanel, StartPanel startPanel, MainFrame frame) {
		file = new File(".\\rankScore.bin");
		this.frame = frame;
		this.resultPanel = resultPanel;
		this.startPanel = startPanel;
		scoreList = new ArrayList<>(); 
		font = new Font("맑은 고딕", Font.BOLD, 30);
		background_image = new ImageIcon(".\\img\\background\\rankingBg.png");
		mainButton_imgae = new ImageIcon(".\\img\\button\\btn_main.png");
		
		if(file.exists()) {
			load();
			save();
		} else if (!file.exists()){
			save();
		}
		
		JLabel rank1_Score = new JLabel(scorePrint(0));
		rank1_Score.setBounds(265, 111, 650, 64);
		rank1_Score.setFont(font);
		rank1_Score.setForeground(new Color(255, 255, 255));
		add(rank1_Score);
		
		JLabel rank2_Score = new JLabel(scorePrint(1));
		rank2_Score.setBounds(265, 185, 650, 64);
		rank2_Score.setFont(font);
		rank2_Score.setForeground(new Color(255, 255, 255));
		add(rank2_Score);
		
		JLabel rank3_Score = new JLabel(scorePrint(2));
		rank3_Score.setBounds(265, 259, 650, 64);
		rank3_Score.setFont(font);
		rank3_Score.setForeground(new Color(255, 255, 255));
		add(rank3_Score);
		
		JLabel rank4_Score = new JLabel(scorePrint(3));
		rank4_Score.setBounds(265, 333, 650, 64);
		rank4_Score.setFont(font);
		rank4_Score.setForeground(new Color(255, 255, 255));
		add(rank4_Score);
		
		JLabel rank5_Score = new JLabel(scorePrint(4));
		rank5_Score.setBounds(265, 407, 650, 64);
		rank5_Score.setFont(font);
		rank5_Score.setForeground(new Color(255, 255, 255));
		add(rank5_Score);
		
		JButton MainButton = new JButton();
		MainButton.addActionListener(this);
		MainButton.setActionCommand("메인화면");
		MainButton.setIcon(mainButton_imgae);
		MainButton.setBounds(716, 564, 230, 70);
		MainButton.setBorderPainted(false);
		MainButton.setFocusPainted(false);
		MainButton.setContentAreaFilled(false);
		add(MainButton);
		
		JLabel rank1 = new JLabel("1등");
		rank1.setBounds(167, 111, 62, 64);
		rank1.setFont(font);
		rank1.setForeground(new Color(255, 255, 255));
		add(rank1);
		
		JLabel rank2 = new JLabel("2등");
		rank2.setBounds(167, 185, 62, 64);
		rank2.setFont(font);
		rank2.setForeground(new Color(255, 255, 255));
		add(rank2);
		
		JLabel rank3 = new JLabel("3등");
		rank3.setBounds(167, 259, 62, 64);
		rank3.setFont(font);
		rank3.setForeground(new Color(255, 255, 255));
		add(rank3);
		
		JLabel rank4 = new JLabel("4등");
		rank4.setBounds(167, 333, 62, 64);
		rank4.setFont(font);
		rank4.setForeground(new Color(255, 255, 255));
		add(rank4);
		
		JLabel rank5 = new JLabel("5등");
		rank5.setBounds(167, 407, 62, 64);
		rank5.setFont(font);
		rank5.setForeground(new Color(255, 255, 255));
		add(rank5);
		
		JLabel background = new JLabel();
		background.setBounds(0, 0, 1000, 700);
		background.setIcon(background_image);
		add(background);
		
		setLayout(null);
		setSize(1000, 700);
		setVisible(true);
	}

	public List<RankData> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<RankData> scoreList) {
		this.scoreList = scoreList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("메인화면")) {
			frame.changeStartPanel();
			System.out.println("메인화면");
		}
	}
	
	// 데이터 로드
	private void load() {
		System.out.println("로드");
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
			scoreList = (List<RankData>) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 데이터 저장
	private void save() {
		System.out.println("세이브");
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
			RankData rankData = new RankData(resultPanel.getStartPanel().getTfdName().getText(), resultPanel.getScorePanel().getScore());
			resultPanel.getStartPanel().getTfdName().setText("");
			scoreList.add(rankData);
			Collections.sort(scoreList, Collections.reverseOrder());
			out.writeObject(scoreList);
			startPanel.getBtnRanking().setEnabled(true);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 데이터 있을 경우에 점수 출력
	private String scorePrint(int i) {
		String result = null;
		try {
			if (scoreList.get(i).getName() != null) {
				result = scoreList.get(i).getName() + " : " + String.format("%,d", scoreList.get(i).getScore()) + "점";
			}
		} catch (IndexOutOfBoundsException e) {}
		return result;
	}
}
