import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.greenart.dbutil.DBUtil;

public class PlayListDaoImp implements PlayListDao {

	public PlayList resultMapping(ResultSet rs) throws SQLException {
		int music_id = rs.getInt("music_id");
		String title = rs.getString("title");
		String artist = rs.getString("artist");
		String genre = rs.getString("genre");

		return new PlayList(music_id, title, artist, genre);
	}

	@Override
	public List<PlayList> PersonalPL(Connection conn, String userID) throws SQLException {
		String query = "select p.music_id, p.title, p.artist, p.genre from perplay as p where user_id = ? ORDER BY p.title";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			List<PlayList> temp = new ArrayList<>();
			while (rs.next()) {
				temp.add(resultMapping(rs));
			}
			return temp;
		} finally {
			DBUtil.closeRS(rs);
			DBUtil.closeStmt(pstmt);
		}
	} // id검색
	
	//------------------------------220726 추가---------------------------------------------
	@Override
	public int DeletePL(Connection conn, String userID, List<Integer> check) throws SQLException {
		String query = "DELETE FROM playlist  WHERE user_id = ? AND music_id = ? LIMIT 1";

		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			pstmt = conn.prepareStatement(query);
			for(int i = 0; i < check.size(); i++) {
				pstmt.setString(1, userID);
				pstmt.setInt(2, check.get(i));
				int count = pstmt.executeUpdate();
				cnt += count;
			}
			return cnt;
			
		} finally {
			DBUtil.closeStmt(pstmt);
		}
	}
	//------------------------------220726 추가---------------------------------------------

}
