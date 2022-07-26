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

public class MusicPlayerGUI extends JFrame {
	private String user_id;
	private JTable table1;
	private List<Integer> playingIDs = new ArrayList<>();
	private MusicPlayer mp = new MusicPlayer(playingIDs);
	private int size;

	public MusicPlayerGUI(String user_id) {
		this.user_id = user_id;
		// --------------------------------------------------22-0726
		setTitle(findUsersName(user_id) + "님의 플레이 리스트");
		// --------------------------------------------------22-0726
		setResizable(false);
		getContentPane().setBackground(Color.black); // 전체창 백그라운드배경
		setBounds(100, 100, 780, 550);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 12, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 71, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 372, SpringLayout.WEST, getContentPane());
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel);
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
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_3, 418, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_3, 33, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_3, 479, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_3, 109, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnNewButton_3);
		btnNewButton_3.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_3.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_3.setFocusPainted(false);

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.prePlay();
				NowMusic song = mp.play();
				lbl_info.setText(mp.musicInfo(song));
			}
		});
		btnNewButton_3.setFont(new Font("굴림", Font.PLAIN, 25));

		JButton startBtn = new JButton(new ImageIcon("src/img/재생.png"));
		springLayout.putConstraint(SpringLayout.NORTH, startBtn, 418, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, startBtn, 116, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, startBtn, 479, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, startBtn, 192, SpringLayout.WEST, getContentPane());
		getContentPane().add(startBtn);
		startBtn.setBorderPainted(false); // 버튼외곽선 없애줌
		startBtn.setContentAreaFilled(false); // 버튼 투명하게설정
		startBtn.setFocusPainted(false);

		// 버튼에 액션을 감지할 수 있는 액션부여
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NowMusic song = mp.play();
				// 버튼이 눌리면 현재 재생곡 정보 띄워주기 -> musicinfo()
				// 라벨에 가져온 음악 정보 띄우기!!!!!!!!!!!!!!
				// 라벨에 내용을 지정하기 위한 메소드
				// get텍스라는 녀석은
				lbl_info.setText(mp.musicInfo(song));
			}
		});

		startBtn.setFont(new Font("굴림", Font.PLAIN, 25));

		JButton stopBtn = new JButton(new ImageIcon("src/img/정지버튼.png"));
		springLayout.putConstraint(SpringLayout.NORTH, stopBtn, 418, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, stopBtn, 194, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, stopBtn, 479, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, stopBtn, 270, SpringLayout.WEST, getContentPane());
		getContentPane().add(stopBtn);
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
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 418, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 274, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 479, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 350, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton.setFocusPainted(false);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.nextPlay();
				NowMusic song = mp.play();
				lbl_info.setText(mp.musicInfo(song));
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 25));

		JLabel lblNewLabel = new JLabel(new ImageIcon("src/img/흔들어.png"));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 341, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 116, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 408, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 270, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("src/img/배경사진.jpg"));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 22, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 22, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -32, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -10, SpringLayout.EAST, panel);
		getContentPane().add(lblNewLabel_1);

		JPanel playlistPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, playlistPanel, 764, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, playlistPanel, 65, SpringLayout.EAST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, playlistPanel, 669, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, playlistPanel, 1131, SpringLayout.EAST, getContentPane());
		getContentPane().add(playlistPanel);

		String[] headings = new String[] { "", "ID", "노래 제목", "아티스트", "장르" }; // 테이블 열

		size = findCnt(user_id);
		Object[][] data = new Object[size][5];

		for (int i = 0; i < size; i++) {
			data[i][0] = new Boolean(false);
			data[i][1] = "";
			data[i][2] = "";
			data[i][3] = "";
			data[i][4] = "";
		}
		printPlaylist(user_id, data);

		// 각 셀마다 노래 제목, 아티스트 데이터를 삽입해야함.
		getContentPane().add(playlistPanel);
		playlistPanel.setLayout(new SpringLayout());

		DefaultTableModel model = new DefaultTableModel(data, headings) { // 체크박스열만 수정이 가능하게 수정함.

			public boolean isCellEditable(int i, int c) {
				if (c > 0)
					return false;

				else
					return true;
			}
		};

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
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 72, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 56, SpringLayout.EAST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 373, SpringLayout.EAST, lblNewLabel_1);
		getContentPane().add(scrollPane);

		table1.getColumnModel().getColumn(0).setPreferredWidth(5); // JTable 의 컬럼 길이 조절
		table1.getColumnModel().getColumn(1).setPreferredWidth(0);
		table1.getColumnModel().getColumn(2).setPreferredWidth(80);
		table1.getColumnModel().getColumn(3).setPreferredWidth(80);
		table1.getColumnModel().getColumn(4).setPreferredWidth(80);

		table1.getColumn("ID").setWidth(0);
		table1.getColumn("ID").setMinWidth(0);
		table1.getColumn("ID").setMaxWidth(0);

		JButton playBtn = new JButton("재생");
		playBtn.setForeground(Color.BLACK);
		springLayout.putConstraint(SpringLayout.NORTH, playBtn, 10, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, playBtn, -107, SpringLayout.EAST, getContentPane());
		getContentPane().add(playBtn);

		JButton deleteBtn = new JButton("삭제");
		springLayout.putConstraint(SpringLayout.NORTH, deleteBtn, 0, SpringLayout.NORTH, playBtn);
		springLayout.putConstraint(SpringLayout.EAST, deleteBtn, 0, SpringLayout.EAST, scrollPane);
		getContentPane().add(deleteBtn);

		JLabel lblNewLabel_2 = new JLabel("플레이리스트");
		lblNewLabel_2.setForeground(Color.CYAN);
		lblNewLabel_2.setFont(new Font("12롯데마트드림Medium", Font.ITALIC, 26));
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 50, SpringLayout.EAST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -18, SpringLayout.NORTH, scrollPane);
		getContentPane().add(lblNewLabel_2);

		// ------------------------------220726
		// 추가---------------------------------------------
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Integer> check = new ArrayList<>();
				List<Integer> deleteJtable = new ArrayList<>();
				for (int i = 0; i < size; i++) {
					if (table1.getValueAt(i, 0) == Boolean.TRUE) {
						check.add((Integer) table1.getValueAt(i, 1));
						deleteJtable.add(i);
					}
				}
				System.out.println(check);
				deletePlaylist(user_id, check, data);

				for (int i = size - 1; i >= 0; i--) {
					if (table1.getValueAt(i, 0) == Boolean.TRUE) {
						((DefaultTableModel) table1.getModel()).removeRow(i);
					}
				}

				size = findCnt(user_id);
			}

		});

		setLocationRelativeTo(null);// 창이 가운데 나오게
	}
	// ------------------------------220726
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
				name =  rs.getString("user_name");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeRS(rs);
			DBUtil.closeRS(rs);
		}
		return name;

	}

	// ------------------------------220726
	// 추가---------------------------------------------
	private void deletePlaylist(String user_id, List<Integer> check, Object[][] data) {
		Connection conn = null;

		PlayListDaoImp dao = new PlayListDaoImp();

		int plList = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			plList = dao.DeletePL(conn, user_id, check);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.closeConn(conn);
		}

	}
	// ------------------------------220726
	// 추가---------------------------------------------

	private void printPlaylist(String user_id, Object[][] data) {
		Connection conn = null;

		PlayListDaoImp dao = new PlayListDaoImp();

		List<PlayList> plList = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			plList = dao.PersonalPL(conn, user_id);

			for (int i = 0; i < plList.size(); i++) {
				data[i][0] = new Boolean(false);
				data[i][1] = plList.get(i).getMusic_id();
				data[i][2] = plList.get(i).getTitle();
				data[i][3] = plList.get(i).getArtist();
				data[i][4] = plList.get(i).getGenre();

				playingIDs.add(plList.get(i).getMusic_id());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.closeConn(conn);
		}

	}

// 플레이 리스트에서 해당 아이디의 곡들의 갯수 리턴
	public int findCnt(String user_id) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) FROM playlist WHERE user_id = ?";

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

}
