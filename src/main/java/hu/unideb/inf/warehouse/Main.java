package hu.unideb.inf.warehouse;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hu.unideb.inf.warehouse.Main;
import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Invoice;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.model.SoldProduct;
import hu.unideb.inf.warehouse.view.CustomerViewController;
import hu.unideb.inf.warehouse.view.InvoicesViewController;
import hu.unideb.inf.warehouse.view.InvoicingViewController;
import hu.unideb.inf.warehouse.view.MainMenuController;
import hu.unideb.inf.warehouse.view.MainViewController;
import hu.unideb.inf.warehouse.view.ProductViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Ez az alap osztályunk amely egyben a programunk belépési pontja.
 * 
 * Az alkalmazás indítása : mvn -e exec:java -Dexec.mainClass="hu.unideb.inf.warehouse.Main" -Dexec.args=""
 * 
 * @author amezner
 * 
 */
public class Main extends Application {

    /**
     * A naplózáshoz használt példány.
     */
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    
	private ObservableList<Customer> customers = FXCollections.observableArrayList();
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private ObservableList<SoldProduct> cart = FXCollections.observableArrayList();
    private ObservableList<Invoice> invoices = FXCollections.observableArrayList();
    
    public Stage primaryStage;
    private BorderPane mainView;
    
	public Main() {
//		customers.add(new Customer("CUS0001", "Kiss Bt.", "Kossuth Lajos u. 5.", "Gyöngyös", "3200", "Magyarország", "info@kiss.hu", "+36 37 555 000", "Arany", 30));
//		customers.add(new Customer("CUS002", "B", "B", "B", "B", "B", "B", "B", "Ezüst", 20));
//		customers.add(new Customer("CUS003", "C", "C", "C", "C", "C", "C", "C", "Bronz", 10));
//		products.add(new Product("PROD001", "AAA", "AAA", 10, 20, 100, "Ez egy ilyen hosszu leirasu termek mert ezt most tesztelni kell. Nem tudok mit mondani erre, ez van, ezt kell szeretni :)"));
//		products.add(new Product("PROD002", "BBB", "BBB", 10, 20, 100, "Ez egy NAGGGGGGGGYON hosszu leirasu termek mert ezt is tesztelni kell. Rengeteg zagyvasagot hordok itt ossze csak azert, hogy a teszt sikeres legyen. Olyan sok dolgot ami senkit nem erdekel es igazabol tok nagy kamu. Tenyleg nem tudok mit mondani erre, ez van, ezt kell szeretni :)"));
//		products.add(new Product("PROD003", "CCC", "CCC", 10, 20, 100, "Ez rovid leiras."));
//		try {
//			products = Loader.loadProducts("testload-products.xml");
//			customers = Loader.loadCustomers("testload-customers.xml");
//		} catch (SAXException | IOException | ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		cart.add(new SoldProduct("DDD", "DDD", "DDD", 10, 20));
//		cart.add(new SoldProduct("EEE", "EEE", "EEE", 100, 17));
//		invoices.add(new Invoice("INV0001",customers.get(0),cart));
//		cart.clear();
//		cart.add(new SoldProduct("DDD", "DDD", "DDD", 10, 20));
//		cart.add(new SoldProduct("EEE", "EEE", "EEE", 100, 17));
//		cart.add(new SoldProduct("Valami", "Valami", "Valami", 1000, 84));
//		cart.add(new SoldProduct("Salala", "Salala", "Salala", 12000, 7));
//		invoices.add(new Invoice("INV0002",customers.get(2),cart));
//		cart.clear();
//		cart.add(new SoldProduct("Valami", "Valami", "Valami", 25000, 10));
//		invoices.add(new Invoice("INV0003",customers.get(1),cart));
//		cart.clear();
//		cart.add(new SoldProduct("Salala", "Salala", "Salala", 20000, 50));
//		invoices.add(new Invoice("INV0004",customers.get(0),cart));
//
//		System.out.println(invoices.get(1).toString());

	}
    
	@Override
	public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("WareHouse Management V1.0");
		showMainView();
		showMainMenu();
	}

	public void showMainView() throws IOException {
        logger.info("GUI inicializálás.");

		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/MainView.fxml"));
        mainView = loader.load();
        Scene scene = new Scene(mainView);
        ((MainViewController)loader.getController()).setMain(this);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	public void showMainMenu() throws IOException {
        logger.info("Főmenü betöltése.");
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/MainMenu.fxml"));
        BorderPane mainMenu = loader.load();
        ((MainMenuController)loader.getController()).setMain(this);
        mainView.setCenter(mainMenu);
	}
	
	public void showCustomerView() throws IOException {
        logger.info("Ügyfél Karbantartás nézet betöltése.");

		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/CustomerView.fxml"));
        BorderPane customerView = loader.load();
        ((CustomerViewController)loader.getController()).setMain(this);
        mainView.setCenter(customerView);
    }
	
	public void showProductView() throws IOException {
        logger.info("Raktar Karbantartás nézet betöltése.");
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/ProductView.fxml"));
        BorderPane productView = loader.load();
        ((ProductViewController)loader.getController()).setMain(this);
        mainView.setCenter(productView);
    }
		
	public void showInvoicingView() throws IOException {
        logger.info("Számlázás nézet betöltése.");
        
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/InvoicingView.fxml"));
        BorderPane invoicingView = loader.load();
        ((InvoicingViewController)loader.getController()).setMain(this);
        mainView.setCenter(invoicingView);
    }
	
	public void showInvoicesView() throws IOException {
        logger.info("Számla megtekintés nézet betöltése.");
        
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/InvoicesView.fxml"));
        BorderPane invoicesView = loader.load();
        ((InvoicesViewController)loader.getController()).setMain(this);
        mainView.setCenter(invoicesView);
    }

	public ObservableList<Customer> getCustomers() {
		return customers;
	}
	
	public ObservableList<Product> getProducts() {
		return products;
	}

	public void setProducts(ObservableList<Product> products) {
		this.products = products;
	}
	
	public ObservableList<SoldProduct> getCart() {
		return cart;
	}
	
	public ObservableList<Invoice> getInvoices() {
		return invoices;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}