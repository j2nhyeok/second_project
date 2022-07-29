package ManagerLogin;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.SQLException;
import kr.co.greenart.dbutil.DBUtil;

public class TestMusicDao {
	public static void main(String[] args) {
		ManagerMusicsDaoImpl impl = new ManagerMusicsDaoImpl();
		
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			System.out.println(impl.read(conn));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn);
		}
	}
}
