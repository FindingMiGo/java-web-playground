package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.JsysException;
import model.Customer;

public class CustomerDao extends BaseDao {
	private String insertSql = "INSERT INTO Customer (customer_code, customer_name, customer_telno, customer_postalcode, customer_address, discount_rate, delete_flag) VALUES (?, ?, ?, ?, ?, ?, ?)";

	public CustomerDao() throws JsysException {
		super();
	}

	public List<Customer> findAllCustomer() throws JsysException {
		ArrayList<Customer> CustomerList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Customer";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				boolean deleteFlag = rs.getBoolean("delete_flag");
				if (deleteFlag == true) {
					continue;
				}

				String customerCode = rs.getString("customer_code");
				String customerName = rs.getString("customer_name");
				String customerTelno = rs.getString("customer_telno");
				String customerPostalcode = rs.getString("customer_postalcode");
				String customerAddress = rs.getString("customer_address");
				int discountRate = rs.getInt("discount_rate");

				Customer Customer = new Customer(customerCode, customerName, customerTelno, customerPostalcode,
						customerAddress, discountRate, deleteFlag);

				CustomerList.add(Customer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new JsysException("情報の取得に失敗しました");
		} finally {
			close();
		}

		return CustomerList;
	}

	public List<Customer> findCustomer(String search) throws JsysException {
		ArrayList<Customer> CustomerList = new ArrayList<>();

		try {
			String sql = "select * from customer where customer_name LIKE ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				boolean deleteFlag = rs.getBoolean("delete_flag");
				if (deleteFlag == true) {
					continue;
				}

				String customerCode = rs.getString("customer_code");
				String customerName = rs.getString("customer_name");
				String customerTelno = rs.getString("customer_telno");
				String customerPostalcode = rs.getString("customer_postalcode");
				String customerAddress = rs.getString("customer_address");
				int discountRate = rs.getInt("discount_rate");

				Customer Customer = new Customer(customerCode, customerName, customerTelno, customerPostalcode,
						customerAddress, discountRate, deleteFlag);

				CustomerList.add(Customer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new JsysException("情報の取得に失敗しました");
		} finally {
			close();
		}

		return CustomerList;
	}

	//	mysql> desc customer_numbering;
	//	+---------------+------+------+-----+---------+-------+
	//	| Field         | Type | Null | Key | Default | Extra |
	//	+---------------+------+------+-----+---------+-------+
	//	| customer_code | int  | NO   |     | NULL    |       |
	//	+---------------+------+------+-----+---------+-------+

	protected String generateCustomerCode() throws JsysException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String num = null;

		try {
			ps = con.prepareStatement("SELECT * FROM customer_numbering");
			rs = ps.executeQuery();
			if (rs.next()) {
				int customerCode = rs.getInt("customer_code");
				num = "KA" + String.format("%04d", customerCode + 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JsysException(e.getMessage()); // エラーメッセージを渡す
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new JsysException(e.getMessage()); // エラーメッセージを渡す
			}
		}
		return num;
	}

	protected int incrementNum() throws JsysException {
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("UPDATE customer_numbering SET customer_code = customer_code + 1;");
			int rowCount = ps.executeUpdate();
			return rowCount; // 更新された行数を返す
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JsysException(insertSql);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				throw new JsysException(insertSql);
			}
		}
	}

	public String registerCustomer(Customer customer) throws JsysException {
		String num = generateCustomerCode();

		try {
			ps = con.prepareStatement(insertSql);
			ps.setString(1, num);
			ps.setString(2, customer.getCustomerName());
			ps.setString(3, customer.getCustomerTelno());
			ps.setString(4, customer.getCustomerPostalcode());
			ps.setString(5, customer.getCustomerAddress());
			ps.setInt(6, customer.getDiscountRate());
			ps.setBoolean(7, customer.isDeleteFlag());

			ps.executeUpdate();
			incrementNum();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JsysException(insertSql);
		} finally {
			close();
		}
		return num;
	}

	public void updateCustomer(Customer customer) throws JsysException {
		String updateSql = "UPDATE customer\r\n"
				+ "SET customer_name = ?,\n"
				+ "    customer_telno = ?,\n"
				+ "    customer_postalcode = ?,\n"
				+ "    customer_address = ?,\n"
				+ "    discount_rate = ?\n"
				+ "WHERE customer_code = ?;";

		try {
			ps = con.prepareStatement(updateSql);

			ps.setString(1, customer.getCustomerName());
			ps.setString(2, customer.getCustomerTelno());
			ps.setString(3, customer.getCustomerPostalcode());
			ps.setString(4, customer.getCustomerAddress());
			ps.setInt(5, customer.getDiscountRate());
			ps.setString(6, customer.getCustomerCode());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JsysException(updateSql);
		} finally {
			close();
		}
	}

	public void registerCustomer(String customerCode, String customerName, String customerTelno,
			String customerPostalcode,
			String customerAddress, int discountRate, boolean deleteFlag) throws JsysException {
		String num = generateCustomerCode();

		try {
			ps = con.prepareStatement(insertSql);
			ps.setString(1, num);
			ps.setString(2, customerName);
			ps.setString(3, customerTelno);
			ps.setString(4, customerPostalcode);
			ps.setString(5, customerAddress);
			ps.setInt(6, discountRate);
			ps.setBoolean(7, deleteFlag);

			ps.executeUpdate();
			incrementNum();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public Customer findCustomerByCustomerCode(String search) throws JsysException {
		String sql = "SELECT *	FROM customer WHERE customer_code = ?";
		Customer customer = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, search);
			rs = ps.executeQuery();

			if (rs.next()) {
				String CustomerCode = rs.getString("customer_code");
				String customerName = rs.getString("customer_name");
				String customerTelno = rs.getString("customer_telno");
				String customerPostalcode = rs.getString("customer_postalcode");
				String customerAddress = rs.getString("customer_address");
				int discountRate = rs.getInt("discount_rate");
				boolean deleteFlag = rs.getBoolean("delete_flag");

				customer = new Customer(CustomerCode, customerName, customerTelno, customerPostalcode,
						customerAddress, discountRate, deleteFlag);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return customer;
	}

	public void deleteCustomerByCode(String code) throws JsysException {
		String sql = "UPDATE customer SET delete_flag = 1 WHERE customer_code = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new JsysException("得意先の削除に失敗しました。");
		} finally {
			close();
		}
	}

	public void resetCustomer() throws JsysException, SQLException {
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
			ps.executeUpdate();
			ps.close();

			ps = con.prepareStatement("TRUNCATE TABLE Customer");
			ps.executeUpdate();
			ps.close();

			ps = con.prepareStatement("UPDATE customer_numbering SET customer_code = 15");
			ps.executeUpdate();
			ps.close();

			String insertSql = "INSERT INTO customer (customer_code, customer_name, customer_telno, customer_postalcode, customer_address, discount_rate, delete_flag)\n"
					+ "VALUES\n"
					+ "    ('KA0001', 'Aストア', '045-128-3581', '220-0001', '横浜市西区北幸２－１', 0, false),\n"
					+ "    ('KA0002', 'Bストア', '045-128-3582', '220-0002', '横浜市西区浅間２－２', 0, false),\n"
					+ "    ('KA0003', 'Cストア', '045-128-3583', '220-0003', '横浜市西区みなとみらい２－１', 0, false),\n"
					+ "    ('KA0004', 'Dストア', '045-128-3584', '220-0004', '横浜市西区南幸１－４', 0, false),\n"
					+ "    ('KA0005', 'Eストア', '045-128-3585', '220-0005', '横浜市西区北幸２－５', 0, false),\n"
					+ "    ('KA0006', 'Fストア', '045-128-3586', '220-0006', '横浜市西区北幸２－６', 0, false),\n"
					+ "    ('KA0007', 'Gストア', '045-128-3587', '220-0007', '横浜市西区高島１－１', 0, false),\n"
					+ "    ('KA0008', 'Hストア', '045-128-3588', '220-0008', '横浜市西区高島１－２', 0, false),\n"
					+ "    ('KA0009', 'Iストア', '045-128-3589', '220-0009', '横浜市西区高島２－３', 0, false),\n"
					+ "    ('KA0010', 'Jストア', '045-150-3590', '220-0010', '横浜市西区高島２－４', 0, false),\n"
					+ "    ('KA0011', 'Kストア', '045-150-3591', '220-0011', '横浜市西区みなとみらい２－１', 0, false),\n"
					+ "    ('KA0012', 'Lストア', '045-150-3592', '220-0012', '横浜市西区みなとみらい２－２', 0, false),\n"
					+ "    ('KA0013', 'Mストア', '045-150-3593', '220-0013', '横浜市西区みなとみらい２－３', 0, false),\n"
					+ "    ('KA0014', 'Nストア', '045-150-3594', '220-0014', '横浜市西区みなとみらい２－４', 0, false),\n"
					+ "    ('KA0015', 'Oストア', '045-150-3595', '220-0015', '横浜市西区みなとみらい２－５', 0, false);\n";

			ps = con.prepareStatement(insertSql);
			ps.executeUpdate();
			ps.close();

			ps = con.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JsysException("リセットの実行に失敗しました");
		} finally {
			ps.close();
			close();
		}

	}
}