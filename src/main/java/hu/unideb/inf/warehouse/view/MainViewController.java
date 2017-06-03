package hu.unideb.inf.warehouse.view;

import hu.unideb.inf.warehouse.Main;
import javafx.fxml.FXML;

/**
 * Ez az osztályunk szolgáltatja az alapot a grafikus keretrendszernek és biztosítja, hogy mindig visszatérhessünk a főmenübe.
 * 
 * @author amezner
 * 
 */
public class MainViewController {
	
	private Main main;

	/**
	 * JavaFX inicializációhoz szükséges metódus, nélküle nem működne a megjelenítés.
	 * 
	 * @param main FXML inicializáláshoz szükséges
	 */
	public void setMain(Main main) {
        this.main = main;
    }
    
	/**
	 * Betölti a főmenüt.
	 */
	@FXML
	void goHome() {
		main.showMainMenu();
	}
}
