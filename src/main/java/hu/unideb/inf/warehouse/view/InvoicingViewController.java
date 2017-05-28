package hu.unideb.inf.warehouse.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.warehouse.Main;
import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.service.*;
import hu.unideb.inf.warehouse.model.Invoice;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.model.SoldProduct;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

/**
 * Számlázást vezérlő osztály. Metódusai vezérlik a grafikus felüleltet. 
 * 
 * @author amezner
 *
 */
public class InvoicingViewController {

	private static Logger logger = LoggerFactory.getLogger(InvoicingViewController.class);

	private Main main;

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
	private TextField quantityField;

	@FXML
	private void initialize() {

		productIDColumn.setCellValueFactory(c -> c.getValue().getProductIDProperty());
		productNameColumn.setCellValueFactory(c -> c.getValue().getProductNameProperty());
		productTypeColumn.setCellValueFactory(c -> c.getValue().getProductTypeProperty());
		productPurchasePriceColumn.setCellValueFactory(c -> c.getValue().getProductPurchasePriceProperty().asObject());
		productSellingPriceColumn.setCellValueFactory(c -> c.getValue().getProductSellingPriceProperty().asObject());
		productOnStockColumn.setCellValueFactory(c -> c.getValue().getProductOnStockProperty().asObject());

		cartIDColumn.setCellValueFactory(c -> c.getValue().getProductIDProperty());
		cartNameColumn.setCellValueFactory(c -> c.getValue().getProductNameProperty());
		cartTypeColumn.setCellValueFactory(c -> c.getValue().getProductTypeProperty());
		cartSoldPriceColumn.setCellValueFactory(c -> c.getValue().getProductSoldPriceProperty().asObject());
		cartQuantityColumn.setCellValueFactory(c -> c.getValue().getProductSoldQuantityProperty().asObject());
		cartSubTotalColumn.setCellValueFactory(c -> c.getValue().getProductSubTotalProperty().asObject());

	}

	@FXML
	private void addToInvoice() throws IOException {
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
						SoldProduct toCart = new SoldProduct(product.getProductID(), 
															 product.getProductName(),
															 product.getProductType(), 
															 product.getProductSellingPrice(),
															 quantity);
						productTable.getItems().get(productTable.getSelectionModel().getSelectedIndex()).setProductOnStock(product.getProductOnStock()-quantity);

						logger.info("Termek hozzaadasa szamlahoz.");
						
						main.getCart().add(toCart);
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
			logger.info("Nincs kivalasztva termek");
		}
	}

	@FXML
	private void finalizeInvoice() {
		logger.info("Adható kedvezmény mértéke :" + DiscountService.volumeDiscount(main.getCart()));
		if (main.getCart().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Kosár validáció");
			alert.setHeaderText(null);
			alert.setContentText("Nincs termék a kosárban!\n\nRakjon be terméket!");
			alert.showAndWait();			
		} else {
			main.getInvoices().add(new Invoice("INV1111",
											   LocalDate.now().toString(),
											   DiscountService.volumeDiscount(main.getCart()), 
											   new Customer("CUSTEST", "C", "C", "C", "C", "C", "C", "C", "Bronz", 10), 
											   main.getCart()));
			main.getCart().clear();
			logger.info("Számla sikeresen lezárva.");
		}
	}
}
