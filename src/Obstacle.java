import java.awt.*;

public class Obstacle extends Rectangle{
    public static final int OBSTACLE_WIDTH=60;
    private int x;
    private int y;
    private int height;
    private Color color1;
    public Obstacle(int y, int height) {
        super(Main.WINDOW_WIDTH-OBSTACLE_WIDTH,y,OBSTACLE_WIDTH,height);
        this.x=Main.WINDOW_WIDTH-OBSTACLE_WIDTH;
        this.y=y;
        this.height=height;
        this.color1=Color.green;
    }

    public int getWidth1() {
        return this.width;
    }


    public Color getColor() {
        return this.color1;
    }

    public void paint(Graphics graphics){
        graphics.fillRect(this.x,this.y,OBSTACLE_WIDTH,this.height);
        graphics.setColor(getColor());
    }

    public void moveLeft(){
        this.x--;
    }


    public int getX1() {
        return x;
    }


    public int getY1() {
        return y;
    }

    public int getHeight1() {
        return height;
    }
}
