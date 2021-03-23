package panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.MainFrame;

public class FrontPanel extends JPanel {
	public FrontPanel(MainFrame frame) {
		// 배경 이미지
		ImageIcon img = new ImageIcon(".\\img\\start_background.png");
		
		JLabel lblBackground = new JLabel();
		lblBackground.setBounds(0, 0, 1000, 700);
		lblBackground.setHorizontalAlignment(JLabel.CENTER);
		lblBackground.setIcon(img);
		add(lblBackground);
		setLayout(null);
		// 패널 사이즈 & 옵션
		setSize(1000, 700);
		setVisible(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				frame.changeStartPanel();
			}
		});
	}
}
