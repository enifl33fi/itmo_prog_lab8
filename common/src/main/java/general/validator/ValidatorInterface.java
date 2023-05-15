package general.validator;

import java.text.ParseException;
import java.util.Date;

public interface ValidatorInterface {
    String validateString(String stringToValidate, String nameOfString,  boolean canBeNull);
    Integer validateInteger(String intToValidate, String nameOfString, boolean canBeNull);
    Long validateLong(String longToValidate, String nameOfString, boolean canBeNull);
    Double validateDouble(String doubleToValidate, String nameOfString, boolean canBeNull);
    Date validateDate(String dateToValidate, String nameOfString) throws ParseException;
}
