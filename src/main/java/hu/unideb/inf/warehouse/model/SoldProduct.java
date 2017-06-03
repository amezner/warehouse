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

	
	/**
	 * Az eladott termék egyedi azonosítója. 
	 */
	private StringProperty soldProductID;
	
	/**
	 * Az eladott termék neve. 
	 */
    private StringProperty soldProductName;
    
	/**
	 * Az eladott termék típusa. 
	 */
    private StringProperty soldProductType;
    
	/**
	 * Az eladott termék ára. Már az ügyfél kedvezményével csökkentett árat tárolja. 
	 */
    private SimpleDoubleProperty soldProductSoldPrice;

	/**
	 * Az eladott termék mennyisége. 
	 */
    private SimpleIntegerProperty soldProductSoldQuantity;
    
	/**
	 * Az eladott termék részösszege. Automatikusan kalkulált érték az eladási árból és a mennyiségből. 
	 */
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
	 * Visszaadja az eladott termék azonosítóját.
	 * 
	 * @return termék azonsítója
	 */
	public String getSoldProductID() {
		return soldProductID.get();
	}

	/**
	 * Visszaadja az eladott termék azonosítóját JavaFX alkalmazáshoz.
	 * 
	 * @return az eladott termék azonosítója
	 */
	public StringProperty getSoldProductIDProperty() {
		return soldProductID;
	}

	/**
	 * Beállítja az eladott termék azonosítáját.
	 * 
	 * @param soldProductID az eladott termék azonosítója
	 */
	public void setSoldProductID(String soldProductID) {
		this.soldProductID.set(soldProductID);
	}

	/**
	 * Visszaadja az eladott termék nevét.
	 * 
	 * @return az eladátt termék neve
	 */
	public String getSoldProductName() {
		return soldProductName.get();
	}
	
	/**
	 * Visszaadja az eladott termék nevét JavaFX alklamazáshoz.
	 * 
	 * @return az eladátt termék neve
	 */
	public StringProperty getSoldProductNameProperty() {
		return soldProductName;
	}

	/**
	 * Beállítja az eladott termék nevét.
	 * 
	 * @param soldProductName az eladott termék neve
	 */
	public void setSoldProductName(String soldProductName) {
		this.soldProductName.set(soldProductName);
	}

	/**
	 * Visszaadja az eladott termék típusát.
	 * 
	 * @return az eladott termék típusa
	 */
	public String getSoldProductType() {
		return soldProductType.get();
	}
	
	/**
	 * Visszaadja az eladott termék típusát JavaFX alkalmazáshoz.
	 * @return az eladott termék típusa
	 */
	public StringProperty getSoldProductTypeProperty() {
		return soldProductType;
	}

	/**
	 * Beállítja az eladott termék típusát.
	 * @param soldProductType eladott termék típusa
	 */
	public void setSoldProductType(String soldProductType) {
		this.soldProductType.set(soldProductType);
	}

	/**
	 * Visszaadja az eladott termékhez tartozó eladási árat.
	 * 
	 * @return az eladott termékhez tartozó eladási ár
	 */
	public Double getSoldProductSoldPrice() {
		return soldProductSoldPrice.get();
	}
	
	/**
	 * Visszaadja az eladott termékhez tartozó eladási árat a JavaFX alkalmazáshoz.
	 * @return az eladott termékhez tartozó eladási ár
	 */
	public SimpleDoubleProperty getSoldProductSoldPriceProperty() {
		return soldProductSoldPrice;
	}

	/**
	 * Beállítja az eladott termékhez tartozó eladási árat.
	 * 
	 * @param soldProductSoldPrice az eladott termékhez tartozó eladási ár
	 */
	public void setSoldProductSoldPrice(Double soldProductSoldPrice) {
		this.soldProductSoldPrice.set(soldProductSoldPrice);
	}
 
	/**
	 * Visszaadja az eladott termékhez tartozó darabszámot.
	 * 
	 * @return az eladott termékhez tartozó darabszáma
	 */
	public Integer getSoldProductSoldQuantity() {
		return soldProductSoldQuantity.get();
	}
	
	/**
	 * Visszaadja az eladott termékhez tartozó darabszámot JavaFX alkalmazáshoz.
	 * @return az eladott termékhez tartozó darabszáma
	 */
	public SimpleIntegerProperty getSoldProductSoldQuantityProperty() {
		return soldProductSoldQuantity;
	}

	/**
	 * Beállítja az eladott termékhez tartozó darabszámot.
	 * 
	 * @param soldProductSoldQuantity az eladott termékhez tartozó darabszáma
	 */
	public void setSoldProductSoldQuantity(Integer soldProductSoldQuantity) {
		this.soldProductSoldQuantity.set(soldProductSoldQuantity);
	}
	
	/**
	 * Visszaadja az eladott termékhez tartozó részösszeget.
	 * 
	 * @return eladott termék részösszege
	 */
	public Double getSoldProductSubTotal() {
		return soldProductSubTotal.get();
	}
	
	/**
	 * Visszaadja az eladott termékhez tartozó részösszeget a JavFX alkalmazáshoz.
	 * @return eladott termék részösszege
	 */
	public SimpleDoubleProperty getSoldProductSubTotalProperty() {
		return soldProductSubTotal;
	}

	/**
	 * Beállítja az eladott termékhez tartozó részösszeget.
	 * @param soldProductSubTotal eladott termékek részösszege
	 */
	public void setSoldProductSubTotal(Double soldProductSubTotal) {
		this.soldProductSubTotal.set(soldProductSubTotal);
	}

}