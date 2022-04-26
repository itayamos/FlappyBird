import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    public static final int WINDOW_WIDTH=1000;
    public static final int WINDOW_HEIGHT=800;
    public static void main(String[] args) {
        Main main=new Main();
    }
    public Main(){
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        GameScene gameScene=new GameScene(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        this.add(gameScene);
        this.addKeyListener(gameScene);
        this.requestFocus();
        this.setFocusable(true);
    }
}

