package project5;
import java.util.ArrayList;

/**
 * BSTMountain class represents the mountain.
 * AVL is the underlying data structure used for the implementation. 
 * It is self balancing tree.
 * Every node in the BST represents the RestStops.
 * We can add nodes of reststops to the mountain. 
 * 
 * @author Oyujin Ulziibaatar
 * 
 */
public class BSTMountain {

    /** 
     * This private class represents a node.
     * It is the building block of the AVL mountain.  
     * The data field stores a RestStop.
     * It has references to its right and left nodes, if any.
     * It has field for storing the height of each node.
     */
    private class Node {

        RestStop data;
        Node left;
        Node right;
        int height;

        /**
         * This is the constructor for new node.
         * It takes RestStop as an argument. 
         * @param d the reststop object that will be stored
         * in the new node's data field.
         */
        public Node (RestStop d){
            this.data = d;
        }
    }

    private Node root;
    private int size=0;
    private ArrayList<String> solution=new ArrayList<>();

    /**
     * Constuctor for new BSTMountain object.
     * It will create a new BTSMountain object,
     * where the root will point to a null.
     */
    public BSTMountain(){
        this.root = null;
    }

    /**
     * This method returns the size of the given mountain.
     * @return number of nodes in the mountain.
     */
    public int size(){
        return size;
    }

    /**
     * It is wrapper function for the recursive add function.
     * If the root is null, it will directly 
     * add the new object to the root.
     * After successfully adding, it will increment the size.
     * @param other is reststop object to be added to the mountain.
     */
    public void add(RestStop other){
        checkForNull(other);

        //if the mountain is empty, it will add the reststop to the root
        if(root == null){
            root = new Node(other);
            updateHeight(root);
            size ++;
            return;
        }
        
        //get the reference of the new root 
        Node rotat = add(other, root);
        //if the reference is null, it means is was not added.
        if(rotat == null){
            return;
        }
        //if it was added, update the height of the root
        updateHeight(rotat);

        //check if the node is out of balance
        //and rotate if needed
        Node newNode = checkBF(rotat);
        
        //if it is out of balance, set the root to the new root
        if(newNode != null){
            root = newNode;
        }
        //increment the size
        size ++;
    
    }

    /**
     * It is the recursive add function.
     * It will iterate through the mountain recursively,
     * Find a place for the node to be added
     * And add if that place is null/empty.
     * @param stop is the RestStop object to be added
     * @param current is the node the function is currently looking at.
     * @return the node it was added to, if a duplicate was found
     * it will return null.
     */
    private Node add(RestStop stop, Node current){

        //first compare the reststops
        int diff = stop.compareTo(current.data);

        //if it is 0, it means there is a duplicate,
        //so the function will return null and terminate
        if(diff == 0){
            return null;
        }

        //it means the the reststop to be added is smaller,
        //so we will go to the left
        if(diff <0){
            //if the left is empty, we will directly
            //add the restsop, and return the node it 
            //was added to
            if(current.left == null){
                current.left = new Node(stop);
                updateHeight(current.left);
                return current;
            }

            //if the left is not empty, we will go to the node.
            Node rotat = add(stop, current.left);
            //duplicate has been found
            if(rotat == null){
                return null;
            }
            //update the height of the node it was added to.
            updateHeight(rotat);
            //check for imbalance of the node it was added to.
            Node newNode = checkBF(rotat);
            //if there was a rotation, set the reference of the 
            //node to the new node
            if(newNode != null){
                current.left = newNode;
            }
        }

        //go right
        else if(diff >0){

            //if the right node is empty, directly add it
            if(current.right == null){
                current.right = new Node(stop);
                updateHeight(current.right);
                //return the reference of the node it was added to
                return current;
            }
            //go right, get the reference of the node it was added to
            Node rotat = add(stop, current.right);
            //check for imbalance
            if(rotat == null){
                return null;
            }
            //update the height of the node
            updateHeight(rotat);
            //check for imbalance
            Node newNode = checkBF(rotat);
            //set the reference to the new node
            if(newNode != null){
                current.right = newNode;
            }
        }
        //return the current node
        return current;
    }

