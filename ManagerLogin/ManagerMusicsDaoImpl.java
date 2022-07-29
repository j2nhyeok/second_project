package ManagerLogin;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.co.greenart.dbutil.DBUtil;
public class ManagerMusicsDaoImpl implements ManagerMusicsDao {
   private Music resultMapping(ResultSet rs) throws SQLException {   // 객체를 변환시켜 내가 원하는 형태의 구조로 정보를 배치시키는 것 = Mapping (문자를 변환하는 건 Parsing)
      int music_id = rs.getInt("music_id");
      String title = rs.getString("title");
      String artist = rs.getString("artist");
      String genre = rs.getString("genre");
      InputStream mp3 = rs.getBinaryStream("mp3");
      int save_count = rs.getInt("save_count");
      int now_rank = rs.getInt("now_rank");

      return new Music(music_id, title, artist, genre, mp3, save_count, now_rank);
   }
   
   // 관리자페이지 음원 목록 삭제 
   public void deleteAdminList(Connection conn, List<Integer> checkId) throws SQLException {
      int cnt = 0;
      String query = "DELETE FROM musics WHERE music_id = ?";
      PreparedStatement pstmt = null;
      
      try {
         pstmt = conn.prepareStatement(query);
         
         for (int i = 0; i < checkId.size(); i++) {
            pstmt.setInt(1, checkId.get(i));
            pstmt.executeUpdate();
            
          
         }
      } finally {
    	  
         DBUtil.closeStmt(pstmt);
      }
   }
   
   // 음원 DB 세로사이
   public int findCnt(Connection conn) throws SQLException {
      int cnt = 0;
      String sql = "SELECT COUNT(*) FROM musics";
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            cnt = rs.getInt("COUNT(*)");
         }
      } finally {
         DBUtil.closeRS(rs);
         DBUtil.closeStmt(pstmt);
      }
      return cnt;
   }
   
   // 관리자 음원 목록 보기
   public List<Music> readAdmin(Connection conn) throws SQLException {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM musics";                    
      List<Music> list = new ArrayList<>();
      try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
               String title = rs.getString("title");
              String artist = rs.getString("artist");
              String genre = rs.getString("genre");
              int save_count = rs.getInt("save_count");
              int now_rank = rs.getInt("now_rank");
              int music_id = rs.getInt("music_id");
              
               list.add(new Music(title, artist, genre , save_count, now_rank, music_id));
            }
            return list;
        } finally {
            DBUtil.closeStmt(pstmt);
            DBUtil.closeRS(rs);
        }
   }
   
   public int lastRank(Connection conn) throws SQLException {
      String sql = "SELECT now_rank FROM musics ORDER BY now_rank DESC LIMIT 1";           
      
      PreparedStatement pstmt =  conn.prepareStatement(sql);
      ResultSet  rs = pstmt.executeQuery(sql);
      int temp = 0;
       if (rs.next()) {
            temp = rs.getInt("now_rank");
         }
       
       
      return temp;
      
   }
   
   // 음원 1개 추가
   @Override
   public int create(Connection conn, String title, String artist, String genre, InputStream mp3, int musicLength)
         throws SQLException {
      
      PreparedStatement pstmt = null;
      String sql = "INSERT INTO musics (title, artist, genre, mp3, now_rank) values (?, ?, ?, ?, ?)";                    
      
      try {
         
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, artist);
            pstmt.setString(3, genre);
            pstmt.setBinaryStream(4, mp3, musicLength);
            pstmt.setInt(5, lastRank(conn) + 1);
            return pstmt.executeUpdate();
        } finally {
            DBUtil.closeStmt(pstmt);
        }
   }

   // 전체 음원 목록 받기
   @Override
   public List<Music> read(Connection conn) throws SQLException {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM musics";                    
      List<Music> list = new ArrayList<>();
      try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
               list.add(resultMapping(rs));
            }
            return list;
        } finally {
            DBUtil.closeStmt(pstmt);
            DBUtil.closeRS(rs);
        }
   }
   
   

   @Override
   public Music read(Connection conn, int music_id) throws SQLException {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM musics WHERE music_id = ?";                    
      List<Music> list = new ArrayList<>();
      try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, music_id);
            rs = pstmt.executeQuery(sql);
            
            if (rs.next()) {
               return (resultMapping(rs));
            }
        } finally {
            DBUtil.closeStmt(pstmt);
            DBUtil.closeRS(rs);
        }
      return null;
   }

   @Override
   public int update(Connection conn, String title, String artist, String genre, InputStream mp3, int mp3Length, int music_id)
         throws SQLException {
      PreparedStatement pstmt = null;
      String sql = "UPDATE musics SET title = ?, artist = ?, genre = ?, mp3 = ? WHERE music_id = ?";    
      String sql2 = "UPDATE musics SET mp3 = ? WHERE music_id = ?";  
      
      try {
//         pstmt2 = conn.prepareStatement(sql2);
//         pstmt2.setBlob(1, null);
//         pstmt2.setInt(2, music_id);
         
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, artist);
            pstmt.setString(3, genre);
            pstmt.setBinaryStream(4, mp3, mp3Length); 
            pstmt.setInt(5, music_id);
            
            return pstmt.executeUpdate();
        } finally {
//           DBUtil.closeStmt(pstmt2);
            DBUtil.closeStmt(pstmt);
        }
   }

   @Override
   public int delete(Connection conn, int music_id) throws SQLException {
      PreparedStatement pstmt = null;
      String sql = "DELETE FROM musics WHERE music_id = ?";                    
      try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, music_id);
            return pstmt.executeUpdate();
        } finally {
            DBUtil.closeStmt(pstmt);
        }
   }
}