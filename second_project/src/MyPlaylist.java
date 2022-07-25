import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JTextPane;

public class MyPlaylist extends JFrame{
	private String user_id;
	public MyPlaylist(String user_id) {
		super(user_id + "님의 플레이리스트");
		
		this.user_id = user_id;
	
		JPanel total = new JPanel();
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		JPanel buttons = new JPanel();

		total.setBackground(Color.white);
		top.setBackground(Color.white);
		bottom.setBackground(Color.white);
		buttons.setBackground(Color.white);

		List<Integer> checkIndex = new ArrayList<>();
		// top & 메인 배너
	
		SpringLayout sl_total = new SpringLayout();

		sl_total.putConstraint(SpringLayout.SOUTH, buttons, -135, SpringLayout.SOUTH, total);
		total.setLayout(sl_total);

		total.add(top);
		total.add(bottom);

		// 수량 선택에 따라 넘아감

		getContentPane().add(total);
		
	
		
		JCheckBox[] rankCheck = new JCheckBox[11];
		
		OrderByRank obr = new OrderByRank();
		
		rankCheck[1] = new JCheckBox();
		
		 rankCheck[2] = new JCheckBox();
	
				
		 rankCheck[3] = new JCheckBox();
	
		
		  rankCheck[4] = new JCheckBox();
		
		
		  rankCheck[5] = new JCheckBox();
	
		  rankCheck[6] = new JCheckBox();
	
		
		  rankCheck[7] = new JCheckBox();
		
		  rankCheck[8] = new JCheckBox();
	
		
		  rankCheck[9] = new JCheckBox();
	
		
		  rankCheck[10] = new JCheckBox();
				
				JButton startBtn = new JButton("재생");
				sl_total.putConstraint(SpringLayout.NORTH, startBtn, 20, SpringLayout.NORTH, total);
				sl_total.putConstraint(SpringLayout.WEST, startBtn, 328, SpringLayout.WEST, total);
				startBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				total.add(startBtn);
				
				JButton stopBtn = new JButton("정지");
				sl_total.putConstraint(SpringLayout.NORTH, stopBtn, 19, SpringLayout.NORTH, total);
				total.add(stopBtn);
				
				JButton prevMusicBtn = new JButton("이전곡");
				sl_total.putConstraint(SpringLayout.NORTH, prevMusicBtn, 19, SpringLayout.NORTH, total);
				sl_total.putConstraint(SpringLayout.EAST, stopBtn, -14, SpringLayout.WEST, prevMusicBtn);
				total.add(prevMusicBtn);
				
				JButton nextBtn = new JButton("다음곡");
				sl_total.putConstraint(SpringLayout.NORTH, nextBtn, 19, SpringLayout.NORTH, total);
				sl_total.putConstraint(SpringLayout.EAST, prevMusicBtn, -18, SpringLayout.WEST, nextBtn);
				sl_total.putConstraint(SpringLayout.EAST, nextBtn, -30, SpringLayout.EAST, total);
				total.add(nextBtn);
				
				JLabel lblNewLabel_1 = new JLabel("New label");
				sl_total.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, startBtn);
				sl_total.putConstraint(SpringLayout.WEST, lblNewLabel_1, 44, SpringLayout.WEST, total);
				sl_total.putConstraint(SpringLayout.EAST, lblNewLabel_1, -39, SpringLayout.WEST, startBtn);
				total.add(lblNewLabel_1);
				
					
					JButton playBtn = new JButton("재생");
					total.add(playBtn);
					
					playBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							List<Integer> list = new ArrayList<>();
							for (int i = 1; i < rankCheck.length; i++ ) {
								if (rankCheck[i].isSelected()) {
									list.add(i);
								}
							}
							
							if(list.size() == 0) {
								JOptionPane.showMessageDialog(null, "곡을 하나 이상 담아주세요.");
							}
							
							else {
								JOptionPane.showMessageDialog(null, "정상적으로 플레이리스트에 담겼습니다.");
								
								for(int i = 1; i < rankCheck.length; i++ ) {      // 체크박스 초기화
									rankCheck[i].setSelected(false);
								}
								
								for(int i = 0; i < list.size(); i++) {             // 플레이리스트 테이블에 데이터 입력
									
						
								}
							}
						}
					});

		// 구매하기 버튼 연결

		setSize(677, 500);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);// 창이 가운데 나오게
		setVisible(true);
		setResizable(false);// 창의 크기를 변경하지 못하게
	
	
	
	
	
	}
}
