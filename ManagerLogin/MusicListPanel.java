package ManagerLogin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import kr.co.greenart.dbutil.DBUtil;
public class MusicListPanel extends JPanel {
	private ManagerMusicsDaoImpl dao = new ManagerMusicsDaoImpl();
	private JTable table;
	private int tableRowCnt;

	public JTable getTable() {
		return table;
	}

	public int getTableRowCnt() {
		return tableRowCnt;
	}

	public MusicListPanel() {
//		List<String> Head = new ArrayList<>(Arrays.asList("곡명", "아티스트", "장르", ", "지금 순위", "이전 순위"));
		Vector<String> Head = new Vector<>(
				Arrays.asList("", "곡명", "아티스트", "장르",  "총 재생", "실시간 순위", "id"));

		DefaultTableModel model = new DefaultTableModel(Head, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column > 0) {
					return false;
				} else {
					return true;
				}
			}
		};

		table = new JTable(model) {
			@Override
			public Class getColumnClass(int column) {
				if (column == 0) {
					return Boolean.class;
				} else
					return String.class;
			}
		};
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			List<Music> mulist = dao.readAdmin(conn);

//			int columnCnt = rsmd.getColumnCount();
//			int rowCnt = table.getRowCount();
			int rowCnt = table.getRowCount();

			for (int i = 0; i < mulist.size(); i++) {
				Vector<Object> tuple = new Vector<Object>();
				tuple.add(false);
				tuple.add(mulist.get(i).getTitle());
				tuple.add(mulist.get(i).getArtist());
				tuple.add(mulist.get(i).getGenre());
				tuple.add(mulist.get(i).getSaveCount());
				tuple.add(mulist.get(i).getNow_rank());
				tuple.add(mulist.get(i).getMusic_id());  // 음원id는 안보이게 

				model.addRow(tuple);
				rowCnt++;
			}
			tableRowCnt = rowCnt;
			
			JPanel panel = new JPanel();
			JScrollPane ScPane = new JScrollPane(table);

			table.getColumnModel().getColumn(0).setPreferredWidth(5); // JTable 의 컬럼 길이 조절
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(60);
			table.getColumnModel().getColumn(4).setPreferredWidth(60);
			table.getColumnModel().getColumn(5).setPreferredWidth(60);
			table.getColumnModel().getColumn(6).setPreferredWidth(60);// id값
	
			
			// 열 안 움직이게 함.
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
			
			ScPane.setPreferredSize(new Dimension(555, 200));
			panel.add(ScPane);
			add(panel, BorderLayout.CENTER);
			revalidate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn);
		}
		
		render.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel cModel = table.getColumnModel();
		for (int i = 1; i < cModel.getColumnCount(); i++) {
			cModel.getColumn(i).setCellRenderer(render);
		}
	}
}
