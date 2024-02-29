package com.findingmigo.talent.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.findingmigo.core.dao.BaseDao;
import com.findingmigo.core.exception.AppException;
import com.findingmigo.talent.model.Talent;

/**
 * 所属タレントのデータを管理するDAOクラス。
 */
public class TalentDao extends BaseDao {

    public TalentDao() throws AppException {
        super();
    }

    public List<Talent> findAll() throws AppException {
        List<Talent> list = new ArrayList<>();
        String sql = "SELECT * FROM Members ORDER BY member_id";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractFromResultSet());
            }
        } catch (SQLException e) {
            logger.error("Failed to find all talents", e);
            throw new AppException("情報の取得に失敗しました");
        } finally {
            close();
        }
        return list;
    }

    public Talent findById(Long id) throws AppException {
        String sql = "SELECT * FROM Members WHERE member_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractFromResultSet();
            }
        } catch (SQLException e) {
            logger.error("Failed to find talent by id: {}", id, e);
            throw new AppException("検索に失敗しました");
        } finally {
            close();
        }
        return null;
    }

    public void save(Talent talent) throws AppException {
        String sql;
        if (talent.getId() == null || talent.getId() == 0) {
            sql = "INSERT INTO Members (name, birthday, join_date, hometown, blood_type, age, member_color) VALUES (?, ?, ?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE Members SET name = ?, birthday = ?, join_date = ?, hometown = ?, blood_type = ?, age = ?, member_color = ? WHERE member_id = ?";
        }

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, talent.getName());
            ps.setDate(2, talent.getBirthday());
            ps.setDate(3, talent.getJoinDate());
            ps.setString(4, talent.getHomeTown());
            ps.setString(5, talent.getBloodType());
            ps.setInt(6, talent.getAge());
            ps.setString(7, talent.getMemberColor());

            if (talent.getId() != null && talent.getId() != 0) {
                ps.setLong(8, talent.getId());
            }
            ps.executeUpdate();
            logger.info("Saved talent: {}", talent.getName());
        } catch (SQLException e) {
            logger.error("Failed to save talent", e);
            throw new AppException("保存に失敗しました");
        } finally {
            close();
        }
    }

    public void delete(Long id) throws AppException {
        String sql = "DELETE FROM Members WHERE member_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            logger.info("Deleted talent with id: {}", id);
        } catch (SQLException e) {
            logger.error("Failed to delete talent by id: {}", id, e);
            throw new AppException("削除に失敗しました");
        } finally {
            close();
        }
    }

    private Talent extractFromResultSet() throws SQLException {
        return new Talent(
            rs.getLong("member_id"),
            rs.getString("name"),
            rs.getDate("birthday"),
            rs.getDate("join_date"),
            rs.getString("hometown"),
            rs.getString("blood_type"),
            rs.getInt("age"),
            rs.getString("member_color")
        );
    }
}
