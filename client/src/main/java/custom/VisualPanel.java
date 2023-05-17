package custom;

import element.CollectionPart;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class VisualPanel extends JPanel {

    private final List<PaintComponent> visualComponents;

    public VisualPanel(){
        visualComponents = new LinkedList<>();
        setBorder(new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(Color.BLACK);
                super.paintBorder(c, g, x, y, width, height);
            }
        });
        setPreferredSize(new Dimension(700, 500));
    }
    public void updateVisualization(LinkedList<CollectionPart> data){
        visualComponents.removeAll(visualComponents.stream()
                .filter((paintComponent) -> !checkExistence(paintComponent.getElem(), data))
                .toList());
        this.repaint();
        List<CollectionPart> curElems = visualComponents.stream()
                .map(PaintComponent::getElem)
                .toList();
        List<PaintComponent> newVisualComponents = data.stream()
                .filter((elem) -> !checkExistence(elem, curElems))
                .map(PaintComponent::new)
                .toList();
        visualComponents.addAll(newVisualComponents);
        newVisualComponents.forEach(this::addPaintComponent);

    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        visualComponents.forEach((paintComponent -> {
            int size = paintComponent.getSize();
            int xCoordinate = (this.getWidth() - size)/2 + paintComponent.getElem().getCoordinates().getX();
            int yCoordinate = (this.getHeight() - size)/2 + paintComponent.getElem().getCoordinates().getY();
            paintComponent.setxCoordinate(xCoordinate);
            paintComponent.setyCoordinate(yCoordinate);
            g2.setColor(Color.BLACK);
            g2.drawOval(xCoordinate, yCoordinate, size, size);
            g2.setColor(paintComponent.getColor());
            g2.fillOval(xCoordinate, yCoordinate, size, size);
        }));
    }
    private void addPaintComponent(PaintComponent component){
        new Thread(() -> {
            int size = component.getPrefSize();
            for (int i = 0; i < size; i++){
                component.setSize(i);
                this.repaint();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private boolean checkExistence(CollectionPart elem, List<CollectionPart> data){
        for (CollectionPart curElem:data){
            if (elem.equals(curElem)){
                return true;
            }
        }
        return false;
    }
}
