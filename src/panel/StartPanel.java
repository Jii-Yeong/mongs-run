package panel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import main.MainFrame;

public class StartPanel extends JPanel {
	public SelectPanel selectPanel;
	
	public StartPanel(MainFrame frame) {
		// 배경 이미지
		ImageIcon img = new ImageIcon(".\\img\\background\\test.png");
		
		// 손모양 커서
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		
		// 배경 레이블 설정
		JLabel lblBackground = new JLabel();
		lblBackground.setBounds(0, 0, 1000, 700);
		lblBackground.setHorizontalAlignment(JLabel.CENTER);
		lblBackground.setIcon(img);
		lblBackground.setCursor(cursor);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.changeSelectPanel();
			}
		});
		add(lblBackground);

		
		// 패널 사이즈 & 옵션
		setSize(1000, 700);
		setVisible(true);
	}
}