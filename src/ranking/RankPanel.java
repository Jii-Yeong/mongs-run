package ranking;

import javax.swing.JPanel;
import javax.swing.JLabel;

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
	private RankData score;
	private List<RankData> scoreList;
	private File file;
	private Font font;
	private ImageIcon background_image;
	private ImageIcon mainButton_imgae;
	/**
	 * Create the panel.
	 */
	public RankPanel(RankData score) {
		file = new File(".\\rankScore.bin");
		this.score = score;
		scoreList = new ArrayList<>(); 
		font = new Font("맑은 고딕", Font.BOLD, 30);
		
		// 이미지 변경 작업필요 **************************************************************************************
		// 배경 1000 * 700
		// 버튼 230 * 70
		background_image = new ImageIcon(".\\img\\ScoreBackground.jpg");
		mainButton_imgae = new ImageIcon(".\\img\\MainButton.png");
		
		if (file.exists()) {
			load();
			save();
		} else {
			save();
		}
		
		JLabel rank1_Score = new JLabel(scorePrint(0));
		rank1_Score.setBounds(265, 111, 333, 64);
		rank1_Score.setFont(font);
		add(rank1_Score);
		
		JLabel rank2_Score = new JLabel(scorePrint(1));
		rank2_Score.setBounds(265, 185, 333, 64);
		rank2_Score.setFont(font);
		add(rank2_Score);
		
		JLabel rank3_Score = new JLabel(scorePrint(2));
		rank3_Score.setBounds(265, 259, 333, 64);
		rank3_Score.setFont(font);
		add(rank3_Score);
		
		JLabel rank4_Score = new JLabel(scorePrint(3));
		rank4_Score.setBounds(265, 333, 333, 64);
		rank4_Score.setFont(font);
		add(rank4_Score);
		
		JLabel rank5_Score = new JLabel(scorePrint(4));
		rank5_Score.setBounds(265, 407, 333, 64);
		rank5_Score.setFont(font);
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
		add(rank1);
		
		JLabel rank2 = new JLabel("2등");
		rank2.setBounds(167, 185, 62, 64);
		rank2.setFont(font);
		add(rank2);
		
		JLabel rank3 = new JLabel("3등");
		rank3.setBounds(167, 259, 62, 64);
		rank3.setFont(font);
		add(rank3);
		
		JLabel rank4 = new JLabel("4등");
		rank4.setBounds(167, 333, 62, 64);
		rank4.setFont(font);
		add(rank4);
		
		JLabel rank5 = new JLabel("5등");
		rank5.setBounds(167, 407, 62, 64);
		rank5.setFont(font);
		add(rank5);
		
		JLabel background = new JLabel();
		background.setBounds(0, 0, 1000, 700);
		background.setIcon(background_image);
		add(background);
		
		setLayout(null);
		setSize(1000, 700);
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
			System.out.println("메인화면");
		}
	}
	
	// 데이터 로드
	private void load() {
		if (file.exists()) {
			try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
				scoreList = (List<RankData>) in.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 데이터 저장
	private void save() {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
			scoreList.add(score);
			Collections.sort(scoreList, Collections.reverseOrder());
			out.writeObject(scoreList);
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
		} catch (IndexOutOfBoundsException e) {
		}
		return result;
	}
}
