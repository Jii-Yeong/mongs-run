package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import img.Field;
import img.Person;
import main.MainFrame;
import score.ScorePanel;
import panel.BackgroundPanel;

public class PlayPanel extends JPanel {
	BackgroundPanel background = new BackgroundPanel();
	List<JPanel> fieldList;
	int fieldX = 0;
	
	static int black = new Color(0, 0, 0).getRGB();
	
	public PlayPanel(MainFrame frame) {
		setPreferredSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));
		setLayout(null);
		ScorePanel scorePanel = new ScorePanel();
		Person person = new Person();
		person.setOpaque(false);
		background.setBounds(0, 0, 1000, 700);
		person.setBounds(100, 225, 200, 400);
		person.setBackground(new Color(0, 0, 0, 1));
		background.setLayout(null);
		background.add(person); // 패널에 person을 추가하는게 아니라, background에 person을 추가.
		scorePanel.setBounds(700, 0, 300, 100);
		scorePanel.setBackground(new Color(0, 0, 0, 0));
		background.add(scorePanel);
		add(background);
		JButton btn = new JButton("dkanrjsk");
		btn.setBounds(0, 0, 100, 100);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changeRankPanel();
			}
		});
		background.add(btn);
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(".\\img\\black.png"));
			getBlack(image);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void getBlack(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (image.getRGB(w, h) == black) {
					System.out.println("w" + w);
					System.out.println("h" + h);
					Field field = new Field();
					field.setBounds(w * 17, h * 25, 100, 100);
					background.add(field);
				}
			}
		}
	}
}
