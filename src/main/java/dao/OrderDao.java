package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.JsysException;
import model.Order;

/**
 * 受注テーブル（orders）にアクセスするDAOクラス。
 */
public class OrderDao extends BaseDao {

	public OrderDao() throws JsysException {
		super();
	}

	/**
	 * 全ての受注情報を取得します。
	 * 得意先名と担当者名を取得するために結合（JOIN）を行っています。
	 */
	public List<Order> findAllOrders() throws JsysException {
		List<Order> orderList = new ArrayList<>();
		String sql = "SELECT o.order_no, o.customer_code, c.customer_name, o.employee_no, e.employee_name, "
				+ "o.total_price, o.detail_num, o.deliver_date, o.order_date "
				+ "FROM orders o "
				+ "INNER JOIN customer c ON o.customer_code = c.customer_code "
				+ "INNER JOIN employee e ON o.employee_no = e.employee_no "
				+ "ORDER BY o.order_no DESC";

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Order order = new Order(
						rs.getString("order_no"),
						rs.getString("customer_code"),
						rs.getString("customer_name"),
						rs.getString("employee_no"),
						rs.getString("employee_name"),
						rs.getInt("total_price"),
						rs.getInt("detail_num"),
						rs.getDate("deliver_date"),
						rs.getDate("order_date")
				);
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JsysException("受注情報の取得に失敗しました。");
		} finally {
			close();
		}
		return orderList;
	}
}
