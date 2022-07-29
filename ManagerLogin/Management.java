package ManagerLogin;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import userPlaylist.*;
// 음원관리 클릭시 서브창 생성해야함
// 회원관리 클릭시 서브창 생성해야함
public class Management extends JDialog implements MouseListener {
	private ImageIcon icon1;

	public Management(JFrame owner) {
		super(owner, true);

		icon1 = new ImageIcon("src/img/관리자메인.png");
		JPanel Manager = new JPanel() {
			public void paintComponent(Graphics g) {
				setTitle("Management");
				g.drawImage(icon1.getImage(), 0, 0, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		getContentPane().add(Manager);
		SpringLayout sl_Manager = new SpringLayout();
		Manager.setLayout(sl_Manager);
		// 음원관리 클릭시 음원관리 서브창 전환시켜야함
		JButton btnNewButton = new JButton(new ImageIcon("src/img/음원관리버튼.png")); // 음원관리
		sl_Manager.putConstraint(SpringLayout.SOUTH, btnNewButton, -151, SpringLayout.SOUTH, Manager);

		btnNewButton.addActionListener(new ActionListener() { // 음원관리 클릭할시 서브창 구현할수있는 메소드 ( 음원관리클래스필요 )
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminFrame dialog = new AdminFrame(); // 음원관리 서브창 생성
				dialog.setVisible(true);
			}
		});
		btnNewButton.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton.setFocusPainted(false);
		sl_Manager.putConstraint(SpringLayout.NORTH, btnNewButton, 104, SpringLayout.NORTH, Manager);
		sl_Manager.putConstraint(SpringLayout.WEST, btnNewButton, -300, SpringLayout.EAST, Manager);
		sl_Manager.putConstraint(SpringLayout.EAST, btnNewButton, -203, SpringLayout.EAST, Manager);
		Manager.add(btnNewButton);
		// 회원관리 클릭시 회원관리 서브창 전환시켜야함
		JButton btnNewButton_1 = new JButton(new ImageIcon("src/img/회원관리버튼.png"));
		sl_Manager.putConstraint(SpringLayout.WEST, btnNewButton_1, 21, SpringLayout.EAST, btnNewButton);
		sl_Manager.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -151, SpringLayout.SOUTH, Manager);
		sl_Manager.putConstraint(SpringLayout.EAST, btnNewButton_1, -85, SpringLayout.EAST, Manager);

		btnNewButton_1.addActionListener(new ActionListener() { // 회원관리 클릭시 서브창 구현할수있는 메소드 ( 회원관리클래스필요 )
			@Override
			public void actionPerformed(ActionEvent e) {
				UserManagement dialog = new UserManagement(Management.this); // 회원관리버튼 전환창
				dialog.setVisible(true);
			}
		});
		btnNewButton_1.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_1.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_1.setFocusPainted(false);
		sl_Manager.putConstraint(SpringLayout.NORTH, btnNewButton_1, 104, SpringLayout.NORTH, Manager);
		Manager.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton(new ImageIcon("src/img/나갈껴.png"));
		sl_Manager.putConstraint(SpringLayout.SOUTH, btnNewButton_2, -46, SpringLayout.SOUTH, Manager);

		btnNewButton_2.addActionListener(new ActionListener() { // 로그아웃 누를시 창닫힘 (로그아웃 DB설정해야함)
			public void actionPerformed(ActionEvent e) { // 로그아웃 실행되고 나가져야함
				dispose();
			}
		});
		btnNewButton_2.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_2.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_2.setFocusPainted(false);
		sl_Manager.putConstraint(SpringLayout.WEST, btnNewButton_2, 112, SpringLayout.WEST, Manager);
		sl_Manager.putConstraint(SpringLayout.EAST, btnNewButton_2, -115, SpringLayout.EAST, Manager);
		Manager.add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("  MusicManager ");
		sl_Manager.putConstraint(SpringLayout.NORTH, btnNewButton_2, 40, SpringLayout.SOUTH, lblNewLabel);
		sl_Manager.putConstraint(SpringLayout.NORTH, lblNewLabel, 1, SpringLayout.SOUTH, btnNewButton);
		sl_Manager.putConstraint(SpringLayout.WEST, lblNewLabel, 94, SpringLayout.WEST, Manager);
		sl_Manager.putConstraint(SpringLayout.SOUTH, lblNewLabel, -135, SpringLayout.SOUTH, Manager);
		Manager.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("   UserManager");
		sl_Manager.putConstraint(SpringLayout.EAST, lblNewLabel, -21, SpringLayout.WEST, lblNewLabel_1);
		sl_Manager.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, lblNewLabel);
		sl_Manager.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, btnNewButton_1);
		sl_Manager.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, btnNewButton_1);
		Manager.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("                       EXIT");
		sl_Manager.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 6, SpringLayout.SOUTH, btnNewButton_2);
		sl_Manager.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, btnNewButton_2);
		sl_Manager.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 0, SpringLayout.SOUTH, Manager);
		sl_Manager.putConstraint(SpringLayout.EAST, lblNewLabel_2, 0, SpringLayout.EAST, btnNewButton_2);
		Manager.add(lblNewLabel_2);

		setResizable(false);
		setSize(400, 400);
	      setLocationRelativeTo(null);// 창이 가운데 나오게

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
