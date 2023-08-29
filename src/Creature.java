import javax.swing.*;
import java.awt.*;

public class Creature {

    private int health;
    private Image creatureImage;
    private int xCor, yCor;

    public Creature(int h, String img, int x, int y) {
        this.health = h;
        this.creatureImage = new ImageIcon(img).getImage();
        this.xCor = x;
        this.yCor = y;
    }

    public int getHealth() {
        return health;
    }

    public void setCreatureImage(String img) {
        this.creatureImage = new ImageIcon(img).getImage();
    }

    public Image getCreatureImage() {
        return creatureImage;
    }

    public void setX(int x) {
        this.xCor = x;
    }

    public int getX() {
        return xCor;
    }

    public void setY(int y) {
        this.yCor = y;
    }

    public int getY() {
        return yCor;
    }
}
