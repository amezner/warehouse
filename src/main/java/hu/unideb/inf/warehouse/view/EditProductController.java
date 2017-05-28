package hu.unideb.inf.warehouse.view;

import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.service.FormValidation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Termék adatainak a szerkesztésére szolgáló osztály. Metódusai vezérlik a szerkesztő űrlapot. 
 * 
 * @author amezner
 *
 */
public class EditProductController {
    
	private Product product;
	
	@FXML
	private TextField productIDField;

	@FXML
	private TextField productNameField;
	
	@FXML
	private TextField productTypeField;
	
	@FXML
    private TextField productPurchasePriceField;
	
	@FXML
    private TextField productSellingPriceField;
	
	@FXML
    private TextField productOnStockField;
	
	@FXML
    private TextArea productDescriptionField;
	
	@FXML
	private Button cancelButton;

	@FXML
	private Button saveButton;
	
	@FXML
	void handleCancelButtonAction() {
	    Stage stage = (Stage) cancelButton.getScene().getWindow();
	    stage.close();
	}
	
	/**
	 * Betölti az űrlapra a paraméterben megkapott termék adatait.
	 * 
	 * @param product egy termék adatatit tartalmazza
	 */
	public void setProduct(Product product) {
    	
    	this.product = product;
    	
        productIDField.setText(product.getProductID());
    	productNameField.setText(product.getProductName());
    	productTypeField.setText(product.getProductType());
    	productPurchasePriceField.setText(product.getProductPurchasePrice().toString());
    	productSellingPriceField.setText(product.getProductSellingPrice().toString());
    	productOnStockField.setText(product.getProductOnStock().toString());
    	productDescriptionField.setText(product.getProductDescription());
    }
    
    /**
     * Véglegesíti az adatbázisban az űrlapon található adatokat.
     * Minden mezőre külön ellenőrzést végez, hogy a mező tartalma megfelel-e a formai követelményeknek.
     * Egyetlen kivétel a leírás mező, oda bármit lehet írni.
     */
	public void saveProduct() {
    	if (FormValidation.validateProductID(productIDField.getText(),true) &&
        	FormValidation.validateProductName(productNameField.getText(),true) &&
        	FormValidation.validateProductType(productTypeField.getText(),true) &&
        	FormValidation.validateProductPurchasePrice(productPurchasePriceField.getText(),true) &&
        	FormValidation.validateProductSellingPrice(productSellingPriceField.getText(),true) &&
        	FormValidation.validateProductOnStock(productOnStockField.getText(),true) &&
        	FormValidation.validateProfit(productPurchasePriceField.getText(), productSellingPriceField.getText(), true)) {

    			product.setProductID(productIDField.getText());
		    	product.setProductName(productNameField.getText());
		    	product.setProductDescription(productDescriptionField.getText());
		    	product.setProductType(productTypeField.getText());
		    	product.setProductPurchasePrice(Integer.parseInt(productPurchasePriceField.getText()));
		    	product.setProductSellingPrice(Integer.parseInt(productSellingPriceField.getText()));
		    	product.setProductOnStock(Integer.parseInt(productOnStockField.getText()));
			    Stage stage = (Stage) saveButton.getScene().getWindow();
			    stage.close();
    	}
    }
    
}
