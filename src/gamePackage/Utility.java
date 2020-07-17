package gamePackage;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Utility {

    private static Scanner s;
    
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Utility.class.getResource(path));
        } catch(IOException e ) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static BufferedImage cropImage(BufferedImage image, int x, int y, int w, int h) {
        return image.getSubimage(x, y, w, h);
    }

    public static String loadFile(String path) {
        try {
            s = new Scanner(new File(path));

            String data = "";

            while(s.hasNextLine()) {
                data += s.nextLine() + "\n";
            }
            return data;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            s.close();
        }
        return null;
    }
}