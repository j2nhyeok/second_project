
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import kr.co.greenart.dbutil.DBUtil;

public class MusicPlayerGUI {
	public JFrame frame;
	MusicPlayer mp = new MusicPlayer();
	private String user_id;
	private JTable table1;

	public MusicPlayerGUI(String user_id) {
		this.user_id = user_id;
		initialize();
	}

	private void initialize() {
		frame = new JFrame(user_id + "님의 플레이 리스트");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.black); // 전체창 백그라운드배경
		frame.setBounds(100, 100, 780, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 12, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 71, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 372, SpringLayout.WEST, frame.getContentPane());
		panel.setBackground(Color.BLACK);
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JLabel lbl_info = new JLabel(new ImageIcon("src/img/상단이미지.png"));
		sl_panel.putConstraint(SpringLayout.NORTH, lbl_info, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lbl_info, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lbl_info, 61, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lbl_info, 360, SpringLayout.WEST, panel);
		lbl_info.setForeground(Color.white);
		lbl_info.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_info.setBackground(Color.BLACK);
		lbl_info.setFont(new Font(" 고딕", Font.PLAIN, 18));
		panel.add(lbl_info);

		JButton btnNewButton_3 = new JButton(new ImageIcon("src/img/뒤로감기.png"));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_3, 418, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_3, 33, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_3, 479, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_3, 109, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_3.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_3.setFocusPainted(false);

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.prePlay();
				Music song = mp.play();
				lbl_info.setText(mp.musicInfo(song));
			}
		});
		btnNewButton_3.setFont(new Font("굴림", Font.PLAIN, 25));

		JButton startBtn = new JButton(new ImageIcon("src/img/재생.png"));
		springLayout.putConstraint(SpringLayout.NORTH, startBtn, 418, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, startBtn, 116, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, startBtn, 479, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, startBtn, 192, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(startBtn);
		startBtn.setBorderPainted(false); // 버튼외곽선 없애줌
		startBtn.setContentAreaFilled(false); // 버튼 투명하게설정
		startBtn.setFocusPainted(false);

		// 버튼에 액션을 감지할 수 있는 액션부여
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music song = mp.play();
				// 버튼이 눌리면 현재 재생곡 정보 띄워주기 -> musicinfo()
				// 라벨에 가져온 음악 정보 띄우기!!!!!!!!!!!!!!
				// 라벨에 내용을 지정하기 위한 메소드
				// get텍스라는 녀석은
				lbl_info.setText(mp.musicInfo(song));
			}
		});

		startBtn.setFont(new Font("굴림", Font.PLAIN, 25));

		JButton stopBtn = new JButton(new ImageIcon("src/img/정지버튼.png"));
		springLayout.putConstraint(SpringLayout.NORTH, stopBtn, 418, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, stopBtn, 194, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, stopBtn, 479, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, stopBtn, 270, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(stopBtn);
		stopBtn.setBorderPainted(false); // 버튼외곽선 없애줌
		stopBtn.setContentAreaFilled(false); // 버튼 투명하게설정
		stopBtn.setFocusPainted(false);

		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.stop();
				lbl_info.setText("STOP");
			}
		});
		stopBtn.setFont(new Font("굴림", Font.PLAIN, 25));

		JButton btnNewButton = new JButton(new ImageIcon("src/img/앞으로감기.png"));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 418, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 274, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 479, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 350, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton.setFocusPainted(false);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.nextPlay();
				Music song = mp.play();
				lbl_info.setText(mp.musicInfo(song));
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 25));

		JLabel lblNewLabel = new JLabel(new ImageIcon("src/img/흔들어.png"));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 341, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 116, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 408, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 270, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("src/img/배경사진.jpg"));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 22, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 22, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -32, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -10, SpringLayout.EAST, panel);
		frame.getContentPane().add(lblNewLabel_1);

		JPanel playlistPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, playlistPanel, 764, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, playlistPanel, 65, SpringLayout.EAST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, playlistPanel, 669, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, playlistPanel, 1131, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(playlistPanel);

		String[] headings = new String[] { "", "노래 제목", "아티스트", "장르" }; // 테이블 열

		// 플레이 리스트가 몇 곡인지 알아 내야함----------------------------------------------구현 완료
		int size = findCnt(user_id);
		Object[][] data = new Object[size][4];
		// ---------------------------------------------------------------------------------------------------------------------------
		for (int i = 0; i < size; i++) {
			data[i][0] = new Boolean(false);
			data[i][1] = "";
			data[i][2] = "";
			data[i][3] = "";
		}
		printPlaylist(user_id, data);

		// 각 셀마다 노래 제목, 아티스트 데이터를 삽입해야함.
		frame.getContentPane().add(playlistPanel);
		playlistPanel.setLayout(new SpringLayout());
