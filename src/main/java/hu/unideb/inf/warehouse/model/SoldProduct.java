/**
 * 
 */
package hu.unideb.inf.warehouse.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Egy számlán belül értékesített termékekhez használt osztály.
 *
 * @author amezner
 * 
 */
public class SoldProduct {

	private StringProperty productID;
    private StringProperty productName;
    private StringProperty productType;
    private SimpleDoubleProperty productSoldPrice;
    private SimpleIntegerProperty productSoldQuantity;
    private SimpleDoubleProperty productSubTotal;
	
	/**
	 * Üres konstruktor.
	 */
    public SoldProduct() {
    	
    }
    
	/**
	 * Eladott termék egyed generáláshoz használt konstruktor.
	 * 
	 * @param productID termékazonosító, egyedi kell, hogy legyen
	 * @param productName termék neve
	 * @param productType termék típusa
	 * @param productSoldPrice eladási ár
	 * @param productSoldQuantity eladott mennyiség
	 */
    public SoldProduct(String productID,
    			   String productName, 
    			   String productType, 
    			   Integer productSoldPrice, 
    			   Integer productSoldQuantity) {
		this.productID = new SimpleStringProperty(productID);
		this.productName = new SimpleStringProperty(productName);
		this.productType = new SimpleStringProperty(productType);
		this.productSoldPrice = new SimpleDoubleProperty(productSoldPrice);
		this.productSoldQuantity = new SimpleIntegerProperty(productSoldQuantity);
		
		this.productSubTotal = new SimpleDoubleProperty();
        NumberBinding multiplication = Bindings.multiply(this.getProductSoldPriceProperty(), this.getProductSoldQuantityProperty());
        this.getProductSubTotalProperty().bind(multiplication);
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
	 * @return the productSoldPrice as an integer value
	 */
	public Double getProductSoldPrice() {
		return productSoldPrice.get();
	}
	
	/**
	 * @return the productSoldPrice
	 */
	public SimpleDoubleProperty getProductSoldPriceProperty() {
		return productSoldPrice;
	}

	/**
	 * @param productSoldPrice the productSoldPrice to set
	 */
	public void setProductSoldPrice(Double productSoldPrice) {
		this.productSoldPrice.set(productSoldPrice);
	}
 
	/**
	 * @return the productSoldQuantity as an integer value
	 */
	public Integer getProductSoldQuantity() {
		return productSoldQuantity.get();
	}
	
	/**
	 * @return the productSoldQuantity
	 */
	public SimpleIntegerProperty getProductSoldQuantityProperty() {
		return productSoldQuantity;
	}

	/**
	 * @param productSoldQuantity the productSoldQuantity to set
	 */
	public void setProductSoldQuantity(Integer productSoldQuantity) {
		this.productSoldQuantity.set(productSoldQuantity);
	}
	
	/**
	 * @return the productSubTotal as an integer value
	 */
	public Double getProductSubTotal() {
		return productSubTotal.get();
	}
	
	/**
	 * @return the productSubTotal
	 */
	public SimpleDoubleProperty getProductSubTotalProperty() {
		return productSubTotal;
	}

	/**
	 * @param productSubTotal the productSubTotal to set
	 */
	public void setProductSubTotal(Double productSubTotal) {
		this.productSubTotal.set(productSubTotal);
	}

}