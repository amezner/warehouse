package hu.unideb.inf.warehouse.service;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Invoice;
import hu.unideb.inf.warehouse.model.SoldProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Az InvoiceHandler osztály metódusainak a tesztelésére szolgáló osztály. 
 * Jelenleg csak a generateNextInvoiceNumber metódust teszteli.
 * 
 * @author amezner
 *
 */
public class InvoiceHandlerTest {

	private Customer testCustomer;
	private Invoice testInvoice1, testInvoice2;
	private SoldProduct testSoldProduct1, testSoldProduct2;
	private ObservableList<SoldProduct> testSoldProducts = FXCollections.observableArrayList();
	private ObservableList<Invoice> testInvoices = FXCollections.observableArrayList();
	
	/**
	 * InvoiceHandler osztály metódusainak a tesztelése előtt beállítjuk a tesztelendő értékeket.
	 */
	@Before
	public void setUp() {
		
		testCustomer = new Customer( "CUS0001", 
									 "Test Buyer", 
									 "Kossuth tér 2.", 
									 "Budapest", 
									 "1051", 
									 "Magyarország",
									 "email.cimem@inf.unideb.hu", 
									 "+36 1 555 0000", 
									 "Alap", 
									 0);
		testSoldProduct1 = new SoldProduct("TE00001", "TESTProduct1", "TestTYPE", 100.0, 100);
		testSoldProduct2 = new SoldProduct("TE00001", "TESTProduct2", "TestTYPE", 100.0, 100);
		testInvoice1 = new Invoice("INV55555", LocalDate.now().toString(), 0, testCustomer, testSoldProducts);
		testInvoice2 = new Invoice("INV66666", LocalDate.now().toString(), 0, testCustomer, testSoldProducts);
		testSoldProducts.add(testSoldProduct1);
		testSoldProducts.add(testSoldProduct2);
		testInvoices.add(testInvoice2);
	}

	/**
	 * Azt teszteljük, hogy megfelelő-e a visszakapott számlaszám.
	 * Szándékosan kisebb értéket ad hozzá másodikként, hogy a rendezést is teszteljük. 
	 */
	@Test
	public void test() {
		Assert.assertEquals("INV66667", InvoiceHandler.generateNextInvoiceNumber(testInvoices));
		testInvoices.add(testInvoice1);
		Assert.assertEquals("INV66667", InvoiceHandler.generateNextInvoiceNumber(testInvoices));
	}

}
