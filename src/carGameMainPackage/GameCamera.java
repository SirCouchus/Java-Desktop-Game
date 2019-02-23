package carGameMainPackage;

import java.awt.*;

public class GameCamera {

    private static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private double xOffset, yOffset;
    private Player p;

    public GameCamera(Player aPlayer, double xOffset, double yOffset){
        this.p = aPlayer;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public double getYOffset(){
        return this.yOffset;
    }

    public double getXOffset() {
        return this.xOffset;
    }

    public void centerOnEntity(Player aPlayer){
        this.xOffset = aPlayer.getPlayerX() - WIDTH / 2 + aPlayer.getWidth()/2;
        this.yOffset = aPlayer.getPlayerY() - HEIGHT / 2 + aPlayer.getHeight()/2;
    }

}
