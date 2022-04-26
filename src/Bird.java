import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Bird extends Rectangle {
    public static final int X_VALUE=400;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;


    public Bird(int y, int width, int height) {
        this.x = X_VALUE;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color=Color.YELLOW;

    }


    public int getX1() {
        return this.x;
    }
    public int getY1() {
        return this.y;
    }

    public int getWidth1() {
        return width;
    }

    public int getHeight1() {
        return this.height;
    }


    public Color getColor() {
        return color;
    }

    public void paint(Graphics graphics){
        graphics.setColor(this.color);
        graphics.fillRect(this.x,this.y,this.width,this.height);
    }

    public void fly(){
        this.y=this.y-80;
    }

    public void gravity(){
        this.y++;
    }
    public boolean inBounds(Bird bird){
        boolean checker=true;
        if (bird.y<=0 || bird.getY()>=Main.WINDOW_HEIGHT){
            checker=false;
        }
        return checker;
    }
}

