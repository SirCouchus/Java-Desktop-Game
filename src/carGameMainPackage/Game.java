package carGameMainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {

    private String background = "/BACKGROUND.png";

    /*
    ActionListener allows us to use Timer as
    the game loop
     */
    private Timer gameLoopTimer;

    private boolean aCollision = false;

    private EnemyBehaviour eb;
    private GameCamera gameCamera;
    private Player p;
    private EnemyCars e;
    private EnemyTrucks t;

    private GameMap map;

   // private GameCamera gameCamera;

    private Menu menu;

    public static enum STATE {
        MENU,
        GAME
    };

    public static STATE State = STATE.MENU;

    public Game(){
        this.setFocusable(true);
        this.gameLoopTimer = new Timer(10, this);
        this.gameLoopTimer.start();
        this.menu = new Menu();
        this.addMouseListener(new MouseInput());
        this.gameCamera = new GameCamera(this.p,0,0);
        this.p = new Player(this);
        this.e = new EnemyCars(this);
        this.t = new EnemyTrucks(this);
        this.map = new GameMap(this);
        this.addKeyListener(new KeyInput(this.p));

    }

    public Player getPlayer(){
        return this.p;
    }
    public EnemyCars getEnemy(){ return this.e; }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if(State == STATE.GAME) {
            /* Map must be drawn before the player */
            //g2d.drawImage(this.getBackgroundImage(), (int) (0 - this.getGameCamera().getXOffset()), (int) (0 - this.getGameCamera().getYOffset()), this);
            this.map.draw(g2d);
            this.e.draw(g2d);
            this.t.draw(g2d);
            this.p.draw(g2d);
        } else if(State == STATE.MENU){
            this.menu.render(g);
        }
        if(this.p.getHealth() <= 0){
            State = STATE.MENU;
        }
    }

    public Image getBackgroundImage(){
        ImageIcon i = new ImageIcon(this.getClass().getResource(this.getBackgroundString()));
        return i.getImage();
    }

    public String getBackgroundString(){
        return this.background;
    }

    public void collision(){
        if(!aCollision && this.p.getPlayerRect().intersects(this.e.getEnemyRect())){
            this.aCollision = true;
            this.p.setHealth(this.p.getHealth()-1);
            this.e.setEnemyHealth(this.e.getEnemyHealth()-1);
        }
        if(this.p.getHealth() <= 0){
            this.gameOver();
        }
        this.aCollision = false;
    }

    /**
     * Checks whether the player entity has collided with
     * a boundary.
     * Sets the player's coordinates so that they cannot
     * drive on grass.
     */
    public void hitBounds(){
        if(!aCollision && this.map.getTile((int)Math.floor(this.p.getPlayerX()/63), (int)Math.floor(this.p.getPlayerY()/63)) == 0){
            if(this.p.isPressed){
                System.out.println("AHH");
                this.p.isAllowed = false;
            }
        } else {
            this.p.isAllowed = true;
        }
        this.aCollision = false;
    }

    /**
     * Checks whether the enemy entity has trespassed
     * out of bounds.
     * If they have, stop them from proceeding further.
     */
    public void enemyHitBounds(){
        if(this.map.getTile((int)Math.floor(this.e.getX()/63), (int)Math.floor(this.e.getY()/63))==0){
            this.e.isAllowed = false;
        } else {
            this.e.isAllowed = true;
        }
    }

    public GameMap getMap(){
        return this.map;
    }

    public boolean isaCollision(){
        return this.aCollision;
    }

    public void gameOver(){
        this.gameLoopTimer.stop();
    }

    public GameCamera getGameCamera(){
        return this.gameCamera;
    }

    /*
     * Have to check whether State == STATE.GAME
     * because otherwise the map will try to load
     * and result in a null pointer exception
     * which was caused by the map only being drawn
     * in the paint method if State == STATE.GAME
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
        if(State == STATE.GAME){
            this.collision(); /* Checks whether player collided with enemy */
            this.hitBounds(); /* Checks whether player collided with bounds */
            this.enemyHitBounds(); /* Checks whether enemy collided with bounds */
            this.e.update();
            this.t.update();
            this.p.update();
            System.out.println("Player X: " + this.p.getPlayerX());
            System.out.println("Player Y " + this.p.getPlayerY());
        }
    }
}
