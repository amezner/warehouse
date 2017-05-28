package hu.unideb.inf.warehouse.view;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.warehouse.Main;
import hu.unideb.inf.warehouse.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Termékek karbantartásához használt vezérlő osztály. Metódusai vezérlik a grafikus felüleltet. 
 * 
 * @author amezner
 *
 */
public class ProductViewController {

    private static Logger logger = LoggerFactory.getLogger(ProductViewController.class);
    
	private Main main;
	
	@FXML
	private TableView<Product> productTable;
	
	@FXML 
	private TableColumn<Product, String> productIDColumn;
	
	@FXML 
	private TableColumn<Product, String> productNameColumn;	
	
	@FXML 
	private TableColumn<Product, String> productTypeColumn;
	
	@FXML 
	private TableColumn<Product, Integer> productPurchasePriceColumn;

	@FXML 
	private TableColumn<Product, Integer> productSellingPriceColumn;

	@FXML 
	private TableColumn<Product, Integer> productOnStockColumn;

	@FXML
    private Label productIDLabel;
	
	@FXML
    private Label productNameLabel;
	
	@FXML
    private Label productDescriptionLabel;
	
	@FXML
	void initialize(){
		productIDLabel.setText("");
		productNameLabel.setText("");
		productDescriptionLabel.setText("");
		
		productIDColumn.setCellValueFactory(c -> c.getValue().getProductIDProperty());
    	productNameColumn.setCellValueFactory(c -> c.getValue().getProductNameProperty());
    	productTypeColumn.setCellValueFactory(c -> c.getValue().getProductTypeProperty());  	
    	productPurchasePriceColumn.setCellValueFactory(c -> c.getValue().getProductPurchasePriceProperty().asObject());
       	productSellingPriceColumn.setCellValueFactory(c -> c.getValue().getProductSellingPriceProperty().asObject());
       	productOnStockColumn.setCellValueFactory(c -> c.getValue().getProductOnStockProperty().asObject());
            	
    	productTable.getSelectionModel()
    				 .selectedItemProperty()
    				 .addListener((o, oldValue, newValue) -> showProductDetails(newValue));
	}

	/**
	 * JavaFX inicializációhoz szükséges metódus, nélküle nem működne a megjelenítés.
	 * 
	 * @param main FXML inicializáláshoz szükséges
	 */
	public void setMain(Main main) {
        this.main = main;
        productTable.setItems(main.getProducts());
    }
    
	@FXML
	void addNewProductAction() throws IOException {

		Product product = new Product("", "", "", 0, 0, 0, "");
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/EditProduct.fxml"));
        BorderPane addNewProduct = loader.load();
        
        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Új Termék Felvétele");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(main.primaryStage);
        Scene scene = new Scene(addNewProduct);
        addDialogStage.setScene(scene);
        ((EditProductController)loader.getController()).setProduct(product);
        addDialogStage.showAndWait(); 
		
		if (!product.getProductID().isEmpty())
			if (!main.getProducts().stream().anyMatch(p -> p.getProductID().equals(product.getProductID())))
				main.getProducts().add(product);
			else {
				logger.warn("Van már ilyen termék!");
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Termék azonsító HIBA");
				alert.setHeaderText(null);
				alert.setContentText("Ezzel a termék azonosítóval már létezik termék ezért nem vettük fel!");
				alert.showAndWait();
			}
	}
	
	@FXML
	void editProductAction() throws IOException {
	
        logger.info("Termek szerkesztes nezet betoltese.");
        
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/EditProduct.fxml"));
        BorderPane addNewProduct = loader.load();
        
        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Új Termék Felvétele");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(main.primaryStage);
        Scene scene = new Scene(addNewProduct);
        addDialogStage.setScene(scene);
        ((EditProductController)loader.getController()).setProduct(productTable.getItems().get(productTable.getSelectionModel().getSelectedIndex()));
        addDialogStage.showAndWait(); 
        
	}
	
	@FXML
	void deleteProductAction() throws IOException {
		
		productTable.getItems().remove(productTable.getSelectionModel().getSelectedIndex());
	
	}
	
    void showProductDetails(Product product) {
    	if (product != null) {
    		productIDLabel.setText(product.getProductID());
    		productNameLabel.setText(product.getProductName());
    		productDescriptionLabel.setText(product.getProductDescription());
    		productDescriptionLabel.setWrapText(true);
    		productDescriptionLabel.setTextAlignment(TextAlignment.JUSTIFY);
    	}
    }
}
