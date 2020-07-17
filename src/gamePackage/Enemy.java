package gamePackage;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Enemy extends DynamicSprite {

    private ArrayList<Node> currentPath = new ArrayList<Node>();
    private Vector2D target;
    private float distanceFromTarget;
    private float attackSpeed = 3.0f;
    private int vision = 50;

    private float startX;
    private float startY;
    private int patrolDistance;
    private float patrolSpeed = 1.0f;

    public Enemy(GameHandler gameHandler, float x, float y, int w, int h) {
        super(gameHandler, x, y, w, h);
        startX = x;
        startY = y;
        patrolDistance = 100;
    }

    public void update() {

        velocity.setX(0);

        if(canSee()) {

            moveToPlayer();
        }

        else {
            patrol();
        }

        velocity.addY(gravity.getY());

        move();
    }

    public void render(Graphics2D g2d) {
        g2d.fillRect((int) (pos.getX()-gameHandler.getCamera().getCameraX()),(int) (pos.getY()-gameHandler.getCamera().getCameraY()), spriteWidth, spriteHeight);
        if(currentPath.size() > 0) {
            gameHandler.getPathFinder().drawPath(g2d, currentPath);
        }
    }

    public void patrol() {

        if(pos.getX() > startX+patrolDistance || pos.getX() < startX-patrolDistance) {
            patrolSpeed *= -1;
        }
        velocity.addX(patrolSpeed);
    }

    public void moveToPlayer() {
        currentPath = gameHandler.getPathFinder().findPath((int) pos.getX(), (int) pos.getY(), (int) gameHandler.getPlayer().getX(), (int) gameHandler.getPlayer().getY(), currentPath);
    }

    public boolean canSee() {
        //System.out.println(" Distance: "+pos.distance(gameHandler.getPlayer().getPos()));
        if(pos.distance(gameHandler.getPlayer().getPos()) < vision) {
            xSpeed = attackSpeed;
            return true;
        }
        currentPath.clear();
        return false;
    }
}