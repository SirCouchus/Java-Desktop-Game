package carGameMainPackage;

public interface PathFinder {

    /**
     * Find path from (sx, sy) to target (tx, ty) by avoiding blocks
     * and taking into account the costs of the tiles.
     * @param mover The entity that will be moving.
     * @param sx The x coordinate of the start location.
     * @param sy The y coordinate of the start location.
     * @param tx The x coordinate of the target location.
     * @param ty The y coordinate of the target location.
     * @return The path found from start to target.
     */
    public Path findPath(Mover mover, int sx, int sy,
                         int tx, int ty);
}
