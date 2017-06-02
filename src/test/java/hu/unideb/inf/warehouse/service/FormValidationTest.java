package hu.unideb.inf.warehouse.service;

import org.junit.Before;
import org.junit.Test;

import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Product;
import org.junit.Assert;

public class FormValidationTest {

	private Product testProduct1;
	private Product testProduct2;

	private Customer testCustomer1;
	private Customer testCustomer2;

	@Before
	public void setUp() {

		// Minden paraméter megfelelő
		testProduct1 = new Product("TE00001", "TESTProduct1", "TestTYPE", 100, 105, 50, "Description");
		testCustomer1 = new Customer("CUS0001", 
									 "Test Buyer", 
									 "Kossuth tér 2.", 
									 "Budapest", 
									 "1051", 
									 "Magyarország",
									 "email.cimem@inf.unideb.hu", 
									 "+36 1 555 0000", 
									 "Alap", 
									 0);

		// Egyik érték sem felel meg a követelményeknek
		testProduct2 = new Product("TE0001", "TESTProduct2!", "TestTYPE!", 100, 99, 0, "");
		testCustomer2 = new Customer("CUS001", 
									 "Test Buyer!", 
									 "Hibás cím=em", 
									 "Város/Hiba", 
									 "Tízötvenegy", 
									 "Magyar+Ország", 
									 "email@enyem", 
									 "06 1 555 000", 
									 "Alap", 
									 100);

	}

	@Test
	public void testValidateCustomerID() {
		Assert.assertTrue(FormValidation.validateCustomerID(testCustomer1.getCustomerID(), false));
		Assert.assertFalse(FormValidation.validateCustomerID(testCustomer2.getCustomerID(), false));
	}

	@Test
	public void testValidateCustomerName() {
		Assert.assertTrue(FormValidation.validateCustomerName(testCustomer1.getCustomerName(), false));
		Assert.assertFalse(FormValidation.validateCustomerName(testCustomer2.getCustomerName(), false));
	}

	@Test
	public void testValidateCustomerPostCode() {
		Assert.assertTrue(FormValidation.validateCustomerPostCode(testCustomer1.getCustomerPostCode(), false));
		Assert.assertFalse(FormValidation.validateCustomerPostCode(testCustomer2.getCustomerPostCode(), false));
	}

	@Test
	public void testValidateCustomerCountry() {
		Assert.assertTrue(FormValidation.validateCustomerCountry(testCustomer1.getCustomerCountry(), false));
		Assert.assertFalse(FormValidation.validateCustomerCountry(testCustomer2.getCustomerCountry(), false));
	}

	@Test
	public void testValidateCustomerCity() {
		Assert.assertTrue(FormValidation.validateCustomerCity(testCustomer1.getCustomerCity(), false));
		Assert.assertFalse(FormValidation.validateCustomerCity(testCustomer2.getCustomerCity(), false));
	}

	@Test
	public void testValidateCustomerAddress() {
		Assert.assertTrue(FormValidation.validateCustomerAddress(testCustomer1.getCustomerAddress(), false));
		Assert.assertFalse(FormValidation.validateCustomerAddress(testCustomer2.getCustomerAddress(), false));
	}

	@Test
	public void testValidateCustomerPhone() {
		Assert.assertTrue(FormValidation.validateCustomerPhone(testCustomer1.getCustomerPhone(), false));
		Assert.assertFalse(FormValidation.validateCustomerPhone(testCustomer2.getCustomerPhone(), false));
	}

	@Test
	public void testValidateCustomerEmail() {
		Assert.assertTrue(FormValidation.validateCustomerEmail(testCustomer1.getCustomerEmail(), false));
		Assert.assertFalse(FormValidation.validateCustomerEmail(testCustomer2.getCustomerEmail(), false));
	}

	@Test
	public void testValidateCustomerDiscount() {
		Assert.assertTrue(FormValidation.validateCustomerDiscount(testCustomer1.getCustomerDiscount().toString(), false));
		Assert.assertFalse(FormValidation.validateCustomerDiscount(testCustomer2.getCustomerDiscount().toString(), false));
	}

	@Test
	public void testValidateProductID() {
		Assert.assertTrue(FormValidation.validateProductID(testProduct1.getProductID(), false));
		Assert.assertFalse(FormValidation.validateProductID(testProduct2.getProductID(), false));
	}

	@Test
	public void testValidateProductName() {
		Assert.assertTrue(FormValidation.validateProductName(testProduct1.getProductName(), false));
		Assert.assertFalse(FormValidation.validateProductName(testProduct2.getProductName(), false));
	}

	@Test
	public void testValidateProductType() {
		Assert.assertTrue(FormValidation.validateProductType(testProduct1.getProductType(), false));
		Assert.assertFalse(FormValidation.validateProductType(testProduct2.getProductType(), false));
	}

	@Test
	public void testValidateProductPurchasePrice() {
		Assert.assertTrue(FormValidation.validateProductPurchasePrice(testProduct1.getProductPurchasePrice().toString(), false));
		Assert.assertFalse(FormValidation.validateProductPurchasePrice("NemSzám", false));	
	}

	@Test
	public void testValidateProductSellingPrice() {
		Assert.assertTrue(FormValidation.validateProductSellingPrice(testProduct1.getProductSellingPrice().toString(), false));
		Assert.assertFalse(FormValidation.validateProductSellingPrice("NemSzám", false));
	}

	@Test
	public void testValidateProductOnStock() {
		Assert.assertTrue(FormValidation.validateProductOnStock(testProduct1.getProductOnStock().toString(), false));
		Assert.assertFalse(FormValidation.validateProductOnStock(testProduct2.getProductOnStock().toString(), false));
	}

	@Test
	public void testValidateProfit() {
		Assert.assertTrue(FormValidation.validateProfit(testProduct1.getProductPurchasePrice().toString(),
														testProduct1.getProductSellingPrice().toString(), false));
		Assert.assertFalse(FormValidation.validateProfit(testProduct2.getProductPurchasePrice().toString(),
														 testProduct2.getProductSellingPrice().toString(), false));
	}

}
