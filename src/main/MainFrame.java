package main;



import java.awt.Container;

import javax.swing.JFrame;

import panal.PlayPanel;
import panal.BackgroundPanel;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		PlayPanel playPanel = new PlayPanel();
		getContentPane().add(playPanel);
		setSize(1000, 700);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}
}
