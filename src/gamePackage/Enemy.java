package gamePackage;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Enemy extends DynamicSprite {

    private ArrayList<Node> currentPath = new ArrayList<Node>();
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
        jumpHeight = 18.0f;
    }

    public void update() {

        velocity.setX(0);

        if(canSee()) {

            moveTo();
        }

        else {
            patrol();
        }

        velocity.addY(gravity.getY());

        move();
    }

    public void render(Graphics2D g2d) {
        g2d.fillRect((int) (pos.getX()-gameHandler.getCamera().getCameraX()),(int) (pos.getY()-gameHandler.getCamera().getCameraY()), spriteWidth, spriteHeight);
        if(currentPath != null) {
            gameHandler.getPathFinder().drawPath(g2d, currentPath);
        }
    }

    public void patrol() {
        
        //TODO add a return method so the com player returns to its starting position after chasing

        // ArrayList<Node> path = gameHandler.getPathFinder().findPath((int) startX, (int) startY, (int) pos.getX(), (int) pos.getY());
        // if(path != null)
        //     currentPath = path;

        if(pos.getX() > startX+patrolDistance || pos.getX() < startX-patrolDistance) {
            patrolSpeed *= -1;
        }
        velocity.addX(patrolSpeed);
    }

    public void moveTo() {

        Node nextNode = getNextNode();

        if(nextNode == null)
            return;
        if(nextNode.getxPos()*Tiles.tileWidth > pos.getX()) {
            velocity.addX(attackSpeed);
        }
        if(nextNode.getxPos()*Tiles.tileWidth < pos.getX()) {
            velocity.addX(-attackSpeed);
        }
        if(nextNode.getyPos()*Tiles.tileHeight - this.spriteHeight > pos.getY()) {//below
            //velocity.addY(jumpHeight);
        }
        if(nextNode.getyPos()*Tiles.tileHeight + this.spriteHeight < pos.getY()) {//up
            jump();
        }
    }

    public boolean canSee() {
        //System.out.println(" Distance: "+pos.distance(gameHandler.getPlayer().getPos()));
        if(pos.distance(gameHandler.getPlayer().getPos()) < vision) {
            xSpeed = attackSpeed;
            ArrayList<Node> path = gameHandler.getPathFinder().findPath((int) pos.getX(), (int) pos.getY(), (int) gameHandler.getPlayer().getX(), (int) gameHandler.getPlayer().getY());
            if(path != null)
                currentPath = path;
            return true;
        }
        currentPath.clear();
        return false;
    }

    public Node getNextNode() {
        if(currentPath.size() > 2) {
            return currentPath.remove(currentPath.size()-2);
        }
        else if(currentPath.size() > 1) {
            return currentPath.remove(0);
        }
        else {
            return null;
        }
    }
}