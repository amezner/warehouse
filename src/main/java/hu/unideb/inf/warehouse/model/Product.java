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
	 * @return the productID as a string 
	 */
	public String getProductID() {
		return productID.get();
	}

	/**
	 * @return the productID as a property
	 */
	public StringProperty getProductIDProperty() {
		return productID;
	}

	/**
	 * @param productID the productID to set
	 */
	public void setProductID(String productID) {
		this.productID.set(productID);
	}

	/**
	 * @return the productName as a string
	 */
	public String getProductName() {
		return productName.get();
	}
	
	/**
	 * @return the productName as a property
	 */
	public StringProperty getProductNameProperty() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName.set(productName);
	}

	/**
	 * @return the productType as a string
	 */
	public String getProductType() {
		return productType.get();
	}
	
	/**
	 * @return the productType as a property
	 */
	public StringProperty getProductTypeProperty() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType.set(productType);
	}

	/**
	 * @return the productPurchasePrice as an integer value
	 */
	public Integer getProductPurchasePrice() {
		return productPurchasePrice.get();
	}
	
	/**
	 * @return the productPurchasePrice
	 */
	public IntegerProperty getProductPurchasePriceProperty() {
		return productPurchasePrice;
	}

	/**
	 * @param productPurchasePrice the productPurchasePrice to set
	 */
	public void setProductPurchasePrice(Integer productPurchasePrice) {
		this.productPurchasePrice.set(productPurchasePrice);
	}
 
	/**
	 * @return the productSellingPrice as an integer value
	 */
	public Integer getProductSellingPrice() {
		return productSellingPrice.get();
	}
	
	/**
	 * @return the productSellingPrice
	 */
	public IntegerProperty getProductSellingPriceProperty() {
		return productSellingPrice;
	}

	/**
	 * @param productSellingPrice the productSellingPrice to set
	 */
	public void setProductSellingPrice(Integer productSellingPrice) {
		this.productSellingPrice.set(productSellingPrice);
	}
	
	/**
	 * @return the productOnStock as an integer value
	 */
	public Integer getProductOnStock() {
		return productOnStock.get();
	}
	
	/**
	 * @return the productOnStock
	 */
	public IntegerProperty getProductOnStockProperty() {
		return productOnStock;
	}

	/**
	 * @param productOnStock the productOnStock to set
	 */
	public void setProductOnStock(Integer productOnStock) {
		this.productOnStock.set(productOnStock);
	}
	
	/**
	 * @return the productDescription as a string 
	 */
	public String getProductDescription() {
		return productDescription.get();
	}

	/**
	 * @return the productDescription as a property
	 */
	public StringProperty getProductDescriptionProperty() {
		return productDescription;
	}

	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription.set(productDescription);
	}
}