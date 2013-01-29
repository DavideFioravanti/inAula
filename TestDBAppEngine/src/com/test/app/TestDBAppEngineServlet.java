package com.test.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TestDBAppEngineServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");

		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "test";
		String userName = "test";
		String password = "test";
		String driver = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driver).newInstance();

			conn = DriverManager
					.getConnection(url + dbName, userName, password);
			Statement stm = conn.createStatement();

			String sqlInsert = "INSERT INTO persone (nome,eta) VALUES ('Stefano','21')";

			//String sqlUpdate = "UPDATE persone SET eta = 22 WHERE ID = 3";

			//stm.executeUpdate(sqlUpdate);

			String sql = "SELECT * FROM persone";
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getInt("ID"));
				System.out.println(rs.getString("nome"));
				System.out.println(rs.getInt("eta"));

			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
