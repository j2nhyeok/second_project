import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import kr.co.greenart.dbutil.DBUtil;


public class StreamingMain extends JFrame {
   private static Scanner sc;
   private String user_id;

   

   public StreamingMain(String user_id) throws SQLException {
      
      super("스트리밍 메인창");
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

      

      JTextField searchField = new JTextField();
      sl_total.putConstraint(SpringLayout.NORTH, searchField, 20, SpringLayout.NORTH, total);
      sl_total.putConstraint(SpringLayout.WEST, searchField, 83, SpringLayout.WEST, total);
      sl_total.putConstraint(SpringLayout.EAST, searchField, 322, SpringLayout.WEST, total);
      total.add(searchField);
      searchField.setColumns(10);

      JButton searchBtn = new JButton("검색");
      sl_total.putConstraint(SpringLayout.NORTH, searchBtn, -1, SpringLayout.NORTH, searchField);
      sl_total.putConstraint(SpringLayout.WEST, searchBtn, 6, SpringLayout.EAST, searchField);
//-----------------------------------------------------------------22-0715
      searchBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
//            new SearchFrame(user_id, searchField.getText());
//            new SearchFrame();
            
         }
      });
      //--------------------------------------------------22-0715
      total.add(searchBtn);

      JPanel rank_panel = new JPanel();
      sl_total.putConstraint(SpringLayout.NORTH, rank_panel, 33, SpringLayout.SOUTH, searchField);
      sl_total.putConstraint(SpringLayout.WEST, rank_panel, 26, SpringLayout.WEST, total);
      sl_total.putConstraint(SpringLayout.SOUTH, rank_panel, -30, SpringLayout.SOUTH, total);
      total.add(rank_panel);

      JPanel panel = new JPanel();
      sl_total.putConstraint(SpringLayout.EAST, rank_panel, -23, SpringLayout.WEST, panel);
      sl_total.putConstraint(SpringLayout.EAST, panel, -25, SpringLayout.EAST, total);
      sl_total.putConstraint(SpringLayout.WEST, panel, 300, SpringLayout.WEST, total);
      sl_total.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, rank_panel);
      sl_total.putConstraint(SpringLayout.SOUTH, panel, -276, SpringLayout.SOUTH, total);
      SpringLayout sl_rank_panel = new SpringLayout();
      rank_panel.setLayout(sl_rank_panel);
      //--------------------------------------------------22-0715
      JLabel lblNewLabel = new JLabel("순위 차트 ( 1 ~ 10 )");
      //--------------------------------------------------22-0715
      sl_rank_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 5, SpringLayout.NORTH, rank_panel);
      sl_rank_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 75, SpringLayout.WEST, rank_panel);
      rank_panel.add(lblNewLabel);
   
      total.add(panel);
      SpringLayout sl_panel = new SpringLayout();
      panel.setLayout(sl_panel);

      JButton MyPage = new JButton("마이 페이지");
      sl_panel.putConstraint(SpringLayout.NORTH, MyPage, 64, SpringLayout.NORTH, panel);
      sl_panel.putConstraint(SpringLayout.WEST, MyPage, 28, SpringLayout.WEST, panel);
      sl_panel.putConstraint(SpringLayout.EAST, MyPage, 125, SpringLayout.WEST, panel);
      panel.add(MyPage);

      JButton logoutBtn = new JButton("로그아웃");
      sl_panel.putConstraint(SpringLayout.WEST, logoutBtn, 19, SpringLayout.EAST, MyPage);
      sl_panel.putConstraint(SpringLayout.EAST, logoutBtn, -23, SpringLayout.EAST, panel);
      panel.add(logoutBtn);

      JButton playlistBtn = new JButton("플레이리스트");
      sl_panel.putConstraint(SpringLayout.NORTH, logoutBtn, 6, SpringLayout.SOUTH, playlistBtn);
      sl_panel.putConstraint(SpringLayout.WEST, playlistBtn, 0, SpringLayout.WEST, logoutBtn);
      sl_panel.putConstraint(SpringLayout.SOUTH, playlistBtn, -53, SpringLayout.SOUTH, panel);
      sl_panel.putConstraint(SpringLayout.EAST, playlistBtn, -23, SpringLayout.EAST, panel);
      panel.add(playlistBtn);
      //--------------------------------------------------22-0726
      JLabel lblNewLabel_2 = new JLabel(findUsersName(user_id) + "님 환영합니다.");
      sl_panel.putConstraint(SpringLayout.NORTH, playlistBtn, 10, SpringLayout.SOUTH, lblNewLabel_2);
      sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 10, SpringLayout.NORTH, panel);
      sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_2, 22, SpringLayout.WEST, panel);
      panel.add(lblNewLabel_2);
      
      
      playlistBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            new MusicPlayerGUI(user_id).setVisible(true);
         }
      });
      
      MyPage.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			MyPage frame = new MyPage(user_id);
			frame.setResizable(false);
			frame.setSize(400,380);
			frame.setVisible(true);
			
		}
	});

      getContentPane().add(total);
      
      JPanel panel_1 = new JPanel();
      sl_total.putConstraint(SpringLayout.NORTH, panel_1, -240, SpringLayout.SOUTH, rank_panel);
      sl_total.putConstraint(SpringLayout.WEST, panel_1, 23, SpringLayout.EAST, rank_panel);
      sl_total.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, rank_panel);
      sl_total.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, panel);
      
      JPanel rankPanel1 = new JPanel();
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankPanel1, 8, SpringLayout.SOUTH, lblNewLabel);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankPanel1, 10, SpringLayout.WEST, rank_panel);
      rank_panel.add(rankPanel1);
      
      JPanel rankPanel2 = new JPanel();
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankPanel2, 58, SpringLayout.NORTH, rank_panel);
      sl_rank_panel.putConstraint(SpringLayout.SOUTH, rankPanel2, -275, SpringLayout.SOUTH, rank_panel);
      sl_rank_panel.putConstraint(SpringLayout.SOUTH, rankPanel1, -6, SpringLayout.NORTH, rankPanel2);
      sl_rank_panel.putConstraint(SpringLayout.EAST, rankPanel1, 0, SpringLayout.EAST, rankPanel2);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankPanel2, 10, SpringLayout.WEST, rank_panel);
      sl_rank_panel.putConstraint(SpringLayout.EAST, rankPanel2, 241, SpringLayout.WEST, rank_panel);
      rank_panel.add(rankPanel2);
      
      JPanel rankPanel3 = new JPanel();
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankPanel3, 6, SpringLayout.SOUTH, rankPanel2);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankPanel3, 10, SpringLayout.WEST, rank_panel);
      sl_rank_panel.putConstraint(SpringLayout.SOUTH, rankPanel3, 30, SpringLayout.SOUTH, rankPanel2);
      SpringLayout sl_rankPanel2 = new SpringLayout();
      rankPanel2.setLayout(sl_rankPanel2);
      sl_rank_panel.putConstraint(SpringLayout.EAST, rankPanel3, 0, SpringLayout.EAST, rankPanel1);
      rank_panel.add(rankPanel3);
      
      JPanel rankPanel4 = new JPanel();
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankPanel4, 6, SpringLayout.SOUTH, rankPanel3);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankPanel4, 10, SpringLayout.WEST, rank_panel);
      sl_rank_panel.putConstraint(SpringLayout.SOUTH, rankPanel4, 30, SpringLayout.SOUTH, rankPanel3);
      SpringLayout sl_rankPanel3 = new SpringLayout();
      rankPanel3.setLayout(sl_rankPanel3);
      sl_rank_panel.putConstraint(SpringLayout.EAST, rankPanel4, 0, SpringLayout.EAST, rankPanel1);
      rank_panel.add(rankPanel4);
      
      JPanel rankPanel5 = new JPanel();
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankPanel5, 6, SpringLayout.SOUTH, rankPanel4);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankPanel5, 0, SpringLayout.WEST, rankPanel1);
      sl_rank_panel.putConstraint(SpringLayout.SOUTH, rankPanel5, 30, SpringLayout.SOUTH, rankPanel4);
      SpringLayout sl_rankPanel4 = new SpringLayout();
      rankPanel4.setLayout(sl_rankPanel4);
      sl_rank_panel.putConstraint(SpringLayout.EAST, rankPanel5, 0, SpringLayout.EAST, rankPanel1);
      rank_panel.add(rankPanel5);
      
      JPanel rankPanel6 = new JPanel();
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankPanel6, 6, SpringLayout.SOUTH, rankPanel5);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankPanel6, 0, SpringLayout.WEST, rankPanel1);
      sl_rank_panel.putConstraint(SpringLayout.SOUTH, rankPanel6, 30, SpringLayout.SOUTH, rankPanel5);
      SpringLayout sl_rankPanel5 = new SpringLayout();
      rankPanel5.setLayout(sl_rankPanel5);
      sl_rank_panel.putConstraint(SpringLayout.EAST, rankPanel6, 0, SpringLayout.EAST, rankPanel1);
      rank_panel.add(rankPanel6);
      
      JPanel rankPanel7 = new JPanel();
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankPanel7, 6, SpringLayout.SOUTH, rankPanel6);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankPanel7, 0, SpringLayout.WEST, rankPanel1);
      sl_rank_panel.putConstraint(SpringLayout.SOUTH, rankPanel7, 30, SpringLayout.SOUTH, rankPanel6);
      SpringLayout sl_rankPanel6 = new SpringLayout();
      rankPanel6.setLayout(sl_rankPanel6);
      
      
      
      JPanel rankPanel8 = new JPanel();
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankPanel8, 6, SpringLayout.SOUTH, rankPanel7);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankPanel8, 0, SpringLayout.WEST, rankPanel1);
      sl_rank_panel.putConstraint(SpringLayout.SOUTH, rankPanel8, 30, SpringLayout.SOUTH, rankPanel7);
      SpringLayout sl_rankPanel7 = new SpringLayout();
      rankPanel7.setLayout(sl_rankPanel7);
      
      
      
      JPanel rankPanel9 = new JPanel();
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankPanel9, 6, SpringLayout.SOUTH, rankPanel8);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankPanel9, 10, SpringLayout.WEST, rank_panel);
      sl_rank_panel.putConstraint(SpringLayout.SOUTH, rankPanel9, 30, SpringLayout.SOUTH, rankPanel8);
      SpringLayout sl_rankPanel8 = new SpringLayout();
      rankPanel8.setLayout(sl_rankPanel8);
      
   
      
      JPanel rankPanel10 = new JPanel();
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankPanel10, 6, SpringLayout.SOUTH, rankPanel9);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankPanel10, 0, SpringLayout.WEST, rankPanel1);
      sl_rank_panel.putConstraint(SpringLayout.SOUTH, rankPanel10, 30, SpringLayout.SOUTH, rankPanel9);
      SpringLayout sl_rankPanel9 = new SpringLayout();
      rankPanel9.setLayout(sl_rankPanel9);
      
   
      
      JCheckBox[] rankCheck = new JCheckBox[11];
      
      OrderByRank obr = new OrderByRank();
      
      rankCheck[1] = new JCheckBox(obr.showInfor(1));
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankCheck[1], 5, SpringLayout.NORTH, rankPanel1);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankCheck[1], 58, SpringLayout.WEST, rankPanel1);
      rankPanel1.add(rankCheck[1]);
      rank_panel.add(rankPanel10);
      SpringLayout sl_rankPanel10 = new SpringLayout();
      rankPanel10.setLayout(sl_rankPanel10);
      
       rankCheck[2] = new JCheckBox(obr.showInfor(2));
      sl_rankPanel2.putConstraint(SpringLayout.NORTH, rankCheck[2], 0, SpringLayout.NORTH, rankPanel2);
      sl_rankPanel2.putConstraint(SpringLayout.WEST, rankCheck[2], 0, SpringLayout.WEST, rankPanel2);
      rankPanel2.add(rankCheck[2]);
   
            
       rankCheck[3] = new JCheckBox(obr.showInfor(3));
      sl_rankPanel3.putConstraint(SpringLayout.NORTH, rankCheck[3], 0, SpringLayout.NORTH, rankPanel3);
      sl_rankPanel3.putConstraint(SpringLayout.WEST, rankCheck[3], 0, SpringLayout.WEST, rankPanel3);
      rankPanel3.add(rankCheck[3]);
   
      
        rankCheck[4] = new JCheckBox(obr.showInfor(4));
      sl_rankPanel4.putConstraint(SpringLayout.NORTH, rankCheck[4], 0, SpringLayout.NORTH, rankPanel4);
      sl_rankPanel4.putConstraint(SpringLayout.WEST, rankCheck[4], 0, SpringLayout.WEST, rankPanel4);
      rankPanel4.add(rankCheck[4]);
      
      
        rankCheck[5] = new JCheckBox(obr.showInfor(5));
      sl_rankPanel5.putConstraint(SpringLayout.NORTH, rankCheck[5], 0, SpringLayout.NORTH, rankPanel5);
      sl_rankPanel5.putConstraint(SpringLayout.WEST, rankCheck[5], 0, SpringLayout.WEST, rankPanel5);
      rankPanel5.add(rankCheck[5]);
      total.add(panel_1);
   
        rankCheck[6] = new JCheckBox(obr.showInfor(6));
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankCheck[6], 0, SpringLayout.NORTH, rankPanel6);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankCheck[6], 0, SpringLayout.WEST, rankPanel6);
      rankPanel6.add(rankCheck[6]);
      sl_rank_panel.putConstraint(SpringLayout.EAST, rankPanel7, 0, SpringLayout.EAST, rankPanel1);
      rank_panel.add(rankPanel7);
   
      
        rankCheck[7] = new JCheckBox(obr.showInfor(7));
      sl_rank_panel.putConstraint(SpringLayout.NORTH, rankCheck[7], 0, SpringLayout.NORTH, rankPanel7);
      sl_rank_panel.putConstraint(SpringLayout.WEST, rankCheck[7], 0, SpringLayout.WEST, rankPanel7);
      rankPanel7.add(rankCheck[7]);
      sl_rank_panel.putConstraint(SpringLayout.EAST, rankPanel8, 0, SpringLayout.EAST, rankPanel1);
      rank_panel.add(rankPanel8);
      
        rankCheck[8] = new JCheckBox(obr.showInfor(8));
      sl_rankPanel8.putConstraint(SpringLayout.NORTH, rankCheck[8], 0, SpringLayout.NORTH, rankPanel8);
      sl_rankPanel8.putConstraint(SpringLayout.WEST, rankCheck[8], 0, SpringLayout.WEST, rankPanel8);
      rankPanel8.add(rankCheck[8]);
   
      sl_rank_panel.putConstraint(SpringLayout.EAST, rankPanel9, 241, SpringLayout.WEST, rank_panel);
      rank_panel.add(rankPanel9);
   
      
        rankCheck[9] = new JCheckBox(obr.showInfor(9));
      sl_rankPanel9.putConstraint(SpringLayout.NORTH, rankCheck[9], 0, SpringLayout.NORTH, rankPanel9);
      sl_rankPanel9.putConstraint(SpringLayout.WEST, rankCheck[9], 0, SpringLayout.WEST, rankPanel9);
      rankPanel9.add(rankCheck[9]);
      sl_rank_panel.putConstraint(SpringLayout.EAST, rankPanel10, 0, SpringLayout.EAST, rankPanel1);
      SpringLayout sl_rankPanel1 = new SpringLayout();
      rankPanel1.setLayout(sl_rankPanel1);
   
      
        rankCheck[10] = new JCheckBox(obr.showInfor(10));
      sl_rankPanel10.putConstraint(SpringLayout.NORTH, rankCheck[10], 0, SpringLayout.NORTH, rankPanel10);
      sl_rankPanel10.putConstraint(SpringLayout.WEST, rankCheck[10], 0, SpringLayout.WEST, rankPanel10);
      rankPanel10.add(rankCheck[10]);
   
      
      JButton btnNewButton = new JButton("곡 담기");
      
      btnNewButton.addActionListener(new ActionListener() {
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
                  
                  insertPL( user_id,  list.get(i));
               }
            }
         }
      });
      
      sl_rank_panel.putConstraint(SpringLayout.NORTH, btnNewButton, 2, SpringLayout.SOUTH, rankPanel10);
      sl_rank_panel.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, rank_panel);
      rank_panel.add(btnNewButton);
      

      
      
      SpringLayout sl_panel_1 = new SpringLayout();
      panel_1.setLayout(sl_panel_1);
      
      JLabel lblNewLabel_1 = new JLabel("광고 넣을거임! 내맘임 ㅋ");
      sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 4, SpringLayout.NORTH, panel_1);
      sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_1, 4, SpringLayout.WEST, panel_1);
      sl_panel_1.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 67, SpringLayout.NORTH, panel_1);
      sl_panel_1.putConstraint(SpringLayout.EAST, lblNewLabel_1, 158, SpringLayout.WEST, panel_1);
      panel_1.add(lblNewLabel_1);

      // 구매하기 버튼 연결

      setSize(638, 500);

      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);// 창이 가운데 나오게
      setVisible(true);
      setResizable(false);// 창의 크기를 변경하지 못하게
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
   
   public static int insertPL(String user_id, int now_rank) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      int result = 0;

      try {
         conn = DBUtil.getConnection();
         pstmt = conn.prepareStatement("INSERT INTO playlist VALUES(?, (SELECT music_id FROM musics WHERE now_rank = ?))");

         pstmt.setString(1, user_id);
         pstmt.setInt(2, now_rank);
      
         result = pstmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();

      } finally { // 사용한 커넥션 닫아주기
         DBUtil.closeStmt(pstmt);
         DBUtil.closeConn(conn);
      }
      return result;
   }
   
	public static void main(String[] args) throws SQLException {
		String user_id = "choi";
		new StreamingMain(user_id);
	}

  
}
