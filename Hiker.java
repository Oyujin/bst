package project5;

/**
 * This class represents a hiker going down the mountain.
 * Every hiker has supplies it can use.
 * Supplies include food, raft, and axe.
 * A hiker stores the supplies in an array.
 * @author Oyujin Ulziibaatar
 */
public class Hiker {

  private String[] h_supplies = new String[6];
  
  /**
   * Constructor for a hiker object.
   * At the start, every supplies are 0.
   */
  public Hiker(){
    h_supplies[0] = "food";
    h_supplies[1] = "0";
    h_supplies[2] = "raft";
    h_supplies[3] = "0";
    h_supplies[4] = "axe";
    h_supplies[5] = "0";
  }

  /**
   * Getter function for the food of a given hiker.
   * @return the number of food as an integer.
   */
  public int getFood(){
      return Integer.valueOf(h_supplies[1]);
  }

  /**
   * This function adds food to the current food.
   * @param f is the food supplies to be added. 
   */
  public void updateFood(int f){
      checkInput(f);
      h_supplies[1]= String.valueOf(getFood()+f);
  }

  /**
   * Getter function for the rafts. 
   * @return the number of rafts a hiker has.
   */
  public int getRaft(){
      return Integer.valueOf(h_supplies[3]);
  }

  /**
   * This function adds rafts to the current rafts.
   * @param r is the number of rafts to be added.
   */
  public void updateRaft(int r){
      checkInput(r);
      h_supplies[3]= String.valueOf(getRaft()+r);
  }

  /**
   * Getter function for the axe.
   * @return the number of axes a hiker has.
   */
  public int getAxe(){
      return Integer.valueOf(h_supplies[5]);
  }

  /**
   * This functions adds axes to the current axes.
   * @param a is the number of axes to be added.
   */
  public void updateAxe(int a){
      checkInput(a);
      h_supplies[5]= String.valueOf(getAxe()+a);
  }

  /**
   * This function will decrement 1 food from the supply.
   */
  public void eat(){
      h_supplies[1]= String.valueOf(getFood()-1);
  }

  /**
   * This function sets the supplies to new numbers.
   * @param f is the number of foods.
   * @param r is the number of rafts.
   * @param a is the number of axes.
   */
  public void setSupplies (int f, int r, int a){
      checkInput(f);
      checkInput(r);
      checkInput(a);
      h_supplies[1]= String.valueOf(f);
      h_supplies[3]= String.valueOf(r);
      h_supplies[5]= String.valueOf(a);
  }

  /**
   * Setter function for axe.
   * @param a is the number of axes it will be set to.
   */
  public void setAxe(int a){
      checkInput(a);
    h_supplies[5]= String.valueOf(a);
  }

  /**
   * Setter function for raft.
   * @param r is the number of rafts it will be set to.
   */
  public void setRaft(int r){
      checkInput(r);
      h_supplies[3]= String.valueOf(r);
  }

  /**
   * Getter function that will get all the supplies
   * from the given reststop.
   * @param rstop the reststop the hiker will get the supplies from.
   */
  public void getSupplies(RestStop rstop){
      updateFood(rstop.getFood());
      updateAxe(rstop.getAxe());
      updateRaft(rstop.getRaft());
  }

  /**
   * It will check if the hiker can pass a river obstacle 
   * given the supplies the hiker has.
   * If a hiker passes, the raft will be decremented.
   * @param river is the number of river obstacles a hiker has to pass.
   * @return boolean value to indicate if the hiker passed or not.
   */
  public boolean passRiver(int river){
      if(getRaft()<river){
          return false;
      }

      setRaft(getRaft()-river);
      return true;
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
   * It check if the hiker can pass a fallen tree obstacle
   * given the supplies the hiker has.
   * If a hiker passes, the axe will be decremented.
   * @param ft is the number of fallen tree obstacle a hiker has to pass.
   * @return a boolean value to indicate if the hiker passed or not.
   */
  public boolean passFallenTree(int ft){
      if(getAxe() < ft){
          return false;
      }
      setAxe(getAxe()-ft);
      return true;
  }

}
