package general;


import inputWorkers.InputHandler;

import java.util.Locale;

/**
 * Class for storing some basic variables and objects.
 * @author Kirill Markov
 * @version 1.0
 */
public class GeneralVars {

    //private final SpaceMarineCollection curCol = new SpaceMarineCollection();
    /**
     * Count of fields in SpaceMarine object. {@link element.SpaceMarine}
     */
    public static final int VAR_COUNT = 12;
    public static final int SYSTEM_VAR_COUNT = 3;
    public static final int USERS_VAR_COUNT = VAR_COUNT - SYSTEM_VAR_COUNT;

    /**
     * Name of the save file.
     */
    public static final String saveFileName = "./docs/test.csv";
    /**
     * Headline for CSV file.
     */
    public static final String HEADLINES =
            "name,x,y,health,heartCount,category,meleeWeapon,chapterName,marinesCount,id,creationDate";
    /**
     * Name of required system variable.
     */
    public static final String SYSTEM_VARIABLE = "FILE_LAB5_PATH";
    private static Locale locale;

    private final InputHandler inputHandler = new InputHandler();

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        GeneralVars.locale = locale;
    }

    public InputHandler getInputHandler() {
        return this.inputHandler;
    }
}
