package hu.unideb.inf.warehouse.service;

import org.junit.Before;
import org.junit.Test;

import hu.unideb.inf.warehouse.model.Product;

public class ProductTest {

    @Before
    public void setup() {    	
    	Product product1 = new Product("TE0001","TESTProduct1","TestTYPE2",100,200,50,"Description");
    	Product product2 = new Product("TE0002","TESTProduct2","TestTYPE2",100,200,100,"Description");

    }
    
    @Test
    public void testMockCreation() {
    	
    }
}
