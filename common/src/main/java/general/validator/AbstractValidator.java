package general.validator;

import exceptions.NullFieldException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class AbstractValidator implements ValidatorInterface {
    @Override
    public String validateString(String stringToValidate, String nameOfString,  boolean canBeNull){
        if(stringToValidate == null || stringToValidate.isBlank()){
            if (canBeNull){
                return null;
            }
            throw new NullFieldException(nameOfString);
        }
        return stringToValidate;
    }
    @Override
    public Integer validateInteger(String intToValidate, String nameOfString, boolean canBeNull){
        if(intToValidate == null || intToValidate.isBlank() || intToValidate.equals("null")){
            if (canBeNull){
                return null;
            }
            throw new NullFieldException(nameOfString);
        }
        try{
            return Integer.parseInt(intToValidate);
        } catch (NumberFormatException e){
            ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");
            throw new NumberFormatException(r.getString("intParseException") + (canBeNull ? r.getString("orEmpty"):""));
        }
    }
    @Override
    public Double validateDouble(String doubleToValidate, String nameOfString, boolean canBeNull){
        if(doubleToValidate == null || doubleToValidate.isBlank() || doubleToValidate.equals("null")){
            if (canBeNull){
                return null;
            }
            throw new NullFieldException(nameOfString);
        }
        try{
            return Double.parseDouble(doubleToValidate);
        } catch (NumberFormatException e){
            ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");
            throw new NumberFormatException(r.getString("doubleParseException") + (canBeNull ? r.getString("orEmpty"):""));
        }
    }
    @Override
    public Date validateDate(String dateToValidate, String nameOfString) throws ParseException {
        if (dateToValidate == null || dateToValidate.isBlank()){
            throw new NullFieldException(nameOfString);
        }
        return new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH).parse(dateToValidate);
    }

    @Override
    public Long validateLong(String longToValidate, String nameOfString, boolean canBeNull) {
        if(longToValidate == null || longToValidate.isBlank() || longToValidate.equals("null")){
            if (canBeNull){
                return null;
            }
            throw new NullFieldException(nameOfString);
        }
        try {
            return Long.parseLong(longToValidate);
        }  catch (NumberFormatException e){
            ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");

            throw new NumberFormatException(r.getString("doubleParseException") + (canBeNull ? r.getString("orEmpty"):""));
        }
    }
}
