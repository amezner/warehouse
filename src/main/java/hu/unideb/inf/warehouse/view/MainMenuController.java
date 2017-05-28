package hu.unideb.inf.warehouse.view;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import org.slf4j.Logger;

import hu.unideb.inf.warehouse.Main;
import hu.unideb.inf.warehouse.dao.*;
import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.service.FormValidation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

/**
 * Ez az osztályunk vezérli a főmenüt.
 * 
 * @author amezner
 * 
 */
public class MainMenuController {

    private static Logger logger = LoggerFactory.getLogger(MainMenuController.class);
    
	private Main main;

    public void setMain(Main main) {
        this.main = main;
    }
    
	@FXML
	private void goCustomerView() throws IOException {
		main.showCustomerView();
	}
	
	@FXML
	private void goProductView() throws IOException {
		main.showProductView();
	}
	
	@FXML
	private void goInvoicingView() throws IOException {
		main.showInvoicingView();
	}

	@FXML
	private void goInvoicesView() throws IOException {
		main.showInvoicesView();
	}
	
	@FXML
	private void goSaveEverything() throws IOException, ParserConfigurationException, TransformerException {
		Finalizer dao = new Finalizer();
		dao.finalizeProducts(main.getProducts());
		dao.finalizeCustomers(main.getCustomers());
		dao.finalizeInvoices(main.getInvoices());
	}
	
	@FXML
	private void goLoadProductXML() throws IOException, ParserConfigurationException, TransformerException, SAXException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(main.primaryStage);
		if (file != null) {
			
		    ObservableList<Product> loadedProducts = Loader.loadProducts(file.getPath());
		    
		    for(Product product : loadedProducts) {
	    		if (main.getProducts().stream().anyMatch(p -> p.getProductID().equals(product.getProductID()))) {
		    		logger.warn("Már van ilyen termék, passzoljuk");
		    	} else {
		    		logger.info("Megfelelő elem, nem duplikátum, ellenőrizzük az adatokat");
		        	if (FormValidation.validateProductID(product.getProductID(),false) &&
		                	FormValidation.validateProductName(product.getProductName(),false) &&
		                	FormValidation.validateProductType(product.getProductType(),false) &&
		                	FormValidation.validateProductPurchasePrice(product.getProductPurchasePrice().toString(),false) &&
		                	FormValidation.validateProductSellingPrice(product.getProductSellingPrice().toString(),false) &&
		                	FormValidation.validateProductOnStock(product.getProductOnStock().toString(),false) &&
		                	FormValidation.validateProfit(product.getProductPurchasePrice().toString(), product.getProductSellingPrice().toString(), false)) {
		        		main.getProducts().add(product);
		        		logger.info(product.getProductID() + " " + product.getProductName() +" -------- Adatok rendben, felvesszük a termékek közé");
		        	} else
		        		logger.info(product.getProductID() + " " + product.getProductName() +" -------- Nem megfelelő adatok, nem vesszük fel a terméket");

		    	}
		    }
	    	logger.info("File betöltése befejeződött.");

		} else
			logger.info("Nem választott file-t!");
	}
	
	@FXML
	private void goLoadCustomerXML() throws IOException, ParserConfigurationException, TransformerException, SAXException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(main.primaryStage);
		if (file != null) {
			
		    ObservableList<Customer> loadedCustomers = Loader.loadCustomers(file.getPath());
		    
		    for(Customer customer : loadedCustomers) {
	    		if (main.getCustomers().stream().anyMatch(p -> p.getCustomerID().equals(customer.getCustomerID()))) {
		    		logger.warn("Már van ilyen ügyfél, passzoljuk");
		    	} else {
		    		logger.info(customer.getCustomerID() + " " + customer.getCustomerName() +"Megfelelő elem, nem duplikátum, ellenőrizzük az adatokat");
		        	if (FormValidation.validateCustomerID(customer.getCustomerID(),false) &&
		                	FormValidation.validateCustomerName(customer.getCustomerName(),false) &&
		                	FormValidation.validateCustomerPostCode(customer.getCustomerPostCode(),false) &&
		                	FormValidation.validateCustomerCity(customer.getCustomerCity(),false) &&
		                	FormValidation.validateCustomerAddress(customer.getCustomerAddress(),false) &&
		                	FormValidation.validateCustomerCountry(customer.getCustomerCountry(),false) &&
		                	FormValidation.validateCustomerEmail(customer.getCustomerEmail(),false) &&
		                	FormValidation.validateCustomerPhone(customer.getCustomerPhone(),false) &&
		                	FormValidation.validateCustomerDiscount(customer.getCustomerDiscount().toString(),false)) {
		        		main.getCustomers().add(customer);
		        		logger.info(customer.getCustomerID() + " " + customer.getCustomerName() +" -------- Adatok rendben, felvesszük ay ügyfelek közé");
		        	} else
		        		logger.info(customer.getCustomerID() + " " + customer.getCustomerName() +" -------- Nem megfelelő adatok, nem vesszük fel az ügyfelet");

		    	}
		    }
	    	logger.info("File betöltése befejeződött.");

		} else
			logger.info("Nem választott file-t!");
	}
}
