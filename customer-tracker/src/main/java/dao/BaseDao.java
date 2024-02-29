package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.JsysException;

public abstract class BaseDao {
	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;

	public BaseDao() throws JsysException {
		// DBに接続
		getConnection();
	}

	private void getConnection() throws JsysException {
		try {
			if (con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost/jsysdb";
				String user = "root";
				String password = "test";
				con = DriverManager.getConnection(url, user, password);

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new JsysException("JDBCドライバが見つかりません");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JsysException("SQL実行中に例外が発生しました");
		}
	}

	protected void close() throws JsysException {
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
			throw new JsysException("close処理中に例外が発生しました");
		}
	}
}
