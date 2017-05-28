package hu.unideb.inf.warehouse.dao;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Invoice;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.model.SoldProduct;

/**
 * Az adatok XML-be történő kiírását megvalósító osztály.
 */
public class Finalizer {

    /**
     * A naplózáshoz használt példány.
     */
	private static Logger logger = LoggerFactory.getLogger(Finalizer.class);
    
    /**
     * Termékek elmentését végző metódus.
     * Paraméterként kapja meg a {@link hu.unideb.inf.warehouse.model.Product} termékeket tartalmazó listát.   
     * Alapértelmezett file : products.xml
     * 
     * @param products egy lista a lementendő termékekről
     * @throws ParserConfigurationException kivételt nem kezeli osztályon belül
     * @throws TransformerException kivételt nem kezeli osztályon belül
     */
    public void finalizeProducts(List<Product> products) throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		
		Element rootElement = doc.createElement("products");
		doc.appendChild(rootElement);
		
		for (Product product : products) {
			
			Element productElement = doc.createElement("product");
			rootElement.appendChild(productElement);
			productElement.setAttribute("productID", product.getProductID());
			
			Element productNameElement = doc.createElement("productName");
			productNameElement.appendChild(doc.createTextNode(product.getProductName()));
			productElement.appendChild(productNameElement);

			Element productTypeElement = doc.createElement("productType");
			productTypeElement.appendChild(doc.createTextNode(product.getProductType()));
			productElement.appendChild(productTypeElement);
			
			Element productPurchasePriceElement = doc.createElement("productPurchasePrice");
			productPurchasePriceElement.appendChild(doc.createTextNode(product.getProductPurchasePrice().toString()));
			productElement.appendChild(productPurchasePriceElement);
			
			Element productSellingPriceElement = doc.createElement("productSellingPrice");
			productSellingPriceElement.appendChild(doc.createTextNode(product.getProductSellingPrice().toString()));
			productElement.appendChild(productSellingPriceElement);
			
			Element productOnStockElement = doc.createElement("productOnStock");
			productOnStockElement.appendChild(doc.createTextNode(product.getProductOnStock().toString()));
			productElement.appendChild(productOnStockElement);
			
			Element productDescriptionElement = doc.createElement("productDescription");
			productDescriptionElement.appendChild(doc.createTextNode(product.getProductDescription()));
			productElement.appendChild(productDescriptionElement);			
			
		}
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
//		logger.info(Main.class.getResource("/database/").getPath()+"products.xml");
//		StreamResult result = new StreamResult(new File(Main.class.getResource("/database/products.xml").getPath()));
		StreamResult result = new StreamResult(new File("products.xml"));
		transformer.transform(source,  result);
        
