package hu.unideb.inf.warehouse.dao;

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

/**
 * A Loader osztály metódusainak a tesztelésére szolgáló osztály. Összesen három metódust tesztel,
 * metódusonként pedig két tesztet hajt végre. A tesztelendő állományokat előre elkészítettük.
 * 
 * @author amezner
 *
 */
public class LoaderTest {

	private ObservableList<Customer> testCustomers = FXCollections.observableArrayList();
	private ObservableList<Invoice> testInvoices = FXCollections.observableArrayList();
	private ObservableList<Product> testProducts = FXCollections.observableArrayList();
	private URL loadCustomers;
	private URL loadInvoices;
	private URL loadProducts;

	/**
	 * Loader osztály metódusainak a tesztelése előtt beállítjuk a teszt file-okat.
	 * 
	 */
	@Before
	public void setUp() {
		loadCustomers = this.getClass().getResource("/test-customers.xml");
		loadInvoices = this.getClass().getResource("/test-invoices.xml");
		loadProducts = this.getClass().getResource("/test-products.xml");
	}

	
	/**
	 * A teszthez betöltjük a teszt termékek állományt amely összesen két terméket tartalmaz. 
	 * Ellenőrizzük, hogy a betöltött elemek száma megfelelő-e illetve, hogy a másodikként betöltött elem
	 * leírásaként az elvárt eredményt kaptuk-e.
	 */
	@Test
	public void testLoadProducts() {

		testProducts = Loader.loadProducts(loadProducts.getFile());

		Assert.assertEquals(2, testProducts.size());
		Assert.assertEquals("Core i5, 8GB RAM, 256GB SSD", testProducts.get(1).getProductDescription());
	}

	/**
	 * A teszthez betöltjük a teszt ügyfél állományt amely összesen két ügyfelet tartalmaz. 
	 * Ellenőrizzük, hogy a betöltött elemek száma megfelelő-e illetve, hogy a másodikként betöltött elem
	 * címeként az elvárt eredményt kaptuk-e. 
	 */
	@Test
	public void testLoadCustomers() {
		testCustomers = Loader.loadCustomers(loadCustomers.getFile());

		Assert.assertEquals(2, testCustomers.size());
		Assert.assertEquals("6545 Consectetuer Rd.", testCustomers.get(1).getCustomerAddress());
	}

	/**
	 * A teszthez betöltjük a teszt számla állományt amely összesen két számlát tartalmaz. 
	 * Ellenőrizzük, hogy a betöltött elemek száma megfelelő-e illetve, hogy a másodikként betöltött elem
	 * számlaszámaként az elvárt eredményt kaptuk-e.
	 */
	@Test
	public void testLoadInvoices() {
		testInvoices = Loader.loadInvoices(loadInvoices.getFile());

		Assert.assertEquals(2, testInvoices.size());
		Assert.assertEquals("INV00002", testInvoices.get(1).getInvoiceNumber());
	}

}
