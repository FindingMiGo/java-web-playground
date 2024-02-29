package dao;

import java.sql.SQLException;

import exception.JaniesException;
import model.Editor;

public class EditorDao extends BaseDao {

	public EditorDao() throws JaniesException {
		super();
	}

	public Editor doLogin(String editorId, String loginPassword) throws JaniesException {
		Editor loginUser = null;

		try {
			String sql = "SELECT * FROM editor WHERE editor_id = ? AND login_password = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, editorId);
			ps.setString(2, loginPassword);
			rs = ps.executeQuery();
			while (rs.next()) {
				String userId = rs.getString("editor_id");
				String userName = rs.getString("editor_name");
				String password = rs.getString("login_password");
				loginUser = new Editor(userId, userName, password);
			}
			if (loginUser == null) {
				throw new JaniesException("ログインできませんでした");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new JaniesException("SQL実行中に例外が発生しました");
		} finally {
			close();
		}

		return loginUser;
	}
}