		logger.info("Termekek lementese.");
	}
	
    /**
     * Ügyfelek elmentését végző metódus.
     * Paraméterként kapja meg a {@link hu.unideb.inf.warehouse.model.Customer} ügyfeleket tartalmazó listát.   
     * Alapértelmezett file : customers.xml
     * 
     * @param customers egy lista a lementendő ügyfelekről.
     * @throws ParserConfigurationException kivételt nem kezeli
     * @throws TransformerException kivételt nem kezeli
     */
    public void finalizeCustomers(List<Customer> customers) throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		
		Element rootElement = doc.createElement("customers");
		doc.appendChild(rootElement);
		
		for (Customer customer : customers) {
			
			Element customerElement = doc.createElement("customer");
			rootElement.appendChild(customerElement);
//			customerElement.setAttribute("customerID", customer.getCustomerID());

			Element customerIDElement = doc.createElement("customerID");
			customerIDElement.appendChild(doc.createTextNode(customer.getCustomerID()));
			customerElement.appendChild(customerIDElement);
			
			Element customerNameElement = doc.createElement("customerName");
			customerNameElement.appendChild(doc.createTextNode(customer.getCustomerName()));
			customerElement.appendChild(customerNameElement);

			Element customerAddressElement = doc.createElement("customerAddress");
			customerAddressElement.appendChild(doc.createTextNode(customer.getCustomerAddress()));
			customerElement.appendChild(customerAddressElement);

			Element customerCityElement = doc.createElement("customerCity");
			customerCityElement.appendChild(doc.createTextNode(customer.getCustomerCity()));
			customerElement.appendChild(customerCityElement);
			
			Element customerPostCodeElement = doc.createElement("customerPostCode");
			customerPostCodeElement.appendChild(doc.createTextNode(customer.getCustomerPostCode()));
			customerElement.appendChild(customerPostCodeElement);

			Element customerCountryElement = doc.createElement("customerCountry");
			customerCountryElement.appendChild(doc.createTextNode(customer.getCustomerCountry()));
			customerElement.appendChild(customerCountryElement);
			
			Element customerEmailElement = doc.createElement("customerEmail");
			customerEmailElement.appendChild(doc.createTextNode(customer.getCustomerEmail()));
			customerElement.appendChild(customerEmailElement);
			
			Element customerPhoneElement = doc.createElement("customerPhone");
			customerPhoneElement.appendChild(doc.createTextNode(customer.getCustomerPhone()));
			customerElement.appendChild(customerPhoneElement);
			
			Element customerLoyaltyElement = doc.createElement("customerLoyalty");
			customerLoyaltyElement.appendChild(doc.createTextNode(customer.getCustomerLoyalty()));
			customerElement.appendChild(customerLoyaltyElement);		
			
			Element customerDiscountElement = doc.createElement("customerDiscount");
			customerDiscountElement.appendChild(doc.createTextNode(customer.getCustomerDiscount().toString()));
			customerElement.appendChild(customerDiscountElement);

		}
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		StreamResult result = new StreamResult(new File("customers.xml"));
		transformer.transform(source,  result);
        
		logger.info("Ugyfelek lementese.");
	}
	
    /**
     * Számla adatbázis elmentését végző metódus.
     * Paraméterként kapja meg a {@link hu.unideb.inf.warehouse.model.Invoice} számlákat tartalmazó listát.   
     * Alapértelmezett file : invoices.xml
     * 
     * @param invoices egy lista a lementendő számlákról.
     * @throws ParserConfigurationException kivételt nem kezeli
     * @throws TransformerException kivételt nem kezeli
     */
	public void finalizeInvoices(List<Invoice> invoices) throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		
		Element rootElement = doc.createElement("invoices");
		doc.appendChild(rootElement);
		
		for (Invoice invoice : invoices) {
	
			Element invoiceElement = doc.createElement("invoice");
			rootElement.appendChild(invoiceElement);
			invoiceElement.setAttribute("invoiceNumber", invoice.getInvoiceNumber());

			Element customerElement = doc.createElement("customer");
			invoiceElement.appendChild(customerElement);
			customerElement.setAttribute("customerID", invoice.getCustomer().getCustomerID());
			
			Element customerNameElement = doc.createElement("customerName");
			customerNameElement.appendChild(doc.createTextNode(invoice.getCustomer().getCustomerName()));
			customerElement.appendChild(customerNameElement);

			Element customerAddressElement = doc.createElement("customerAddress");
			customerAddressElement.appendChild(doc.createTextNode(invoice.getCustomer().getCustomerAddress()));
			customerElement.appendChild(customerAddressElement);

			Element customerCityElement = doc.createElement("customerCity");
			customerCityElement.appendChild(doc.createTextNode(invoice.getCustomer().getCustomerCity()));
			customerElement.appendChild(customerCityElement);
			
			Element customerPostCodeElement = doc.createElement("customerPostCode");
			customerPostCodeElement.appendChild(doc.createTextNode(invoice.getCustomer().getCustomerPostCode()));
			customerElement.appendChild(customerPostCodeElement);

			Element customerCountryElement = doc.createElement("customerCountry");
			customerCountryElement.appendChild(doc.createTextNode(invoice.getCustomer().getCustomerCountry()));
			customerElement.appendChild(customerCountryElement);
			
			Element customerEmailElement = doc.createElement("customerEmail");
			customerEmailElement.appendChild(doc.createTextNode(invoice.getCustomer().getCustomerEmail()));
			customerElement.appendChild(customerEmailElement);
			
			Element customerPhoneElement = doc.createElement("customerPhone");
			customerPhoneElement.appendChild(doc.createTextNode(invoice.getCustomer().getCustomerPhone()));
			customerElement.appendChild(customerPhoneElement);
			
			Element customerLoyaltyElement = doc.createElement("customerLoyalty");
			customerLoyaltyElement.appendChild(doc.createTextNode(invoice.getCustomer().getCustomerLoyalty()));
			customerElement.appendChild(customerLoyaltyElement);		
			
			Element customerDiscountElement = doc.createElement("customerDiscount");
			customerDiscountElement.appendChild(doc.createTextNode(invoice.getCustomer().getCustomerDiscount().toString()));
			customerElement.appendChild(customerDiscountElement);		
			
			for (SoldProduct soldProduct : invoice.getSoldProducts()) {

				Element soldProductElement = doc.createElement("soldProduct");
				invoiceElement.appendChild(soldProductElement);
				soldProductElement.setAttribute("productID", soldProduct.getProductID());
				
				Element soldProductNameElement = doc.createElement("productName");
				soldProductNameElement.appendChild(doc.createTextNode(soldProduct.getProductName()));
				soldProductElement.appendChild(soldProductNameElement);

				Element soldProductTypeElement = doc.createElement("productType");
				soldProductTypeElement.appendChild(doc.createTextNode(soldProduct.getProductType()));
				soldProductElement.appendChild(soldProductTypeElement);
				
				Element soldProductSoldPriceElement = doc.createElement("productSoldPrice");
				soldProductSoldPriceElement.appendChild(doc.createTextNode(soldProduct.getProductSoldPrice().toString()));
				soldProductElement.appendChild(soldProductSoldPriceElement);		

				Element soldProductSoldQuantityElement = doc.createElement("productSoldQuantity");
				soldProductSoldQuantityElement.appendChild(doc.createTextNode(soldProduct.getProductSoldQuantity().toString()));
				soldProductElement.appendChild(soldProductSoldQuantityElement);	
					
			}
		}
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		StreamResult result = new StreamResult(new File("invoices.xml"));
		transformer.transform(source,  result);
        
		logger.info("Számlák lementese.");
	}
}
