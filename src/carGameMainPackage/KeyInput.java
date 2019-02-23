package carGameMainPackage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    /*
    This class allows the Game class to
    create a new instance of this class
    and assign the game's player to it.
    The point of this class is to use the keyAdapter
    which checks if the user has entered an input
     */
    private Player p;

    public KeyInput(Player aPlayer){
        this.p = aPlayer;
    }

    public void keyPressed(KeyEvent e){
        this.p.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        this.p.keyReleased(e);
    }
}
