package hu.unideb.inf.warehouse.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Ebben az osztályban található a programban használt összes validációs metódus.
 * 
 * @author amezner
 *
 */
public class FormValidation {
	
	/**
	 * Ügyfélazonosító formátumát tesztelő metődus.
	 * Elfogadott formátum : 3 tetszőleges karakter melyet kötelezően 4 szám követ.
	 * 
	 * @param customerID a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateCustomerID(String customerID, boolean showAlert) {
		Pattern pattern = Pattern.compile("[A-Z]{3}[0-9]{4}");
		Matcher matcher = pattern.matcher(customerID);
		if (matcher.find() && matcher.group().equals(customerID))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Vevő azonosító validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen vevő azonosító!\n\nHelyes formátum : ABC1234");
				alert.showAndWait();
			}
			return false;
		}	
	}
	
	/**
	 * A vevő nevének ellenőrzésére szolgáló metódus.
	 * A vevő neve csak betűket, számokat, szóközöket, '.' illetve '-' karaktereket tartalmazhat!
	 * 
	 * @param customerName a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis. 
	 */
	public static boolean validateCustomerName(String customerName, boolean showAlert) {
		Pattern pattern = Pattern.compile("^\\p{L}[\\p{L}0-9 .-]*");
		Matcher matcher = pattern.matcher(customerName);
		if (matcher.find() && matcher.group().equals(customerName))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Vevő név validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen vevő név!\n\nA vevő neve csak betűket, számokat, szóközöket, '.' illetve '-' karaktereket tartalmazhat!");
				alert.showAndWait();
			}
			return false;
		}	
	}	
	
	/**
	 * Ügyfél irányítószámának ellenőrzésére szolgáló metódus.
	 * Az irányítószám csak számokat tartalmazhat, ebből is legalább egyet és nem kezdődhet nullával.
	 * 
	 * @param postCode a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateCustomerPostCode(String postCode, boolean showAlert) {
		Pattern pattern = Pattern.compile("[1-9]([0-9]*)"); 
		Matcher matcher = pattern.matcher(postCode);
		if (matcher.find() && matcher.group().equals(postCode))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Irányítószám validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen irányítószám!\n\nAz irányítószám csak számokat tartalmazhat!");
				alert.showAndWait();
			}
			return false;
		}	
	}

	/**
	 * Ügyfél országának ellenőrzésére szolgáló metódus.
	 * Az ország neve csak betűket, szóközöket illetve '-' karaktert tartalmazhat!
	 * 
	 * @param customerCountry a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateCustomerCountry(String customerCountry, boolean showAlert) {
		Pattern pattern = Pattern.compile("^[\\p{L} -]+$");
		Matcher matcher = pattern.matcher(customerCountry);
		if (matcher.find() && matcher.group().equals(customerCountry))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Ország validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen országnév!\n\nAz ország neve csak betűket, szóközöket illetve '-' karaktert tartalmazhat!");
				alert.showAndWait();
			}
			return false;
		}
	}

	/**
	 * Ügyfél városának ellenőrzésére szolgáló metódus.
	 * A város neve csak betűket, szóközöket illetve '-' karaktert tartalmazhat!
	 * 
	 * @param customerCity a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateCustomerCity(String customerCity, boolean showAlert) {
		Pattern pattern = Pattern.compile("^\\p{L}[\\p{L} -]*");
		Matcher matcher = pattern.matcher(customerCity);
		if (matcher.find() && matcher.group().equals(customerCity))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Városnév validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen városnév!\n\nA város neve csak betűket, szóközöket illetve '-' karaktert tartalmazhat!");
				alert.showAndWait();
			}
			return false;
		}
	}
	
	/**
	 * Ügyfél címének ellenőrzésére szolgáló metódus.
	 * A cím mező csak betűket, számokat, szóközöket, '.', ',' illetve '-' karaktereket tartalmazhat!
	 * 
	 * @param customerAddress a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateCustomerAddress(String customerAddress, boolean showAlert) {
		Pattern pattern = Pattern.compile("[\\p{L}0-9 .,-]*");
		Matcher matcher = pattern.matcher(customerAddress);
		if (matcher.find() && matcher.group().equals(customerAddress))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Cím validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen cím!\n\nA cím mező csak betűket, számokat, szóközöket, '.', ',' illetve '-' karaktereket tartalmazhat!");
				alert.showAndWait();
			}
			return false;
		}
	}
	
	/**
	 * Ügyfél telefonszámát ellenőrző metódus.
	 * Mindenképpen + jellel kell, hogy kezdődjön melyet csak számok követhetnek. A számokat szóközzel tetszőleges el lehet választani egymástól.
	 * 
	 * @param customerPhone a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateCustomerPhone(String customerPhone, boolean showAlert) {
		Pattern pattern = Pattern.compile("^\\+(?:[0-9] ?)*");
		Matcher matcher = pattern.matcher(customerPhone);
		if (matcher.find() && matcher.group().equals(customerPhone))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Telefonszám validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen telefonszám formátum!\n\nA telefonszámot +36 30 123 456 formátumban adja meg!");
				alert.showAndWait();
			}
			return false;
		}	
	}
	
	/**
	 * Ügyfél email címének ellenőrzésére szolgáló metódus.
	 * Legalább egy karakter kis- vagy nagybető illetve szám amelyet tetszőlegesen ponttal elválasztva ugyanígy követhet
	 * az előzőekben leírthoz hasonló karaktersorozat. Ezt egy @ jellel elválasztva szintén az előzőekhez hasonló
	 * karakterlánc követ melyet egy ponttal elvalasztott további követ mely tetszőleges számban ismételhető.
	 * 
	 * @param customerEmail a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateCustomerEmail(String customerEmail, boolean showAlert) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z0-9]+)+");
		Matcher matcher = pattern.matcher(customerEmail);
		if (matcher.find() && matcher.group().equals(customerEmail))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Email cím validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen email cím!\n\nAdjon meg valódi email címet!");
				alert.showAndWait();
			}
			return false;
		}	
	}
	
	/**
	 * Ügyfél kedvezményét ellenőrző metódus.
	 * A kedvezmény maximum két számjegyű egész szám.
	 * 
	 * @param customerDiscount a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateCustomerDiscount(String customerDiscount, boolean showAlert) {
		Pattern pattern = Pattern.compile("[0-9]{1,2}");
		Matcher matcher = pattern.matcher(customerDiscount);
		if (matcher.find() && matcher.group().equals(customerDiscount))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Kedvezmény validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen kedvezmény formátum!\n\nA kedvezmény két számjegyű egész szám!");
				alert.showAndWait();
			}
			return false;
		}	
	}
	
	/**
	 * Termékazonosító formátumát tesztelő metődus.
	 * Elfogadott formátum : 3 tetszőleges karakter melyet kötelezően 4 szám követ.
	 * 
	 * @param productID a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateProductID(String productID, boolean showAlert) {
		Pattern pattern = Pattern.compile("[A-Z]{2}[0-9]{5}");
		Matcher matcher = pattern.matcher(productID);
		if (matcher.find() && matcher.group().equals(productID))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Termék azonosító validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen termék azonosító!\n\nHelyes formátum : AB12345");
				alert.showAndWait();
			}
			return false;
		}	
	}
	
	/**
	 * A termék nevének ellenőrzésére szolgáló metódus.
	 * A termék neve csak betűket, számokat, szóközöket, '.' illetve '-' karaktereket tartalmazhat!
	 * 
	 * @param productName a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis. 
	 */
	public static boolean validateProductName(String productName, boolean showAlert) {
		Pattern pattern = Pattern.compile("^\\p{L}[\\p{L}0-9 .-]*");
		Matcher matcher = pattern.matcher(productName);
		if (matcher.find() && matcher.group().equals(productName))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Termék név validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen termék név!\n\nA termék neve csak betűket, számokat, szóközöket, '.' illetve '-' karaktereket tartalmazhat!");
				alert.showAndWait();
			}
			return false;
		}	
	}	
	
	/**
	 * A termék típusának ellenőrzésére szolgáló metódus.
	 * A termék típusa csak betűket, számokat, szóközöket, '.' illetve '-' karaktereket tartalmazhat!
	 * 
	 * @param productType a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis. 
	 */
	public static boolean validateProductType(String productType, boolean showAlert) {
		Pattern pattern = Pattern.compile("^\\p{L}[\\p{L}0-9 .-]*");
		Matcher matcher = pattern.matcher(productType);
		if (matcher.find() && matcher.group().equals(productType))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Termék név validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen termék típus!\n\nA termék típusa csak betűket, számokat, szóközöket, '.' illetve '-' karaktereket tartalmazhat!");
				alert.showAndWait();
			}
			return false;
		}	
	}	
	
	/**
	 * Termék beszerzési árának ellenőrzésére szolgáló metódus.
	 * A beszerzési ár csak egész szám lehet és nem lehet nulla.
	 * 
	 * @param productPurchasePrice a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateProductPurchasePrice(String productPurchasePrice, boolean showAlert) {
		Pattern pattern = Pattern.compile("[1-9]([0-9]*)"); 
		Matcher matcher = pattern.matcher(productPurchasePrice);
		if (matcher.find() && matcher.group().equals(productPurchasePrice))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Beszerzési ár validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen beszerzési ár!\n\nA beszerzési ár csak egész szám lehet és nem lehet nulla!");
				alert.showAndWait();
			}
			return false;
		}	
	}
	
	/**
	 * Termék eladási árának ellenőrzésére szolgáló metódus.
	 * Az eladási ár csak egész szám lehet és nem lehet nulla.
	 * 
	 * @param productSellingPrice a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateProductSellingPrice(String productSellingPrice, boolean showAlert) {
		Pattern pattern = Pattern.compile("[1-9]([0-9]*)");
		Matcher matcher = pattern.matcher(productSellingPrice);
		if (matcher.find() && matcher.group().equals(productSellingPrice))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Eladási ár validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen eladási ár!\n\nAz eladási ár csak egész szám lehet és nem lehet nulla!");
				alert.showAndWait();
			}
			return false;
		}	
	}
	
	/**
	 * Termék raktérkészletének ellenőrzésére szolgáló metódus.
	 * Az raktárkészlet darabszáma csak pozitív egész szám lehet és nem lehet nulla.
	 * 
	 * @param productOnStock a tesztelendő adat
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a paraméterben megkapott adat megfelel a validációs feltételeknek, egyébként hamis.
	 */
	public static boolean validateProductOnStock(String productOnStock, boolean showAlert) {
		Pattern pattern = Pattern.compile("[1-9]([0-9]*)"); 
		Matcher matcher = pattern.matcher(productOnStock);
		if (matcher.find() && matcher.group().equals(productOnStock))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Eladási ár validáció");
				alert.setHeaderText(null);
				alert.setContentText("Helytelen készlet érték!\n\nA készlet csak pozitív egész szám lehet és nem lehet nulla!");
				alert.showAndWait();
			}
			return false;
		}	
	}
	
	/**
	 * Termék priftabilitás vizsgálatára szolgáló metódus.
	 * Az eladási ár nem lehet kisebb, mint a beszerzési ár.
	 * 
	 * @param productPurchasePrice a tesztelendő beszerzési ár
	 * @param productSellingPrice a tesztelendő eladási ár
	 * @param showAlert amennyiben igaz, akkor megjeleníti a figyelmeztető üzenetet
	 * @return igaz amennyiben a beszerzési ár kisebb, mint az eladási ár
	 */
	public static boolean validateProfit(String productPurchasePrice, String productSellingPrice, boolean showAlert) {
		if (Integer.parseInt(productPurchasePrice) < Integer.parseInt(productSellingPrice))
			return true;
		else {
			if (showAlert) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Profitabilitás validáció");
				alert.setHeaderText(null);
				alert.setContentText("Az eladási ár nem lehet kisebb, mint a beszerzési ár!");
				alert.showAndWait();
			}
			return false;
		}	
	}
}
