package carGameMainPackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tiles {

    private BufferedImage image;
    private BufferedImage tiles[] = new BufferedImage[2];

    /*
    Tiles are almost always going to be about
    64 pixels so I decided to make them variables
    rather than assigning widths and heights statically
     */
    private int tileWidth = 64;
    private int tileHeight = 63;

    public Tiles(String path) throws IOException {
        this.image = ImageIO.read(getClass().getResource(path));
        this.setTiles();
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setTiles(){
        this.tiles[0] = this.image.getSubimage(0,0,this.getTileWidth(),this.getTileHeight());
        this.tiles[1] = this.image.getSubimage(65,0,this.getTileWidth(),this.getTileHeight());
    }

    public BufferedImage getTiles(int index) {
        return this.tiles[index];
    }

    public int getTileWidth(){
        return this.tileWidth;
    }

    public int getTileHeight(){
        return this.tileHeight;
    }


}
