package general.validator;

import element.*;
import exceptions.NullFieldException;
import exceptions.WrongFieldException;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class GeneralValidator extends AbstractValidator {
    public String validateName(String name) {
        return validateString(name, "name", false);
    }

    public int validateX(String xStr) {
        int x = validateInteger(xStr, "x", false);
        if (x > 201) {
            throw new WrongFieldException("value of x <= 201");
        }
        return x;
    }

    public int validateY(String yStr) {
        int y = validateInteger(yStr, "y", false);
        if (y <= -440) {
            throw new WrongFieldException("value of y > -440.");
        }
        return y;
    }

    public Double validateHealth(String healthStr) {
        Double health = validateDouble(healthStr, "health", true);
        if (health != null && health <= 0) {
            throw new WrongFieldException("value of health > 0");
        }
        return health;
    }

    public int validateHeartCount(String heartCountStr) {
        int heartCount = validateInteger(heartCountStr, "heart count", false);
        if (heartCount <= 0 || heartCount > 3) {
            throw new WrongFieldException("value of 0 < heartCount <= 3");
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
        MeleeWeapon meleeWeapon = null;
        if (meleeWeaponStr == null || meleeWeaponStr.equals("") || meleeWeaponStr.equals("null")) {
            return meleeWeapon;
        }
        meleeWeapon = MeleeWeapon.valueOf(meleeWeaponStr);
        return meleeWeapon;
    }
    public String validateChapterName(String chapterName) {
        return validateString(chapterName, "chapter name", false);
    }
    public Integer validateMarinesCount(String marinesCountStr) {
        Integer marinesCount = validateInteger(marinesCountStr, "marines count", false);
        if (marinesCount <= 0 || marinesCount > 1000) {
            throw new WrongFieldException("value of 0 < marinesCount <= 1000");
        }
        return marinesCount;
    }
    public long validateId(String idStr, List<Long> ids) {
        long id = Long.parseLong(idStr);
//        if (ids.contains(id)) {
//            throw new IdCollapseException();
//        }
        return id;
    }
    public long validateId(String idStr){
        return validateLong(idStr, "id", false);
    }
    public Date validateCreationDate(String creationDateStr) throws ParseException {
        return validateDate(creationDateStr, "creation date");
    }
    public String validateLogin(String login){
        login = validateString(login, "login", false);
        if (!Pattern.matches("\\w+", login)){
            throw new WrongFieldException("Only word characters [a-zA-Z_0-9].");
        }
        return login;
    }
    public String validatePassword(char[] passwordSecure){
        return validateString(String.valueOf(passwordSecure), "password", false);
    }
    public void validateSecondPassword(char[] passwordSecureTwo, char[] passwordSecure){
        if (!(Arrays.equals(passwordSecureTwo, passwordSecure))){
            throw new WrongFieldException("passwords doesn't match");
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
