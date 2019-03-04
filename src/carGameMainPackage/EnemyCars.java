package carGameMainPackage;

import java.awt.*;

public class EnemyCars extends EnemyBehaviour {

    private Game game;

    public EnemyCars(Game game) {
        this.setEnemyHealth(100);
        this.setEnemyPath("/ENEMY_ENTITY_RED.png");
        this.setX(700);
        this.setY(600);
        this.setWidth();
        this.setHeight();
        this.enemyRect = new Rectangle(700, 600, this.getWidth(), this.getHeight());
        this.game = game;
    }

    public void update(){
        if(this.isAllowed) {
            this.setX(700 - (int) this.game.getGameCamera().getXOffset());
            this.setY(600 - (int) this.game.getGameCamera().getYOffset());
            this.enemyRect.setLocation(this.getX(), this.getY());
        }
        //this.enemyRect.setBounds((int)(this.getX() - game.getGameCamera().getXOffset()), (int)(this.getY() - game.getGameCamera().getYOffset()), this.getWidth(), this.getHeight());
        this.mover();
    }

}
