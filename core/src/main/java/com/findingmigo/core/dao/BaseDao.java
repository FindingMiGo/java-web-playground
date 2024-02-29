package com.findingmigo.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.findingmigo.core.exception.AppException;

/**
 * データベース接続の基底クラス。
 */
public abstract class BaseDao {
    protected static final Logger logger = LoggerFactory.getLogger(BaseDao.class);
    protected Connection con = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;

    // TODO: 実際は設定ファイルから読み込むようにするのが望ましい
    private static final String URL = "jdbc:mysql://localhost/app_db";
    private static final String USER = "root";
    private static final String PASS = "test";

    public BaseDao() throws AppException {
        getConnection();
    }

    private void getConnection() throws AppException {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (Exception e) {
            logger.error("DB connection error", e);
            throw new AppException("データベース接続に失敗しました");
        }
    }

    protected void close() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            logger.warn("Close resource error", e);
        }
    }
}
