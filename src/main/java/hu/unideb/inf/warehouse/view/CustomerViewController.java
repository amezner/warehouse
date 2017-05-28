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

    @FXML
    private void initialize() {
    	
    	customerIDColumn.setCellValueFactory(c -> c.getValue().getCustomerIDProperty());
    	customerNameColumn.setCellValueFactory(c -> c.getValue().getCustomerNameProperty());
    	customerAddressColumn.setCellValueFactory(c -> c.getValue().getCustomerAddressProperty());
    	customerCityColumn.setCellValueFactory(c -> c.getValue().getCustomerCityProperty());
    	customerPostCodeColumn.setCellValueFactory(c -> c.getValue().getCustomerPostCodeProperty());
    	customerCountryColumn.setCellValueFactory(c -> c.getValue().getCustomerCountryProperty());
    	customerLoyaltyColumn.setCellValueFactory(c -> c.getValue().getCustomerLoyaltyProperty());    	
    	customerDiscountColumn.setCellValueFactory(c -> c.getValue().getCustomerDiscountProperty().asObject());
    
    }	

    public void setMain(Main main) {
    
    	this.main = main;
        customerTable.setItems(main.getCustomers());
    
    }

	@FXML
	private void addNewCustomerAction() throws IOException {
		
		Customer customer = new Customer("", "", "", "", "", "", "", "", "", 0);

		logger.info("Új ügyfél hozzáadása nézet betöltése.");
        
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/EditCustomer.fxml"));
        BorderPane addNewCustomer = loader.load();
        
        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Új Ügyfél Felvétele");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(main.primaryStage);
        Scene scene = new Scene(addNewCustomer);
        addDialogStage.setScene(scene);
        ((EditCustomerController)loader.getController()).setCustomer(customer);
        addDialogStage.showAndWait();
    	
        if (!customer.getCustomerID().isEmpty())
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

	@FXML
	private void editCustomerAction() throws IOException {
        
		logger.info("Meglévő ügyfél szerkesztése nézet betöltése.");
        
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/EditCustomer.fxml"));
        BorderPane editCustomer = loader.load();
        
        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Ügyfél szerkesztése");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(main.primaryStage);
        Scene scene = new Scene(editCustomer);
        addDialogStage.setScene(scene);
        ((EditCustomerController)loader.getController()).setCustomer(customerTable.getItems().get(customerTable.getSelectionModel().getSelectedIndex()));

        addDialogStage.showAndWait();    
        
	}
	
	@FXML
	private void deleteCustomerAction() throws IOException {
		
		customerTable.getItems().remove(customerTable.getSelectionModel().getSelectedIndex());
	
	}
	
}
