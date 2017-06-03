package hu.unideb.inf.warehouse.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Invoice;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.model.SoldProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Az adatok XML-ből történő beolvasását megvalósító osztály.
 * 
 * @author amezner
 * 
 */
public class Loader {

    /**
     * A naplózáshoz használt példány.
     */
	private static Logger logger = LoggerFactory.getLogger(Loader.class);
    
    /**
     * A termékek XML-ből történő beolvasását megvalósító metódus.
     * 
     * @param fileName a beolvasandó file neve
     * @return egy listában adja vissza a betöltött elemeket
     */
     public static ObservableList<Product> loadProducts(String fileName) {
		
    	ObservableList<Product> products = FXCollections.observableArrayList();

		try (FileInputStream xmlFile = new FileInputStream(fileName)) {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			try {

				dBuilder = dbFactory.newDocumentBuilder();
				Document doc;
				try {

					doc = dBuilder.parse(xmlFile);
					doc.getDocumentElement().normalize();
					NodeList nlist = doc.getElementsByTagName("product");
					
					for (int i = 0; i < nlist.getLength(); i++) {
						logger.info("Beolvasott elem szama : " + i);

						Node node = nlist.item(i);
						logger.info(node.getNodeName());
						
						Element productElement = (Element) node;
						products.add( new Product(
							productElement.getAttribute("productID"),
							productElement.getElementsByTagName("productName").item(0).getTextContent(),
							productElement.getElementsByTagName("productType").item(0).getTextContent(),
							Integer.parseInt(productElement.getElementsByTagName("productPurchasePrice").item(0).getTextContent()),
							Integer.parseInt(productElement.getElementsByTagName("productSellingPrice").item(0).getTextContent()),
							Integer.parseInt(productElement.getElementsByTagName("productOnStock").item(0).getTextContent()),
							productElement.getElementsByTagName("productDescription").item(0).getTextContent()));
					}
				} catch (SAXException e) {
					logger.error("XML feldolgozási hiba: " + e.getMessage());
				}
			} catch (ParserConfigurationException e) {
				logger.error("XML feldolgozási hiba: " + e.getMessage());
			}
		} catch (FileNotFoundException e) {
			logger.error(fileName + "nem található: " + e.getMessage());
		} catch (IOException e1) {
			logger.error(fileName + "IO hiba: " + e1.getMessage());
		}
		return products;
	}
    
    /**
     * Az ügyfelek XML-ből történő beolvasását megvalósító metódus.
     * 
     * @param fileName betöltendő file neve
     * @return egy listában adja vissza a betöltött elemeket
     */
    public static ObservableList<Customer> loadCustomers(String fileName) {
    	
    	ObservableList<Customer> customers = FXCollections.observableArrayList();

		try (FileInputStream xmlFile = new FileInputStream(fileName)) {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			try {
				
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc;
				try {
					
					doc = dBuilder.parse(xmlFile);
					doc.getDocumentElement().normalize();
					NodeList nlist = doc.getElementsByTagName("customer");
					
					for (int i = 0; i < nlist.getLength(); i++) {
						logger.info("Beolvasott elem szama : " + i);
			
						Node node = nlist.item(i);
						logger.info(node.getNodeName());
						
						Element customerElement = (Element) node;
						
						customers.add(new Customer(
							customerElement.getElementsByTagName("customerID").item(0).getTextContent(),
							customerElement.getElementsByTagName("customerName").item(0).getTextContent(),
							customerElement.getElementsByTagName("customerAddress").item(0).getTextContent(),
							customerElement.getElementsByTagName("customerCity").item(0).getTextContent(),
							customerElement.getElementsByTagName("customerPostCode").item(0).getTextContent(),
							customerElement.getElementsByTagName("customerCountry").item(0).getTextContent(),
							customerElement.getElementsByTagName("customerEmail").item(0).getTextContent(),
							customerElement.getElementsByTagName("customerPhone").item(0).getTextContent(),
							customerElement.getElementsByTagName("customerLoyalty").item(0).getTextContent(),
							Integer.parseInt(customerElement.getElementsByTagName("customerDiscount").item(0).getTextContent())));
					}
				} catch (SAXException e) {
					logger.error("XML feldolgozási hiba: " + e.getMessage());
				}
			} catch (ParserConfigurationException e) {
				logger.error("XML feldolgozási hiba: " + e.getMessage());
			}
		} catch (FileNotFoundException e) {
			logger.error(fileName + "nem található: " + e.getMessage());
		} catch (IOException e1) {
			logger.error(fileName + "IO hiba: " + e1.getMessage());
		}
		return customers;
	}
	
