package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.JaniesException;

public abstract class BaseDao {
	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;

	public BaseDao() throws JaniesException {
		// DBに接続
		getConnection();
	}

	private void getConnection() throws JaniesException {
		try {
			if (con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost/JaniesDB";
				String url = "jdbc:mysql://localhost:3306/JaniesDB?serverTimezone=Asia/Tokyo";

				String user = "root";
				String password = "test";
				con = DriverManager.getConnection(url, user, password);

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new JaniesException("JDBCドライバが見つかりません");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JaniesException("SQL実行中に例外が発生しました");
		}
	}

	protected void close() throws JaniesException {
		try {
			if (con != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new JaniesException("close処理中に例外が発生しました");
		}
	}
}
