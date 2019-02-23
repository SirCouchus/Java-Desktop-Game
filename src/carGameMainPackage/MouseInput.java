package carGameMainPackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int myx = e.getX();
        int myy = e.getY();
        if((myx >= Main.getWidth()/2-50) && (myx <= Main.getWidth()/2 + 100)){
            if(myy >= 150 && myy <= 200){
                Game.State = Game.STATE.GAME;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
