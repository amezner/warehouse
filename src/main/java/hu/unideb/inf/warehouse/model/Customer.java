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

    private StringProperty customerID;
    private StringProperty customerName;
    private StringProperty customerAddress;
    private StringProperty customerCity;
    private StringProperty customerPostCode;
    private StringProperty customerCountry;
    private StringProperty customerEmail;
    private StringProperty customerPhone;
    private StringProperty customerLoyalty;
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
	 * @return the customerID as a string 
	 */
	public String getCustomerID() {
		return customerID.get();
	}

	/**
	 * @return the customerID as a property
	 */
	public StringProperty getCustomerIDProperty() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID.set(customerID);
	}

	/**
	 * @return the customerName as a string
	 */
	public String getCustomerName() {
		return customerName.get();
	}
	
	/**
	 * @return the customerName as a property
	 */
	public StringProperty getCustomerNameProperty() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName.set(customerName);
	}

	/**
	 * @return the customerAddress as a string
	 */
	public String getCustomerAddress() {
		return customerAddress.get();
	}
	
	/**
	 * @return the customerAddress as a property
	 */
	public StringProperty getCustomerAddressProperty() {
		return customerAddress;
	}

	/**
	 * @param customerAddress the customerAddress to set
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress.set(customerAddress);
	}

	/**
	 * @return the customerCity as a string
	 */
	public String getCustomerCity() {
		return customerCity.get();
	}
	
	/**
	 * @return the customerCity as a property
	 */
	public StringProperty getCustomerCityProperty() {
		return customerCity;
	}

	/**
	 * @param customerCity the customerCity to set
	 */
	public void setCustomerCity(String customerCity) {
		this.customerCity.set(customerCity);
	}
	
	/**
	 * @return the customerPostCode as a string
	 */
	public String getCustomerPostCode() {
		return customerPostCode.get();
	}
	
	/**
	 * @return the customerPostCode as a property
	 */
	public StringProperty getCustomerPostCodeProperty() {
		return customerPostCode;
	}

	/**
	 * @param customerPostCode the customerPostCode to set
	 */
	public void setCustomerPostCode(String customerPostCode) {
		this.customerPostCode.set(customerPostCode);
	}

	/**
	 * @return the customerCountry as a string
	 */
	public String getCustomerCountry() {
		return customerCountry.get();
	}
	
	/**
	 * @return the customerCountry as a property
	 */
	public StringProperty getCustomerCountryProperty() {
		return customerCountry;
	}

	/**
	 * @param customerCountry the customerCountry to set
	 */
	public void setCustomerCountry(String customerCountry) {
		this.customerCountry.set(customerCountry);
	}

	/**
	 * @return the customerEmail as a string
	 */
	public String getCustomerEmail() {
		return customerEmail.get();
	}
	
	/**
	 * @return the customerEmail as a property
	 */
	public StringProperty getCustomerEmailProperty() {
		return customerEmail;
	}

	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail.set(customerEmail);
	}

	/**
	 * @return the customerPhone as a string
	 */
	public String getCustomerPhone() {
		return customerPhone.get();
	}
	
	/**
	 * @return the customerPhone as a property
	 */
	public StringProperty getCustomerPhoneProperty() {
		return customerPhone;
	}

	/**
	 * @param customerPhone the customerPhone to set
	 */
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone.set(customerPhone);
	}
	
	/**
	 * @return the customerLoyalty as a string
	 */
	public String getCustomerLoyalty() {
		return customerLoyalty.get();
	}
	
	/**
	 * @return the customerLoyalty as a property
	 */
	public StringProperty getCustomerLoyaltyProperty() {
		return customerLoyalty;
	}

	/**
	 * @param customerLoyalty the customerLoyalty to set
	 */
	public void setCustomerLoyalty(String customerLoyalty) {
		this.customerLoyalty.set(customerLoyalty);
	}

	/**
	 * @return the customerDiscount as an integer value
	 */
	public Integer getCustomerDiscount() {
		return customerDiscount.get();
	}
	
	/**
	 * @return the customerDiscount
	 */
	public IntegerProperty getCustomerDiscountProperty() {
		return customerDiscount;
	}

	/**
	 * @param customerDiscount the customerDiscount to set
	 */
	public void setCustomerDiscount(Integer customerDiscount) {
		this.customerDiscount.set(customerDiscount);
	}
    
	/**
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
