package main;

import javax.swing.JFrame;

import panal.PlayPanel;
import ranking.RankData;
import ranking.RankPanel;
import result.ResultPanel;

public class MainFrame extends JFrame {
	public MainFrame() {
		PlayPanel playPanel = new PlayPanel();
		
		RankPanel rankPanel = new RankPanel(new RankData("양종문", 1234));
		ResultPanel resultPanel = new ResultPanel(rankPanel);
		
		add(resultPanel);
		setSize(1000, 700);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}
}
