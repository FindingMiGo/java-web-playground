package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import exception.JaniesException;
import model.Janies;

public class JaniesDao extends BaseDao {
	public JaniesDao() throws JaniesException {
		super();
	}

	public List<Janies> findAllJanies() throws JaniesException {
		ArrayList<Janies> janiesList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Members";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				long janiesId = rs.getLong("member_id");
				String janiesName = rs.getString("name");
				Janies janies = new Janies(janiesId, janiesName);
				janiesList.add(janies);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new JaniesException("情報の取得に失敗しました");
		} finally {
			close();
		}

		return janiesList;
	}

	public List<Janies> findJanies(String name) throws JaniesException {
		ArrayList<Janies> janiesList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Members WHERE name LIKE ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				long janiesId = rs.getLong("member_id");
				String janiesName = rs.getString("name");
				Janies janies = new Janies(janiesId, janiesName);
				janiesList.add(janies);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new JaniesException("情報の取得に失敗しました");
		} finally {
			close();
		}

		return janiesList;
	}

	public Janies findJanies(int id) throws JaniesException, ParseException {
		Janies janies = null;

		try {
			String sql = "SELECT * FROM Members WHERE member_id LIKE ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				String janiesId = rs.getString("member_id");
				String janiesName = rs.getString("name");
				String janiesBirthday = rs.getString("birthday");
				String janiesJoinDate = rs.getString("join_date");
				String janiesHomeTown = rs.getString("hometown");
				String janiesBloodType = rs.getString("blood_type");
				String janiesAge = rs.getString("age");
				String janiesColor = rs.getString("member_color");

				janies = new Janies(janiesId, janiesName, janiesBirthday, janiesJoinDate, janiesHomeTown,
						janiesBloodType, janiesAge, janiesColor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new JaniesException("情報の取得に失敗しました");
		}

		return janies;
	}

	public void registerJanies(Janies janies) throws SQLException {
		String sql;
		Long id = janies.getId();

		if (id == 0) {
			sql = "INSERT INTO Members (name, birthday, join_date, hometown, blood_type, age, member_color) VALUES (?, ?, ?, ?, ?, ?, ?)";
		} else {
			sql = "UPDATE Members SET name = ?, birthday = ?, join_date = ?, hometown = ?, blood_type = ?, age = ?, member_color = ? WHERE member_id = ?";
		}

		ps = con.prepareStatement(sql);
		ps.setString(1, janies.getName());
		ps.setDate(2, janies.getBirthday());
		ps.setDate(3, janies.getJoinDate());
		ps.setString(4, janies.getHomeTown());
		ps.setString(5, janies.getBloodType());
		ps.setInt(6, janies.getAge());
		ps.setString(7, janies.getMemberColor());

		if (id != 0) {
			ps.setLong(8, id);
		}

		ps.executeUpdate();
	}

	public void deleteJanies(Long id) throws SQLException {
		String sql;

		sql = "DELETE FROM Members WHERE member_id = ?";

		ps = con.prepareStatement(sql);
		ps.setLong(1, id);
		ps.executeUpdate();
	}

	public void resetJanies() throws SQLException {
		String sql;

		sql = "TRUNCATE TABLE Members";
		ps = con.prepareStatement(sql);
		ps.executeUpdate();

		sql = "INSERT INTO Members (name, birthday, join_date, hometown, blood_type, age, member_color)\n"
				+ "VALUES\n"
				+ "    ('岩本照', '1993-05-16', '2006-10-01', '埼玉県', 'A', 30, '黄色'),\n"
				+ "    ('深澤辰哉', '1992-05-05', '2004-08-12', '東京都', 'B', 31, '紫'),\n"
				+ "    ('ラウール', '2003-06-27', '2015-05-02', '東京都', 'A', 20, '白'),\n"
				+ "    ('渡辺翔太', '1992-11-05', '2005-06-04', '東京都', 'B', 30, '青'),\n"
				+ "    ('向井康二', '1994-06-21', '2006-10-08', '奈良県', 'A', 29, 'オレンジ'),\n"
				+ "    ('阿部亮平', '1993-11-27', '2004-08-12', '千葉県', 'AB', 29, '緑'),\n"
				+ "    ('目黒蓮', '1997-02-16', '2010-10-31', '東京都', 'B', 26, '黒'),\n"
				+ "    ('宮舘涼太', '1993-03-25', '2005-10-01', '東京都', 'A', 30, '赤'),\n"
				+ "    ('佐久間大介', '1992-07-05', '2005-09-25', '東京都', 'O', 31, 'ピンク');\n"
				+ "";

		ps = con.prepareStatement(sql);
		ps.executeUpdate();
	}

}