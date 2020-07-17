package gamePackage;
import java.awt.image.BufferedImage;

public class GameAssets {

    public static final int imgWidth = 64, imgHeight = 64;
    
    public static BufferedImage floorImg;

    public static void loadAssets() {
        BufferedImage itemSheet = Utility.loadImage("PlatformerItems.png");
        floorImg = Utility.cropImage(itemSheet, 0, 0, imgWidth, imgHeight);
    }
}