package gamePackage;
import java.awt.image.BufferedImage;

public class GameAssets {

    public static final int imgWidth = 64, imgHeight = 64;
    public static final int PimgWidth = 128, PimgHeight = 128;
    
    //tiles
    public static BufferedImage floorImg, spikeImg, flagImg;

    //items
    public static BufferedImage pAxe;
    public static BufferedImage[] healthItem;
    //enemies
    public static BufferedImage turretLeftImg, turretRightImg, turretBulletImg;

    //player
    public static BufferedImage playerIdle;
    public static BufferedImage[] playerLeft, playerRight, playerJumpLeft, playerJumpRight;

    //player axe
    
    public static BufferedImage[] pAxeLeft, pAxeRight;

    public static void loadAssets() {
        BufferedImage itemSheet = Utility.loadImage("PlatformerItems.png");
        BufferedImage playerSheet = Utility.loadImage("PlayerAnimations.png");

        //tiles
        floorImg = Utility.cropImage(itemSheet, 0, 0, imgWidth, imgHeight);
        spikeImg = Utility.cropImage(itemSheet, imgWidth, 0, imgWidth, imgHeight);
        flagImg = Utility.cropImage(itemSheet, imgWidth*2, 0, imgWidth, imgHeight);

        //enemies
        turretLeftImg = Utility.cropImage(itemSheet, 0, imgHeight, imgWidth, imgHeight);
        turretRightImg = Utility.cropImage(itemSheet, imgWidth, imgHeight, imgWidth, imgHeight);
        turretBulletImg = Utility.cropImage(itemSheet, imgWidth*2, imgHeight, imgWidth, imgHeight);

        //player
        playerIdle = Utility.cropImage(playerSheet, 0, 0, PimgWidth, PimgHeight);

        playerLeft = new BufferedImage[4];
        playerLeft[0] = Utility.cropImage(playerSheet, 0, PimgHeight*3, PimgWidth, PimgHeight);
        playerLeft[1] = Utility.cropImage(playerSheet, PimgWidth, PimgHeight*3, PimgWidth, PimgHeight);
        playerLeft[2] = Utility.cropImage(playerSheet, PimgWidth*2, PimgHeight*3, PimgWidth, PimgHeight);
        playerLeft[3] = Utility.cropImage(playerSheet, PimgWidth*3, PimgHeight*3, PimgWidth, PimgHeight);


        playerRight = new BufferedImage[4];//fifth row
        playerRight[0] = Utility.cropImage(playerSheet, 0, PimgHeight, PimgWidth, PimgHeight);
        playerRight[1] = Utility.cropImage(playerSheet, PimgWidth, PimgHeight, PimgWidth, PimgHeight);
        playerRight[2] = Utility.cropImage(playerSheet, PimgWidth*2, PimgHeight, PimgWidth, PimgHeight);
        playerRight[3] = Utility.cropImage(playerSheet, PimgWidth*3, PimgHeight, PimgWidth, PimgHeight);

        playerJumpLeft = new BufferedImage[4];//fifth row
        playerJumpLeft[0] = Utility.cropImage(playerSheet, 0, PimgHeight*4, PimgWidth, PimgHeight);
        playerJumpLeft[1] = Utility.cropImage(playerSheet, PimgWidth, PimgHeight*4, PimgWidth, PimgHeight);
        playerJumpLeft[2] = Utility.cropImage(playerSheet, PimgWidth*2, PimgHeight*4, PimgWidth, PimgHeight);
        playerJumpLeft[3] = Utility.cropImage(playerSheet, PimgWidth*3, PimgHeight*4, PimgWidth, PimgHeight);

        playerJumpRight = new BufferedImage[4];//fifth row
        playerJumpRight[0] = Utility.cropImage(playerSheet, 0, PimgHeight*2, PimgWidth, PimgHeight);
        playerJumpRight[1] = Utility.cropImage(playerSheet, PimgWidth, PimgHeight*2, PimgWidth, PimgHeight);
        playerJumpRight[2] = Utility.cropImage(playerSheet, PimgWidth*2, PimgHeight*2, PimgWidth, PimgHeight);
        playerJumpRight[3] = Utility.cropImage(playerSheet, PimgWidth*3, PimgHeight*2, PimgWidth, PimgHeight);

        //player axe        
        pAxeRight = new BufferedImage[4];
        pAxeRight[0] = Utility.cropImage(itemSheet, (int)(imgWidth*0.3f), (int) (imgHeight*2.3), (int)(imgWidth*0.7f), (int)(imgHeight*0.7f));
        pAxeRight[1] = Utility.cropImage(itemSheet, (int)(imgWidth*1.3f), (int) (imgHeight*2.3), (int)(imgWidth*0.7f), (int)(imgHeight*0.7f));
        pAxeRight[2] = Utility.cropImage(itemSheet, (int)(imgWidth*2.3f), (int) (imgHeight*2.3), (int)(imgWidth*0.7f), (int)(imgHeight*0.7f));
        pAxeRight[3] = Utility.cropImage(itemSheet, (int)(imgWidth*3.3f), (int) (imgHeight*2.3), (int)(imgWidth*0.7f), (int)(imgHeight*0.7f));

        pAxeLeft = new BufferedImage[4];
        pAxeLeft[0] = Utility.cropImage(itemSheet, (int)(imgWidth*4.3f), (int) (imgHeight*2.3), (int)(imgWidth*0.7f), (int)(imgHeight*0.7f));
        pAxeLeft[1] = Utility.cropImage(itemSheet, (int)(imgWidth*5.3f), (int) (imgHeight*2.3), (int)(imgWidth*0.7f), (int)(imgHeight*0.7f));
        pAxeLeft[2] = Utility.cropImage(itemSheet, (int)(imgWidth*6.3f), (int) (imgHeight*2.3), (int)(imgWidth*0.7f), (int)(imgHeight*0.7f));
        pAxeLeft[3] = Utility.cropImage(itemSheet, (int)(imgWidth*7.3f), (int) (imgHeight*2.3), (int)(imgWidth*0.7f), (int)(imgHeight*0.7f));

        //items 
        pAxe = Utility.cropImage(itemSheet, (int)(imgWidth*8.3f), (int) (imgHeight*2.3), (int)(imgWidth*0.7f), (int)(imgHeight*0.7f));

        healthItem = new BufferedImage[2];
        healthItem[0] = Utility.cropImage(itemSheet, imgWidth*3, 0, imgWidth, imgHeight);
        healthItem[1] = Utility.cropImage(itemSheet, imgWidth*4, 0, imgWidth, imgHeight);

    }
}