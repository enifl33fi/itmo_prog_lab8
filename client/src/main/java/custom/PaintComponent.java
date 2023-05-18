package custom;

import element.CollectionPart;

import java.awt.*;

public class PaintComponent {
    private final CollectionPart elem;
    private final Color color;
    private int size;
    private int prefSize;
    private int xCoordinate;
    private int yCoordinate;

    public PaintComponent(CollectionPart elem){
        this.elem = elem;
        this.color = new Color(elem.getOwner().hashCode());
        this.prefSize = elem.getHeartCount() * 20;
        this.size = 0;
    }

    public CollectionPart getElem() {
        return elem;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate + size/2;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate + size/2;
    }

    public int getPrefSize() {
        return prefSize;
    }
    public boolean isSelected(int xMouse, int yMouse){
        return getDistance(xMouse, yMouse) <= size/2;
    }
    public double getDistance(int xMouse, int yMouse){
        return Math.sqrt(Math.pow(xMouse - xCoordinate, 2) + Math.pow(yMouse - yCoordinate, 2));
    }
}
