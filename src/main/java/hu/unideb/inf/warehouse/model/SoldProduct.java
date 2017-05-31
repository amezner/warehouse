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

	private StringProperty soldProductID;
    private StringProperty soldProductName;
    private StringProperty soldProductType;
    private SimpleDoubleProperty soldProductSoldPrice;
    private SimpleIntegerProperty soldProductSoldQuantity;
    private SimpleDoubleProperty soldProductSubTotal;
	
	/**
	 * Üres konstruktor.
	 */
    public SoldProduct() {
    	
    }
    
	/**
	 * Eladott termék egyed generáláshoz használt konstruktor.
	 * 
	 * @param soldProductID termékazonosító, egyedi kell, hogy legyen
	 * @param soldProductName termék neve
	 * @param soldProductType termék típusa
	 * @param soldProductSoldPrice eladási ár
	 * @param soldProductSoldQuantity eladott mennyiség
	 */
    public SoldProduct(String soldProductID,
    			   String soldProductName, 
    			   String soldProductType, 
    			   Double soldProductSoldPrice, 
    			   Integer soldProductSoldQuantity) {
		this.soldProductID = new SimpleStringProperty(soldProductID);
		this.soldProductName = new SimpleStringProperty(soldProductName);
		this.soldProductType = new SimpleStringProperty(soldProductType);
		this.soldProductSoldPrice = new SimpleDoubleProperty(soldProductSoldPrice);
		this.soldProductSoldQuantity = new SimpleIntegerProperty(soldProductSoldQuantity);
		
		this.soldProductSubTotal = new SimpleDoubleProperty();
        NumberBinding multiplication = Bindings.multiply(this.getSoldProductSoldPriceProperty(), this.getSoldProductSoldQuantityProperty());
        this.getSoldProductSubTotalProperty().bind(multiplication);
    }

	/**
	 * @return the soldProductID as a string 
	 */
	public String getSoldProductID() {
		return soldProductID.get();
	}

	/**
	 * @return the soldProductID as a property
	 */
	public StringProperty getSoldProductIDProperty() {
		return soldProductID;
	}

	/**
	 * @param soldProductID the soldProductID to set
	 */
	public void setSoldProductID(String soldProductID) {
		this.soldProductID.set(soldProductID);
	}

	/**
	 * @return the soldProductName as a string
	 */
	public String getSoldProductName() {
		return soldProductName.get();
	}
	
	/**
	 * @return the soldProductName as a property
	 */
	public StringProperty getSoldProductNameProperty() {
		return soldProductName;
	}

	/**
	 * @param soldProductName the soldProductName to set
	 */
	public void setSoldProductName(String soldProductName) {
		this.soldProductName.set(soldProductName);
	}

	/**
	 * @return the soldProductType as a string
	 */
	public String getSoldProductType() {
		return soldProductType.get();
	}
	
	/**
	 * @return the soldProductType as a property
	 */
	public StringProperty getSoldProductTypeProperty() {
		return soldProductType;
	}

	/**
	 * @param soldProductType the soldProductType to set
	 */
	public void setSoldProductType(String soldProductType) {
		this.soldProductType.set(soldProductType);
	}

	/**
	 * @return the soldProductSoldPrice as an Double value
	 */
	public Double getSoldProductSoldPrice() {
		return soldProductSoldPrice.get();
	}
	
	/**
	 * @return the soldProductSoldPrice
	 */
	public SimpleDoubleProperty getSoldProductSoldPriceProperty() {
		return soldProductSoldPrice;
	}

	/**
	 * @param soldProductSoldPrice the soldProductSoldPrice to set
	 */
	public void setSoldProductSoldPrice(Double soldProductSoldPrice) {
		this.soldProductSoldPrice.set(soldProductSoldPrice);
	}
 
	/**
	 * @return the soldProductSoldQuantity as an integer value
	 */
	public Integer getSoldProductSoldQuantity() {
		return soldProductSoldQuantity.get();
	}
	
	/**
	 * @return the soldProductSoldQuantity
	 */
	public SimpleIntegerProperty getSoldProductSoldQuantityProperty() {
		return soldProductSoldQuantity;
	}

	/**
	 * @param soldProductSoldQuantity the soldProductSoldQuantity to set
	 */
	public void setSoldProductSoldQuantity(Integer soldProductSoldQuantity) {
		this.soldProductSoldQuantity.set(soldProductSoldQuantity);
	}
	
	/**
	 * @return the soldProductSubTotal as an Double value
	 */
	public Double getSoldProductSubTotal() {
		return soldProductSubTotal.get();
	}
	
	/**
	 * @return the soldProductSubTotal
	 */
	public SimpleDoubleProperty getSoldProductSubTotalProperty() {
		return soldProductSubTotal;
	}

	/**
	 * @param soldProductSubTotal the soldProductSubTotal to set
	 */
	public void setSoldProductSubTotal(Double soldProductSubTotal) {
		this.soldProductSubTotal.set(soldProductSubTotal);
	}

}