package hu.unideb.inf.warehouse.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.warehouse.Main;
import hu.unideb.inf.warehouse.model.Invoice;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.model.SoldProduct;
import hu.unideb.inf.warehouse.service.DiscountService;
import hu.unideb.inf.warehouse.service.InvoiceHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Számlázást vezérlő osztály. Metódusai vezérlik a grafikus felüleltet. 
 * 
 * @author amezner
 *
 */
public class InvoicingViewController {

	private static Logger logger = LoggerFactory.getLogger(InvoicingViewController.class);

	private Main main;

	/**
	 * JavaFX inicializációhoz szükséges metódus, nélküle nem működne a megjelenítés.
	 * 
	 * @param main FXML inicializáláshoz szükséges
	 */
	public void setMain(Main main) {

		this.main = main;
		cartTable.setItems(main.getCart());
		productTable.setItems(main.getProducts());

	}

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
	private TableView<SoldProduct> cartTable;

	@FXML
	private TableColumn<SoldProduct, String> cartIDColumn;

	@FXML
	private TableColumn<SoldProduct, String> cartNameColumn;

	@FXML
	private TableColumn<SoldProduct, String> cartTypeColumn;

	@FXML
	private TableColumn<SoldProduct, Double> cartSoldPriceColumn;

	@FXML
	private TableColumn<SoldProduct, Integer> cartQuantityColumn;

	@FXML
	private TableColumn<SoldProduct, Double> cartSubTotalColumn;
	
	@FXML
	private Label customerNameLabel;
	
	@FXML
	private Label customerDiscountLabel;

    /**
     * JavaFX elemek inicializálását végző metódus.
     */
	@FXML
	void initialize() {

//		logger.info("ez meg lefut");
//		System.out.println(main.isCartCustomerSelected());
//		if (!main.isCartCustomerSelected()) {
//			logger.info("Valami nem stimmel");
//			customerNameLabel.setText(main.getCartCustomer().getCustomerName());
//			customerDiscountLabel.setText(main.getCartCustomer().getCustomerDiscount().toString());
//		} else 
//		{
			customerNameLabel.setText("Válasszon ügyfelet!");
			customerDiscountLabel.setText("Válasszon ügyfelet!");
//		}
		
		productIDColumn.setCellValueFactory(c -> c.getValue().getProductIDProperty());
		productNameColumn.setCellValueFactory(c -> c.getValue().getProductNameProperty());
		productTypeColumn.setCellValueFactory(c -> c.getValue().getProductTypeProperty());
		productPurchasePriceColumn.setCellValueFactory(c -> c.getValue().getProductPurchasePriceProperty().asObject());
		productSellingPriceColumn.setCellValueFactory(c -> c.getValue().getProductSellingPriceProperty().asObject());
		productOnStockColumn.setCellValueFactory(c -> c.getValue().getProductOnStockProperty().asObject());

		cartIDColumn.setCellValueFactory(c -> c.getValue().getSoldProductIDProperty());
		cartNameColumn.setCellValueFactory(c -> c.getValue().getSoldProductNameProperty());
		cartTypeColumn.setCellValueFactory(c -> c.getValue().getSoldProductTypeProperty());
		cartSoldPriceColumn.setCellValueFactory(c -> c.getValue().getSoldProductSoldPriceProperty().asObject());
		cartQuantityColumn.setCellValueFactory(c -> c.getValue().getSoldProductSoldQuantityProperty().asObject());
		cartSubTotalColumn.setCellValueFactory(c -> c.getValue().getSoldProductSubTotalProperty().asObject());

	}

	
	void showChooseCustomer() {
        
		logger.info("Ügyfélválasztó betöltése számlázáshoz.");
        
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/ChooseCustomer.fxml"));
        
		try {
		
			BorderPane chooseCustomer = loader.load();
	        Stage dialog = new Stage();
	        dialog.setTitle("Ügyfél kiválasztás számlázáshoz");
	        dialog.initModality(Modality.WINDOW_MODAL);
	        dialog.initOwner(main.primaryStage);
	        Scene scene = new Scene(chooseCustomer);
	        dialog.setScene(scene);
	        dialog.initStyle(StageStyle.UNDECORATED);
	       
	        ((ChooseCustomerController)loader.getController()).setMain(main);
	 
	        dialog.showAndWait();
	        
		} catch (IOException e) {
			
			logger.error("IO hiba Ügyfél Kiválasztás Nézet betöltés közben: " + e.getMessage());
		
		}        
    }
	
