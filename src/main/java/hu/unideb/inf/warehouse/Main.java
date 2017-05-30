package hu.unideb.inf.warehouse;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.warehouse.model.Customer;
import hu.unideb.inf.warehouse.model.Invoice;
import hu.unideb.inf.warehouse.model.Product;
import hu.unideb.inf.warehouse.model.SoldProduct;
import hu.unideb.inf.warehouse.view.*;
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
    private boolean cartCustomerSelected = false;
    private Customer cartCustomer = null;
    private ObservableList<Invoice> invoices = FXCollections.observableArrayList();
    
    /**
     * JavaFX ablakkezeléshez szüksége színpad. 
     */
    public Stage primaryStage;
    
    /**
     * JavaFX főképernyő megjelenítéséhez szüksége színpad. 
     */
    public BorderPane mainView;
    
	/**
	 * Az alkalmazásunk belépési pontja.
	 * 
	 * @param args az alkalmazás indítási paraméterei, nem használjuk semmire
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
     * JavaFX fő belépési pontja.
     *
     * @param primaryStage kijelöli az elsődleges színpadot a JavaFX indításához
     */
    @Override
	public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("WareHouse Management V1.0");
		showMainView();
		showMainMenu();
	}

	/**
	 * JavaFX alkalmazás keretrendszer megjelenítő metódus.
	 */
	public void showMainView() {
        logger.info("GUI inicializálás.");

		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/MainView.fxml"));
        try {
			mainView = loader.load();
	        Scene scene = new Scene(mainView);
	        ((MainViewController)loader.getController()).setMain(this);
	        primaryStage.setScene(scene);
	        primaryStage.show();
		} catch (IOException e) {
			logger.error("IO hiba MainView betöltés közben: " + e.getMessage());
		}
    }
	
	/**
	 * JavaFX alkalmazás főmenü megjelenítő metódus. Ez teszi lehetővé, hogy navigálni tudjunk a különbzöző nézetek között.
	 */
	public void showMainMenu() {
        logger.info("Főmenü betöltése.");
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/MainMenu.fxml"));
        BorderPane mainMenu;
		try {
			mainMenu = loader.load();
	        ((MainMenuController)loader.getController()).setMain(this);
	        mainView.setCenter(mainMenu);
	    } catch (IOException e) {
			logger.error("IO hiba MainMenu betöltés közben: " + e.getMessage());
		}
	}


	/**
	 * Metódus ami visszaadja az ügyfél adatbázist.
	 * 
	 * @return ügyfelek
	 */
	public ObservableList<Customer> getCustomers() {
		return customers;
	}
	
	/**
	 * Metódus ami visszaadja a termékek adatbázisát.
	 * 
	 * @return termékek
	 */
	public ObservableList<Product> getProducts() {
		return products;
	}

	/**
	 * Metódus ami visszaadja a kosarat.
	 * 
	 * @return kosár amiben eladott termékek vannak
	 */
	public ObservableList<SoldProduct> getCart() {
		return cart;
	}
	
	/**
	 * Metódus ami visszaadja a számlákat.
	 * 
	 * @return számlák listája
	 */
	public ObservableList<Invoice> getInvoices() {
		return invoices;
	}

	/**
	 * Számlázás vezérléséhez szükséges segédmetódus.
	 * 
	 * @return igaz ha már választottunk ügyfelet
	 */
	public boolean isCartCustomerSelected() {
		return cartCustomerSelected;
	}

	/**
	 * Számlázás vezérléséhez szükséges segédmetódus.
	 * 
	 * @param cartCustomerSelected beállítja, hogy választottunk-e már ügyfelet
	 */
	public void setCartCustomerSelected(boolean cartCustomerSelected) {
		this.cartCustomerSelected = cartCustomerSelected;
	}

	/**
	 * Metódus ami visszaadja a számlázáshoz kiválasztott ügyfelet.
	 * 
	 * @return ügyfél
	 */
	public Customer getCartCustomer() {
		return cartCustomer;
	}

	/**
	 * Metódus ami beállítja a számlázáshoz a kiválasztott ügyfelet.
	 * 
	 * @param cartCustomer ügyfél
	 */
	public void setCartCustomer(Customer cartCustomer) {
		this.cartCustomer = cartCustomer;
	}

	/**
     * Ügyfelek inicializáláshoz szükséges metódus.
     * 
	 * @param customers beállítandó ügyfelek
	 */
	public void setCustomers(ObservableList<Customer> customers) {
		this.customers = customers;
	}

	/**
	 * Termékek inicializáláshoz szükséges metódus.
	 * 
	 * @param products beállítandó termékek
	 */
	public void setProducts(ObservableList<Product> products) {
		this.products = products;
	}

	/**
	 * Számlák inicializáláshoz szükséges metódus.
	 * 
	 * @param invoices beállítandó számlák
	 */
	public void setInvoices(ObservableList<Invoice> invoices) {
		this.invoices = invoices;
	}

}