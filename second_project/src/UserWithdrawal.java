import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

// 회원탈퇴 서브창
class UserWithdrawal extends JDialog implements MouseListener {
	private ImageIcon icon2;
	private JTextField textField;
	private JTextField textField_1;
	
	
	public UserWithdrawal(JFrame owner) {
		super(owner, true);
		
		icon2 = new ImageIcon("src/img/마이페이지메인.png");
		JPanel unsubscribe = new JPanel() {
			public void paintComponent(Graphics g) {
				setTitle("Unsubscribe");
				g.drawImage(icon2.getImage(), 0, 0, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		
		getContentPane().add(unsubscribe);
		SpringLayout sl_unsubscribe = new SpringLayout();
		unsubscribe.setLayout(sl_unsubscribe);
		
		textField_1 = new JTextField("1번");
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, textField_1, 130, SpringLayout.NORTH, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.EAST, textField_1, -102, SpringLayout.EAST, unsubscribe);
		unsubscribe.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("src/img/기존비밀번호.png"));
		sl_unsubscribe.putConstraint(SpringLayout.WEST, lblNewLabel_1, 68, SpringLayout.WEST, unsubscribe);
		unsubscribe.add(lblNewLabel_1);
		
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("src/img/기존비밀번호.png"));
		sl_unsubscribe.putConstraint(SpringLayout.WEST, textField_1, 8, SpringLayout.EAST, lblNewLabel);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, textField_1);
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, textField_1);
		sl_unsubscribe.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblNewLabel_1);
		sl_unsubscribe.putConstraint(SpringLayout.EAST, lblNewLabel, -278, SpringLayout.EAST, unsubscribe);
		unsubscribe.add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton(new ImageIcon("src/img/돌아가기투명.png"));
		sl_unsubscribe.putConstraint(SpringLayout.WEST, btnNewButton, 206, SpringLayout.WEST, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, btnNewButton, -70, SpringLayout.SOUTH, unsubscribe);
		btnNewButton.addActionListener(new ActionListener() { // 돌아가기 버튼
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		unsubscribe.add(btnNewButton);
		btnNewButton.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton.setFocusPainted(false);
		
		JButton btnNewButton_1 = new JButton(new ImageIcon("src/img/구독취소.png"));
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, btnNewButton_1, 31, SpringLayout.SOUTH, lblNewLabel_1);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -58, SpringLayout.SOUTH, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.EAST, btnNewButton_1, -18, SpringLayout.WEST, btnNewButton);
		unsubscribe.add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_1.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_1.setFocusPainted(false);
		
		textField = new JTextField("2번");
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, btnNewButton, 41, SpringLayout.SOUTH, textField);
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, textField);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 0, SpringLayout.SOUTH, textField);
		sl_unsubscribe.putConstraint(SpringLayout.EAST, lblNewLabel_1, -6, SpringLayout.WEST, textField);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, textField_1, -30, SpringLayout.NORTH, textField);
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, textField, 190, SpringLayout.NORTH, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, textField, -171, SpringLayout.SOUTH, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.WEST, textField, 124, SpringLayout.WEST, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.EAST, textField, -102, SpringLayout.EAST, unsubscribe);
		unsubscribe.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("비밀번호 변경");
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 6, SpringLayout.SOUTH, btnNewButton_1);
		sl_unsubscribe.putConstraint(SpringLayout.WEST, lblNewLabel_2, 101, SpringLayout.WEST, unsubscribe);
		unsubscribe.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("회원탈퇴");
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 0, SpringLayout.NORTH, lblNewLabel_2);
		sl_unsubscribe.putConstraint(SpringLayout.WEST, lblNewLabel_3, 67, SpringLayout.EAST, lblNewLabel_2);
		unsubscribe.add(lblNewLabel_3);
		
		setSize(400,420);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
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