    /**
     * A számlák XML-ből történő beolvasását megvalósító metódus.
     * 
     * @param fileName betöltendő file neve
     * @return egy listában adja vissza a betöltött elemeket
     */
    public static ObservableList<Invoice> loadInvoices(String fileName) {
    	
    	ObservableList<Invoice> invoices = FXCollections.observableArrayList();

		try (FileInputStream xmlFile = new FileInputStream(fileName)){
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			try {
				
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc;
				try {
					
					doc = dBuilder.parse(xmlFile);					
					doc.getDocumentElement().normalize();
					NodeList nlist = doc.getElementsByTagName("invoice");

					for (int i = 0; i < nlist.getLength(); i++) {
						logger.info("Beolvasott elem szama : " + i);

						Node node = nlist.item(i);			
						Element invoiceElement = (Element) node;

						Element customerElement = (Element) invoiceElement.getElementsByTagName("customer").item(0);			
						Customer customer = new Customer(
								customerElement.getAttribute("customerID"),
								customerElement.getElementsByTagName("customerName").item(0).getTextContent(),
								customerElement.getElementsByTagName("customerAddress").item(0).getTextContent(),
								customerElement.getElementsByTagName("customerCity").item(0).getTextContent(),
								customerElement.getElementsByTagName("customerPostCode").item(0).getTextContent(),
								customerElement.getElementsByTagName("customerCountry").item(0).getTextContent(),
								customerElement.getElementsByTagName("customerEmail").item(0).getTextContent(),
								customerElement.getElementsByTagName("customerPhone").item(0).getTextContent(),
								customerElement.getElementsByTagName("customerLoyalty").item(0).getTextContent(),
								Integer.parseInt(customerElement.getElementsByTagName("customerDiscount").item(0).getTextContent()));

						ObservableList<SoldProduct> soldProducts = FXCollections.observableArrayList();
						for (int j = 0; j < invoiceElement.getElementsByTagName("soldProduct").getLength(); j++) {
							Element soldProductElement = (Element) invoiceElement.getElementsByTagName("soldProduct").item(j);
							soldProducts.add( new SoldProduct (soldProductElement.getAttribute("soldProductID"),
															   soldProductElement.getElementsByTagName("soldProductName").item(0).getTextContent(),
															   soldProductElement.getElementsByTagName("soldProductType").item(0).getTextContent(),
															   Double.parseDouble(soldProductElement.getElementsByTagName("soldProductPrice").item(0).getTextContent()),
															   Integer.parseInt(soldProductElement.getElementsByTagName("soldProductQuantity").item(0).getTextContent())));
						}
						
						invoices.add(new Invoice (invoiceElement.getAttribute("invoiceNumber"),
												  invoiceElement.getElementsByTagName("invoiceDate").item(0).getTextContent(),
												  Integer.parseInt(invoiceElement.getElementsByTagName("invoiceDiscount").item(0).getTextContent()),
												  customer, 
												  soldProducts));

					}
				} catch (SAXException e) {
					logger.error("XML feldolgozási hiba: " + e.getMessage());
				}
			} catch (ParserConfigurationException e) {
				logger.error("XML feldolgozási hiba: " + e.getMessage());
			}
		} catch (FileNotFoundException e) {
			logger.error(fileName + "nem található: " + e.getMessage());
		} catch (IOException e1) {
			logger.error(fileName + "IO hiba: " + e1.getMessage());
		}
		return invoices;
	}
    
}
