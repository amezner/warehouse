package hu.unideb.inf.warehouse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.model.SoldProduct;
import javafx.collections.ObservableList;

/**
 * Kedvezményeket felügyelő osztály. 
 * 
 * @author amezner
 *
 */
public class DiscountService {

	private static Logger logger = LoggerFactory.getLogger(DiscountService.class);
	

	/**
	 * Profit kontrol céljából létrehozott metódus. Ez biztosítja, hogy nem értékesítünk veszteséggel.
	 * 
	 * @param customer vizsgálandó ügyfél
	 * @param product vizsgálandó termék
	 * @return igaz amenniyben a termék haszonnal értékesíthető.
	 */
	public static boolean customerDiscount(Customer customer, Product product) {
		if (product.getProductSellingPrice() * (100-customer.getCustomerDiscount()) / 100 > product.getProductPurchasePrice() ) {
			logger.info("Eladható termék, van rajta haszon");
			return true;
		} else {
			logger.info("Ezen a terméken buknánk így nem adjuk oda a vevőnek.");
			return false;
		}
		
	}
	
	
	/**
	 * Mennyiségi kedvezményt vizsgáló metódus. A kedvezmény mértéke 101-500 darab terméknél 1%, 501-1000 darab terméknél 2% illetve 1000+ darab termék felett 3%.
	 * Amennyiben kevesebb, mint 100 darab termék van a paraméterként kapott listában akkor nincs kedvezmény.
	 *  
	 * @param cart ebben a listában lévő termékek mennyiségét használjuk a kedvezmény kiszámításához.
	 * @return egész szám, amely később a kedvezményt mutatja.
	 */
	public static int volumeDiscount (ObservableList<SoldProduct> cart) {
		int soldQuantity = 0;
		for (SoldProduct cartItem : cart) {
			soldQuantity = soldQuantity + cartItem.getSoldProductSoldQuantity(); 
		}
		if (soldQuantity < 100)
			return 0;
		else if (soldQuantity < 500)
			return 1;
		else if (soldQuantity < 1000)
			return 2;
		else 
			return 3;
	
	}
}
