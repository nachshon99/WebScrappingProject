import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 760;

    public static void main(String[] args) {
        Window window = new Window();
    }

    public Window(){
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("IP Of Countries");

        MainScene mainScene = new MainScene(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        this.add(mainScene);

        this.setVisible(true);

    }
}
