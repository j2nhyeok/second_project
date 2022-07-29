package ManagerLogin;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ManagerLogin extends JFrame implements MouseListener {
	private ImageIcon icon;
	private JTextField textField;
	private JTextField textField_1;

	public ManagerLogin() {

		icon = new ImageIcon("src/img/관리자메인.png");
		JPanel pnl = new JPanel() {
			public void paintComponent(Graphics g) {
				setTitle("Management");
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		getContentPane().add(pnl);
		SpringLayout sl_pnl = new SpringLayout();
		pnl.setLayout(sl_pnl);

		JButton btnNewButton = new JButton(new ImageIcon("src/img/닫기버튼.png"));
		btnNewButton.addActionListener(new ActionListener() { // 돌아가기 버튼
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sl_pnl.putConstraint(SpringLayout.NORTH, btnNewButton, 281, SpringLayout.NORTH, pnl);
		sl_pnl.putConstraint(SpringLayout.SOUTH, btnNewButton, -45, SpringLayout.SOUTH, pnl);
		btnNewButton.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton.setFocusPainted(false);
		sl_pnl.putConstraint(SpringLayout.EAST, btnNewButton, -106, SpringLayout.EAST, pnl);
		pnl.add(btnNewButton);

		JButton btnNewButton_1 = new JButton(new ImageIcon("src/img/관리자로그인.png"));

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = textField_1.getText().trim();
				String pw = textField.getText().trim();

				if (id.length() == 0 || pw.length() == 0) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호 입력해주세요", "아이디나 비번을 입력해주세요",
							JOptionPane.DEFAULT_OPTION);
				} else if (id.equals("6조") && pw.equals("0000")) {
					JOptionPane.showMessageDialog(null, "로그인 성공", "확인", JOptionPane.DEFAULT_OPTION);
					Management dialog = new Management(ManagerLogin.this); // 관리자페이지 서브창 생성
					dialog.show();
				} else {
					JOptionPane.showConfirmDialog(null, "아이디 또는 비밀번호 확인해주세요", "재입력", JOptionPane.DEFAULT_OPTION);
				}
			}
		});

		sl_pnl.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -45, SpringLayout.SOUTH, pnl);
		btnNewButton_1.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_1.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_1.setFocusPainted(false);
		sl_pnl.putConstraint(SpringLayout.WEST, btnNewButton, 31, SpringLayout.EAST, btnNewButton_1);
		sl_pnl.putConstraint(SpringLayout.WEST, btnNewButton_1, 103, SpringLayout.WEST, pnl);
		sl_pnl.putConstraint(SpringLayout.EAST, btnNewButton_1, -214, SpringLayout.EAST, pnl);
		pnl.add(btnNewButton_1);

		textField_1 = new JTextField(); // 아이디 입력란 텍스트필드
		sl_pnl.putConstraint(SpringLayout.NORTH, textField_1, 149, SpringLayout.NORTH, pnl);
		sl_pnl.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, btnNewButton_1);
		sl_pnl.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, btnNewButton);
		pnl.add(textField_1);
		textField_1.setColumns(10);

		textField = new JPasswordField(); // 비밀번호 입력 텍스트필드
		sl_pnl.putConstraint(SpringLayout.SOUTH, textField_1, -45, SpringLayout.NORTH, textField);
		sl_pnl.putConstraint(SpringLayout.NORTH, btnNewButton_1, 21, SpringLayout.SOUTH, textField);
		sl_pnl.putConstraint(SpringLayout.NORTH, textField, -134, SpringLayout.SOUTH, pnl);
		sl_pnl.putConstraint(SpringLayout.WEST, textField, 103, SpringLayout.WEST, pnl);
		sl_pnl.putConstraint(SpringLayout.SOUTH, textField, -21, SpringLayout.NORTH, btnNewButton);
		sl_pnl.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, btnNewButton);
		pnl.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel(new ImageIcon("src/img/아이디.png"));
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel, -31, SpringLayout.NORTH, textField_1);
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, btnNewButton_1);
		sl_pnl.putConstraint(SpringLayout.SOUTH, lblNewLabel, -6, SpringLayout.NORTH, textField_1);
		pnl.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("src/img/비번치라.png")); // 비번
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel_1, -31, SpringLayout.NORTH, textField);
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel_1, 103, SpringLayout.WEST, pnl);
		sl_pnl.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -6, SpringLayout.NORTH, textField);
		pnl.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("    Login");
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 6, SpringLayout.SOUTH, btnNewButton_1);
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel_2, 10, SpringLayout.WEST, btnNewButton_1);
		sl_pnl.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 21, SpringLayout.SOUTH, btnNewButton_1);
		sl_pnl.putConstraint(SpringLayout.EAST, lblNewLabel_2, -10, SpringLayout.EAST, btnNewButton_1);
		pnl.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("        EXIT");
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 6, SpringLayout.SOUTH, btnNewButton);
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, btnNewButton);
		sl_pnl.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, 21, SpringLayout.SOUTH, btnNewButton);
		sl_pnl.putConstraint(SpringLayout.EAST, lblNewLabel_3, 57, SpringLayout.WEST, btnNewButton);
		pnl.add(lblNewLabel_3);

		setResizable(false);
		setSize(400, 400);
		setLocationRelativeTo(null);// 창이 가운데 나오게
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ManagerLogin().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
