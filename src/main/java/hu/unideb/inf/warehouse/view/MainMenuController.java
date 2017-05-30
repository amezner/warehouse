package hu.unideb.inf.warehouse.view;

import java.io.File;
import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import hu.unideb.inf.warehouse.Main;
import hu.unideb.inf.warehouse.dao.*;
import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Invoice;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.service.FormValidation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
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

	/**
	 * JavaFX inicializációhoz szükséges metódus, nélküle nem működne a megjelenítés.
	 * 
	 * @param main FXML inicializáláshoz szükséges
	 */
	public void setMain(Main main) {
        this.main = main;
    }
    
	@FXML
	void goCustomerView() {
		showCustomerView();
	}
	
	@FXML
	void goProductView() {
		showProductView();
	}
	
	@FXML
	void goInvoicingView() {
		showInvoicingView();
	}

	@FXML
	void goInvoicesView() {
		showInvoicesView();
	}
	
	@FXML
	void goSaveEverything() {
		Finalizer dao = new Finalizer();
		dao.finalizeProducts(main.getProducts());
		dao.finalizeCustomers(main.getCustomers());
		dao.finalizeInvoices(main.getInvoices());
	}

	@FXML
	void goLoadEverything() {
		main.setProducts(Loader.loadProducts("products.xml"));
		main.setCustomers(Loader.loadCustomers("customers.xml"));
		main.setInvoices(Loader.loadInvoices("invoices.xml"));				
	}
	
	@FXML
	void goLoadProductXML() {
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
	void goLoadCustomerXML() {
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
	
	@FXML
	void goLoadInvoicesXML() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(main.primaryStage);
		if (file != null) {
			
		    ObservableList<Invoice> loadedInvoices = Loader.loadInvoices(file.getPath());
		    
		    for(Invoice invoice : loadedInvoices) {
	    		if (main.getInvoices().stream().anyMatch(p -> p.getInvoiceNumber().equals(invoice.getInvoiceNumber())))
		    		logger.warn("Már van ilyen számla, passzoljuk");
		    	else {
	        		main.getInvoices().add(invoice);
	        		logger.info(invoice.getInvoiceNumber() + " -------- Adatok rendben, felvesszük a számlák közé");
		    	}
		    }
		}
	}
	
	
	void showInvoicingView() {
	    logger.info("Számlázás nézet betöltése.");
	    
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(Main.class.getResource("/view/InvoicingView.fxml"));
		try {
			
			BorderPane invoicingView = loader.load();
		    ((InvoicingViewController)loader.getController()).setMain(main);
		    main.mainView.setCenter(invoicingView);
		    
		} catch (IOException e) {
			
			logger.error("IO hiba Számlázás Nézet betöltés közben: " + e.getMessage());
		
		}      

	}
	
	void showCustomerView() {
        logger.info("Ügyfél Karbantartás nézet betöltése.");

		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/CustomerView.fxml"));
		try {
			
			BorderPane customerView = loader.load();
	        ((CustomerViewController)loader.getController()).setMain(main);
	        main.mainView.setCenter(customerView);
	        
		} catch (IOException e) {
			
			logger.error("IO hiba Ügyfél Karbantartás Nézet betöltés közben: " + e.getMessage());
		
		}      

    }
	
	void showProductView() {
        logger.info("Raktár Karbantartás nézet betöltése.");
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/ProductView.fxml"));
		try {
			
			BorderPane productView = loader.load();
	        ((ProductViewController)loader.getController()).setMain(main);
	        main.mainView.setCenter(productView);
	        
		} catch (IOException e) {
			
			logger.error("IO hiba Raktár Karbantartás Nézet betöltés közben: " + e.getMessage());
		
		}     

    }
	
	void showInvoicesView() {
        logger.info("Számla Megtekintés nézet betöltése.");
        
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/InvoicesView.fxml"));
		try {
			
			BorderPane invoicesView = loader.load();
	        ((InvoicesViewController)loader.getController()).setMain(main);
	        main.mainView.setCenter(invoicesView);
	        
		} catch (IOException e) {
			
			logger.error("IO hiba Számla Megtekintés Nézet betöltés közben: " + e.getMessage());
		
		}     

    }
}
