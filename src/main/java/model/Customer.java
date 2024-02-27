package model;

//mysql> desc customer;
//+---------------------+-------------+------+-----+---------+-------+
//| Field               | Type        | Null | Key | Default | Extra |
//+---------------------+-------------+------+-----+---------+-------+
//| customer_code       | varchar(6)  | NO   | PRI | NULL    |       |
//| customer_name       | varchar(32) | YES  |     | NULL    |       |
//| customer_telno      | varchar(13) | YES  |     | NULL    |       |
//| customer_postalcode | varchar(8)  | YES  |     | NULL    |       |
//| customer_address    | varchar(40) | YES  |     | NULL    |       |
//| discount_rate       | int         | YES  |     | NULL    |       |
//| delete_flag         | bit(1)      | NO   |     | b'0'    |       |
//+---------------------+-------------+------+-----+---------+-------+

public class Customer {
	private String customerCode;
	private String customerName;
	private String customerTelno;
	private String customerPostalcode;
	private String customerAddress;
	private int discountRate;
	private boolean deleteFlag;

	public Customer(String customerCode, String customerName, String customerTelno, String customerPostalcode,
			String customerAddress, int discountRate, boolean deleteFlag) {
		super();
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.customerTelno = customerTelno;
		this.customerPostalcode = customerPostalcode;
		this.customerAddress = customerAddress;
		this.discountRate = discountRate;
		this.deleteFlag = deleteFlag;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTelno() {
		return customerTelno;
	}

	public void setCustomerTelno(String customerTelno) {
		this.customerTelno = customerTelno;
	}

	public String getCustomerPostalcode() {
		return customerPostalcode;
	}

	public void setCustomerPostalcode(String customerPostalcode) {
		this.customerPostalcode = customerPostalcode;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	@Override
	public String toString() {
		return "Customer [customerCode=" + customerCode + ", customerName=" + customerName + ", customerTelno="
				+ customerTelno + ", customerPostalcode=" + customerPostalcode + ", customerAddress=" + customerAddress
				+ ", discountRate=" + discountRate + ", deleteFlag=" + deleteFlag + "]";
	}
}
