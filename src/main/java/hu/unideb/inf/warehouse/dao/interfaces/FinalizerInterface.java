package hu.unideb.inf.warehouse.dao.interfaces;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Invoice;
import hu.unideb.inf.warehouse.model.Product;


/**
 * Adatperzisztálást leíró interfésze.
 * 
 * @author amezner
 *
 */
public interface FinalizerInterface {

	/**
	 * Termékek elmentését végző metódus.
	 * Paraméterként kapja meg a {@link hu.unideb.inf.warehouse.model.Product} termékeket tartalmazó listát.   
	 * Alapértelmezett file : products.xml
	 * 
	 * @param products egy lista a lementendő termékekről
	 * @throws ParserConfigurationException kivételt nem kezeli osztályon belül
	 * @throws TransformerException kivételt nem kezeli osztályon belül
	 */
	void finalizeProducts(List<Product> products);

	/**
	 * Ügyfelek elmentését végző metódus.
	 * Paraméterként kapja meg a {@link hu.unideb.inf.warehouse.model.Customer} ügyfeleket tartalmazó listát.   
	 * Alapértelmezett file : customers.xml
	 * 
	 * @param customers egy lista a lementendő ügyfelekről.
	 * @throws ParserConfigurationException kivételt nem kezeli
	 * @throws TransformerException kivételt nem kezeli
	 */
	void finalizeCustomers(List<Customer> customers);

	/**
	 * Számla adatbázis elmentését végző metódus.
	 * Paraméterként kapja meg a {@link hu.unideb.inf.warehouse.model.Invoice} számlákat tartalmazó listát.   
	 * Alapértelmezett file : invoices.xml
	 * 
	 * @param invoices egy lista a lementendő számlákról.
	 * @throws ParserConfigurationException kivételt nem kezeli
	 * @throws TransformerException kivételt nem kezeli
	 */
	void finalizeInvoices(List<Invoice> invoices);

}