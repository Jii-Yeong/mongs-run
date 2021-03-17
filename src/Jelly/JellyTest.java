package Jelly;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JellyTest extends JPanel {

	static int red = new Color(237, 28, 36).getRGB();
	
	public JellyTest() {
		File mapJelly = new File(".\\map.png");
		BufferedImage image = null;
		try {
			image = ImageIO.read(mapJelly);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}