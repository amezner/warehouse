package hu.unideb.inf.warehouse.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.warehouse.Main;
import hu.unideb.inf.warehouse.model.Invoice;
import hu.unideb.inf.warehouse.service.InvoiceHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Számla megtekintést vezérlő osztály. Metódusai vezérlik a grafikus felüleltet. 
 * 
 * @author amezner
 *
 */
public class InvoicesViewController {

	private static Logger logger = LoggerFactory.getLogger(InvoicesViewController.class);

	
	/**
	 * JavaFX inicializációhoz szükséges metódus, nélküle nem működne a megjelenítés.
	 * 
	 * @param main FXML inicializáláshoz szükséges
	 */
	public void setMain(Main main) {
		invoiceTable.setItems(main.getInvoices());
	}

	@FXML
	private TableView<Invoice> invoiceTable;

	@FXML
	private TableColumn<Invoice, String> invoiceNumberColumn;

	@FXML
	private TableColumn<Invoice, String> customerNameColumn;

	@FXML
	private TableColumn<Invoice, String> invoiceDateColumn;

    /**
     * JavaFX elemek inicializálását végző metódus.
     */
	@FXML
	void initialize() {

		invoiceNumberColumn.setCellValueFactory(c -> c.getValue().getInvoiceNumberProperty());
		customerNameColumn.setCellValueFactory(c -> c.getValue().getCustomer().getCustomerNameProperty());
		invoiceDateColumn.setCellValueFactory(c -> c.getValue().getInvoiceDateProperty());

	}

	/**
	 * Számla megtekintése gomb eseményét kezelő metódus. 
	 */
	@FXML
	void viewInvoice() {
		
		if (invoiceTable.getSelectionModel().getSelectedIndex() >= 0) {
		  Invoice invoice = invoiceTable.getItems().get(invoiceTable.getSelectionModel().getSelectedIndex());
		  InvoiceHandler.showInvoiceInWindow(invoice);
		} else {
			logger.info("Nincs kiválasztva számla!");
		}
	}

}
