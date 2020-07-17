package gamePackage;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class PathFinder {

    private GameHandler gameHandler;

    private Node[][] nodes;

    public PathFinder(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.gameHandler.setPathFinder(this);
    }

    public void createNodes() {
        int gW = this.gameHandler.getWorldHandler().getGameWidth();
        int gH = this.gameHandler.getWorldHandler().getGameHeight();
        nodes = new Node[gW][gH];

        for(int x = 1; x<gW-1; x++) {//leaves out the edges of the map
            for(int y = 1; y<gH-1; y++) {
                Node n = nodes[x][y] = new Node(this.gameHandler.getWorldHandler().getTileType(x, y).getId(), x, y);//node has same id as tile
                Node n1 = nodes[x+1][y] = new Node(this.gameHandler.getWorldHandler().getTileType(x+1, y).getId(), x+1, y);//right neighbor
                Node n2 = nodes[x][y+1] = new Node(this.gameHandler.getWorldHandler().getTileType(x, y+1).getId(), x, y+1);//bottom neighbor
                Node n3 = nodes[x-1][y]; //left
                Node n4 = nodes[x][y-1]; //top
                n.addConnection(n1);
                n.addConnection(n2);
                n.addConnection(n3);
                n.addConnection(n4);
            }
        }
    }

    public ArrayList<Node> findPath(int startX, int startY, int targetX, int targetY, ArrayList<Node> previousPath) {

        Node startNode;
        Queue<Node> queue = new LinkedList<Node>();
        ArrayList<Node> seen = new ArrayList<Node>();
        ArrayList<Node> bestPath = new ArrayList<Node>();
        ArrayList<Node> currentPath;
        Node targetNode = nodes[(int)(targetX /Tiles.tileWidth)][(int)(targetY/Tiles.tileHeight)];

        int currentPathLength;
        int bestPathLength;

        boolean foundaPath = false;
        boolean firstPathFound = false;

        if(previousPath.size() < 1) {

            currentPath = new ArrayList<Node>();
            startNode = nodes[(int)(startX /Tiles.tileWidth)][(int)(startY/Tiles.tileHeight)];
            queue.add(startNode);

            currentPathLength = 0;
            bestPathLength = 1;
        }

        else {
            currentPath = new ArrayList<Node>();
            previousPath.forEach(x -> currentPath.add(x));
            startNode = previousPath.get(previousPath.size()-1);//take off last path
            queue.add(startNode);

            currentPathLength = currentPath.size();
            bestPathLength = currentPath.size() + 1;
        }

        while(!queue.isEmpty()) {

            if(startNode.equals(targetNode))
                return previousPath;

            Node currentNode = queue.remove();

            currentPath.add(currentNode);
            seen.add(currentNode);

            currentPathLength++;

            if(currentNode.equals(targetNode)) {
                bestPathLength = currentPathLength;
                currentPath.forEach(x -> bestPath.add(x));
                firstPathFound = true;
                foundaPath = true;
            }

            if(currentPathLength < bestPathLength && foundaPath) {
                bestPathLength = currentPathLength;
                currentPath.forEach(x -> bestPath.add(x));
            }

            if(currentPathLength > bestPathLength && firstPathFound) {
                //if current path is longer than best path
                currentPathLength = 0;
                currentPath.clear();
                queue.clear();
                queue.add(startNode);
                continue;
            }
       
            for(Node n : currentNode.getConnections()) {
                if(!seen.contains(n)) {
                    queue.add(n);
                }
            }
            foundaPath = false;
        }
        bestPath.forEach(x -> System.out.print(" x: "+x.getxPos()+" y: "+x.getyPos()+" "));
        System.out.println();
        return bestPath;
    }

    public void drawPath(Graphics2D g2d, ArrayList<Node> path) {

        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(2.0f));
        int[] xPoints = new int[path.size()];
        int[] yPoints = new int[path.size()];
        for(int i = 0; i<path.size()-1; i+=2) {
            Node n1 = path.get(i);
            Node n2 = path.get(i+1);

            // xPoints[i] = ((int) ((n1.getxPos()*Tiles.tileWidth)-gameHandler.getCamera().getCameraX()));
            // yPoints[i] = ((int) ((n2.getyPos()*Tiles.tileHeight)-gameHandler.getCamera().getCameraY()));

            int x1 = xPoints[i] = ((int) ((n1.getxPos()*Tiles.tileWidth)-gameHandler.getCamera().getCameraX()));
            int y1 = yPoints[i] = ((int) ((n1.getyPos()*Tiles.tileHeight)-gameHandler.getCamera().getCameraY()));
            int x2 = xPoints[i+1] = ((int) ((n2.getxPos()*Tiles.tileWidth)-gameHandler.getCamera().getCameraX()));
            int y2 = yPoints[i+1] = ((int) ((n2.getyPos()*Tiles.tileHeight)-gameHandler.getCamera().getCameraY()));
            g2d.drawLine(x1, y1, x2, y2);
        }
        //g2d.drawPolyline(xPoints, yPoints, xPoints.length);
    }
}

class Node {

    private final int id;
    private ArrayList<Node> connections;

    private int xPos;
    private int yPos;

    public Node(int id, int x, int y) {
        this.id = id;
        this.xPos = x;
        this.yPos = y;
        connections = new ArrayList<Node>();
    }

    public void addConnection(Node n) {
        if(n != null)
            connections.add(n);
    }
    
    //getters and setters

    public int getxPos() {
        return this.xPos;
    }

    public int getyPos() {
        return this.yPos;
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<Node> getConnections() {
        return this.connections;
    }
}
