package hu.unideb.inf.warehouse.service;

import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.model.SoldProduct;
import javafx.collections.ObservableList;

public class DiscountService {

	public static boolean customerDiscount(Customer customer, Product product) {
		
		return false;
		
	}
	
	public static int volumeDiscount (ObservableList<SoldProduct> cart) {
		int soldQuantity = 0;
		for (SoldProduct cartItem : cart) {
			soldQuantity = soldQuantity + cartItem.getProductSoldQuantity(); 
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