// 플레이 리스트가 몇 곡인지 알아 내야함----------------------------------------------구현 완료
		DefaultTableModel model = new DefaultTableModel(data, headings) { // 체크박스열만 수정이 가능하게 수정함.

			public boolean isCellEditable(int i, int c) {
				if (c > 0)
					return false;

				else
					return true;
			}
		};
// 플레이 리스트가 몇 곡인지 알아 내야함----------------------------------------------구현 완료

		table1 = new JTable(model) {

			@Override
			public Class<?> getColumnClass(int column) {
				if (column == 0) {
					return Boolean.class;
				}
				return super.getColumnClass(column);
			}

		};
		table1.setBackground(Color.CYAN);
		table1.setPreferredScrollableViewportSize(new Dimension(200, 200));

		JScrollPane scrollPane = new JScrollPane(table1);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 72, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 56, SpringLayout.EAST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 373, SpringLayout.EAST, lblNewLabel_1);
		frame.getContentPane().add(scrollPane);

		table1.getColumnModel().getColumn(0).setPreferredWidth(5); // JTable 의 컬럼 길이 조절
		table1.getColumnModel().getColumn(1).setPreferredWidth(80);
		table1.getColumnModel().getColumn(2).setPreferredWidth(80);
		table1.getColumnModel().getColumn(3).setPreferredWidth(80);

		JButton playBtn = new JButton("재생");
		playBtn.setForeground(Color.BLACK);
		springLayout.putConstraint(SpringLayout.NORTH, playBtn, 10, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, playBtn, -107, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(playBtn);

		JButton deleteBtn = new JButton("삭제");
		springLayout.putConstraint(SpringLayout.NORTH, deleteBtn, 0, SpringLayout.NORTH, playBtn);
		springLayout.putConstraint(SpringLayout.EAST, deleteBtn, 0, SpringLayout.EAST, scrollPane);
		frame.getContentPane().add(deleteBtn);
//=================================================================================
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Integer> check = new ArrayList<>();
				for (int i = 0; i < data.length; i++) {
					if (table1.getValueAt(i, 0) == Boolean.TRUE) {
						check.add(i);
					}
				}
				System.out.println(check);
//				for (int i = 0; i < check.size(); i++) {
//					deletePlaylist(table1.getValueAt(i, 1));
//				}

			}
		});

		frame.setLocationRelativeTo(null);// 창이 가운데 나오게
	}

	// 플레이 리스트에서 삭제
	
	private void deletePlaylist(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT A.title, A.artist, A.genre FROM musics2 AS A INNER JOIN practice_playlist AS B ON A.music_id = B.music_id  WHERE B.user_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);

			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "행이 정상적으로 삭제 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}

	}

// 플레이 리스트 스크롤 테이블에 값 넣어줌----------------------------------------------------------------------------------------------------------------------------------
	private void printPlaylist(String user_id, Object[][] data) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT A.title, A.artist, A.genre FROM musics2 AS A INNER JOIN practice_playlist AS B ON A.music_id = B.music_id  WHERE B.user_id = ? ORDER BY title";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			int temp = 0;
			while (rs.next()) {

				data[temp][0] = new Boolean(false);
				data[temp][1] = rs.getString("A.title");
				data[temp][2] = rs.getString("A.artist");
				data[temp][3] = rs.getString("A.genre");
				temp++;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeRS(rs);
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}

	}
//---------------------------------------------------------------------------------------------------------------------------

//------------------------------- 플레이 리스트에서 해당 아이디의 곡들의 갯수 리턴---------------------------------------------------
	private int findCnt(String user_id) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) FROM practice_playlist WHERE user_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt("COUNT(*)");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeRS(rs);
			DBUtil.closeRS(rs);
		}

		return cnt;
	}
//--밑에 있는 클래스 삭제----------------------------------------------------------------------------------------------------------------------------------

}
