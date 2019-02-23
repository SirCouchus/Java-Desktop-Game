package carGameMainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import static java.lang.Math.min;

public class Player{

    private String playerImage = "/playerEntity2.png";

    private PlayerSound sound;

    private int width = 82;
    private int height = 40;

    private double velX = 0;
    private double velY = 0;

    private double radians = 0;
    private double rotationAmount = 0;

    private Rectangle playerRect;

    private Game game;

    private double locationX = this.getWidth()/2;
    private double locationY = this.getHeight()/2;

    private double x;
    private double y;

    /*This variable is used to monitor whether the
    movement key is held. This allows the user
    to steer the car whilst also holding the up key
    */
    public boolean isPressed = false;
    private boolean reverseIsPressed = false;

    public boolean isAllowed = true;

    private String soundFile = "/bensound-allthat.wav";

    private int health;
    private int healthBarX;
    private int healthBarY;

    private AffineTransform transform;

    public Player(Game game){
        this.x = 150;
        this.y = 150;
        this.health = 100;
        this.healthBarX = 10;
        this.healthBarY = 10;
        this.sound.playSound(this.soundFile);
        this.game = game;
        this.playerRect = new Rectangle((int)(x - game.getGameCamera().getXOffset()), (int)(y - game.getGameCamera().getYOffset()));
    }

    public void update(){
        game.getGameCamera().centerOnEntity(this);
        if(this.isPressed) {
            this.x += Math.cos(this.getRotationAmount() * (Math.PI / 180));
            this.y += Math.sin(this.getRotationAmount() * (Math.PI / 180));
        }
        if(this.reverseIsPressed){
            this.x -= Math.cos(this.getRotationAmount() * (Math.PI/180));
            this.y -= Math.sin(this.getRotationAmount() * (Math.PI/180));
        }
        this.setRotationAmount(this.getRotationAmount() + this.radians);
        this.playerRect.setBounds((int)(x - game.getGameCamera().getXOffset()), (int)(y - game.getGameCamera().getYOffset()), this.getWidth(), this.getHeight());

    }

    public double getPlayerX(){
        return this.x;
    }

    public double getPlayerY(){
        return this.y;
    }

    public void setPlayerX(double anX){
        this.x += anX;
    }

    public void setPlayerY(double aY){
        this.y += aY;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth(){
        return this.health;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT){
            this.radians = 1;
        } else if(key == KeyEvent.VK_LEFT){
            this.radians = -1;
        } else if(key == KeyEvent.VK_DOWN){
            this.reverseIsPressed = true;
        } else if(key == KeyEvent.VK_UP && isAllowed){
            if(this.game.getMap().getTile((int)Math.rint(this.getPlayerX()/63), (int)Math.rint(this.getPlayerY()/63))==0){
                this.isPressed = false;
            }
            this.isPressed = true;
        } else if(key == KeyEvent.VK_SPACE){
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT){
            this.radians = 0;
        }else if(key == KeyEvent.VK_LEFT){
            this.radians = 0;
        } else if(key == KeyEvent.VK_DOWN){
            this.reverseIsPressed = false;
        } else if(key == KeyEvent.VK_UP){
            this.isPressed = false;
        } else if(key == KeyEvent.VK_SPACE){

        }
    }

    public void draw(Graphics g2d){
        g2d.drawRect(9,9,this.getHealth()+1, 11);
        g2d.setColor(Color.RED);
        g2d.fillRect(this.healthBarX,this.healthBarY,this.getHealth(), this.healthBarY);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(this.healthBarX,this.healthBarY,this.getHealth(),this.healthBarY);
        /*
         * The x and y are set to the gameCamera xOffset and gameCamera yOffset.
         * If it was set to the playerX and playerY, the camera would not follow
         * the car when its rotation changes.
         */
        ((Graphics2D) g2d).rotate(Math.toRadians(this.rotationAmount), (int)(x - game.getGameCamera().getXOffset()), (int)(y - game.getGameCamera().getYOffset()));
        ((Graphics2D) g2d).draw(this.playerRect);
        g2d.drawImage(this.getPlayerImage(), (int)(x - game.getGameCamera().getXOffset()), (int)(y - game.getGameCamera().getYOffset()), null);
    }

    public Rectangle getPlayerRect(){
        return this.playerRect;
    }

    public double getRotationAmount(){
        return this.rotationAmount;
    }
    private void setRotationAmount(double rotationAmount){
        this.rotationAmount = rotationAmount;
    }

    public Image getPlayerImage(){
        ImageIcon i = new ImageIcon(this.getClass().getResource(this.playerImage));
        return i.getImage();
    }

}
