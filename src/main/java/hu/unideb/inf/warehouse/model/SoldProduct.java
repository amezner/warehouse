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

	private StringProperty soldSoldProductID;
    private StringProperty soldSoldProductName;
    private StringProperty soldSoldProductType;
    private SimpleDoubleProperty soldSoldProductSoldPrice;
    private SimpleIntegerProperty soldSoldProductSoldQuantity;
    private SimpleDoubleProperty soldSoldProductSubTotal;
	
	/**
	 * Üres konstruktor.
	 */
    public SoldProduct() {
    	
    }
    
	/**
	 * Eladott termék egyed generáláshoz használt konstruktor.
	 * 
	 * @param soldSoldProductID termékazonosító, egyedi kell, hogy legyen
	 * @param soldSoldProductName termék neve
	 * @param soldSoldProductType termék típusa
	 * @param soldSoldProductSoldPrice eladási ár
	 * @param soldSoldProductSoldQuantity eladott mennyiség
	 */
    public SoldProduct(String soldSoldProductID,
    			   String soldSoldProductName, 
    			   String soldSoldProductType, 
    			   Double soldSoldProductSoldPrice, 
    			   Integer soldSoldProductSoldQuantity) {
		this.soldSoldProductID = new SimpleStringProperty(soldSoldProductID);
		this.soldSoldProductName = new SimpleStringProperty(soldSoldProductName);
		this.soldSoldProductType = new SimpleStringProperty(soldSoldProductType);
		this.soldSoldProductSoldPrice = new SimpleDoubleProperty(soldSoldProductSoldPrice);
		this.soldSoldProductSoldQuantity = new SimpleIntegerProperty(soldSoldProductSoldQuantity);
		
		this.soldSoldProductSubTotal = new SimpleDoubleProperty();
        NumberBinding multiplication = Bindings.multiply(this.getSoldProductSoldPriceProperty(), this.getSoldProductSoldQuantityProperty());
        this.getSoldProductSubTotalProperty().bind(multiplication);
    }

	/**
	 * @return the soldSoldProductID as a string 
	 */
	public String getSoldProductID() {
		return soldSoldProductID.get();
	}

	/**
	 * @return the soldSoldProductID as a property
	 */
	public StringProperty getSoldProductIDProperty() {
		return soldSoldProductID;
	}

	/**
	 * @param soldSoldProductID the soldSoldProductID to set
	 */
	public void setSoldProductID(String soldSoldProductID) {
		this.soldSoldProductID.set(soldSoldProductID);
	}

	/**
	 * @return the soldSoldProductName as a string
	 */
	public String getSoldProductName() {
		return soldSoldProductName.get();
	}
	
	/**
	 * @return the soldSoldProductName as a property
	 */
	public StringProperty getSoldProductNameProperty() {
		return soldSoldProductName;
	}

	/**
	 * @param soldSoldProductName the soldSoldProductName to set
	 */
	public void setSoldProductName(String soldSoldProductName) {
		this.soldSoldProductName.set(soldSoldProductName);
	}

	/**
	 * @return the soldSoldProductType as a string
	 */
	public String getSoldProductType() {
		return soldSoldProductType.get();
	}
	
	/**
	 * @return the soldSoldProductType as a property
	 */
	public StringProperty getSoldProductTypeProperty() {
		return soldSoldProductType;
	}

	/**
	 * @param soldSoldProductType the soldSoldProductType to set
	 */
	public void setSoldProductType(String soldSoldProductType) {
		this.soldSoldProductType.set(soldSoldProductType);
	}

	/**
	 * @return the soldSoldProductSoldPrice as an Double value
	 */
	public Double getSoldProductSoldPrice() {
		return soldSoldProductSoldPrice.get();
	}
	
	/**
	 * @return the soldSoldProductSoldPrice
	 */
	public SimpleDoubleProperty getSoldProductSoldPriceProperty() {
		return soldSoldProductSoldPrice;
	}

	/**
	 * @param soldSoldProductSoldPrice the soldSoldProductSoldPrice to set
	 */
	public void setSoldProductSoldPrice(Double soldSoldProductSoldPrice) {
		this.soldSoldProductSoldPrice.set(soldSoldProductSoldPrice);
	}
 
	/**
	 * @return the soldSoldProductSoldQuantity as an integer value
	 */
	public Integer getSoldProductSoldQuantity() {
		return soldSoldProductSoldQuantity.get();
	}
	
	/**
	 * @return the soldSoldProductSoldQuantity
	 */
	public SimpleIntegerProperty getSoldProductSoldQuantityProperty() {
		return soldSoldProductSoldQuantity;
	}

	/**
	 * @param soldSoldProductSoldQuantity the soldSoldProductSoldQuantity to set
	 */
	public void setSoldProductSoldQuantity(Integer soldSoldProductSoldQuantity) {
		this.soldSoldProductSoldQuantity.set(soldSoldProductSoldQuantity);
	}
	
	/**
	 * @return the soldSoldProductSubTotal as an Double value
	 */
	public Double getSoldProductSubTotal() {
		return soldSoldProductSubTotal.get();
	}
	
	/**
	 * @return the soldSoldProductSubTotal
	 */
	public SimpleDoubleProperty getSoldProductSubTotalProperty() {
		return soldSoldProductSubTotal;
	}

	/**
	 * @param soldSoldProductSubTotal the soldSoldProductSubTotal to set
	 */
	public void setSoldProductSubTotal(Double soldSoldProductSubTotal) {
		this.soldSoldProductSubTotal.set(soldSoldProductSubTotal);
	}

}