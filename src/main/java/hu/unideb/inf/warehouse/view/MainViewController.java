package hu.unideb.inf.warehouse.view;

import java.io.IOException;

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

    public void setMain(Main main) {
        this.main = main;
    }
    
	@FXML
	private void goHome() throws IOException {
		main.showMainMenu();
	}
}
