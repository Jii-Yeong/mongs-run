package img;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackTest extends JFrame {
	private ImageIcon img = new ImageIcon(".\\img\\bg1.png");
	int w;
	int h;
	double ratio;
	int imageWidth;
    int imageHeight;
	
	public BackTest() {
		JLabel lbl = new JLabel();
		
//		pnl = new JPanel();
//		pnl.getSize();
		
//		add(getContentPane());
		lbl.setIcon(img);
		lbl.setSize(getContentPane().getSize());
		lbl.setLocation(0, 0);
		getContentPane().add(lbl);
		
		
		
		setSize(1000, 700);
//		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		BackTest frame = new BackTest();
	}
}
