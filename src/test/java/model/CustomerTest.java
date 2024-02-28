package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Customerモデルのテストクラス。
 */
public class CustomerTest {

	@Test
	public void testCustomerCreation() {
		Customer customer = new Customer("KA0001", "Aストア", "03-1234-5678", "100-0001", "東京都千代田区", 10, false);
		
		assertEquals("KA0001", customer.getCustomerCode());
		assertEquals("Aストア", customer.getCustomerName());
		assertEquals(10, customer.getDiscountRate());
		assertFalse(customer.isDeleteFlag());
	}

	@Test
	public void testSetterGetter() {
		Customer customer = new Customer();
		customer.setCustomerName("Test Store");
		
		assertEquals("Test Store", customer.getCustomerName());
	}
}
