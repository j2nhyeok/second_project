import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PlayListDao {
	
	public List<PlayList> PersonalPL(Connection conn, String userID) throws SQLException; //id검색 
	//------------------------------220726 수정---------------------------------------------
	public int DeletePL(Connection conn, String userID, List<Integer> check) throws SQLException;
	//------------------------------220726 수정---------------------------------------------
	

}
