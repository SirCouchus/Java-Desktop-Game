package carGameMainPackage;

import java.util.ArrayList;

public class Path {

    /* List of steps in this path */
    private ArrayList steps = new ArrayList();

    public Path(){

    }

    public int getLength(){
        return this.steps.size();
    }

    /**
     * Get the tile at certain index
     * @param index The index to retrieve
     * @return The information about the tile
     */
    public Step getStep(int index){
        return (Step) this.steps.get(index);
    }

    public int getX(int index){
        return this.getStep(index).x;
    }

    public int getY(int index){
        return this.getStep(index).y;
    }

    /**
     * Add a step to the path
     * @param x The x coordinate of the step.
     * @param y The y coordinate of the step.
     */
    public void appendStep(int x, int y){
        this.steps.add(new Step(x,y));
    }

    /**
     * Add step to the front of the path.
     * @param x The x coordinate of the step.
     * @param y The y coordinate of the step.
     */
    public void preparedStep(int x, int y){
        this.steps.add(0, new Step(x,y));
    }

    /**
     * Check whether path contains the given step.
     * @param x The x coordinate of the step to check.
     * @param y The y coordinate of the step to check.
     * @return Return true if path contains the step.
     */
    public boolean contains(int x, int y){
        return this.steps.contains(new Step(x,y));
    }

    public class Step {

        private int x;
        private int y;

        /**
         * Create a new step.
         * @param x The x coordinate of the new step.
         * @param y The y coordinate of the new step.
         */
        public Step(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return this.x;
        }

        public int getY(){
            return this.y;
        }

        public boolean equals(Object other){
            if(other instanceof Step){
                Step o = (Step) other;

                return (o.x == x) && (o.y == y);
            }
            return false;
        }
    }

}
