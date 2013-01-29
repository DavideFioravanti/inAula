import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestDatabase {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Connection conn = null;
		String url ="jdbc:mysql://localhost:3306/";
		String dbName = "test";
		String userName = "test";
		String password = "test";
		String driver = "com.mysql.jdbc.Driver";
		
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url+dbName,userName,password);
		Statement stm = conn.createStatement();
		
		String sqlInsert = "INSERT INTO persone (nome,eta) VALUES ('Stefano','21')";
		
		String sqlUpdate = "UPDATE persone SET eta = 22 WHERE ID = 3";
		
		stm.executeUpdate(sqlUpdate);
		
		String sql = "SELECT * FROM persone";
		ResultSet rs = stm.executeQuery(sql);
		
		while (rs.next()) {
			System.out.println(rs.getInt("ID"));
			System.out.println(rs.getString("nome"));
			System.out.println(rs.getInt("eta"));
		
		}
		
		
	}

}
