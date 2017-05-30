package hu.unideb.inf.warehouse.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Egy számlát reprezentáló osztály. 
 * 
 * @author amezner
 * 
 */
public class Invoice {

	/**
	 * Minden számlához tartozik egy egyedi azonosító ami a számlaszám.
	 */
	private StringProperty invoiceNumber;
	

	/**
	 * Számla kiállításának a dátuma.
	 */
	private StringProperty invoiceDate;

	/**
	 * Számlakedvezmény.
	 */
	private SimpleIntegerProperty invoiceDiscount;
	
	/**
	 * A vevő adatait tartalmazza.
	 */
	private Customer customer;

	/**
	 * Egy lista ami {@link SoldProduct} egyedeket tartalmaz. A lista minden egyede
	 * egy termék  ami kiszámlázásra került.
	 */
	private ObservableList<SoldProduct> soldProducts = FXCollections.observableArrayList();
	

	/**
	 * @return the invoiceNumber as a string 
	 */
	public String getInvoiceNumber() {
		return invoiceNumber.get();
	}

	/**
	 * @return the invoiceNumber as a property
	 */
	public StringProperty getInvoiceNumberProperty() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber.set(invoiceNumber);
	}
	
	/**
	 * @return the invoiceDate as a string 
	 */
	public String getInvoiceDate() {
		return invoiceDate.get();
	}

	/**
	 * @return the invoiceDate as a property
	 */
	public StringProperty getInvoiceDateProperty() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate.set(invoiceDate);
	}
	
	/**
	 * @return the invoiceDiscount as an integer value
	 */
	public Integer getInvoiceDiscount() {
		return invoiceDiscount.get();
	}
	
	/**
	 * @return the invoiceDiscount
	 */
	public IntegerProperty getInvoiceDiscountProperty() {
		return invoiceDiscount;
	}

	/**
	 * @param invoiceDiscount the invoiceDiscount to set
	 */
	public void setInvoiceDiscount(Integer invoiceDiscount) {
		this.invoiceDiscount.set(invoiceDiscount);
	}
	
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * @return the soldProducts
	 */
	public ObservableList<SoldProduct> getSoldProducts() {
		return soldProducts;
	}
	
	/**
	 * @param soldProducts the soldProducts to set
	 */
	public void setSoldProducts(ObservableList<SoldProduct> soldProducts) {
		this.soldProducts = soldProducts;
	}
	
	/**
	 * Invoice osztály konstruktora.
	 * 
	 * @param invoiceNumber az egyedi számlaszám
	 * @param invoiceDate számla dátuma
	 * @param invoiceDiscount alkalmazott számla kedvezmény
	 * @param customer a vevő adatait tartalmazza ami {@link Customer} osztály típusú.
	 * @param soldProducts a megvásárolt termákeket tartalmazó lista.
	 */
	public Invoice(String invoiceNumber, String invoiceDate, Integer invoiceDiscount, Customer customer, ObservableList<SoldProduct> soldProducts) {
		super();
		this.invoiceNumber = new SimpleStringProperty(invoiceNumber);
		this.invoiceDate = new SimpleStringProperty(invoiceDate);
		this.invoiceDiscount = new SimpleIntegerProperty(invoiceDiscount);
		this.customer = customer;
		this.soldProducts.addAll(soldProducts);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		symbols.setDecimalSeparator('.');
		DecimalFormat decimalformat = new DecimalFormat("###,##0.00 Ft",symbols);
		StringBuilder sb = new StringBuilder();
		sb.append("\n=========================================================================================================\n");
		sb.append("                                     WareHouse Management Számla\n");
		sb.append("=========================================================================================================\n");
		sb.append(String.format("%-35s%-10s%n", "Számlaszám :", invoiceNumber.getValue()));
		sb.append(String.format("%-35s%-10s%n", "Számla kiállításának dátuma :", invoiceDate.getValue()));
		sb.append("=========================================================================================================\n");
		sb.append("Vevő adatai : " + "\n");
		sb.append(customer.getCustomerName() + "\n");
		sb.append(customer.getCustomerFullAddress() + "\n");
		sb.append(customer.getCustomerEmail() + "\n");
		sb.append(customer.getCustomerPhone() + "\n");
		sb.append("=========================================================================================================\n");
		sb.append("Tagsági kártya típusa : " + customer.getCustomerLoyalty() + "\n");
		sb.append("Alkalmazott kedvezmény mértéke : " + customer.getCustomerDiscount() + "\n");
		sb.append("=========================================================================================================\n");
		sb.append("\n");
		sb.append(String.format("%-10s%-30s%-15s%15s%15s%20s%n", "ID" , "Megnevezés" , "Típus" , "Eladási Ár" , "Mennyiség" , "Részösszeg"));
		for (SoldProduct soldProduct : soldProducts) {
			sb.append(String.format("%-10s%-30s%-15s%15s%15s%20s%n",
									soldProduct.getSoldProductID(),
									soldProduct.getSoldProductName(),
									soldProduct.getSoldProductType(),
									soldProduct.getSoldProductSoldPrice(),
									soldProduct.getSoldProductSoldQuantity(),
									decimalformat.format(soldProduct.getSoldProductSubTotal())));
			
		}
		sb.append("\n");
		sb.append("=========================================================================================================\n");
		sb.append(String.format("%-65s%40s%n", "Számla végösszege kedvemények levonása előtt :" , decimalformat.format(getInvoiceTotal())));
		sb.append("---------------------------------------------------------------------------------------------------------\n");
		sb.append(String.format("%-25s%-2s%-3s%75s%n", "Kedvezmény mértéke : ", invoiceDiscount.getValue(), '%', decimalformat.format(getInvoiceTotal()/100*(invoiceDiscount.getValue()))));
		double total = getInvoiceTotal()/100*(100-invoiceDiscount.getValue());
		sb.append("Számla végösszege : " + String.format("%85s%n", decimalformat.format(total)));
		sb.append("=========================================================================================================\n");
		return sb.toString();
		
	}
	
	/**
	 * Visszadja a számla végösszegét.
	 *   
	 * @return a számla végösszege
	 */
	public Double getInvoiceTotal() {
		Double total = 0.0;
		for (SoldProduct soldProduct : soldProducts) {
			total = total + soldProduct.getSoldProductSubTotal();
		}
		return total;
	}
	
}
