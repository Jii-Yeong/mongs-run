package panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import main.MainFrame;

import java.awt.Font;

public class SelectPanel extends JPanel {
	// 시작 버튼
	private JButton btnStart;
	
	// 캐릭터 선택 버튼
	private JButton btnIcon1;
	private JButton btnIcon2;
	private JButton btnIcon3;
	private JButton btnIcon4;

	// 커서
	private Cursor cursor;

	// 생성자
	public SelectPanel(MainFrame frame) {
		
		// 시작 버튼, 배경 이미지
		ImageIcon imgStart = new ImageIcon(".\\img\\button\\start_btn.png");
		ImageIcon imgBackground = new ImageIcon(".\\img\\background\\test.png");
		
		// 캐릭터 이미지
		ImageIcon btnCharacter1 = new ImageIcon(".\\img\\character\\char1.png");
		ImageIcon btnCharacter2 = new ImageIcon(".\\img\\character\\char2.png");
		ImageIcon btnCharacter3 = new ImageIcon(".\\img\\character\\char3.png");
		ImageIcon btnCharacter4 = new ImageIcon(".\\img\\character\\char4.png");
		
		// 커서 객체 생성
		cursor = new Cursor(Cursor.HAND_CURSOR);
		
		// 선택 버튼 이미지
		JLabel lblSelected = new JLabel("SELECTED");
		lblSelected.setFont(new Font("Yu Gothic", Font.BOLD, 18));
		lblSelected.setForeground(Color.WHITE);
		lblSelected.setBackground(new Color(255, 200, 44, 220));
		lblSelected.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelected.setBounds(55, 292, 176, 52);
		lblSelected.setOpaque(true);
		lblSelected.setVisible(false);
		add(lblSelected);
		
		
		// 시작 버튼
		btnStart = new JButton(imgStart);
		btnStart.setBounds(333, 500, 300, 106);
		setButton(btnStart);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.changePlayPanel();
			}
		});
		add(btnStart);
		
		// 캐릭터 선택 버튼 1 ~ 4
		btnIcon1 = new JButton(btnCharacter1);
		btnIcon1.setBounds(40, 155, 204, 298);
		setButton(btnIcon1);
		add(btnIcon1);
		btnIcon1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblSelected.setVisible(true);
				lblSelected.setBounds(55, 300, 176, 52);
			}
		});

		btnIcon2 = new JButton(btnCharacter2);
		btnIcon2.setBounds(284, 155, 204, 298);
		setButton(btnIcon2);
		add(btnIcon2);
		btnIcon2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblSelected.setVisible(true);
				lblSelected.setBounds(300, 300, 176, 52);
			}
		});

		btnIcon3 = new JButton(btnCharacter3);
		btnIcon3.setBounds(521, 155, 204, 298);
		setButton(btnIcon3);
		add(btnIcon3);
		btnIcon3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblSelected.setVisible(true);
				lblSelected.setBounds(530, 300, 176, 52);
			}
		});
		
		btnIcon4 = new JButton(btnCharacter4);
		btnIcon4.setBounds(757, 155, 204, 298);
		setButton(btnIcon4);
		add(btnIcon4);
		btnIcon4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblSelected.setVisible(true);
				lblSelected.setBounds(770, 300, 176, 52);
			}
		});
		
		// 불투명 배경
		JLabel lblBackground2 = new JLabel();
		lblBackground2.setBackground(new Color(0, 0, 0, 190));
		lblBackground2.setBounds(0, 0, 1000, 700);
		lblBackground2.setOpaque(true);
		lblBackground2.setHorizontalAlignment(JLabel.CENTER);
		add(lblBackground2);
		
		// 기본 배경
		JLabel lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1000, 700);
		lblBackground.setHorizontalAlignment(JLabel.CENTER);
		add(lblBackground);
		

		// 패널 사이즈 & 옵션
		setSize(1000, 750);
		setLayout(null);
		setVisible(true);
	}
	
	// 버튼 기본 설정 메소드
	private void setButton(JButton btn) {
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
		btn.setCursor(cursor);
	}
}