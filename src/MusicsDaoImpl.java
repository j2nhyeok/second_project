import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import kr.co.greenart.dbutil.DBUtil;
public class MusicsDaoImpl implements MusicsDao {
	private Music resultMapping(ResultSet rs) throws SQLException { // 객체를 변환시켜 내가 원하는 형태의 구조로 정보를 배치시키는 것 = Mapping
																	// (문자를 변환하는 건 Parsing)
		int music_id = rs.getInt("music_id");
		String title = rs.getString("title");
		String artist = rs.getString("artist");
		String genre = rs.getString("genre");
		InputStream mp3 = rs.getBinaryStream("mp3");
		int save_count = rs.getInt("save_count");
		int now_rank = rs.getInt("now_rank");

		return new Music(music_id, title, artist, genre, mp3, save_count, now_rank);
	}

	// 음원 1개 추가
	@Override
	public int create(Connection conn, String title, String artist, String genre, InputStream music, int musicLength)
			throws SQLException {

		PreparedStatement pstmt = null;
		String sql = "INSERT INTO musics (title, artist, genre, music) values (?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, artist);
			pstmt.setString(3, genre);
			pstmt.setBinaryStream(4, music, musicLength);

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
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return (resultMapping(rs));
			}
		} finally {
			DBUtil.closeStmt(pstmt);
			DBUtil.closeRS(rs);
		}
		return null;
	}

	public int setTotalPlay(Connection conn, int totalplay, int music_id) throws SQLException {
		PreparedStatement pstmt = null;

		String sql = "update musics set total_play = ? where music_id = ?";
		List<Music> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, totalplay += 1);
			pstmt.setInt(2, music_id);

			return pstmt.executeUpdate();

		} finally {
			DBUtil.closeStmt(pstmt);

		}

	}


	@Override
	public int update(Connection conn, String title, String artist, String genre, InputStream mp3, int mp3Length)
			throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "UPDATE musics SET title = ?, artist = ?, genre = ?, mp3 = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, artist);
			pstmt.setString(3, genre);
			pstmt.setBinaryStream(4, mp3, mp3Length);

			return pstmt.executeUpdate();
		} finally {
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
	
	@Override
	public int totalCntMusics(Connection conn) throws SQLException {
		conn = DBUtil.getConnection();
		int cnt = 0;
		String sql = "SELECT COUNT(*) FROM musics";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			cnt =  rs.getInt("COUNT(*)");
		}
		DBUtil.closeRS(rs);
		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);

		return cnt;
	}
	
	
	public int rtSaveCnt(Connection conn, int music_id) throws SQLException{
		conn = DBUtil.getConnection();
		
		int cnt = 0;
		String sql = "SELECT save_count FROM musics where music_id  = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, music_id);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			cnt =  rs.getInt("save_count");
		}
		DBUtil.closeRS(rs);
		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);

		return cnt;
	}
	
	public int rtNowRank(Connection conn, int music_id) throws SQLException{
		conn = DBUtil.getConnection();
		
		int cnt = 0;
		String sql = "SELECT now_rank FROM musics where music_id  = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, music_id);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			cnt =  rs.getInt("now_rank");
		}
		DBUtil.closeRS(rs);
		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);

		return cnt;
	}
}
