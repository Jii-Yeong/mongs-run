package img;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Person extends JLabel {
	
	public Person() {
		ImageIcon img = new ImageIcon(".\\img\\Person.gif");
		Image im = img.getImage();
		setIcon(img);
	}
}
