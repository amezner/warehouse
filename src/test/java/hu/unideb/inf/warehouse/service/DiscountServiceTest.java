package hu.unideb.inf.warehouse.service;

import org.junit.Before;
import org.junit.Test;

import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.model.SoldProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;

/**
 * A Discount osztály metódusainak a tesztelésére szolgáló osztály. Összesen két metódust tesztel,
 * metódusonként pedig két illetve négy tesztet hajt végre. 
 * 
 * @author amezner
 *
 */
public class DiscountServiceTest {

	private ObservableList<SoldProduct> cart;
	
	private Product testProduct1; 
	private Product testProduct2; 
	
	private Customer testCustomer1;

	private SoldProduct soldProduct1;
	private SoldProduct soldProduct2;
	private SoldProduct soldProduct3;
	private SoldProduct soldProduct4;
	
	/**
	 * Discount osztály metódusainak a tesztelése előtt beállítjuk a teszt értékeket.
	 */	
	@Before
	public void setUp() {
    	
		cart = FXCollections.observableArrayList();
		
		testProduct1 = new Product("TE00001","TESTProduct1","TestTYPE",100,105,50,"Description");
    	testProduct2 = new Product("TE00002","TESTProduct2","TestTYPE",100,120,1000,"Description");
		
    	testCustomer1 = new Customer ("CUS0001", "Test Buyer", "A", "A", "A", "A", "A", "A", "Ezüst", 10);
		
    	soldProduct1 = new SoldProduct ("TE00001", "TestProduct1", "TestTYPE", 100.0, 1);
		soldProduct2 = new SoldProduct ("TE00002", "TestProduct2", "TestTYPE", 100.0, 100);
		soldProduct3 = new SoldProduct ("TE00002", "TestProduct2", "TestTYPE", 100.0, 400);
		soldProduct4 = new SoldProduct ("TE00002", "TestProduct2", "TestTYPE", 100.0, 500);
	}

	/**
	 * Két tesztet hajtunk végre.
	 * Az első tesztben hibás míg a második tesztben rendben lévő adatokat tesztelünk.  
	 */
	@Test
	public void testCustomerDiscount() {
		Assert.assertFalse(DiscountService.customerDiscount(testCustomer1, testProduct1));
		Assert.assertTrue(DiscountService.customerDiscount(testCustomer1, testProduct2));
	}

	/**
	 * Négy tesztet hajtunk végre.
	 * Gyakorlatilag leteszteljük az összes lehetséges visszatérési értéket.
	 */
	@Test
	public void testVolumeDiscount() {
		cart.add(soldProduct1);
		Assert.assertEquals("Nincs mennyiségi kedvezmény", 0, DiscountService.volumeDiscount(cart));
		cart.add(soldProduct2);
		Assert.assertEquals("1-et kellett volna kapnunk", 1 , DiscountService.volumeDiscount(cart));
		cart.add(soldProduct3);
		Assert.assertEquals("2-t kellett volna kapnunk", 2 , DiscountService.volumeDiscount(cart));
		cart.add(soldProduct4);
		Assert.assertEquals("3-at kellett volna kapnunk", 3 , DiscountService.volumeDiscount(cart));
	}

}
