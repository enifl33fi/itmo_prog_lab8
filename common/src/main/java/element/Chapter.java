package element;

import exceptions.NullFieldException;
import exceptions.WrongFieldException;

import java.io.Serializable;

public class Chapter implements Serializable {
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Integer
            marinesCount; // Поле не может быть null, Значение поля должно быть больше 0, Максимальное
    // значение поля: 1000
    public Chapter(String name, Integer marinesCount) {
        if (name == null || name.equals("")) {
            throw new NullFieldException("chapterName");
        }
        if (marinesCount != null && (marinesCount <= 0 || marinesCount > 1000)) {
            throw new WrongFieldException(
                    "The value of the field marinesCount must be greater than 0, the maximum value of the field: 1000");
        } else if (marinesCount == null) {
            throw new NullFieldException("marinesCount");
        }
        this.name = name;
        this.marinesCount = marinesCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarinesCount() {
        return marinesCount;
    }

    public void setMarinesCount(Integer marinesCount) {
        this.marinesCount = marinesCount;
    }
}
