
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import kr.co.greenart.dbutil.DBUtil;

public class LoginGUI extends JFrame implements MouseListener {
	JScrollPane scrollPane;
	private ImageIcon icon;
	private JTextField jPWT;
	private JTextField jIDT;
	private UserDaoImp dao = new UserDaoImp();
	private boolean isID;
	private boolean isPW;

	public LoginGUI() {

		icon = new ImageIcon("src/img/실험3.png");

		// 배경 Panel 생성후 컨텐츠페인으로 지정
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				setTitle("Water Melon");
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		SpringLayout sl_background = new SpringLayout();
		background.setLayout(sl_background);

		JButton button = new JButton(new ImageIcon("src/img/로그인1.png")); // 로그인
		sl_background.putConstraint(SpringLayout.WEST, button, 63, SpringLayout.WEST, background);
		sl_background.putConstraint(SpringLayout.EAST, button, -49, SpringLayout.EAST, background);
		button.setForeground(Color.darkGray);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		background.add(button);

		JButton button1 = new JButton(new ImageIcon("src/img/회원가입.png"));
		sl_background.putConstraint(SpringLayout.SOUTH, button, -6, SpringLayout.NORTH, button1);
		
				sl_background.putConstraint(SpringLayout.SOUTH, button1, -52, SpringLayout.SOUTH, background);
		sl_background.putConstraint(SpringLayout.NORTH, button1, 360, SpringLayout.NORTH, background);

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUpGUI dialog = new SignUpGUI(LoginGUI.this);
				dialog.show();
			}
		});

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;

				try {
					conn = DBUtil.getConnection();
					String rs = dao.getPW(conn, jIDT.getText());
					User result = dao.readPW(conn, jPWT.getText());
					if (result != null && rs.equals(jPWT.getText())) {
						isPW = true;
					} else {
						isPW = false;
					}

					User result2 = dao.readID(conn, jIDT.getText());
					if (result2 != null) {
						isID = true;
					} else {
						isID = false;
					}

					if (isID == true && isPW == true) {
						JOptionPane.showMessageDialog(null, "로그인 성공  \n Welcome to 19Century", "확인",
								JOptionPane.DEFAULT_OPTION);
						StreamingMain cdialog = new StreamingMain(jIDT.getText());
						cdialog.show();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 확인하세요", "확인", JOptionPane.DEFAULT_OPTION);
					}

				} catch (SQLException e1) {

					e1.printStackTrace();
				} finally {
					DBUtil.closeConn(conn);

				}

			}
		});
		sl_background.putConstraint(SpringLayout.WEST, button1, 223, SpringLayout.WEST, background);
		sl_background.putConstraint(SpringLayout.EAST, button1, -60, SpringLayout.EAST, background);
		button1.setFont(new Font("Arial", Font.BOLD, 12));
		button1.setBorderPainted(false); // 버튼외곽선 없애줌
		button1.setContentAreaFilled(false); // 버튼 투명하게설정
		button1.setFocusPainted(false);
		background.add(button1);
		scrollPane = new JScrollPane(background);

		jPWT = new JPasswordField();
		sl_background.putConstraint(SpringLayout.NORTH, button, 16, SpringLayout.SOUTH, jPWT);
		sl_background.putConstraint(SpringLayout.NORTH, jPWT, 262, SpringLayout.NORTH, background);
		sl_background.putConstraint(SpringLayout.SOUTH, jPWT, -150, SpringLayout.SOUTH, background);
		background.add(jPWT);
		jPWT.setColumns(10);

		jIDT = new JTextField();
		sl_background.putConstraint(SpringLayout.WEST, jPWT, 0, SpringLayout.WEST, jIDT);
		sl_background.putConstraint(SpringLayout.EAST, jPWT, 0, SpringLayout.EAST, jIDT);
		sl_background.putConstraint(SpringLayout.WEST, jIDT, 109, SpringLayout.WEST, background);
		sl_background.putConstraint(SpringLayout.EAST, jIDT, -95, SpringLayout.EAST, background);
		background.add(jIDT);
		jIDT.setColumns(10);

		JLabel lblNewLabel = new JLabel(new ImageIcon("src/img/아이디1.png"));
		sl_background.putConstraint(SpringLayout.WEST, lblNewLabel, 66, SpringLayout.WEST, background);
		sl_background.putConstraint(SpringLayout.SOUTH, lblNewLabel, -256, SpringLayout.SOUTH, background);
		sl_background.putConstraint(SpringLayout.EAST, lblNewLabel, -207, SpringLayout.EAST, background);
		sl_background.putConstraint(SpringLayout.NORTH, jIDT, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_background.putConstraint(SpringLayout.NORTH, lblNewLabel, 158, SpringLayout.NORTH, background);
		background.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("src/img/패스워드.png"));
		sl_background.putConstraint(SpringLayout.WEST, lblNewLabel_1, 66, SpringLayout.WEST, background);
		sl_background.putConstraint(SpringLayout.EAST, lblNewLabel_1, -207, SpringLayout.EAST, background);
		sl_background.putConstraint(SpringLayout.SOUTH, jIDT, -21, SpringLayout.NORTH, lblNewLabel_1);
		sl_background.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 237, SpringLayout.NORTH, background);
		sl_background.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -8, SpringLayout.NORTH, jPWT);
		background.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("      SignUp");
		sl_background.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 3, SpringLayout.SOUTH, button1);
		sl_background.putConstraint(SpringLayout.WEST, lblNewLabel_2, 10, SpringLayout.WEST, button1);
		sl_background.putConstraint(SpringLayout.EAST, lblNewLabel_2, 0, SpringLayout.EAST, button);
		background.add(lblNewLabel_2);
		setContentPane(scrollPane);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			jPWT.setText("");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public static void main(String[] args) {
		LoginGUI frame = new LoginGUI();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 480);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);// 창이 가운데 나오게
	}

}