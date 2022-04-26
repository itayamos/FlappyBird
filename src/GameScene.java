import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class GameScene extends JPanel implements KeyListener {
    public static final int FAST_GAME=5;
    public static final int MARGIN=200;
    public static final int Create_Obstacle=2000;
    public static final int Bound=300;
    public static final int Timer_Waiting=1000;
    private boolean flag;
    private Bird player;
    private Obstacle sun;
    private ArrayList<Obstacle> UpperObstacle;
    private ArrayList<Obstacle> BottomObstacle;
    private int time;
    private int score;
    public GameScene(int x, int y, int width, int height){
        this.setBounds(x,y,width,height);
        this.setBackground(Color.cyan);
        this.player=new Bird(Main.WINDOW_HEIGHT/5,60,60);
        this.sun=new Obstacle(0,Obstacle.OBSTACLE_WIDTH);
        this.BottomObstacle=new ArrayList<>();
        this.UpperObstacle=new ArrayList<>();
        this.mainGameLoop();
        this.obstacleCreator();
        this.setDoubleBuffered(true);
        this.checkCollision();
        this.flag=false;
        this.time=0;
        this.score=0;
        this.Timer1();
    }

    public boolean isFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void Timer1() {
        new Thread(()-> {
            JLabel title = new JLabel();
            JLabel record = new JLabel();
            title.removeAll();
            record.removeAll();
            while (player.inBounds(player)&&!isFlag()) {
                int second = time % 60;
                int minute = time / 60;
                score=(int)(time/2.1);
                String time1=minute+":"+second;
                title.setText("time: "+time1);
                record.setText("score: " +score);
                title.setBounds(0, 0, 200, 100);
                record.setBounds(0, 150, 200, 100);
                this.add(title);
                this.add(record);
                title.removeAll();
                this.time++;
                title.setFont(new Font("MV BALI", Font.PLAIN, 45));
                try {
                    Thread.sleep(Timer_Waiting);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean checkIt(Bird b,ArrayList<Obstacle> obstacle){
        boolean collide=false;
        Rectangle rect1 = new Rectangle(
                b.getX1(),
                b.getY1(),
                b.getWidth1(),
                b.getHeight1()
        );
        try {
            for (Obstacle value : obstacle) {
                if (value != null) {
                    Rectangle rect2 = new Rectangle(
                            value.getX1(),
                            value.getY1(),
                            value.getWidth1(),
                            value.getHeight1());
                    if (rect1.intersects(rect2)) {
                        collide = true;
                        System.out.println("Game Over");
                    }
                } else
                    break;
            }
        }catch (ConcurrentModificationException e){
            e.getCause();
        }


        return collide;
    }

    private void checkCollision(){
        new Thread(()->{
            while (!isFlag()) {
                try {
                    if (checkIt(this.player, this.UpperObstacle) || checkIt(this.player, this.BottomObstacle)) {
                        setFlag(true);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    e.getCause();
                    break;
                }
            }
        }).start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.player.paint(g);
        this.sun.paint(g);
        try {
            for (Obstacle value : UpperObstacle) {
                value.paint(g);
            }
            for (Obstacle value : BottomObstacle) {
                value.paint(g);
            }
        }catch (ConcurrentModificationException e){
            e.getCause();
        }

        if(isFlag()){
                g.setColor(Color.RED);
                g.setFont(new Font("Game", Font.PLAIN, 45));
                g.drawString("GAME OVER", 150, 100);
        }
    }

    private void mainGameLoop() {
        new Thread(() -> {
            while (player.inBounds(player) && !isFlag()) {
                for (Obstacle value : UpperObstacle) {
                    value.moveLeft();
                }
                for (Obstacle value : BottomObstacle) {
                    value.moveLeft();
                }
                player.gravity();

                repaint();
                try {
                    Thread.sleep(FAST_GAME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void obstacleCreator(){
        new Thread(()-> {
            while (player.inBounds(player) && !isFlag()) {
                Random random = new Random();
                int t = random.nextInt(Bound) + MARGIN;

                int BottomY = t + MARGIN;
                //t represents the upper height of the upper obstacle
                this.UpperObstacle.add(new Obstacle(0,t));
                this.BottomObstacle.add(new Obstacle(BottomY,BottomY));
                try {
                    Thread.sleep(Create_Obstacle);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE && player.inBounds(player)){
            player.fly();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
