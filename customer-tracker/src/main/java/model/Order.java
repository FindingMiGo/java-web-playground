package model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 受注情報を保持するモデルクラス。
 * 得意先や担当従業員の情報も含みます。
 */
public class Order implements Serializable {
	private String orderNo;
	private String customerCode;
	private String customerName; // 表示用に結合して取得
	private String employeeNo;
	private String employeeName; // 表示用に結合して取得
	private int totalPrice;
	private int detailNum;
	private Date deliverDate;
	private Date orderDate;

	public Order() {
	}

	public Order(String orderNo, String customerCode, String customerName, String employeeNo, String employeeName,
			int totalPrice, int detailNum, Date deliverDate, Date orderDate) {
		this.orderNo = orderNo;
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.totalPrice = totalPrice;
		this.detailNum = detailNum;
		this.deliverDate = deliverDate;
		this.orderDate = orderDate;
	}

	// Getters and Setters
	public String getOrderNo() { return orderNo; }
	public void setOrderNo(String orderNo) { this.orderNo = orderNo; }

	public String getCustomerCode() { return customerCode; }
	public void setCustomerCode(String customerCode) { this.customerCode = customerCode; }

	public String getCustomerName() { return customerName; }
	public void setCustomerName(String customerName) { this.customerName = customerName; }

	public String getEmployeeNo() { return employeeNo; }
	public void setEmployeeNo(String employeeNo) { this.employeeNo = employeeNo; }

	public String getEmployeeName() { return employeeName; }
	public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

	public int getTotalPrice() { return totalPrice; }
	public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

	public int getDetailNum() { return detailNum; }
	public void setDetailNum(int detailNum) { this.detailNum = detailNum; }

	public Date getDeliverDate() { return deliverDate; }
	public void setDeliverDate(Date deliverDate) { this.deliverDate = deliverDate; }

	public Date getOrderDate() { return orderDate; }
	public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
}
