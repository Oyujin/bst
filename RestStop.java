package project5;
import java.lang.IllegalArgumentException;

/**
 * This class represents the reststops.
 * Every reststop has label, supplies and obstacles. 
 * The supplies and the obstacles are saved in an array. 
 * This class implements the Comparable interface.
 * @author Oyujin Ulziibaatar
 * 
 */
public class RestStop implements Comparable<RestStop>{

    private String label;
    private String[] supplies = new String[6];
    private String[] obstacles = new String[4];

    /**
     * It is the constructor of a RestStop class.
     * To have a reststop the only thing that is needed
     * is the label.
     * @param x is the label.
     */
    public RestStop(String x){

        this.label = x;
        //names of the supplies and the obstacles are added for coherency.
        supplies[0] = "food";
        supplies[1] = "0";
        supplies[2] = "raft";
        supplies[3] = "0";
        supplies[4] = "axe";
        supplies[5] = "0";

        obstacles[0]= "fallen tree";
        obstacles[1]= "0";
        obstacles[2]= "river";
        obstacles[3]= "0";

    }

    /**
     * Getter for label of the reststop.
     * @return the label.
     */
    public String getLabel(){
        return this.label;
    }

    /**
     * Getter for the food of the reststop.
     * @return the food as an integer.
     */
    public int getFood(){
        return Integer.valueOf(supplies[1]);
    }

    /**
     * Getter function for the raft from the supplies.
     * @return the number of rafts as an integer.
     */
    public int getRaft(){
        return Integer.valueOf(supplies[3]);
    }

    /**
     * Getter function for the axe from the obstacles.
     * @return the number of axes as an integer.
     */
    public int getAxe(){
        return Integer.valueOf(supplies[5]);
    }

    /**
     * Setter function for the food.
     * @param f the number of foods that will be added to the supply.
     */
    public void setFood(int f){
        checkInput(f);
        supplies[1]= String.valueOf(getFood()+f);
    }

    /**
     * Setter function for the raft.
     * @param r is the number of rafts that will be added to the supply.
     */
    public void setRaft(int r){
        checkInput(r);
        supplies[3]= String.valueOf(getRaft()+r);
    }

    /**
     * Setter function for the axe.
     * @param a is the number of axes that will be added to the supply.
     */
    public void setAxe(int a){
        checkInput(a);
        supplies[5]= String.valueOf(getAxe()+a);
    }

    /**
     * Getter function for the number of fallen tree obstacle.
     * @return the number of fallen tree obstacle.
     */
    public int getFallenTree(){
        return Integer.valueOf(obstacles[1]);
    }

    /**
     * Setter function for the fallen tree obstacle.
     * @param ft is the number of fallen tree obstacle to be added.
     */
    public void setFallenTree(int ft){
        checkInput(ft);
        obstacles[1]=String.valueOf(getFallenTree() + ft);
    }

    /**
     * Getter function for the river obstacle.
     * @return the number of river obstacles.
     */
    public int getRiver(){
        return Integer.valueOf(obstacles[3]);
    }

    /**
     * Setter function for the river obstacle.
     * @param river is the number of river obstacle to be added.
     */
    public void setRiver(int river){
        checkInput(river);
        obstacles[3]= String.valueOf(getRiver() + river);
    }

    /**
     * This function checks if the input is positive integer.
     * @param c is the number to be checked.
     * @throws IllegalArgumentException if the number is negative.
     */
    public void checkInput(int c) throws IllegalArgumentException{
        if(c < 0){
            throw new IllegalArgumentException
            ("The input for any of the supplies and any of the obstacles has to be positive integer.");
        } 
    }

    /**
     * It compares the 2 reststops.
     * It will only compare the labels of the 2 reststops.
     * @param other is the RestStop object to be compared to.
     * @return a negative integer, zero, or a positive integer as this object is 
      * less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(RestStop other){
        return this.label.compareTo(other.label);
    }

    /**
     * It will return the string representation
     * of all the obstacles of the reststop.
     * @return the obstavles as a one whole string.
     */
    public String printObstacle (){
        return "ft-"+ this.getFallenTree() + " river-"+ this.getRiver();
    }

    /**
     * It will return the string representation
     * of all the supplies of the reststop.
     * @return the supplies as a one whole string.
     */
    public String printSupply(){
        return "axe-" + this.getAxe() + " food-" + this.getFood() + " raft-" + this.getRaft();
    }
    
}