    /**
     * It checks the balance factors of the node given.
     * If there is imblance at the given node,
     * if will rotate based on the situation.
     * @param node is the node to be checked
     * @return the reference to the new node if rotated,
     * else it will return null to indicate 
     * there was no rotation
     */
    public Node checkBF(Node node){
        checkForNull(node);
        if(balFact(node) == -2){
            if(balFact(node.left)==1){
                return rotatLR(node);
            }
            else{
                return rotatLL(node);
            }
        }

        if(balFact(node) == 2){
            if(balFact(node.right)==-1){
                return rotatRL(node);
            }
            else{
                return rotatRR(node);
            }
        }

        return null;
    }

    /**
     * This function does LL rotation.
     * It is a rotation when the left node of a given node
     * has a balnce factor of -1 or 0.
     * @param node is the node that has imbalance(root of the subtree)
     * @return the reference to the new root of the subtree after rotation.
     * 
     * Credits to Prof.Klukowska, the pseudocode was taken from
     * professor's slide for AVL tree.
     */
    public Node rotatLL(Node node){
        checkForNull(node);
        Node B = node.left;
        node.left = B.right;
        B.right = node;
        updateHeight(node);
        updateHeight(B);

        return B;
    }

    /**
     * This function does RR rotation
     * This rotation is done when the right node of 
     * a given node has a balance factor of 1 or 0.
     * @param node is the node that has imbalance(root of the subtree)
     * @return the reference to the new root of the subtree after rotation.
     * 
     * Credits to Prof.Klukowska, the pseudocode was taken from
     * professor's slide for AVL tree.
     */
    public Node rotatRR(Node node){
        checkForNull(node);
        Node B = node.right;
        node.right = B.left;
        B.left = node;
        updateHeight(node);
        updateHeight(B);

        return B;
    }

    /**
     * This function does LR rotation.
     * This rotation is done when the left node of
     * a given node has a balance factor of 1.
     * @param node is the node that has imbalance(root of the subtree)
     * @return the reference to the new root of the subtree after rotation.
     * 
     * Credits to Prof.Klukowska, the pseudocode was taken from
     * professor's slide for AVL tree.
     */
    public Node rotatLR(Node node){
        checkForNull(node);
        Node B = node.left;
        Node C = B.right;

        node.left = C.right;
        B.right = C.left;
        C.left = B;
        C.right = node;

        updateHeight(node);
        updateHeight(B);
        updateHeight(C);

        return C;
    }

    /**
     * This function does RL rotation.
     * This rotation is done when the right node of
     * a given node has a balance factor of -1.
     * @param node is the node that has imbalance(root of the subtree).
     * @return the reference to the new root of the subtree after rotation.
     * 
     * Credits to Prof.Klukowska, the pseudocode was taken from
     * professor's slide for AVL tree.
     */
    public Node rotatRL(Node node){
        checkForNull(node);
        Node B = node.right;
        Node C = B.left;

        node.right = C.left;
        B.left = C.right;
        C.right = B;
        C.left = node;

        updateHeight(node);
        updateHeight(B);
        updateHeight(C);

        return C;
    }

    /**
     * This function updates the height of a given node.
     * It will look at the node's left child and right child
     * Compare their heights, and pick the max, add 1, and
     * set it as the given node's height.
     * @param node is the node that its height will be updated
     */
    public void updateHeight (Node node){
        checkForNull(node);

        //if the node is a leaf
        if(node.left == null && node.right == null){ 
            node.height = 0;
        }

        //if the node does not have right subtree
        else if(node.right == null){
            node.height = node.left.height +1;
        }

        //if the node does not have left subtree
        else if(node.left == null){
            node.height = node.right.height +1;
        }

        //if the node has right and left subtree
        else{
            node.height = java.lang.Math.max(node.right.height, node.left.height) + 1;
        }
    }

