package carGameMainPackage;

import javax.swing.*;
import java.awt.*;

public abstract class EnemyBehaviour {

    private int velX;
    private int velY;

    public boolean isAllowed = true;

    public int width;
    public int height;

   // private Game game = new Game();

    private String enemyImage = "";

    private int enemyHealth;

    public Rectangle enemyRect;

    private int x;
    private int y;

    public void enemySpeed(){
        this.x = this.velX;
        this.y = this.velY;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public void setWidth(){
        this.width = 82;
    }

    public void setHeight(){
        this.height = 40;
    }

    public void setVelX(int velX){
        this.velX = velX;
    }

    public void setVelY(int velY){
        this.velY = velY;
    }

    public int getVelX(){
        return this.velX;
    }

    public int getVelY(){
        return this.velY;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setEnemyHealth(int enemyHealth){
        this.enemyHealth = enemyHealth;
    }

    public int getEnemyHealth(){
        return this.enemyHealth;
    }

    public Rectangle getEnemyRect(){
        return this.enemyRect;
    }

    public void setEnemyPath(String path){
        this.enemyImage = path;
    }

    public String getEnemyPath(){
        return this.enemyImage;
    }

    public Image getEnemyImage(){
        ImageIcon i = new ImageIcon(this.getClass().getResource(this.getEnemyPath()));
        return i.getImage();
    }

    public void draw(Graphics g2d){
        g2d.drawImage(this.getEnemyImage(), this.getX(), this.getY(), null);
        ((Graphics2D)g2d).draw(this.enemyRect);
    }

    public void mover() {
        int direction = (int) Math.floor(Math.random() * 4);
        if (direction == 0) {
            this.setVelX(-5);
        } else if (direction == 1) {
            this.setVelY(-5);
        } else if (direction == 2) {
            this.setVelX(5);
        } else {
            this.setVelY(5);
        }
    }
}
