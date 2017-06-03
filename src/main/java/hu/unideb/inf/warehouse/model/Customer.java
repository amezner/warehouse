package hu.unideb.inf.warehouse.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Egy ügyfelet reprezentáló osztály.
 *  
 * @author amezner
 * 
 */
public class Customer {

	/**
	 * Az ügyfél egyedi azonosítója. 
	 */
	private StringProperty customerID;

	/**
	 * Az ügyfél neve. 
	 */
	private StringProperty customerName;

	/**
	 * Az ügyfél teljes címének cím mezője. 
	 */
	private StringProperty customerAddress;

	/**
	 * Az ügyfél teljes címének város mezője. 
	 */
	private StringProperty customerCity;

	/**
	 * Az ügyfél teljes címének irányítószám mezője. 
	 */
	private StringProperty customerPostCode;
    
	/**
	 * Az ügyfél teljes címének ország mezője. 
	 */
	private StringProperty customerCountry;
    
	/**
	 * Az ügyfél email címe. 
	 */
	private StringProperty customerEmail;

	/**
	 * Az ügyfél telefonszáma. 
	 */
	private StringProperty customerPhone;
	
	/**
	 * Az ügyfél törzsvásárlói kártyájának típusa. 
	 */
	private StringProperty customerLoyalty;
	
	/**
	 * Az ügyfélhez tartotzó kedvezmény mértéke. 
	 */
    private IntegerProperty customerDiscount;
	
	/**
	 * Egy üres konstruktor. Lehet, hogy a kesőbbiekben még szükségünk lesz rá. :)
	 */
    public Customer() {
    	
    }
    
	/**
	 * Ügyfél egyed generáláshoz használt konstruktor.
	 * 
	 * @param customerID Ügyfélazonosító, egyedi kell, hogy legyen azonban ezt nem itt kezeljük.
	 * @param customerName Ügyfél neve
	 * @param customerAddress Cím részét képező paraméter amely az utca/házszám/stb. adatokat tartalmazza
	 * @param customerCity Cím részét képező paraméter amely a várost írja le 
	 * @param customerPostCode Cím részét képező paraméter amely az irányítószámot mutatja
	 * @param customerCountry Cím részét képező paraméter amely az országot írja le
	 * @param customerEmail Ügyfél email címe
	 * @param customerPhone Úgyfél telefonszáma
	 * @param customerLoyalty Tagsági kártya típusa
	 * @param customerDiscount Kedvezmény kiszámításához használt érték
	 */
    public Customer(String customerID, 
    			 	String customerName, 
    			 	String customerAddress, 
    			 	String customerCity, 
    			 	String customerPostCode, 
    			 	String customerCountry, 
    			 	String customerEmail, 
    			 	String customerPhone, 
    			 	String customerLoyalty, 
    			 	Integer customerDiscount) {
		this.customerID = new SimpleStringProperty(customerID);
		this.customerName = new SimpleStringProperty(customerName);
		this.customerAddress = new SimpleStringProperty(customerAddress);
	 	this.customerCity = new SimpleStringProperty(customerCity);
	 	this.customerPostCode = new SimpleStringProperty(customerPostCode);
	 	this.customerCountry = new SimpleStringProperty(customerCountry);
	 	this.customerEmail = new SimpleStringProperty(customerEmail);
	 	this.customerPhone = new SimpleStringProperty(customerPhone);
		this.customerLoyalty = new SimpleStringProperty(customerLoyalty);
		this.customerDiscount = new SimpleIntegerProperty(customerDiscount);
	}

	/**
	 * Visszaadja az ügyfélazonosítót.
	 * 
	 * @return ügyfélazonosító
	 */
	public String getCustomerID() {
		return customerID.get();
	}

	/**
	 * Visszaadja az ügyfélazonosítót JavaFX felülethez.
	 * 
	 * @return ügyfélazonosító
	 */
	public StringProperty getCustomerIDProperty() {
		return customerID;
	}

	/**
	 * Beállítja az ügyfélazonosítót.
	 * 
	 * @param customerID beállítandó ügyfélazonosító
	 */
	public void setCustomerID(String customerID) {
		this.customerID.set(customerID);
	}

	/**
	 * Visszaadja az ügyfél nevét.
	 * @return ügyfél neve
	 */
	public String getCustomerName() {
		return customerName.get();
	}
	
	/**
	 * Visszaadja az ügyfél nevét JavaFX felülethez.
	 * 
	 * @return ügyfélnév
	 */
	public StringProperty getCustomerNameProperty() {
		return customerName;
	}

	/**
	 * Beállítja az ügyfél nevét.
	 * 
	 * @param customerName beállítandó ügyfélnév.
	 */
	public void setCustomerName(String customerName) {
		this.customerName.set(customerName);
	}

	/**
	 * Visszaadja a ügyfél címét.
	 * 
	 * @return ügyfél cím
	 */
	public String getCustomerAddress() {
		return customerAddress.get();
	}
	
	/**
	 * Visszaadja az ügyfél címét JavaFX alkalmazáshoz.
	 * 
	 * @return the customerAddress as a property
	 */
	public StringProperty getCustomerAddressProperty() {
		return customerAddress;
	}

	/**
	 * Beállítja az ügyfél címét.
	 * 
	 * @param customerAddress beállítandó ügyfél címe
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress.set(customerAddress);
	}

	/**
	 * Visszaadja az ügyfél városát.
	 * 
	 * @return ügyfél városa
	 */
	public String getCustomerCity() {
		return customerCity.get();
	}
	
