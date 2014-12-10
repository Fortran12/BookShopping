package util;

import java.util.Locale;
import java.util.ResourceBundle;

//import constants.ErrorConstants;
//import constants.SucessConstants;

public final class PropertyUtil {

	/**
	 * Instantiates a new property util.
	 */
	private PropertyUtil(){
		new PropertyUtil();
	}
	
	/**
	 * Accepts the filename of the property file and returns the bundle.
	 *
	 * @param filename -The name of the property file from which the resource bundle
	 * needs to be loaded.
	 * @return the bundles
	 * @returns ResourceBoundle object
	 */
	
	private static ResourceBundle getBundles(
			final String filename) {
		final Locale currentLocale = Locale.getDefault();
		return ResourceBundle.getBundle(filename, currentLocale);

	}
	
	public static String getJspConstant(final String key, final String fileName) {
		final ResourceBundle messages = getBundles(fileName);
		return messages.getString(key);

	}
	
	public static String getErrorMessage(final String key, final String fileName) {
		final ResourceBundle messages = getBundles(fileName);
		return messages.getString(key);

	}
	
	public static String getSuccessMessage(final String key, final String fileName) {
		final ResourceBundle messages = getBundles(fileName);
		return messages.getString(key);

	}

	public static String getDatabaseMessage(final String key, final String fileName) {
		final ResourceBundle messages = getBundles(fileName);
		return messages.getString(key);

	}
	/**
	 * Called by the BOs to get the success messages.Accepts the key as argument
	 * and returns the message as string by reading from the property file
	 * 
	 * @param key
	 *            -The key for which the messages is to be retrieved from the
	 *            properties file
	 * @return message as string
	 */
	/*public static String getSucessMessage(final String key) {
		final ResourceBundle messages = getBundles(SucessConstants.SUCCESSMESSAGES);
		return messages.getString(key);

	}*/

	/**
	 * Called by the BOs to get the error messages.Accepts the key as argument
	 * and returns the message as string by reading from the property file
	 * 
	 * @param key
	 *            -The key for which the messages is to be retrieved from the
	 *            properties file
	 * @return message as string
	 */
	/*public static String getErrorMessage(final String key) {
		final ResourceBundle messages = getBundles(ErrorConstants.ERRORMESSAGES);
		return messages.getString(key);

	}*/
	/**
	 * This method accepts the label attribute name and returns the label text
	 * based on the locale
	 * 
	 * @param label
	 *            - The label attribute name
	 * @return label text
	 */
	/*public static String getUIText(final String label) {
		final ResourceBundle bundle = getBundles(Constants.UITEXT);
		return bundle.getString(label);

	}*/
}