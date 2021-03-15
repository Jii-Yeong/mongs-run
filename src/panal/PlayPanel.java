package panal;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import img.Person;

public class PlayPanel extends JPanel {
	public PlayPanel() {
		setPreferredSize(new Dimension(750, 450));
		setMaximumSize(new Dimension(750, 450));
		Person person = new Person();
		add(person);
		
	}
}
