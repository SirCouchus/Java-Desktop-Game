package carGameMainPackage;

import java.util.ArrayList;
import java.util.Collections;

public class AStarPathFinder {
    /* The set of nodes that haven't been searched */
    private ArrayList closed = new ArrayList();
    /* The set of nodes that can still be visited */
    private ArrayList open = new ArrayList();

    /* Import the tile-based map */
    private GameMap map;
    /* The maximum depth of search before giving up */
    private int maxSearchDistance;

    private Node[][] nodes;
    /* True if diagonal movement allowed */
    private boolean allowDiagMovement;

    /* The 2D array containing map information */
    private int[][] tiledMap;

    /* The actual heuristic */
    private AStarHeuristic heuristic;


    /**
     * @param map The map to be searched
     * @param maxSearchDistance The max depth before we give up
     * @param allowDiagMovement True if diagonal movement allowed
     * @param heuristic The heuristic used to determine map search order
     */
    public AStarPathFinder(GameMap map, int maxSearchDistance,
                           boolean allowDiagMovement, AStarHeuristic heuristic){
        this.heuristic = heuristic;
        this.map = map;
        this.tiledMap = map.getMap();
        this.maxSearchDistance = maxSearchDistance;
        this.allowDiagMovement = allowDiagMovement;

        this.nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
        for (int i = 0; i < map.getWidthInTiles(); i++){
            for(int j = 0; j < map.getHeightInTiles(); j++){
                this.nodes[i][j] = new Node(i,j);
            }
        }
    }

    public Path findPath(Mover mover, int sx, int sy, int tx, int ty){
        if(this.map.blocked(mover, tx, ty)){
            return null;
        }

        /* Initial state. Closed is empty, open only
         * contains the starting tile. */
        this.nodes[sx][sy].cost = 0;
        this.nodes[sx][sy].depth = 0;
        this.closed.clear();
        this.open.clear();
        this.open.add(this.nodes[sx][sy]);

        this.nodes[tx][ty].parent = null;

        /* While we haven't exceeded the max search depth... */
        int maxDepth = 0;
        while((maxDepth < this.maxSearchDistance) &&
                (this.open.size() != 0)){
            /* Check the first node in the open list.
             * This is most likely the next step based on
             * our heuristic. */

            Node current = getFirstInOpen();
            if(current == this.nodes[tx][ty]){
                break;
            }
            this.removeFromOpen(current);
            this.addToClosed(current);

            /* Search through all neighbouring nodes
             * and evaluate them as next steps. */
            for(int x = -1; x < 2; x++){
                for(int y = -1; y < 2; y++){
                    /* Not neighbour - current tile */
                    if((x==0) && (y==0)){
                        continue;
                    }
                    if(!this.allowDiagMovement){
                        if((x!=0) && (y!=0)){
                            continue;
                        }
                    }
                    /* Determine location of neighbour and evaluate */
                    int xp = x + current.x;
                    int yp = y + current.y;

                    if(this.isValidLocation(mover, sx, sy, xp, yp)){
                        /* The cost to get to this node is the cost of the
                         * current, plus the movement cost. */
                        float nextStepCost = current.cost + getMovementCost(mover, current.x,
                                current.y, xp, yp);
                        Node neighbour = this.nodes[xp][yp];
                        this.map.pathFinderVisited(xp, yp);

                        /* If the new cost is lower than previously,
                         * make sure the node hasn't determined a
                         * better path. Therefore, re-evaluate the node. */
                        if(nextStepCost < neighbour.cost){
                            if(inOpenList(neighbour)){
                                removeFromOpen(neighbour);
                            }
                            if(inClosedList(neighbour)){
                                removeFromClosed(neighbour);
                            }
                        }
                        /* If the node hasn't been processed and discarded,
                         * reset its cost to current cost and add it to the
                         * next possible step (open list). */
                        if(!inOpenList(neighbour) && !(inClosedList(neighbour))){
                            neighbour.cost = nextStepCost;
                            neighbour.heuristic = getHeuristicCost(mover, xp yp, tx, ty);
                            maxDepth = Math.max(maxDepth, neighbour.setParent(current));
                            addToOpen(neighbour);
                        }
                    }
                }
            }
        }
        /* Run out of search, no path. Return null */
        if(this.nodes[tx][ty].parent == null){
            return null;
        }

        /* Found path. Use parent references of the nodes to
         * find a way from target to the start. Record nodes
         * along the way. */

        Path path = new Path();
        Node target = this.nodes[tx][ty];
        while(target != this.nodes[sx][sy]){
            path.preparedStep(target.x, target.y);
            target = target.parent;
        }
        path.preparedStep(sx, sy);

        /* BINGO. Path! */
        return path;
    }

    protected Node getFirstInOpen(){
        return (Node) open.first();
    }

    protected void addToOpen(Node node){
        open.add(node);
    }

    protected boolean inOpenList(Node node){
        return open.contains(node);
    }

    protected void removeFromOpen(Node node){
        open.remove(node);
    }

    protected void addToClosed(Node node){
        closed.add(node);
    }

    protected boolean isClosedList(Node node){
        return closed.contains(node);
    }

    protected void removeFromClosed(Node node){
        closed.remove(node);
    }

    protected boolean isValidLocation(Mover mover, int sx, int sy,
                                      int x, int y){
        boolean invalid = (x < 0) || (y < 0) ||
                (x >= map.getWidthInTiles()) || (y >= map.getHeightInTiles());
        if((!invalid) && ((sx != x) || (sy != y))){
            invalid = map.blocked(mover, x, y);
        }

        return !invalid;
    }

    public float getMovementCost(Mover mover, int sx, int sy,
                                 int tx, int ty){
        return map.getCost(mover, sx, sy, tx, ty);
    }

    public float getHeuristicCost(Mover mover, int x, int y,
                                  int tx, int ty){
        return heuristic.getCost(map, mover, x, y, tx, ty);
    }

    private class SortedList {
        private ArrayList list = new ArrayList();

        public Object first() {
            return list.get(0);
        }

        public void clear() {
            list.clear();
        }

        public void add(Object o){
            list.add(o);
            Collections.sort(list);
        }

        public void remove(Object o){
            list.remove(o);
        }

        public int size(){
            return list.size();
        }

        public boolean contains(Object o){
            return list.contains(o);
        }
    }

    private class Node implements Comparable {
        private int x;
        private int y;
        private float cost;
        private Node parent;
        private float heuristic;
        private int depth;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int setParent(Node parent){
            depth = parent.depth + 1;
            this.parent = parent;

            return depth;
        }

        public int compareTo(Object other){
            Node o = (Node) other;

            float f = heuristic + cost;
            float of = o.heuristic + o.cost;

            if(f < of){
                return -1;
            } else if (f > of){
                return 1;
            } else {
                return 0;
            }
        }
    }

}
