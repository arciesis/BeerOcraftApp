package xyz.beerocraft.language;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Translation {

    /**
     * The path to the .properties files
     */
    public static final String BUNDLE_NAME = "language.bundle";

    /**
     * The resourceBundle loader
     */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * A locale entry for translation
     */
    private static Locale locale_fr_FR = new Locale("fr", "FR");


    /**
     * Method that return the value of a key contained in a translation file
     *
     * @param key the entry wich I want to have the translation
     * @return the Value of the entry if no Exception and an error meassage otherwise
     */
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
        return "!" + key + "!";
    }


}
