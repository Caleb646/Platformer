package gamePackage;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Hashtable;
import java.util.HashSet;
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

    public Hashtable<Node, Integer> initGscore() {

        Hashtable<Node, Integer> gScore = new Hashtable<Node, Integer>();

        int gW = this.gameHandler.getWorldHandler().getGameWidth();
        int gH = this.gameHandler.getWorldHandler().getGameHeight();

        for(int x = 1; x<gW-1; x++) {
            for(int y = 1; y<gH-1; y++) {
                gScore.put(nodes[x][y], 5000);
            }
        }

        return gScore;
        
    }

    public int heuristic(int startX, int startY, int targetX, int targetY) {

        return Math.abs((startX-targetX)) + Math.abs((startY-targetY));
    }

    public ArrayList<Node> buildPath(Hashtable<Node, Node> cameFrom, Node currentNode) {
        ArrayList<Node> path = new ArrayList<Node>();
        while(cameFrom.containsKey(currentNode)) {
            currentNode = cameFrom.get(currentNode);
            path.add(currentNode);
        }
        return path;

    }

    public ArrayList<Node> findPath(int startX, int startY, int targetX, int targetY) {
        int count = 0;
        int normX = startX / Tiles.tileWidth;
        int normY = startY / Tiles.tileHeight;
        int normTargetX = targetX / Tiles.tileWidth;
        int normTargetY = targetY / Tiles.tileHeight;

        Node startNode = nodes[normX][normY];
        Node targetNode = nodes[normTargetX][normTargetY];

        Data data = new Data(0, count, startNode);
        PriorityQueue<Data> openSet = new PriorityQueue<Data>();
        openSet.add(data);

        Hashtable<Node, Integer> gScore = initGscore();
        gScore.put(startNode, 0);
        Hashtable<Node, Integer> fScore = initGscore();
        fScore.put(startNode, heuristic(normX, normY, normTargetX, normTargetY));

        Hashtable<Node, Node> cameFrom = new Hashtable<Node, Node>();

        HashSet<Node> openSetHash = new HashSet<Node>();
        openSetHash.add(startNode);

        ArrayList<Node> path;

        while(!openSet.isEmpty()) {

            Node currentNode = openSet.remove().node;
            openSetHash.remove(currentNode);

            if(currentNode.equals(targetNode)) {
                path = buildPath(cameFrom, currentNode);
                return path;
            }

            for(Node n : currentNode.getConnections()) {

                int tempScore = gScore.get(currentNode) + 1;
                int currentScore = gScore.get(n) == null ? 1 : gScore.get(n);
                if(tempScore < currentScore) {
                    cameFrom.put(n, currentNode);
                    gScore.put(n, tempScore);
                    fScore.put(n, tempScore +(heuristic(n.getxPos(), n.getyPos(), normTargetX, normTargetY)));
                    if(!openSetHash.contains(n)){
                        count += 1;
                        Data newData = new Data(fScore.get(n), count, n);
                        openSet.add(newData);
                        openSetHash.add(n);
                    }
                }
            }
        }
        return null; 
    }

    public void drawPath(Graphics2D g2d, ArrayList<Node> path) {

        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(2.0f));
        int[] xPoints = new int[path.size()];
        int[] yPoints = new int[path.size()];
        for(int i = 0; i<path.size()-1; i+=2) {
            Node n1 = path.get(i);
            Node n2 = path.get(i+1);

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

    private int xPos;// normalized position
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

class Data implements Comparable<Data> {
    public int fScore;
    public int count;
    public Node node;

    public Data(int zc, int c, Node node) {
        this.fScore = zc;
        this.count = c;
        this.node = node;
    }

    @Override
    public int compareTo(Data o) {
        if(fScore > count)
            return -1;
        else if(fScore < count) {
            return 1;
        }
        return 0;
    }
}
