package carGameMainPackage;

import java.util.ArrayList;

public class AStarPathFinder {
    /* The set of nodes that haven't been searched */
    private ArrayList closed = new ArrayList();
    /* The set of nodes that can still be visited */
    private ArrayList open = new ArrayList();

    /* Import the tile-based map */
    private GameMap map;
    /* The maximum depth of search before giving up */
    private int maxSearchDistance;

    /* The complete set of nodes across map
     * This is a Class that needs to be made.
     * Do not import SOAP node */
    private Node[][] nodes;
    /* True if diagonal movement allowed */
    private boolean allowDiagMovement;

    /* The 2D array containing map information */
    private int[][] tiledMap;

    /* The actual heuristic */
    private int heuristic;


    /*
     * @param map The map to be searched
     * @param maxSearchDistance The max depth before we give up
     * @param allowDiagMovement True if diagonal movement allowed
     * @param heuristic The heuristic used to determine map search order
     */
    public AStarPathFinder(GameMap map, int maxSearchDistance,
                           boolean allowDiagMovement, int heuristic){
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

}
