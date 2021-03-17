package panel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

import main.MainFrame;
import java.awt.Font;

public class StartPanel extends JPanel {
	public SelectPanel selectPanel;
	private JTextField tfdName;

	private Cursor cursor;
	
	public StartPanel(MainFrame frame) {
		// 배경 이미지
		ImageIcon img = new ImageIcon(".\\img\\background\\test.png");
		
		// 시작 버튼, 랭킹 버튼
		ImageIcon imgStart = new ImageIcon(".\\img\\button\\btn_start.png");
		ImageIcon imgRank = new ImageIcon(".\\img\\button\\btn_rank.png");
		
		// 손모양 커서
		cursor = new Cursor(Cursor.HAND_CURSOR);
		
		// 배경 레이블 설정
		JLabel lblBackground = new JLabel();
		lblBackground.setBounds(0, 0, 1000, 700);
		lblBackground.setHorizontalAlignment(JLabel.CENTER);
		lblBackground.setIcon(img);
		lblBackground.setCursor(cursor);
		setLayout(null);
		
		// 이름 입력란
		tfdName = new JTextField();
		tfdName.setBounds(490, 283, 212, 35);
		tfdName.setColumns(10);
		
		tfdName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int length = ((JTextComponent) e.getSource()).getText().length();
				if (length > 5) {
					e.consume();
				}
				super.keyTyped(e);
			}
		});
		
		
		// 게임 시작 버튼 - SelectPanel 전환
		JButton btnStart = new JButton(imgStart);
		btnStart.setBounds(304, 396, 197, 80);
		setButton(btnStart);
		add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfdName.getText().contains(" ")) {
					JOptionPane.showMessageDialog(null, "공백이 포함되었습니다", "오류", JOptionPane.WARNING_MESSAGE);
				} else if (tfdName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요", "오류", JOptionPane.WARNING_MESSAGE);
				} else {
					frame.changeSelectPanel();
				}
			}
		});
		
		
		// 랭킹 버튼 - RankPanel 전환
		JButton btnRanking = new JButton(imgRank);
		btnRanking.setBounds(513, 396, 197, 80);
		setButton(btnRanking);
		add(btnRanking);
		btnRanking.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changeRankPanel();
			}
		});
		add(tfdName);
		
		// 이름 라벨
		JLabel lblName = new JLabel("닉네임");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("함초롬돋움", Font.BOLD, 25));
		lblName.setBackground(Color.WHITE);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(319, 283, 147, 34);
		add(lblName);
		
		
		JLabel lblBackground2 = new JLabel();
		lblBackground2.setBounds(0, 155, 1000, 430);
		lblBackground2.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackground2.setBackground(new Color(0, 0, 0, 170));
		lblBackground2.setOpaque(true);
		add(lblBackground2);
		add(lblBackground);

		
		// 패널 사이즈 & 옵션
		setSize(1000, 700);
		setVisible(true);
	}
	
	// 버튼 기본 설정 메소드
	private void setButton(JButton btn) {
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
		btn.setCursor(cursor);
	}
	
	public JTextField getTfdName() {
		return tfdName;
	}

	public void setTfdName(JTextField tfdName) {
		this.tfdName = tfdName;
	}
}