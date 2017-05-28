package hu.unideb.inf.warehouse.view;

import hu.unideb.inf.warehouse.Main;
import hu.unideb.inf.warehouse.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;

/**
 * Számlázás előtti ügyfélkiválasztást támogató osztály.
 * 
 * @author amezner
 *
 */
public class ChooseCustomerController {

	private Main main;

	public void setMain(Main main) {
		this.main = main;
		customerTable.setItems(main.getCustomers());
	}
	
	@FXML
	private TableView<Customer> customerTable;
	
	@FXML
	private TableColumn<Customer, String> customerIDColumn;
	
	@FXML
	private TableColumn<Customer, String> customerNameColumn;
	
	@FXML
	private Label customerCountryLabel;
	
	@FXML
	private Label customerPostCodeLabel;
	
	@FXML
	private Label customerCityLabel;
	
	@FXML
	private Label customerAddressLabel;
	
	@FXML
	private Label customerLoyaltyLabel;
	
	@FXML
	private Label customerDiscountLabel;
	
	@FXML 
	private Button chooseButton;

	@FXML
	private void initialize() {

		customerCountryLabel.setText("");
		customerPostCodeLabel.setText("");
		customerCityLabel.setText("");
		customerAddressLabel.setText("");
		customerLoyaltyLabel.setText("");
		customerDiscountLabel.setText("");
		
		customerNameColumn.setCellValueFactory(c -> c.getValue().getCustomerNameProperty());
		customerIDColumn.setCellValueFactory(c -> c.getValue().getCustomerIDProperty());
		
		customerTable.getSelectionModel()
        			 .selectedItemProperty()
        			 .addListener((o, oldValue, newValue) -> showCustomerDetails(newValue));
	}
	
    private void showCustomerDetails(Customer customer) {
        if (customer != null) {
        	customerCountryLabel.setText(customer.getCustomerCountry());
        	customerPostCodeLabel.setText(customer.getCustomerPostCode());
        	customerCityLabel.setText(customer.getCustomerCity());
        	customerAddressLabel.setText(customer.getCustomerAddress());
        	customerLoyaltyLabel.setText(customer.getCustomerLoyalty());
        	customerDiscountLabel.setText(customer.getCustomerDiscount().toString());
        }
    }
    
	@FXML
	private void handleButtonAction(ActionEvent event) {
		if (customerTable.getSelectionModel().getSelectedIndex() >= 0) {
			main.setCartCustomer(customerTable.getItems().get(customerTable.getSelectionModel().getSelectedIndex()));
			main.setCartCustomerSelected(true);
			Stage stage = (Stage) chooseButton.getScene().getWindow();
		    stage.close();			
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ügyfél kiválasztás");
			alert.setHeaderText(null);
			alert.setContentText("Válasszon ügyfelet!");
			alert.showAndWait();
		}
			
	}
	
}