    /**
     * This function calculates the balance factor
     * for a given node and return the balance factor.
     * It will substract the height of the left child from
     * the height of the right child.
     * @param node is the node the balance factor
     * will be calculated for.
     * @return the calculated balance factor.
     */
    public int balFact (Node node){
        checkForNull(node);
        if (node.right == null && node.left == null){
            return 0;
        }
        else if(node.right == null){
            return -node.height;
        }

        else if(node.left == null){
            return node.height;
        }

        else{
            return node.right.height - node.left.height;
        }
    }

    /**
     * It is the wrapper function for the recursive goDown function.
     * It checks if the tree is empty,
     * if not, then it will create a string called path,
     * new hiker object, and run the recursive goDown function.
     */
    public void goDown(){
        if (size == 0){
            return;
        }
        String path = "";
        Hiker hiker = new Hiker();
        goDown(this.root, path, hiker);
    }

    /**
     * This function goes through the mountain
     * recursively to find the path that leads
     * to the end of the tree.
     * @param stop is the node the function is currently at.
     * @param res is the string that is containing the paths taken.
     * @param person is the hiker that is going down the mountain.
     * @return boolean value that indicates if the path is legal.
     */
    private boolean goDown(Node stop, String res, Hiker person){
        
        //get the supplies from the reststop, if any
        person.getSupplies(stop.data);

        //if a person does not have food and the has not gotten to the foot of the mountain
        //cannot go down, so this path is no legal
        if(person.getFood()==0 && stop.height!=0){
            return false;
        }

        //try to pass fallentree obstacle if the reststop has one.
        if(stop.data.getFallenTree() != 0){
            if(!person.passFallenTree(stop.data.getFallenTree())){
                return false;
            }
        }

        //try to pass river obstacle if the reststop has one.
        if(stop.data.getRiver() != 0){
            if(!person.passRiver(stop.data.getRiver())){
                return false;
            }
        }

        //if we passed every obstacles
        //and it is the end of the mountain, we have found a legal path
        if(stop.height ==0){
            //add the restsop to the string res
            res += stop.data.getLabel();
            this.solution.add(res);
            return true;
        }

        //since we passed the reststop successfully we will add it to the possible path
        res += stop.data.getLabel() + " ";

        //check if the subtrees of the current nodes are worth going for:
        //if any of the child is smaller than the parent by more than 1
        //it will lead to cliff, so we will not take that path.
        boolean right = false;
        boolean left = false;
        
        if(stop.right != null && stop.left != null){
            right = (stop.right.height == stop.height-1);
            left = (stop.left.height == stop.height-1);
        }

        else if(stop.right == null){
            left = (stop.left.height == stop.height-1);
        }

        else if(stop.left == null){
            right = (stop.right.height == stop.height-1);
        }

        //since we will be going down, we will eat one food.
        person.eat();
        //record all the supplies the hiker has at this stop
        int hiker_food = person.getFood();
        int hiker_axe = person.getAxe();
        int hiker_raft = person.getRaft();

        //if the right child is a cliff, we will go left
        if(!right && left){
            return goDown(stop.left, res, person);
        }

        //if the left child is a class, we will go right
        if(right && !left){
            return goDown(stop.right, res, person);
        }
        
        //since both the children is not a cliff go both ways
        //we will go both right and left
        left = goDown(stop.left, res, person);

        //recover the supplies before going the other way
        person.setSupplies(hiker_food,hiker_raft, hiker_axe);
        right = goDown(stop.right, res, person);

        //if any of the path was successfull 
        //return true
        if(right && left){
            return true;
        }

        if(right){
            return true;
        }

        if(left){
            return true;
        }

        return false;
    }

    /**
     * This method checks if the given object is null.
     * @param other is the object to be checked.
     * @throws IllegalArgumentException if the input object is null.
     */
    public void checkForNull (Object other) throws IllegalArgumentException{
        if (other==null){
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * This function prints the solutions.
     */
    @Override
    public String toString(){
        if(solution.size() ==0){
            return"";
        }

        for(int i=0; i<solution.size(); i++){
            System.out.println(solution.get(i));
        }
        return "";
    }
}
