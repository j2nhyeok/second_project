import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class MyPage extends JFrame implements MouseListener {
	private ImageIcon icon;
	//--------------------------------------------------22-0726;
	private String user_id;
	
	public MyPage(String user_id) {
		//--------------------------------------------------22-0726
		super("MyPage");
		
		this.user_id = user_id;
		icon = new ImageIcon("src/img/마이페이지메인.png");
		JPanel pnl = new JPanel() {
            public void paintComponent(Graphics g) {
            	setTitle("MyPage");
                g.drawImage(icon.getImage(), 0, 0, null);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
		
		
		getContentPane().add(pnl);
		SpringLayout sl_pnl = new SpringLayout();
		pnl.setLayout(sl_pnl);
		
		JButton btnNewButton_1 = new JButton(new ImageIcon("src/img/탈퇴할래.png"));
		sl_pnl.putConstraint(SpringLayout.WEST, btnNewButton_1, 201, SpringLayout.WEST, pnl);
		sl_pnl.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -33, SpringLayout.SOUTH, pnl);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserWithdrawal dialog = new UserWithdrawal(MyPage.this); // 비번변경 서브창 생성
				dialog.show();
			}
		});
		btnNewButton_1.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_1.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_1.setFocusPainted(false);
		pnl.add(btnNewButton_1);
		
		
		JButton btnNewButton = new JButton(new ImageIcon("src/img/패스워드변경.png"));
		sl_pnl.putConstraint(SpringLayout.WEST, btnNewButton, 95, SpringLayout.WEST, pnl);
		sl_pnl.putConstraint(SpringLayout.SOUTH, btnNewButton, -45, SpringLayout.SOUTH, pnl);
		sl_pnl.putConstraint(SpringLayout.EAST, btnNewButton, -36, SpringLayout.WEST, btnNewButton_1);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PasswordChange dialog = new PasswordChange(MyPage.this); // 비번변경 서브창 생성
				dialog.show();
			}
		});
		btnNewButton.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton.setFocusPainted(false);
		pnl.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("src/img/이름아이콘.png"));
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel, 114, SpringLayout.NORTH, pnl);
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel, 77, SpringLayout.WEST, pnl);
		sl_pnl.putConstraint(SpringLayout.EAST, lblNewLabel, -263, SpringLayout.EAST, pnl);
		pnl.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("src/img/아이디아이콘.png"));
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 174, SpringLayout.NORTH, pnl);
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel_1, 77, SpringLayout.WEST, pnl);
		sl_pnl.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -143, SpringLayout.SOUTH, pnl);
		sl_pnl.putConstraint(SpringLayout.EAST, lblNewLabel_1, -263, SpringLayout.EAST, pnl);
		sl_pnl.putConstraint(SpringLayout.NORTH, btnNewButton, 36, SpringLayout.SOUTH, lblNewLabel_1);
		sl_pnl.putConstraint(SpringLayout.SOUTH, lblNewLabel, -36, SpringLayout.NORTH, lblNewLabel_1);
		pnl.add(lblNewLabel_1);
		
		//--------------------------------------------------22-0726
		JLabel label = new JLabel(user_id);
		sl_pnl.putConstraint(SpringLayout.NORTH, btnNewButton_1, 84, SpringLayout.SOUTH, label);
		sl_pnl.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, lblNewLabel);
		sl_pnl.putConstraint(SpringLayout.WEST, label, 6, SpringLayout.EAST, lblNewLabel);
		sl_pnl.putConstraint(SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, lblNewLabel);
		sl_pnl.putConstraint(SpringLayout.EAST, label, 156, SpringLayout.EAST, lblNewLabel);
		pnl.add(label);
		
		JLabel lblNewLabel_2 = new JLabel(user_id);
		sl_pnl.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 0, SpringLayout.NORTH, lblNewLabel_1);
		sl_pnl.putConstraint(SpringLayout.WEST, lblNewLabel_2, 6, SpringLayout.EAST, lblNewLabel_1);
		sl_pnl.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 0, SpringLayout.SOUTH, lblNewLabel_1);
		sl_pnl.putConstraint(SpringLayout.EAST, lblNewLabel_2, 0, SpringLayout.EAST, label);
		pnl.add(lblNewLabel_2);
		//--------------------------------------------------22-0726
		
		setLocationRelativeTo(null);// 창이 가운데 나오게
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400,380);
		setVisible(true);
		
		//--------------------------------------------------22-0726
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


		
	


