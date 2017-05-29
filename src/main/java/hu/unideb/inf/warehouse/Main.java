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
/**
 * @author akos
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
     *  JavaFX ablakkezeléshez szüksége színpad. 
     */
    public Stage primaryStage;
    
    /**
     *  JavaFX főképernyő megjelenítéséhez szüksége színpad. 
     */
    public BorderPane mainView;
    
//	public Main() {
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
//	}
    

    
	/**
	 * Az alkalmazásunk belépési pontja.
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
	 * 
	 * @throws IOException nem kezeli az IOException kivételt
	 */
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
	
	/**
	 * JavaFX alkalmazás főmenü megjelenítő metódus. Ez teszi lehetővé, hogy navigálni tudjunk a különbzöző nézetek között.
	 * 
	 * @throws IOException nem kezeli az IOException kivételt
	 */
	public void showMainMenu() throws IOException {
        logger.info("Főmenü betöltése.");
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/MainMenu.fxml"));
        BorderPane mainMenu = loader.load();
        ((MainMenuController)loader.getController()).setMain(this);
        mainView.setCenter(mainMenu);
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