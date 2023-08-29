import java.awt.*;

public class Wall extends Building {

    private boolean isVisible;

    public Wall(Image img, int x, int y) {
        super(img, x, y);
        this.isVisible = false;
    }


    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }
}
