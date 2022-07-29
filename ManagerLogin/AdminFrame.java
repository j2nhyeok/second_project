package ManagerLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import kr.co.greenart.dbutil.DBUtil;
import userPlaylist.*;

public class AdminFrame extends JDialog implements ActionListener {
	private MusicListPanel mulist;
	private JButton btnEdit;
	private JButton btnDelete;
	private List<Integer> checkedIds;
	private static MusicsDaoImpl MSdao = new MusicsDaoImpl();
	private PlayListDaoImp PLdao = new PlayListDaoImp();
	public static JTable table;

	public List<Integer> getCheckedIds() {
		return checkedIds;
	}

	public AdminFrame() {
		setModal(true);
		// ---------------------------------------- 내일 할 일
		// -----------------------------------------
		// 집계 버튼 추가
		// 지금 순위 기준으로 목록 나오게 (view만들기)

		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 504, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JButton btnInsert = new JButton("음원추가");
		sl_panel.putConstraint(SpringLayout.EAST, btnInsert, -21, SpringLayout.EAST, panel);
		panel.add(btnInsert);

		mulist = new MusicListPanel();
		sl_panel.putConstraint(SpringLayout.NORTH, btnInsert, 26, SpringLayout.NORTH, mulist);
		sl_panel.putConstraint(SpringLayout.NORTH, mulist, 44, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, mulist, -214, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, mulist, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, mulist, -129, SpringLayout.EAST, panel);
		JPanel panel_1 = mulist;

		panel.add(panel_1);
		panel_1.setLayout(new SpringLayout());

		btnEdit = new JButton("수정");
		sl_panel.putConstraint(SpringLayout.NORTH, btnEdit, 105, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnEdit, 17, SpringLayout.EAST, mulist);
		sl_panel.putConstraint(SpringLayout.EAST, btnEdit, -21, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnInsert, 0, SpringLayout.WEST, btnEdit);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnInsert, -6, SpringLayout.NORTH, btnEdit);
		panel.add(btnEdit);

		btnDelete = new JButton("삭제");
		sl_panel.putConstraint(SpringLayout.WEST, btnDelete, 17, SpringLayout.EAST, mulist);
		sl_panel.putConstraint(SpringLayout.EAST, btnDelete, -21, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnEdit, -6, SpringLayout.NORTH, btnDelete);
		sl_panel.putConstraint(SpringLayout.NORTH, btnDelete, 140, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnDelete, -335, SpringLayout.SOUTH, panel);
		panel.add(btnDelete);

//		음원 추가 버튼
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertDialog insert = new InsertDialog();
				insert.setVisible(true);
				
				try {
					resetJTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnEdit.addActionListener(this);
		btnDelete.addActionListener(this);

		setSize(740, 381);
		setLocationRelativeTo(null);// 창이 가운데 나오게
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void resetJTable() throws SQLException {

		Connection conn = null;
		ManagerMusicsDaoImpl mdao = new ManagerMusicsDaoImpl();
		int size = MSdao.totalCntMusics(conn);
		try {
			conn = DBUtil.getConnection();
			List<Music> temp = mdao.readAdmin(conn);
			System.out.println(temp);
		System.out.println(table.getRowCount());
		System.out.println(size);
		System.out.println(temp.size());
			for (int i = 0; i < size; i++) {
				System.out.println(temp.get(i).getTitle() + " " + temp.get(i).getArtist() + " " + temp.get(i).getGenre()
						+ " " + temp.get(i).getSaveCount() + " " + temp.get(i).getNow_rank() + " "
						+ temp.get(i).getMusic_id() + " ");
			}

			for (int i = 0; i < size; i++) {
				table.setValueAt(temp.get(i).getTitle(), i, 1); // 타이틀
				table.setValueAt(temp.get(i).getArtist(), i, 2); // 아티스트
				table.setValueAt(temp.get(i).getGenre(), i, 3); // 장르
				table.setValueAt(temp.get(i).getSaveCount(), i, 4); // 담은 수
				table.setValueAt(temp.get(i).getNow_rank(), i, 5);// 현재순위
				table.setValueAt(temp.get(i).getMusic_id(), i, 6);// ; 아이디

			}
	

		} finally {
			DBUtil.closeConn(conn);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ManagerMusicsDaoImpl dao = new ManagerMusicsDaoImpl();
		Connection conn = null;

		table = mulist.getTable();
		int size = mulist.getTableRowCnt(); // 테이블 행 사이
		List<Integer> row = new ArrayList<>();
		checkedIds = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			if (table.getValueAt(i, 0) == Boolean.TRUE) {
				checkedIds.add((Integer) table.getValueAt(i, 6));
				row.add(i);
			}
		}

		// 음악 수정 버튼을 눌렸을 때
		if (e.getSource() == btnEdit) {
			// 음악 수정은 음악 목록 보기에서 체크 되어 있을 때만 가능
			// 음악 목록 보기가 활성화 되어 있는지 확인
			if (checkedIds.size() == 1) { // 음악이 1개만 선택 되어 있다면?
				EditDialog edit = new EditDialog(AdminFrame.this);
				edit.setVisible(true);
				try {
					resetJTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else { // 음악이 선택되지 않았거나 여러개 선택되었다면?
				JOptionPane.showConfirmDialog(btnEdit, "음악을 하나 선택해주세요.");
			}
		}

		// 삭제 버튼을 눌렸을 때
		if (e.getSource() == btnDelete) {
			// 음악 삭제도 음악 목록 보기에서 체크 되어 있을 때만 가능
			// 음악 목록 보기가 활성화 되어 있는지 확인
			if (checkedIds.size() == 0) { // 음악이 선택 되어 있지않다면?
				JOptionPane.showConfirmDialog(btnDelete, "음악을 하나 이상 선택해주세요.");
			} else {
				try {
					conn = DBUtil.getConnection();
				
					dao.deleteAdminList(conn, checkedIds);
					
//					for(int i = 0; i < row.size(); i++) {
//					DefaultTableModel model = (DefaultTableModel) table.getModel();
//					model.removeRow(row.get(i));
//					}
							
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					DBUtil.closeConn(conn);
				}

				try {
			
					for (int i = 0; i < MSdao.totalCntMusics(conn); i++) {
						conn = DBUtil.getConnection();
						PLdao.updateRank(conn,PLdao.printMusicId(conn, i), i + 1);
					}

					JOptionPane.showMessageDialog(null, "음원이 삭제되었니다", "확인", JOptionPane.DEFAULT_OPTION);
					
					resetJTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			
					
			}
		}
	}
}