	@FXML
	void pickCustomer() {
	
		if (!main.isCartCustomerSelected()) {
	        
			showChooseCustomer();
			
			customerNameLabel.setText(main.getCartCustomer().getCustomerName());
			customerDiscountLabel.setText(main.getCartCustomer().getCustomerDiscount().toString());

		} else {
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ügyfélválasztás");
			alert.setHeaderText(null);
			alert.setContentText("Már választott ügyfelet!\n\nZárja le a számlát mielőtt új ügyfelet választana.");
			alert.showAndWait();
			
		}
		
	}
	
	@FXML
	void addToInvoice() {
	
		if (main.isCartCustomerSelected()) {
		
			if (productTable.getSelectionModel().getSelectedIndex() >= 0) {
				
				Product product = productTable.getItems().get(productTable.getSelectionModel().getSelectedIndex());
	
				TextInputDialog getQuantity = new TextInputDialog();
				getQuantity.setTitle("");
				getQuantity.setHeaderText("Adja meg a vásárolandó mennyiséget :");
				Optional<String> result = getQuantity.showAndWait();
	
				if (result.isPresent())
					if (result.get().matches("[0-9]+")) {
						Integer quantity = Integer.parseInt(result.get());
						
						logger.info(quantity.toString());
						if (quantity <= product.getProductOnStock()) {

							if (DiscountService.customerDiscount(main.getCartCustomer(), product)) {
								double soldPrice = product.getProductSellingPrice() * (double) (100 - main.getCartCustomer().getCustomerDiscount()) / 100;
								SoldProduct toCart = new SoldProduct(product.getProductID(), 
																	 product.getProductName(),
																	 product.getProductType(), 
																	 soldPrice,
																	 quantity);
								productTable.getItems().get(productTable.getSelectionModel().getSelectedIndex()).setProductOnStock(product.getProductOnStock() - quantity);
		
								logger.info("Termek hozzaadasa szamlahoz.");								
								main.getCart().add(toCart);
								
							}
						} else {
							
							Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("Darabszám validáció");
							alert.setHeaderText(null);
							alert.setContentText("Nincs raktáron elegendő termék!");
							alert.showAndWait();
							
						}
					} else {
						
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Darabszám validáció");
						alert.setHeaderText(null);
						alert.setContentText("Helytelen érték!\n\nA darabszám csak pozitív egész szám lehet!");
						alert.showAndWait();
						
					}
	
			} else {
				
				logger.info("Nincs kiválasztva termék");
				
			}
			
		} else {
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ügyfélválasztás");
			alert.setHeaderText(null);
			alert.setContentText("Még nem választott ügyfelet!\n\nA számlázás megkezdése előtt ügyfelet kell választani!");
			alert.showAndWait();
			
		}
	}

	@FXML
	void finalizeInvoice() {
		
		logger.info("Adható kedvezmény mértéke :" + DiscountService.volumeDiscount(main.getCart()));
		
		if (main.getCart().isEmpty()) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Kosár validáció");
			alert.setHeaderText(null);
			alert.setContentText("Nincs termék a kosárban!\n\nRakjon be terméket!");
			alert.showAndWait();
			
		} else {
			
			main.getInvoices().add(new Invoice(InvoiceHandler.generateNextInvoiceNumber(main.getInvoices()),
											   LocalDate.now().toString(),
											   DiscountService.volumeDiscount(main.getCart()), 
											   main.getCartCustomer(), 
											   main.getCart()));
			main.getCart().clear();
			main.setCartCustomerSelected(false);
			main.setCartCustomer(null);
			customerNameLabel.setText("Válasszon ügyfelet!");
			customerDiscountLabel.setText("Válasszon ügyfelet!");
			logger.info("Számla sikeresen lezárva.");
		
		}
		
	}
	
}