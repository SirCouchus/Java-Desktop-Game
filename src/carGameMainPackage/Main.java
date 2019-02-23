package carGameMainPackage;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    JFrame frame = new JFrame();

    public Main(){
        frame.pack();
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setTitle("Driver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(new Game());
        frame.setVisible(true);
    }

    public static int getWidth(){
        return WIDTH;
    }

    public static void main(String[] args){
        new Main();
    }
}
