package gamePackage;
import java.awt.Graphics2D;

public abstract class StateHandler {
    
    private static StateHandler currentState;

    public abstract void update();
    public abstract void render(Graphics2D g2d);

    public static void setCurrentState(StateHandler s) {
        currentState = s;
    }

    public static StateHandler getCurrentState() {
        return currentState;
    }
}