package carGameMainPackage;

public interface AStarHeuristic {

    /**
     * Get the cost of the tile. The lower the cost, the
     * more likely the tile will be searched.
     * @param map The game map.
     * @param mover The entity that is traversing the map.
     * @param x The x coordinate of the tile being evaluated.
     * @param y The y coordinate of the tile being evaluated.
     * @param tx The x coordinate of the target.
     * @param ty The y coordinate of the target.
     * @return The cost associated with the given tile.
     */
    public float getCost(GameMap map, Mover mover,
                         int x, int y, int tx, int ty);
}
