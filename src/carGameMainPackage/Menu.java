package carGameMainPackage;

import javax.swing.*;
import java.awt.*;

public class Menu {

    private String backgroundUrl = "/menu-background.png";

    private Rectangle playButton = new Rectangle(Main.getWidth() / 2 -50, 150, 100, 50);
    private Rectangle helpButton = new Rectangle(Main.getWidth() / 2 - 50, 250, 100, 50);
    private Rectangle quitButton = new Rectangle(Main.getWidth() / 2 - 50, 350, 100, 50);

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(this.getMenuBackground(), 0, 0, null);

        Font font0 = new Font("arial", Font.BOLD, 50);
        g.setFont(font0);
        g.setColor(Color.WHITE);
        g.drawString("The", Main.getWidth()/2 -50, 50);
        g.drawString("Driver", Main.getWidth()/2 - 75, 100);

        Font font1 = new Font("arial", Font.BOLD, 30);
        g.setFont(font1);

        g.drawString("Play!", playButton.x + 19, playButton.y + 35);
        g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
        g.drawString("Quit", quitButton.x + 19, quitButton.y + 35);

        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
    }

    private Image getMenuBackground(){
        ImageIcon i = new ImageIcon(this.getClass().getResource(this.backgroundUrl));
        return i.getImage();
    }

}