	/**
	 * Visszaadja az ügyfél városát JavaFX alkalmazáshoz.
	 * 
	 * @return ügyfél városa
	 */
	public StringProperty getCustomerCityProperty() {
		return customerCity;
	}

	/**
	 * Beállítja az ügyfél városát.
	 * 
	 * @param customerCity beállítandó ügyfél városa
	 */
	public void setCustomerCity(String customerCity) {
		this.customerCity.set(customerCity);
	}
	
	/**
	 * Visszaadja az ügyfél irányítószámát.
	 * 
	 * @return ügyfél irányítószáma
	 */
	public String getCustomerPostCode() {
		return customerPostCode.get();
	}
	
	/**
	 * Visszaadja az ügyfél irányítószámát JavaFX alkalmazéshoz.
	 * 
	 * @return ügyfél irányítószáma
	 */
	public StringProperty getCustomerPostCodeProperty() {
		return customerPostCode;
	}

	/**
	 * Beállítja az ügyfél irányítószámát. 
	 * 
	 * @param customerPostCode beállítndó ügyfél irányítószáma
	 */
	public void setCustomerPostCode(String customerPostCode) {
		this.customerPostCode.set(customerPostCode);
	}

	/**
	 * Visszaadja az ügyfél országát.
	 * 
	 * @return ügyfél országa
	 */
	public String getCustomerCountry() {
		return customerCountry.get();
	}
	
	/**
	 * Visszaadja az ügyfél országát JavaFX alkalmazáshoz.

	 * @return ügyfél országa
	 */
	public StringProperty getCustomerCountryProperty() {
		return customerCountry;
	}

	/**
	 * Beállítja az ügyfél országát.
	 * 
	 * @param customerCountry beállítandó ügyfél országa
	 */
	public void setCustomerCountry(String customerCountry) {
		this.customerCountry.set(customerCountry);
	}

	/**
	 * Visszadja az ügyfél email címét.
	 * 
	 * @return ügyfél email címe
	 */
	public String getCustomerEmail() {
		return customerEmail.get();
	}
	
	/**
	 * Visszadja az ügyfél email címét JavaFX alkalmazáshoz.
	 * 
	 * @return ügyfél email címe
	 */
	public StringProperty getCustomerEmailProperty() {
		return customerEmail;
	}

	/**
	 * Beállítja az ügyfél email címét.
	 * 
	 * @param customerEmail beállítandó ügyfel email címe
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail.set(customerEmail);
	}

	/**
	 * Visszaadja az ügyfél telefonszámát.
	 * 
	 * @return ügyfél telefonszáma
	 */
	public String getCustomerPhone() {
		return customerPhone.get();
	}
	
	/**
	 * Visszaadja az ügyfél telefonszámát JavaFX alkalmazáshoz.
	 * 
	 * @return ügyfél telefonszáma
	 */
	public StringProperty getCustomerPhoneProperty() {
		return customerPhone;
	}

	/**
	 * Beállítja az ügyfél telefonszámát.
	 * 
	 * @param customerPhone beállítandó telefonszám
	 */
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone.set(customerPhone);
	}
	
	/**
	 * Visszaadja az ügyfél tagsági szintjét. 
	 * 
	 * @return tagsági szint
	 */
	public String getCustomerLoyalty() {
		return customerLoyalty.get();
	}
	
	/**
	 * Visszaadja az ügyfél tagsági szintjét JavaFX alkalmazáshoz.
	 * 
	 * @return tagsági szint
	 */
	public StringProperty getCustomerLoyaltyProperty() {
		return customerLoyalty;
	}

	/**
	 * Beállítja az ügyfél tagsági szintjét. 
	 * @param customerLoyalty beállítandó tagsági szint
	 */
	public void setCustomerLoyalty(String customerLoyalty) {
		this.customerLoyalty.set(customerLoyalty);
	}

	/**
	 * Visszaadja az ügyfélre érvényes kedvezmény mértékét.
	 * 
	 * @return kedvezmény mértéke
	 */
	public Integer getCustomerDiscount() {
		return customerDiscount.get();
	}
	
	/**
	 * Beállítja az ügyfélre érvényes kedvezmény mértékét JavaFX alkalmazáshoz.
	 * 
	 * @return kedvezmény mértéke
	 */
	public IntegerProperty getCustomerDiscountProperty() {
		return customerDiscount;
	}

	/**
	 * Beállítja az ügyfélre érvényes kedvezmény mértékét.
	 * 
	 * @param customerDiscount beállítandő kedvezmény mértéke
	 */
	public void setCustomerDiscount(Integer customerDiscount) {
		this.customerDiscount.set(customerDiscount);
	}
    
	/**
	 * A metódus az ügyfél teljes címét adja vissza leegyszerűsítendő az adatkezelést.
	 * 
	 * @return vevő teljes címe
	 */
	public String getCustomerFullAddress() {
		return getCustomerPostCode() + " " + getCustomerCity() + "\n" + getCustomerAddress() + "\n" + getCustomerCountry();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", customerCity=" + customerCity + ", customerPostCode=" + customerPostCode
				+ ", customerCountry=" + customerCountry + ", customerEmail=" + customerEmail + ", customerPhone="
				+ customerPhone + ", customerLoyalty=" + customerLoyalty + ", customerDiscount=" + customerDiscount
				+ "]";
	}
	
	
}
