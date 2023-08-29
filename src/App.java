import javax.swing.JFrame;
import java.awt.EventQueue;

public class App extends JFrame {

    public App() {
        add(new Board());
        setTitle("Graphics Project");
        setSize(Board.BOARD_WIDTH, Board.BOARD_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var app = new App();
            app.setResizable(false);
            app.setVisible(true);
        });
    }
}
