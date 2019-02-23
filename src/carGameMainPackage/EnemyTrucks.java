package carGameMainPackage;

import java.awt.*;

public class EnemyTrucks extends EnemyBehaviour {


    private Game game;

    public EnemyTrucks(Game game){
        this.setEnemyHealth(200);
        this.setEnemyPath("/TRUCK_ENEMY_GREEN.png");
        this.setX(700);
        this.setY(500);
        enemyRect = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.game = game;
    }

    public void update(){
        this.setX(700 - (int)this.game.getGameCamera().getXOffset());
        this.setY(500 - (int)this.game.getGameCamera().getYOffset());
        this.mover();
    }

    @Override
    public void setWidth(){
        this.width = 100;
    }

    @Override
    public void setHeight(){
        this.height = 48;
    }
}
