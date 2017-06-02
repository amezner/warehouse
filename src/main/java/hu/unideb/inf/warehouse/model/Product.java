/**
 * 
 */
package hu.unideb.inf.warehouse.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Egy terméket reprezentáló osztály.
 * 
 * @author amezner
 *
 */
public class Product {

    private StringProperty productID;
    private StringProperty productName;
    private StringProperty productType;
    private IntegerProperty productPurchasePrice;
    private IntegerProperty productSellingPrice;
    private IntegerProperty productOnStock;
    private StringProperty productDescription;
	
	/**
	 * Egy üres konstruktor. Lehet, hogy a kesőbbiekben még szükségünk lesz rá. :)
	 */
    public Product() {
    	
    }
    
	/**
	 * Raktárban kezelt termék generáláshoz használt konstruktor.
	 * 
	 * @param productID Termékazonosító, egyedi kell, hogy legyen azonban ezt nem itt kezeljük.
	 * @param productName Termék neve.
	 * @param productType Termék típusa, jelenleg nem használt de a kesőbbiekben kellhet.
	 * @param productPurchasePrice Termék beszerzési ára. Kedvezmények alkalmazása miatt fontos.
	 * @param productSellingPrice Termék eladási ára.
	 * @param productOnStock Raktáron lévő termék mennyisége.
	 * @param productDescription Termék leírása.
	 */
    public Product(String productID,
    			   String productName, 
    			   String productType, 
    			   Integer productPurchasePrice, 
    			   Integer productSellingPrice, 
    			   Integer productOnStock,
    			   String productDescription) {
		this.productID = new SimpleStringProperty(productID);
		this.productName = new SimpleStringProperty(productName);
		this.productType = new SimpleStringProperty(productType);
		this.productPurchasePrice = new SimpleIntegerProperty(productPurchasePrice);
		this.productSellingPrice = new SimpleIntegerProperty(productSellingPrice);
		this.productOnStock = new SimpleIntegerProperty(productOnStock);
		this.productDescription = new SimpleStringProperty(productDescription);
    }

	/**
	 * Visszaadja a raktáron levő termék azonosítóját.
	 * 
	 * @return a raktáron levő  termék azonosítója
	 */
	public String getProductID() {
		return productID.get();
	}

	/**
	 * Visszaadja a raktáron levő termék azonosítóját JavaFX alkalmazáshoz.
	 * 
	 * @return a raktáron levő  termék azonosítója
	 */
	public StringProperty getProductIDProperty() {
		return productID;
	}

	/**
	 * Beállítja a raktáron levő termék azonosítóját.
	 * 
	 * @param productID a raktáron levő termék azonosítója
	 */
	public void setProductID(String productID) {
		this.productID.set(productID);
	}

	/**
	 * Visszaadja a raktáron levő termék nevét.
	 * 
	 * @return a raktáron levő termék neve
	 */
	public String getProductName() {
		return productName.get();
	}
	
	/**
	 * Visszaadja a raktáron levő termék nevét JavaFX alkalmazáshoz.
	 * 
	 * @return a raktáron levő termék neve
	 */
	public StringProperty getProductNameProperty() {
		return productName;
	}

	/**
	 * Beállítja a raktáron levő termék nevét.
	 * @param productName a raktáron levő termék neve
	 */
	public void setProductName(String productName) {
		this.productName.set(productName);
	}

	/**
	 * Visszaadja a raktáron levő termék típusát.
	 * 
	 * @return a raktáron levő termék típusa
	 */
	public String getProductType() {
		return productType.get();
	}
	
	/**
	 * Visszaadja a raktáron levő termék típusát JavaFX alkalmazáshoz.
	 * 
	 * @return a raktáron levő termék típusa
	 */
	public StringProperty getProductTypeProperty() {
		return productType;
	}

	/**
	 * Beállítja a raktáron levő termék típusát.
	 * 
	 * @param productType a raktáron levő termék típusat
	 */
	public void setProductType(String productType) {
		this.productType.set(productType);
	}

	/**
	 * Visszaadja a raktáron levő termék eladási árát.
	 * 
	 * @return a raktáron levő termék beszerzési ára
	 */
	public Integer getProductPurchasePrice() {
		return productPurchasePrice.get();
	}
	
	/**
	 * Visszaadja a raktáron levő termék beszerzési árát JavaFX alkalmazáshoz.
	 * 
	 * @return a raktáron levő termék beszerzési ára
	 */
	public IntegerProperty getProductPurchasePriceProperty() {
		return productPurchasePrice;
	}

	/**
	 * Beállítja a raktáron levő termék beszerzési árát.
	 * 
	 * @param productPurchasePrice a raktáron levő termék beszerzési ára
	 */
	public void setProductPurchasePrice(Integer productPurchasePrice) {
		this.productPurchasePrice.set(productPurchasePrice);
	}
 
	/**
	 * Visszaadja a raktáron levő termék eladási árát.
	 * 
	 * @return a raktáron levő termék eladási ára
	 */
	public Integer getProductSellingPrice() {
		return productSellingPrice.get();
	}
	
	/**
	 * Visszaadja a raktáron levő termék eladási árát JavaFX alkalmazáshoz.
	 * 
	 * @return a raktáron levő termék eladási ára
	 */
	public IntegerProperty getProductSellingPriceProperty() {
		return productSellingPrice;
	}

	/**
	 * Beállítja a raktáron levő termék eladási árát.
	 * 
	 * @param productSellingPrice a raktáron levő termék eladási ára	 
	*/
	public void setProductSellingPrice(Integer productSellingPrice) {
		this.productSellingPrice.set(productSellingPrice);
	}
	
	/**
	 * Visszaadja a raktáron levő termék mennyiségét.
	 * 
	 * @return a raktáron levő termék mennyisége
	 */
	public Integer getProductOnStock() {
		return productOnStock.get();
	}
	
	/**
	 * Visszaadja a raktáron levő termék mennyiségét JavaFX alkalmazáshoz.
	 * 
	 * @return a raktáron levő termék mennyisége
	 */
	public IntegerProperty getProductOnStockProperty() {
		return productOnStock;
	}

	/**
	 * Beállítja a raktáron levő termék mennyiségét.
	 * 
	 * @param productOnStock a raktáron levő termék mennyisége
	 */
	public void setProductOnStock(Integer productOnStock) {
		this.productOnStock.set(productOnStock);
	}
	
	/**
	 * Visszaadja a raktáron levő termék leírását.
	 * 
	 * @return a raktáron levő termék leírása 
	 */
	public String getProductDescription() {
		return productDescription.get();
	}

	/**
	 * Visszaadja a raktáron levő termék leírását JavaFX alkalmazáshoz.
	 * 
	 * @return a raktáron levő termék leírása 
	 */
	public StringProperty getProductDescriptionProperty() {
		return productDescription;
	}

	/**
	 * Beállítja a raktáron levő termék leírását. 
	 * 
	 * @param productDescription a raktáron levő termék leírása
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription.set(productDescription);
	}
}