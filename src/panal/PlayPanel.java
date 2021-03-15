package panal;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import img.Person;

public class PlayPanel extends JPanel {
	BackgroundPanel background = new BackgroundPanel();
	public PlayPanel() {
		setPreferredSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));
		Person person = new Person();
		add(person);
		add(background);
	}
}
