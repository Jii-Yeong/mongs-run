package panal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import img.Field;
import img.Person;
import main.MainFrame;

public class PlayPanel extends JPanel {
	BackgroundPanel background = new BackgroundPanel();
	Field field = new Field();
	List<JPanel> fieldList;
	int fieldX = 0;
	
	public PlayPanel(MainFrame frame) {
		setPreferredSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));
		setLayout(null);
		Person person = new Person();
		person.setOpaque(false);
		background.setBounds(0, 0, 1000, 700);
		person.setBounds(100, 200, 200, 400);
		person.setBackground(new Color(0, 0, 0, 1));
		background.setLayout(null);
		background.add(person); // 패널에 person을 추가하는게 아니라, background에 person을 추가.
		add(background);
		field.setBounds(0, 512, 150, 150);
		background.add(field);
		fieldList = new ArrayList<>();
		JButton btn = new JButton("dkanrjsk");
		btn.setBounds(0, 0, 100, 100);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changeRankPanel();
			}
		});
		background.add(btn);
		
		for (int i = 0; i < 10; i++) { // 땅 채우기
			fieldList.add(new Field());
			fieldList.get(i).setBounds(fieldX, 512, 150, 150);
			fieldX += 150;
			background.add(fieldList.get(i));
			
			if (i == 3) { // 땅 비우는 if문, 조건문 값 수정
				fieldX += 150;
				continue;
			}
		}
	}
}
