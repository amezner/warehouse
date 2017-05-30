package hu.unideb.inf.warehouse.service;

import org.junit.Before;
import org.junit.Test;
import hu.unideb.inf.warehouse.dao.Loader;
import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.model.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;

import org.junit.Assert;

public class LoaderTest {
    
	private ObservableList<Customer> testCustomers = FXCollections.observableArrayList();
    private ObservableList<Invoice> testInvoices = FXCollections.observableArrayList();
	private ObservableList<Product> testProducts = FXCollections.observableArrayList();
    private URL loadCustomers;
    private URL loadInvoices;
    private URL loadProducts;

    @Before
	public void setUp() throws Exception {
    	loadCustomers = this.getClass().getResource("/test-customers.xml"); ;
    	loadInvoices = this.getClass().getResource("/test-invoices.xml"); ;
    	loadProducts = this.getClass().getResource("/test-products.xml"); 
    }

	@Test
	public void testLoadProducts() {

		testProducts = Loader.loadProducts(loadProducts.getFile());
		
		Assert.assertEquals(2, testProducts.size());
		Assert.assertEquals("Core i5, 8GB RAM, 256GB SSD", testProducts.get(1).getProductDescription());
	}

	@Test
	public void testLoadCustomers() {
		testCustomers = Loader.loadCustomers(loadCustomers.getFile());

		Assert.assertEquals(2, testCustomers.size());
		Assert.assertEquals("6545 Consectetuer Rd.", testCustomers.get(1).getCustomerAddress());
	}

	@Test
	public void testLoadInvoices() {
		testInvoices = Loader.loadInvoices(loadInvoices.getFile());

		Assert.assertEquals(2, testInvoices.size());
		Assert.assertEquals("INV00002", testInvoices.get(1).getInvoiceNumber());
	}

}
