package project5;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * MountainHike class is the main program that
 * gets inputs, constructs a BSTMountain with those inputs,
 * and runs the recursive function to find a path.
 * 
 * @author Oyujin Ulziibaatar
 */
public class MountainHike{

    /**
     * This is the main function that will take the arguments,
     * check if they are valid, if they are not valid, the 
     * main function will print an error message and terminate.
     * After checking, it will add it to the mountain,
     * and run the recursive function to get the paths.
     * @param args is the input stream that will
     * get the name of the input file.
     */
    public static void main(String[] args){

        //checks if the user entered any argument
        if(args.length ==0){
            System.err.println("Error: please enter a file name as an argument.");
            System.exit(1);
        }

        File inputFile = new File (args[0]);

        //check if the inputted file exists
        if(!inputFile.exists()){
            System.err.println("Error: the file you have inputted does not exist.");
            System.exit(1);
        }

        //check if the inputted file can be opened and read
        if(!inputFile.canRead()){
            System.err.println("Error: the file you have inputted cannot be opened.");
            System.exit(1);
        }
        
        BSTMountain hike = new BSTMountain();
        Scanner in = null;
        //check if the file can be opened
		try{
            in = new Scanner(inputFile);
        }
        catch(FileNotFoundException e){
            System.err.println("Error: the file cannot be opened for reading.");
            System.exit(1);
        }


		String line = null;
		String[] tokens;

        //reads every line of the input, splits them
        //create reststop if appropriate and add it to the mountain
        while(in.hasNextLine()){
            line = in.nextLine();
            tokens = line.split("\\s+");

            //if the supposed label is empty, skip
            if(tokens[0].equals("")){
                continue;
            }

            RestStop stop = new RestStop(tokens[0]);
            boolean obs = false;
            
            //add supplies and obstacles to the reststop
            for (int j=1; j<tokens.length; j++){
                if(obs == false){
                    if (tokens[j].equals("food")){
                        stop.setFood(1);
                    }

                    else if(tokens[j].equals("raft")){
                        stop.setRaft(1);
                    }

                    else if(tokens[j].equals("axe")){
                        stop.setAxe(1);
                    }

                    //if we have reached obstacles, set the obstacle value to true
                    //it is to prevent any supplies to be added after the obstacles
                    else if (tokens[j].equals("river") || tokens[j].equals("fallen")){
                        obs = true;
                    }
                }

                //add the obstacles to the reststop
                if(obs == true){
                    if(tokens[j].equals("river")){
                        stop.setRiver(1);
                    }

                    else if(tokens[j].equals("fallen") && (j+1) < tokens.length && tokens[j+1].equals("tree")){
                        stop.setFallenTree(1);
                    }

                    else if (tokens[j].equals("tree")){
                        continue;
                    }
                }  
            }

            //add the reststop to the mountain
            hike.add(stop);
        }
        
        in.close();
        
        //if the mountain is empty, print an error message and terminate
        if(hike.size() == 0){
            System.err.println("The mountain is empty, cannot go down");
            System.exit(1);
        }

        //if the mountain is valid, godown the mountain
        hike.goDown();

        //print the paths found from going down the mountain
        System.out.println(hike);
    }
}