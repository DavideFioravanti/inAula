package pdm.test.sensori;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class ListaSensori
 */
@WebServlet("/lista")
public class ListaSensori extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaSensori() {
        super();
        // TODO Auto-generated constructor stub
    }
    private Connection conn = null;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "sensori";
		String userName = "sensori";
		String password = "sensori";
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().println("getlista");
		Statement stm;
		try {
			stm = conn.createStatement();


			String sql = "SELECT * FROM sensori";
			ResultSet rs = stm.executeQuery(sql);

			JSONArray arr = new JSONArray();
		
			
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				
				obj.put("id", rs.getInt("id")).put("nome", rs.getString("nome"));
				
				
				arr.put(obj);
				
//				System.out.println(rs.getInt("ID"));
//				System.out.println(rs.getString("nome"));
//				System.out.println(rs.getString("stato"));

			}
			
			
			
			
			response.getWriter().println(arr.toString());
			
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
