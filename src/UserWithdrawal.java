import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.DefaultButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import kr.co.greenart.dbutil.DBUtil;

// 회원탈퇴 서브창
class UserWithdrawal extends JDialog implements MouseListener {
	private ImageIcon icon2;
	private JPasswordField textField;
	private JPasswordField textField_1;
	private String user_id;
	private boolean isPwSame;
	private boolean isPwSame2;

	public UserWithdrawal(JFrame owner, String user_id) {
		super(owner, true);
		this.user_id = user_id;

		icon2 = new ImageIcon("src/imgs/마이페이지메인.png");
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

		textField_1 = new JPasswordField();
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, textField_1, 130, SpringLayout.NORTH, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, textField_1, -225, SpringLayout.SOUTH, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.EAST, textField_1, -102, SpringLayout.EAST, unsubscribe);
		unsubscribe.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("src/imgs/기존비밀번호.png"));
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 184, SpringLayout.NORTH, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.WEST, lblNewLabel_1, 68, SpringLayout.WEST, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -171, SpringLayout.SOUTH, unsubscribe);
		unsubscribe.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel(new ImageIcon("src/imgs/기존비밀번호.png"));
		sl_unsubscribe.putConstraint(SpringLayout.WEST, textField_1, 8, SpringLayout.EAST, lblNewLabel);
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, lblNewLabel, 130, SpringLayout.NORTH, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.WEST, lblNewLabel, 68, SpringLayout.WEST, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, textField_1);
		sl_unsubscribe.putConstraint(SpringLayout.EAST, lblNewLabel, -278, SpringLayout.EAST, unsubscribe);
		unsubscribe.add(lblNewLabel);

		JButton btnNewButton = new JButton(new ImageIcon("src/imgs/돌아가기투명.png"));
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

		JButton btnNewButton_1 = new JButton(new ImageIcon("src/imgs/구독취소.png"));
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -58, SpringLayout.SOUTH, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.EAST, btnNewButton_1, -18, SpringLayout.WEST, btnNewButton);
		unsubscribe.add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_1.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		

		textField = new JPasswordField(); // 비밀번호 확인
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, btnNewButton_1, 31, SpringLayout.SOUTH, textField);
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, btnNewButton, 41, SpringLayout.SOUTH, textField);
		sl_unsubscribe.putConstraint(SpringLayout.EAST, lblNewLabel_1, -6, SpringLayout.WEST, textField);
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, lblNewLabel_1);
		sl_unsubscribe.putConstraint(SpringLayout.WEST, textField, 114, SpringLayout.WEST, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, textField, -171, SpringLayout.SOUTH, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.EAST, textField, -102, SpringLayout.EAST, unsubscribe);
		unsubscribe.add(textField);
		textField.setColumns(10);
		 
		JLabel lblNewLabel_2 = new JLabel("        탈퇴완료");
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 6, SpringLayout.SOUTH, btnNewButton_1);
		sl_unsubscribe.putConstraint(SpringLayout.WEST, lblNewLabel_2, 88, SpringLayout.WEST, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -37, SpringLayout.SOUTH, unsubscribe);
		unsubscribe.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("돌아가기");
		sl_unsubscribe.putConstraint(SpringLayout.EAST, lblNewLabel_2, -41, SpringLayout.WEST, lblNewLabel_3);
		sl_unsubscribe.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 18, SpringLayout.SOUTH, btnNewButton);
		sl_unsubscribe.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, -37, SpringLayout.SOUTH, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.WEST, lblNewLabel_3, 228, SpringLayout.WEST, unsubscribe);
		sl_unsubscribe.putConstraint(SpringLayout.EAST, lblNewLabel_3, -85, SpringLayout.EAST, unsubscribe);
		unsubscribe.add(lblNewLabel_3);

		setSize(400, 420);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserDaoImp dao = new UserDaoImp();
				Connection conn = null;

				try {

					conn = DBUtil.getConnection();
					String nowpw = dao.getPW(conn, user_id);

					if (textField_1.getText().equals(nowpw)) {
						isPwSame = true;
					} else {
						isPwSame = false;
					}

					if (textField_1.getText().equals(textField.getText())) {
						isPwSame2 = true;
					} else {
						isPwSame2 = false;
					}

					if (isPwSame && isPwSame2) {
						dao.delete(conn, user_id);
						JOptionPane.showMessageDialog(null, "계정이 삭제되었습니다." , "확인" , 
								JOptionPane.DEFAULT_OPTION);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다." , "확인" , 
								JOptionPane.DEFAULT_OPTION);
					}

				} catch (SQLException e1) {

				} finally {
					DBUtil.closeConn(conn);
				}
			}
		});

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
