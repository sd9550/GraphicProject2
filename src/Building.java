import java.awt.*;

public class Building {

    private Image image;
    private int xCor, yCor;

    public Building(Image img, int x, int y) {
        this.image = img;
        this.xCor = x;
        this.yCor = y;
    }

    public int getX() {
        return xCor;
    }

    public void setX(int xCor) {
        this.xCor = xCor;
    }

    public int getY() {
        return yCor;
    }

    public void setY(int yCor) {
        this.yCor = yCor;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
