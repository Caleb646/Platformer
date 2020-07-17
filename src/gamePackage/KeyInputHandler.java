package gamePackage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

public class KeyInputHandler implements KeyListener {

    public boolean UP, DOWN, RIGHT, LEFT, GROUND, SPACE;

    public KeyInputHandler() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE: 
                SPACE = true;
                break;
            case KeyEvent.VK_A: 
                LEFT = true;
                break;
            case KeyEvent.VK_D: 
                RIGHT = true;
                break;

            case KeyEvent.VK_S: 
                DOWN = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE: 
                SPACE = false;
                break;
            case KeyEvent.VK_A: 
                LEFT = false;
                break;
            case KeyEvent.VK_D: 
                RIGHT = false;
                break;

            case KeyEvent.VK_S: 
                DOWN = false;
                break;
        }
    }

    public void setSpace(boolean b) {
        SPACE = b;
    }
}