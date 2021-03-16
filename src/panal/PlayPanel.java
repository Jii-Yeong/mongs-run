package panal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import img.Person;
import main.MainFrame;

public class PlayPanel extends JPanel implements Runnable {
	Person person;
	BackgroundPanel background = new BackgroundPanel();
	int personY = 0;
	Thread t;
	public PlayPanel(MainFrame frame) {
		setPreferredSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));
		setLayout(null);
		person = new Person();
		person.setOpaque(false);
		background.setBounds(0, 0, 1000, 700);
		person.setBounds(100, 100, 200, 400);
		person.setBackground(new Color(0, 0, 0, 1));
		JButton btn = new JButton("일단 버튼");
		btn.setBounds(0, 0, 100, 100);
		background.setLayout(null);
		background.add(person); // 패널에 person을 추가하는게 아니라, background에 person을 추가.
		add(background);
		background.add(btn);
		t = new Thread(this);
		t.start();
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				person.setBounds(100, 200, 200, 400);
				personY -= 1;
				
			}
		});
		
	}
	@Override
	public void run() {
		while (true) {
			gravity();
		}
	}
	
	public void gravity() {
		person.setBounds(100, personY, 200, 400);
		personY += 1;
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
