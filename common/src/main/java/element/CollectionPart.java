package element;

import java.io.Serializable;
import java.util.Date;

public interface CollectionPart extends Comparable<CollectionPart>, Serializable {
    long getId();

    String getName();

    void setName(String name);

    Coordinates getCoordinates();

    void setCoordinates(Coordinates coordinates);

    Date getCreationDate();

    Double getHealth();

    void setHealth(Double health);

    int getHeartCount();

    void setHeartCount(int heartCount);

    AstartesCategory getCategory();

    void setCategory(AstartesCategory category);

    MeleeWeapon getMeleeWeapon();

    void setMeleeWeapon(MeleeWeapon meleeWeapon);

    Chapter getChapter();

    void setChapter(Chapter chapter);

    @Override
    int compareTo(CollectionPart o);

    String toLineCSV();

    String getOwner();

    void setId(long id);
    @Override
    boolean equals(Object obj);

}