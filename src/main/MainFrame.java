package main;

import javax.swing.JFrame;

import panal.PlayPanel;

public class MainFrame extends JFrame {
	public MainFrame() {
		PlayPanel playPanel = new PlayPanel();
		add(playPanel);
		setSize(1000, 700);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}
}
