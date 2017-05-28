package hu.unideb.inf.warehouse.view;

import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.service.FormValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Ügyfél adatainak a szerkesztésére szolgáló osztály. Metódusai vezérlik a szerkesztő űrlapot. 
 * 
 * @author amezner
 *
 */
public class EditCustomerController {

	private ObservableList<String> loyaltyCardTypeList = FXCollections.observableArrayList("Arany","Ezüst","Bronz","Alap");
	private Customer customer;
	
	@FXML
    private TextField customerIDField;

	@FXML
	private TextField customerNameField;

	@FXML
    private TextField customerCountryField;
	
	@FXML
    private TextField customerAddressField;

	@FXML
    private TextField customerCityField;

	@FXML
    private TextField customerPostCodeField;

	@FXML
    private TextField customerEmailField;

	@FXML
    private TextField customerPhoneField;

	@FXML
    private ChoiceBox<String> customerLoyaltyBox;

	@FXML
    private TextField customerDiscountField;

	@FXML
	private Button cancelButton;

	@FXML
	private Button saveButton;
	
	@FXML
	void handleCancelButtonAction() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	void initialize(){
		customerLoyaltyBox.setValue("Alap");
		customerLoyaltyBox.setItems(loyaltyCardTypeList);
	}
	
	/**
	 * Betölti az űrlapra a paraméterben megkapott ügyfél adatait.
	 * 
	 * @param customer egy ügyfél adatatit tartalmazza
	 */
	public void setCustomer(Customer customer) {
        this.customer = customer;
        
    	customerIDField.setText(customer.getCustomerID());
    	customerNameField.setText(customer.getCustomerName());
    	customerAddressField.setText(customer.getCustomerAddress());
    	customerCityField.setText(customer.getCustomerCity());
    	customerPostCodeField.setText(customer.getCustomerPostCode());
    	customerCountryField.setText(customer.getCustomerCountry());
    	customerLoyaltyBox.setValue(customer.getCustomerLoyalty());
		customerLoyaltyBox.setItems(loyaltyCardTypeList);
    	customerDiscountField.setText(customer.getCustomerDiscount().toString());
    	customerEmailField.setText(customer.getCustomerEmail());
    	customerPhoneField.setText(customer.getCustomerPhone());
    }
    
    /**
     * Véglegesíti az adatbázisban az űrlapon található adatokat.
     * Minden mezőre külön ellenőrzést végez, hogy a mező tartalma megfelel-e a formai követelményeknek.
     */
	public void saveCustomer() {
    	if (FormValidation.validateCustomerID(customerIDField.getText(),true) &&
    		FormValidation.validateCustomerName(customerNameField.getText(),true) &&
    		FormValidation.validateCustomerCountry(customerCountryField.getText(),true) &&
    		FormValidation.validateCustomerPostCode(customerPostCodeField.getText(),true) &&
    		FormValidation.validateCustomerCity(customerCityField.getText(),true) &&
    		FormValidation.validateCustomerAddress(customerAddressField.getText(),true) &&
    		FormValidation.validateCustomerEmail(customerEmailField.getText(),true) &&
    		FormValidation.validateCustomerPhone(customerPhoneField.getText(),true) &&
    		FormValidation.validateCustomerDiscount(customerDiscountField.getText(),true)) {
    		
	        	customer.setCustomerID(customerIDField.getText());
	        	customer.setCustomerName(customerNameField.getText());
	        	customer.setCustomerAddress(customerAddressField.getText());
	        	customer.setCustomerCity(customerCityField.getText());
	        	customer.setCustomerPostCode(customerPostCodeField.getText());
	        	customer.setCustomerCountry(customerCountryField.getText());
	        	customer.setCustomerLoyalty(customerLoyaltyBox.getValue());
	        	customer.setCustomerDiscount(Integer.parseInt(customerDiscountField.getText()));
	        	customer.setCustomerEmail(customerEmailField.getText());
	        	customer.setCustomerPhone(customerPhoneField.getText());
	        	
	        	Stage stage = (Stage) saveButton.getScene().getWindow();
	    	    stage.close();	    	    
    	}
    }
}
