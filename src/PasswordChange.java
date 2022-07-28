import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;

import kr.co.greenart.dbutil.DBUtil;
// 비번변경 서브창
class PasswordChange extends JDialog implements MouseListener {
	private ImageIcon icon1;
	private JPasswordField newpwT1;
	private JPasswordField newpwT2;
	private JPasswordField nowpwT;
	private UserDaoImp dao = new UserDaoImp();
	private boolean isSamePw;
	private String user_id;

	public PasswordChange(JFrame owner, String user_id) {
		super(owner, true);
		this.user_id = user_id;

		icon1 = new ImageIcon("src/imgs/마이페이지메인.png");
		JPanel passwordpnl = new JPanel() {
			public void paintComponent(Graphics g) {
				setTitle("Password Change");
				g.drawImage(icon1.getImage(), 0, 0, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		SpringLayout sl_passwordpnl = new SpringLayout();
		passwordpnl.setLayout(sl_passwordpnl);

		JButton btnNewButton_2 = new JButton(new ImageIcon("src/imgs/비밀번호변경완료.png")); // 변경하기 버튼
		sl_passwordpnl.putConstraint(SpringLayout.SOUTH, btnNewButton_2, -43, SpringLayout.SOUTH, passwordpnl);
		btnNewButton_2.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_2.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_2.setFocusPainted(false);
		passwordpnl.add(btnNewButton_2);

		JButton btnNewButton_1 = new JButton(new ImageIcon("src/imgs/돌아가기투명.png"));
		sl_passwordpnl.putConstraint(SpringLayout.EAST, btnNewButton_2, -20, SpringLayout.WEST, btnNewButton_1);
		sl_passwordpnl.putConstraint(SpringLayout.NORTH, btnNewButton_1, 260, SpringLayout.NORTH, passwordpnl);
		sl_passwordpnl.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -55, SpringLayout.SOUTH, passwordpnl);

		btnNewButton_1.addActionListener(new ActionListener() { // 돌아가기 버튼
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sl_passwordpnl.putConstraint(SpringLayout.WEST, btnNewButton_1, 204, SpringLayout.WEST, passwordpnl);
		btnNewButton_1.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_1.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_1.setFocusPainted(false);
		passwordpnl.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("src/imgs/기존비밀번호.png"));
		sl_passwordpnl.putConstraint(SpringLayout.WEST, lblNewLabel_1, 78, SpringLayout.WEST, passwordpnl);
		passwordpnl.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(new ImageIcon("src/imgs/변경할비밀번호.png"));
		passwordpnl.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel(new ImageIcon("src/imgs/변경할비밀번호.png"));
		sl_passwordpnl.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		sl_passwordpnl.putConstraint(SpringLayout.WEST, lblNewLabel, 78, SpringLayout.WEST, passwordpnl);
		sl_passwordpnl.putConstraint(SpringLayout.SOUTH, lblNewLabel, -156, SpringLayout.SOUTH, passwordpnl);
		sl_passwordpnl.putConstraint(SpringLayout.NORTH, btnNewButton_2, 25, SpringLayout.SOUTH, lblNewLabel);
		passwordpnl.add(lblNewLabel);

		nowpwT = new JPasswordField();
		sl_passwordpnl.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, nowpwT);
		sl_passwordpnl.putConstraint(SpringLayout.EAST, lblNewLabel_1, -6, SpringLayout.WEST, nowpwT);
		sl_passwordpnl.putConstraint(SpringLayout.NORTH, nowpwT, 113, SpringLayout.NORTH, passwordpnl);
		sl_passwordpnl.putConstraint(SpringLayout.WEST, nowpwT, 124, SpringLayout.WEST, passwordpnl);
		sl_passwordpnl.putConstraint(SpringLayout.EAST, nowpwT, -104, SpringLayout.EAST, passwordpnl);
		sl_passwordpnl.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 0, SpringLayout.SOUTH, nowpwT);
		passwordpnl.add(nowpwT);
		nowpwT.setColumns(10);

		newpwT1 = new JPasswordField(); // 새 비밀번호
		sl_passwordpnl.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 0, SpringLayout.NORTH, newpwT1);
		sl_passwordpnl.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 0, SpringLayout.SOUTH, newpwT1);
		sl_passwordpnl.putConstraint(SpringLayout.EAST, lblNewLabel_2, -6, SpringLayout.WEST, newpwT1);
		sl_passwordpnl.putConstraint(SpringLayout.SOUTH, nowpwT, -19, SpringLayout.NORTH, newpwT1);
		sl_passwordpnl.putConstraint(SpringLayout.SOUTH, newpwT1, -201, SpringLayout.SOUTH, passwordpnl);
		sl_passwordpnl.putConstraint(SpringLayout.NORTH, newpwT1, 156, SpringLayout.NORTH, passwordpnl);
		sl_passwordpnl.putConstraint(SpringLayout.WEST, newpwT1, 124, SpringLayout.WEST, passwordpnl);
		sl_passwordpnl.putConstraint(SpringLayout.EAST, newpwT1, -104, SpringLayout.EAST, passwordpnl);
		passwordpnl.add(newpwT1);
		newpwT1.setColumns(10);

		newpwT2 = new JPasswordField();
		sl_passwordpnl.putConstraint(SpringLayout.NORTH, newpwT2, 21, SpringLayout.SOUTH, newpwT1);
		sl_passwordpnl.putConstraint(SpringLayout.SOUTH, newpwT2, -25, SpringLayout.NORTH, btnNewButton_2);
		sl_passwordpnl.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, newpwT2);
		sl_passwordpnl.putConstraint(SpringLayout.WEST, newpwT2, 124, SpringLayout.WEST, passwordpnl);
		sl_passwordpnl.putConstraint(SpringLayout.EAST, newpwT2, -104, SpringLayout.EAST, passwordpnl);
		passwordpnl.add(newpwT2);
		newpwT2.setColumns(10);

		getContentPane().add(passwordpnl);
		
		JLabel lblNewLabel_3 = new JLabel("돌아가기");
		sl_passwordpnl.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 16, SpringLayout.SOUTH, btnNewButton_1);
		sl_passwordpnl.putConstraint(SpringLayout.EAST, lblNewLabel_3, 0, SpringLayout.EAST, nowpwT);
		passwordpnl.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(" 변경완료");
		sl_passwordpnl.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 0, SpringLayout.NORTH, lblNewLabel_3);
		sl_passwordpnl.putConstraint(SpringLayout.WEST, lblNewLabel_4, -17, SpringLayout.WEST, nowpwT);
		sl_passwordpnl.putConstraint(SpringLayout.EAST, lblNewLabel_4, 48, SpringLayout.WEST, nowpwT);
		passwordpnl.add(lblNewLabel_4);
		setSize(400, 420);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 현재 비밀번호와 기존 회원의 비밀번호가 일치하면 true;
				// 새 비밀번호1과 새 비밀번호 2가 일치하면 true
				// 둘 다 true 일 때 업데이트 ㄱㄱ
				Connection conn = null;
				WorkingLogin wl = new WorkingLogin();
				try {
					conn = DBUtil.getConnection();
				

					if (wl.isPassWordSame(newpwT1.getText(), newpwT2.getText())) {
						isSamePw = true;
					}

					if (isSamePw) {
						dao.pwChange(conn, newpwT1.getText(), user_id);
						JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다." , "확인" , 
								JOptionPane.DEFAULT_OPTION);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지않습니다." , "확인" , 
								JOptionPane.DEFAULT_OPTION);
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
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
