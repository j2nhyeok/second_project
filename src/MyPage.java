import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import kr.co.greenart.dbutil.DBUtil;

public class MyPage extends JFrame implements MouseListener {
	private ImageIcon icon;
	private String user_id;

	public MyPage(String user_id) {
		super("MyPage");
		this.user_id = user_id;

		icon = new ImageIcon("src/imgs/마이페이지메인.png");
		JPanel pnl = new JPanel() {
			public void paintComponent(Graphics g) {
				setTitle("MyPage");
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		getContentPane().add(pnl);
		SpringLayout sl_pnl = new SpringLayout();
		pnl.setLayout(sl_pnl);

		JButton btnNewButton_1 = new JButton(new ImageIcon("src/imgs/탈퇴할래.png"));
		sl_pnl.putConstraint(SpringLayout.EAST, btnNewButton_1, -111, SpringLayout.EAST, pnl);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserWithdrawal dialog = new UserWithdrawal(MyPage.this, user_id); // 비번변경 서브창 생성
				dialog.show();
			}
		});
		btnNewButton_1.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_1.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_1.setFocusPainted(false);
		pnl.add(btnNewButton_1);

		JButton btnNewButton = new JButton(new ImageIcon("src/imgs/패스워드변경.png"));
		sl_pnl.putConstraint(SpringLayout.WEST, btnNewButton_1, 31, SpringLayout.EAST, btnNewButton);
		sl_pnl.putConstraint(SpringLayout.WEST, btnNewButton, 112, SpringLayout.WEST, pnl);
		sl_pnl.putConstraint(SpringLayout.SOUTH, btnNewButton, -57, SpringLayout.SOUTH, pnl);
		sl_pnl.putConstraint(SpringLayout.EAST, btnNewButton, -212, SpringLayout.EAST, pnl);
		sl_pnl.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, btnNewButton);
		sl_pnl.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -1, SpringLayout.SOUTH, btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PasswordChange dialog = new PasswordChange(MyPage.this, user_id); // 비번변경 서브창 생성
				dialog.show();
			}
		});
		btnNewButton.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton.setFocusPainted(false);
		pnl.add(btnNewButton);

		JLabel lblNewLabel = new JLabel(new ImageIcon("src/imgs/이름아이콘.png"));
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel, 114, SpringLayout.NORTH, pnl);
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel, 77, SpringLayout.WEST, pnl);
		pnl.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("src/imgs/아이디아이콘.png"));
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel_1, 77, SpringLayout.WEST, pnl);
		sl_pnl.putConstraint(SpringLayout.SOUTH, lblNewLabel, -36, SpringLayout.NORTH, lblNewLabel_1);
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 174, SpringLayout.NORTH, pnl);
		pnl.add(lblNewLabel_1);

		JLabel label = new JLabel(findUsersName(user_id));
		sl_pnl.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, lblNewLabel);
		sl_pnl.putConstraint(SpringLayout.WEST, label, 6, SpringLayout.EAST, lblNewLabel);
		sl_pnl.putConstraint(SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, lblNewLabel);
		sl_pnl.putConstraint(SpringLayout.EAST, label, 156, SpringLayout.EAST, lblNewLabel);
		pnl.add(label);

		JLabel lblNewLabel_2 = new JLabel(user_id);
		sl_pnl.putConstraint(SpringLayout.NORTH, btnNewButton, 24, SpringLayout.SOUTH, lblNewLabel_2);
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 0, SpringLayout.NORTH, lblNewLabel_1);
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel_2, 6, SpringLayout.EAST, lblNewLabel_1);
		sl_pnl.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 0, SpringLayout.SOUTH, lblNewLabel_1);
		sl_pnl.putConstraint(SpringLayout.EAST, lblNewLabel_2, 0, SpringLayout.EAST, label);
		pnl.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(" 비밀번호변경");
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 6, SpringLayout.SOUTH, btnNewButton);
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel_3, -14, SpringLayout.WEST, btnNewButton);
		sl_pnl.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, 30, SpringLayout.SOUTH, btnNewButton);
		pnl.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("회원탈퇴");
		sl_pnl.putConstraint(SpringLayout.EAST, lblNewLabel_3, -24, SpringLayout.WEST, lblNewLabel_4);
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel_4, 213, SpringLayout.WEST, pnl);
		sl_pnl.putConstraint(SpringLayout.EAST, lblNewLabel_4, -111, SpringLayout.EAST, pnl);
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 6, SpringLayout.SOUTH, btnNewButton_1);
		sl_pnl.putConstraint(SpringLayout.SOUTH, lblNewLabel_4, 30, SpringLayout.SOUTH, btnNewButton_1);
		pnl.add(lblNewLabel_4);

		setLocationRelativeTo(null);// 창이 가운데 나오게
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 380);
		setVisible(true);
	}

	private String findUsersName(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = "";
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT user_name FROM users WHERE user_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("user_name");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeRS(rs);
			DBUtil.closeRS(rs);
		}
		return name;

	}

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
