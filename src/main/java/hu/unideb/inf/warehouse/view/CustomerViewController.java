package hu.unideb.inf.warehouse.view;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.warehouse.Main;
import hu.unideb.inf.warehouse.model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Ügyfelek karbantartásához használt vezérlő osztály. Metódusai vezérlik a grafikus felüleltet. 
 * 
 * @author amezner
 *
 */
public class CustomerViewController {

    /**
     * A naplózáshoz használt példány.
     */
    private static Logger logger = LoggerFactory.getLogger(CustomerViewController.class);
    
	private Main main;
    
	@FXML
    private TableView<Customer> customerTable;
	
	@FXML
	private TableColumn<Customer, String> customerIDColumn;

	@FXML
	private TableColumn<Customer, String> customerNameColumn;

	@FXML
	private TableColumn<Customer, String> customerAddressColumn;

	@FXML
	private TableColumn<Customer, String> customerCityColumn;

	@FXML
	private TableColumn<Customer, String> customerPostCodeColumn;

	@FXML
	private TableColumn<Customer, String> customerCountryColumn;

	@FXML
	private TableColumn<Customer, String> customerLoyaltyColumn;

	@FXML
	private TableColumn<Customer, Integer> customerDiscountColumn;

    /**
     * JavaFX elemek inicializálását végző metódus.
     */
    @FXML
    void initialize() {
    	
    	customerIDColumn.setCellValueFactory(c -> c.getValue().getCustomerIDProperty());
    	customerNameColumn.setCellValueFactory(c -> c.getValue().getCustomerNameProperty());
    	customerAddressColumn.setCellValueFactory(c -> c.getValue().getCustomerAddressProperty());
    	customerCityColumn.setCellValueFactory(c -> c.getValue().getCustomerCityProperty());
    	customerPostCodeColumn.setCellValueFactory(c -> c.getValue().getCustomerPostCodeProperty());
    	customerCountryColumn.setCellValueFactory(c -> c.getValue().getCustomerCountryProperty());
    	customerLoyaltyColumn.setCellValueFactory(c -> c.getValue().getCustomerLoyaltyProperty());    	
    	customerDiscountColumn.setCellValueFactory(c -> c.getValue().getCustomerDiscountProperty().asObject());
    
    }	

	/**
	 * JavaFX inicializációhoz szükséges metódus, nélküle nem működne a megjelenítés.
	 * 
	 * @param main FXML inicializáláshoz szükséges
	 */
   public void setMain(Main main) {
    
    	this.main = main;
        customerTable.setItems(main.getCustomers());
    
    }

   
	/**
	 * Új ügyfél hozzáadását kezelő metódus. Betölti a szükséges ablakot és bekéri az ügyfél adatait. 
	 */
	@FXML
	void addNewCustomerAction() {
		
        logger.info("Új Ügyfél Hozzáadás Nézet betöltése.");

		Customer customer = new Customer("", "", "", "", "", "", "", "", "", 0);

		logger.info("Új ügyfél hozzáadása nézet betöltése.");
        
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/EditCustomer.fxml"));
		try {

			BorderPane addNewCustomer = loader.load();
	        Stage addDialogStage = new Stage();
	        addDialogStage.setTitle("Új Ügyfél Felvétele");
	        addDialogStage.initModality(Modality.WINDOW_MODAL);
	        addDialogStage.initOwner(main.primaryStage);
	        Scene scene = new Scene(addNewCustomer);
	        addDialogStage.setScene(scene);
	        ((EditCustomerController)loader.getController()).setCustomer(customer);
	        addDialogStage.showAndWait();
	    	
	        if (!customer.getCustomerID().isEmpty()) {
	        	if (!main.getCustomers().stream().anyMatch(c -> c.getCustomerID().equals(customer.getCustomerID())))
	        		main.getCustomers().add(customer);
	        	else {
					logger.warn("Van már ilyen ügyfél!");
					
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Ügyfél azonsító HIBA");
					alert.setHeaderText(null);
					alert.setContentText("Ezzel az ügyfél azonosítóval már létezik ügyfél ezért nem vettük fel!");
					alert.showAndWait();
	        	}
	        }
		} catch (IOException e) {
			
			logger.error("IO hiba Új Ügyfél Hozzáadás Nézet betöltés közben: " + e.getMessage());
			
		}
        
	}

	/**
	 * Régi ügyfél szerkesztését kezelő metódus. Betölti a szükséges ablakot az ügyfél adataival. 
	 */
	@FXML
	void editCustomerAction() {
        
		logger.info("Meglévő Ügyfél Szerkesztése Nézet betöltése.");
        
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/EditCustomer.fxml"));
		try {
			BorderPane editCustomer = loader.load();
	        Stage addDialogStage = new Stage();
	        addDialogStage.setTitle("Ügyfél szerkesztése");
	        addDialogStage.initModality(Modality.WINDOW_MODAL);
	        addDialogStage.initOwner(main.primaryStage);
	        Scene scene = new Scene(editCustomer);
	        addDialogStage.setScene(scene);
	        ((EditCustomerController)loader.getController()).setCustomer(customerTable.getItems().get(customerTable.getSelectionModel().getSelectedIndex()));

	        addDialogStage.showAndWait();    
		} catch (IOException e) {
			
			logger.error("IO hiba Ügyfél Szerkesztés Nézet betöltés közben: " + e.getMessage());
			
		}
        
	}

	/**
	 * Meglévő ügyfél törlésére szolgáló metódus. 
	 */
	@FXML
	void deleteCustomerAction() {
		
        logger.info("Ügyfél törlése.");

		customerTable.getItems().remove(customerTable.getSelectionModel().getSelectedIndex());
	
	}
	
}
