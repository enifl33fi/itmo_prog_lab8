package general.validator;

import element.*;
import exceptions.NullFieldException;
import exceptions.WrongFieldException;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class GeneralValidator extends AbstractValidator {
    public String validateName(String name) {
        ResourceBundle rElem = ResourceBundle.getBundle("GUI.bundles.ElementDialog");
        return validateString(name, rElem.getString("nameField"), false);
    }

    public int validateX(String xStr) {
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");
        ResourceBundle rElem = ResourceBundle.getBundle("GUI.bundles.ElementDialog");
        int x = validateInteger(xStr, rElem.getString("xCoordinateField"), false);
        if (x > 201) {
            throw new WrongFieldException(r.getString("xValueException"));
        }
        return x;
    }

    public int validateY(String yStr) {
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");
        ResourceBundle rElem = ResourceBundle.getBundle("GUI.bundles.ElementDialog");
        int y = validateInteger(yStr, rElem.getString("yCoordinateField"), false);
        if (y <= -440) {
            throw new WrongFieldException(r.getString("yValueException"));
        }
        return y;
    }

    public Double validateHealth(String healthStr) {
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");
        ResourceBundle rElem = ResourceBundle.getBundle("GUI.bundles.ElementDialog");
        Double health = validateDouble(healthStr, rElem.getString("healthField"), true);
        if (health != null && health <= 0) {
            throw new WrongFieldException(r.getString("healthValueException"));
        }
        return health;
    }

    public int validateHeartCount(String heartCountStr) {
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");
        ResourceBundle rElem = ResourceBundle.getBundle("GUI.bundles.ElementDialog");
        int heartCount = validateInteger(heartCountStr, rElem.getString("heartCountField"), false);
        if (heartCount <= 0 || heartCount > 3) {
            throw new WrongFieldException(r.getString("heartCountValueException"));
        }
        return heartCount;
    }
    public AstartesCategory validateCategory(String categoryStr) {
        AstartesCategory category = null;
        if (categoryStr == null || categoryStr.equals("") || categoryStr.equals("null")) {
            return category;
        }
        category = AstartesCategory.valueOf(categoryStr);
        return category;
    }
    public MeleeWeapon validateMeleeWeapon(String meleeWeaponStr) {
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");
        MeleeWeapon meleeWeapon = null;
        if (meleeWeaponStr == null || meleeWeaponStr.equals("") || meleeWeaponStr.equals("null")) {
            return meleeWeapon;
        }
        meleeWeapon = MeleeWeapon.valueOf(meleeWeaponStr);
        return meleeWeapon;
    }
    public String validateChapterName(String chapterName) {
        ResourceBundle rElem = ResourceBundle.getBundle("GUI.bundles.ElementDialog");
        return validateString(chapterName, rElem.getString("chapterNameField"), false);
    }
    public Integer validateMarinesCount(String marinesCountStr) {
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");
        ResourceBundle rElem = ResourceBundle.getBundle("GUI.bundles.ElementDialog");
        Integer marinesCount = validateInteger(marinesCountStr, rElem.getString("marinesCountField"), false);
        if (marinesCount <= 0 || marinesCount > 1000) {
            throw new WrongFieldException(r.getString("marinesCountValueException"));
        }
        return marinesCount;
    }
    public long validateId(String idStr, List<Long> ids) {
        ResourceBundle rElem = ResourceBundle.getBundle("GUI.bundles.ElementDialog");
        long id = validateLong(idStr, rElem.getString("idField"), false);
//        if (ids.contains(id)) {
//            throw new IdCollapseException();
//        }
        return id;
    }
    public long validateId(String idStr){
        return validateLong(idStr, "id", false);
    }
    public Date validateCreationDate(String creationDateStr) throws ParseException {
        ResourceBundle rElem = ResourceBundle.getBundle("GUI.bundles.ElementDialog");
        return validateDate(creationDateStr, rElem.getString("creationDateField"));
    }
    public String validateLogin(String login){
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");
        ResourceBundle rLogin = ResourceBundle.getBundle("GUI.bundles.RegOrEnter");
        login = validateString(login, rLogin.getString("loginName"), false);
        if (!Pattern.matches("\\w+", login)){
            throw new WrongFieldException(r.getString("formatException"));
        }
        return login;
    }
    public String validatePassword(char[] passwordSecure){
        ResourceBundle rLogin = ResourceBundle.getBundle("GUI.bundles.RegOrEnter");
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");
        return validateString(String.valueOf(passwordSecure), rLogin.getString("passwordName"), false);
    }
    public void validateSecondPassword(char[] passwordSecureTwo, char[] passwordSecure){
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.Exceptions");
        if (!(Arrays.equals(passwordSecureTwo, passwordSecure))){
            throw new WrongFieldException(r.getString("PasswordNotMatchException"));
        }
    }
    public SpaceMarine validateSpaceMarine(
            String name,
            String xStr,
            String yStr,
            String healthStr,
            String heartCountStr,
            String categoryStr,
            String meleeWeaponStr,
            String chapterName,
            String marinesCountStr,
            String idStr,
            List<Long> ids,
            String creationDateStr,
            String owner)
            throws ParseException {
        name = this.validateName(name);
        int x = this.validateX(xStr);
        int y = this.validateY(yStr);
        Coordinates coordinates = new Coordinates(x, y);
        Double health = this.validateHealth(healthStr);
        int heartCount = this.validateHeartCount(heartCountStr);
        AstartesCategory category = this.validateCategory(categoryStr);
        MeleeWeapon meleeWeapon = this.validateMeleeWeapon(meleeWeaponStr);
        chapterName = this.validateChapterName(chapterName);
        Integer marinesCount = this.validateMarinesCount(marinesCountStr);
        Chapter chapter = new Chapter(chapterName, marinesCount);
        long id = this.validateId(idStr, ids);
        Date creationDate = this.validateCreationDate(creationDateStr);
        return new SpaceMarine(
                name, coordinates, health, heartCount, category, meleeWeapon, chapter, id, creationDate, owner);
    }
    public SpaceMarine validateSpaceMarine(String[] args, String owner) {
        String name = this.validateName(args[0]);
        int x = this.validateX(args[1]);
        int y = this.validateY(args[2]);
        Coordinates coordinates = new Coordinates(x, y);
        Double health = this.validateHealth(args[3]);
        int heartCount = this.validateHeartCount(args[4]);
        AstartesCategory category = this.validateCategory(args[5]);
        MeleeWeapon meleeWeapon = this.validateMeleeWeapon(args[6]);
        String chapterName = this.validateChapterName(args[7]);
        Integer marinesCount = this.validateMarinesCount(args[8]);
        Chapter chapter = new Chapter(chapterName, marinesCount);
        return new SpaceMarine(name, coordinates, health, heartCount, category, meleeWeapon, chapter, owner);
    }
    public SpaceMarine validateSpaceMarine(String[] args) throws ParseException {
        long id = this.validateId(args[0]);
        String name = this.validateName(args[1]);
        int x = this.validateX(args[2]);
        int y = this.validateY(args[3]);
        Coordinates coordinates = new Coordinates(x, y);
        Double health = this.validateHealth(args[4]);
        int heartCount = this.validateHeartCount(args[5]);
        AstartesCategory category = this.validateCategory(args[6]);
        MeleeWeapon meleeWeapon = this.validateMeleeWeapon(args[7]);
        String chapterName = this.validateChapterName(args[8]);
        Integer marinesCount = this.validateMarinesCount(args[9]);
        Chapter chapter = new Chapter(chapterName, marinesCount);
        Date creationDate = this.validateCreationDate(args[10]);
        String owner = args[11];
        return new SpaceMarine(
                name, coordinates, health, heartCount, category, meleeWeapon, chapter, id, creationDate, owner);

    }
    public void simpleValidate(String fieldStr, Class<?> givClass, boolean canBeNull){
        if(fieldStr == null || fieldStr.isBlank()){
            if (canBeNull){
                return;
            }
            throw new NullFieldException("");
        }
        try {
            givClass.cast(fieldStr);
        } catch (ClassCastException e){
            throw new ClassCastException("must be " + givClass.getName());
        }
    }
}
