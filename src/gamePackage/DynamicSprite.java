package gamePackage;

import java.awt.Graphics2D;

public class DynamicSprite extends Sprite {
    protected Vector2D velocity;
    protected float xSpeed = 4.0f;
    protected int maxSpeed = 10;
    protected float jumpHeight = 15.0f;

    public DynamicSprite(GameHandler gameHandler, float x, float y, int w, int h) {
        super(gameHandler, x, y, w, h);
        velocity = new Vector2D(0.0f, 0.0f);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2d) {

    }

    public boolean onGround() {

        return tileCollision((int) ((pos.getX()+ spriteWidth / 2) /Tiles.tileWidth), (int) (pos.getY()+spriteHeight+5)/Tiles.tileHeight);
    }

    public void move() {

        if(canMoveX()) {
            pos.addX(velocity.getX());
        }
        if(canMoveY()) {
            pos.addY(velocity.getY());
        }

        if(velocity.getY() > maxSpeed) {
            velocity.setY(maxSpeed);
        }
    }

    public boolean canMoveX() {
        if(velocity.getX() > 0) {//running right
            if(!tileCollision((int) (pos.getX() + spriteWidth + velocity.getX()) / Tiles.tileWidth, (int) pos.getY() / Tiles.tileHeight) && //top right
            !tileCollision((int) (pos.getX() + spriteWidth + velocity.getX()) / Tiles.tileWidth, (int) (pos.getY() + spriteHeight) / Tiles.tileHeight))//bottom right
                return true;
        }
        else if(velocity.getX() < 0) {//running left
            if(!tileCollision((int) (pos.getX() + velocity.getX()) / Tiles.tileWidth, (int) pos.getY() / Tiles.tileHeight) && //top left
            !tileCollision((int) (pos.getX() + velocity.getX()) / Tiles.tileWidth, (int) (pos.getY() + spriteHeight) / Tiles.tileHeight))//bottom left
                return true;
        }
        return false;
    }

    public boolean canMoveY() {
        if(velocity.getY() < 0) {//running up
            if(!tileCollision((int) (pos.getX()) / Tiles.tileWidth, (int) (pos.getY() + velocity.getY()) / Tiles.tileHeight) && //top left
            !tileCollision((int) (pos.getX() + spriteWidth) / Tiles.tileWidth, (int) (pos.getY() + velocity.getY()) / Tiles.tileHeight))//top right
                return true;
        }
        else if(velocity.getY() > 0) {//running down
            if(!tileCollision((int) (pos.getX()) / Tiles.tileWidth, (int) (pos.getY() + velocity.getY() + spriteHeight) / Tiles.tileHeight) && //bottom left
            !tileCollision((int) (pos.getX() + spriteWidth) / Tiles.tileWidth, (int) (pos.getY() + velocity.getY() + spriteHeight) / Tiles.tileHeight))//bottom right
                return true;
        }
        velocity.setY(0);
        return false;
    }

    public boolean tileCollision(int x, int y) {
        if(this.gameHandler.getWorldHandler().getTileType(x, y).isSolid())
            return true;
        return false;
    }

    //getters and setters

    public float getXspeed() {
        return this.xSpeed;
    }

    public float getJumpHeight() {
        return this.jumpHeight;
    }

    public Vector2D getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Vector2D v) {
        this.velocity = v;
    }
    
}