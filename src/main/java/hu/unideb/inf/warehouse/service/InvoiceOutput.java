package hu.unideb.inf.warehouse.service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import hu.unideb.inf.warehouse.model.Invoice;
import hu.unideb.inf.warehouse.model.SoldProduct;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InvoiceOutput {

	public static void showInvoiceInWindow(Invoice invoice) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		symbols.setDecimalSeparator('.');
		DecimalFormat decimalformat = new DecimalFormat("###,##0.00 Ft",symbols);
		StringBuilder sb = new StringBuilder();
		sb.append("\n=========================================================================================================\n");
		sb.append("                                     WareHouse Management Számla\n");
		sb.append("=========================================================================================================\n");
		sb.append(String.format("%-35s%-10s%n", "Számlaszám :", invoice.getInvoiceNumber()));
		sb.append(String.format("%-35s%-10s%n", "Számla kiállításának dátuma :", invoice.getInvoiceDate()));
		sb.append("=========================================================================================================\n");
		sb.append("Vevő adatai : " + "\n");
		sb.append(invoice.getCustomer().getCustomerName() + "\n");
		sb.append(invoice.getCustomer().getCustomerFullAddress() + "\n");
		sb.append(invoice.getCustomer().getCustomerEmail() + "\n");
		sb.append(invoice.getCustomer().getCustomerPhone() + "\n");
		sb.append("=========================================================================================================\n");
		sb.append(String.format("%-35s%-10s%n", "Tagsági kártya típusa : ", invoice.getCustomer().getCustomerLoyalty()));
		sb.append(String.format("%-35s%-5s%n", "Alkalmazott kedvezmény mértéke : ", (invoice.getCustomer().getCustomerDiscount().toString() + '%')));
		sb.append("\nA kedvezményt a kiszámlázott termékek eladási ára már tartalmazza!\n");
		sb.append("=========================================================================================================\n");
		sb.append("\n");
		sb.append(String.format("%-10s%-30s%-15s%15s%15s%20s%n", "ID" , "Megnevezés" , "Típus" , "Eladási Ár" , "Mennyiség" , "Részösszeg"));
		for (SoldProduct soldProduct : invoice.getSoldProducts()) {
			sb.append(String.format("%-10s%-30s%-15s%15s%15s%20s%n",
									soldProduct.getProductID(),
									soldProduct.getProductName(),
									soldProduct.getProductType(),
									soldProduct.getProductSoldPrice(),
									soldProduct.getProductSoldQuantity(),
									decimalformat.format(soldProduct.getProductSubTotal())));
			
		}
		sb.append("\n");
		sb.append("=========================================================================================================\n");
		sb.append(String.format("%-65s%40s%n", "Számla végösszege kedvemények levonása előtt :" , decimalformat.format(invoice.getInvoiceTotal())));
		sb.append("---------------------------------------------------------------------------------------------------------\n");
		sb.append(String.format("%-25s%-5s%75s%n", "Kedvezmény mértéke : ", (invoice.getInvoiceDiscount().toString() + '%'), decimalformat.format(invoice.getInvoiceTotal()/100*(invoice.getInvoiceDiscount()))));
		double total = invoice.getInvoiceTotal()/100*(100-invoice.getInvoiceDiscount());
		sb.append("Számla végösszege : " + String.format("%85s%n", decimalformat.format(total)));

		sb.append("=========================================================================================================\n");
		
		Stage nt = new Stage();
		Group root = new Group();
		Scene szamla = new Scene(root, 780,600);
		ScrollPane sp = new ScrollPane();
		sp.setPannable(true);
		Text szamlaKi = new Text(sb.toString());
		szamlaKi.setFont(Font.font("Monospaced",12));
		sp.setContent(szamlaKi);
        root.getChildren().addAll(sp);
		nt.setScene(szamla);
        nt.showAndWait();
		
		
	}
}
