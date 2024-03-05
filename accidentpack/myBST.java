package accidentpack;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * myBST - Binary Search Tree (BST) constructor class 
 * utilizing state as a parameter for BST sorting
 * @author Domenic Mancuso
 * @version 3/4/2024
 * 
 */
public class myBST {
	Node root;
	String state;
	static ArrayList<myBST> accidentsByState = new ArrayList<>();
	
	/**
	 * node constructor 
	 */
	private static class Node {
        Report data;
        Node left, right;    
        
        /**
    	 * node constructor 
    	 */
        public Node(Report value) {
            data = value;
            left = right = null;
        }
    }
	
	/**
	 * empty BST constructor
	 */
    public myBST() {
        root = null;
        state = null;
    }
 /**
  * insert - recursive method to insert node into BST root and assign state
  * @param value of Report data
  */
    public void insert (Report value) {
    	if (root ==null) {
    	root =  new Node(value);
    	state = value.getState();
//    	root.data= value;
    	}else {
        root = insert(root, value);
    	}}    
    /**
     * insert - helper method inserts using in-Order insertion
     * @param root in BST
     * @param value of Report data
     * @return root
     */
    private Node insert(Node root, Report value) {
    	if (root == null) {
    	
    		return new Node(value);
    	}
    	if (value.compareTo(root.data) <0) {
    		root.left = insert(root.left, value);
    	} else if  (value.compareTo(root.data) >0) {
    		root.right = insert(root.right, value);
    	}
    	else {
    		root.right = insert(root.right,value);
    	}
    	return root;
    }

    

    /**
     * inOrder - recursive traversal method for Binary Search Tree
     */
public void inOrder() {
inOrder(root);
}
/**
 * inOrder - helper method using in-order traversal. Only prints root data
 * if start date is after or equal to datePath value
 * @param root
 */
    private void inOrder(Node root) {
    	if (program5.datePath == null) {
    	    System.err.println("datePath is null, cannot proceed with in-order traversal");
    	    return;
    	}
	if (root==null) {
//		System.out.println("leaf reached!");
		return;}
	inOrder(root.left);
    if (root.data.getStartTime().isAfter(program5.datePath) || root.data.getStartTime().isEqual(program5.datePath)) {
	System.out.println(root.data.getState() + " " + root.data.getStartTime() + " number of children: " +countChildren(root));
    }
inOrder(root.right);  
    }
    
    /**
     * countChildren - recursive method to count number of children
     * of root in BST based on input date
     * @param root
     * @return number of children of node based on input date
     */
    public int countChildren() {
    return	countChildren(root);
    }
    /**
     * countChildren - recursive method to count number of children
     * of root in BST based on input date
     * @param root
     * @return number of children of node based on input date
     */
    private int countChildren(Node root) {
    	if (root==null) {
    		return 0;
    	}
    	int a = 0;
    	if (root.data.getStartTime().isAfter(program5.datePath) || root.data.getStartTime().isEqual(program5.datePath))
    		a+=1;
 
    	return a+countChildren(root.left) + countChildren(root.right);
	}
//    old method
//    public static int countChildren(Node root) {
//    	if (root==null) {
//    		return 0;
//    	}
//    	return 1+countChildren(root.left) + countChildren(root.right);
//    }
    
    
    /**
     * countRecords - recursive method to count number of 
     * records in BST tree based on input date
     * @return number of Records in BST tree based on input date
     */
    public int countRecords() {
   return countRecords(root);
    }
    
    
    /**
     * countRecords - recursive helper method to count number of
     * records in BST tree based on input date
     * @return number of Records in BST tree based on input date
     */
    private int countRecords(Node root) {
    	if (root==null) 
    		return 0;
    	int a=0;
    	if (root.data.getStartTime().isAfter(program5.datePath) || root.data.getStartTime().isEqual(program5.datePath)) 
    		a+=1;
    	return a+ countRecords(root.left)+countRecords(root.right);
    }
   
//    private int countRecords(Node root) {
//    	if (root==null)
//    		return 0;
//    	return 1+ countRecords(root.left)+countRecords(root.right);
//    }
    
    
    /**
     * findMax - from lab. Not currently used in this program. 
     * This method does not work properly so please disregard
     * I believe it counts the number of children of root node
     * @return number of leaves in BST though it is not working as professor intended
     */
public int findMax() {
	return findMax(root);
}
/**
 * findMax - helper method for findMax
 * @param root
 * @return something incorrect lol
 */
private int findMax(Node root) {
	if (root == null) return 0;
		return Math.max(findMax(root.left),findMax(root.right))+1;
	}

/**
 * isBST - from lab. recursive method determines if data structure is a Binary Search Tree  
 * @return true if is BST
 */
public boolean isBST() {
	return isBST(root);
}
/**
 * isBST - helper method for isBST. Uses a fancy lambda statement
 * @param root
 * @return true if root has a right and left child
 */
private boolean isBST(Node root) {
	if (root==null) return false;
	 return (!isBST(root.left)&&isBST(root.right))?false:true;
	
}

///**
// * iterativeSearch - did not work. 
// * @param datePath
// */
//public void iterativeSearch(LocalDate datePath)
//{
//	    int count = 0;  // Initialize a counter
//
//	    Node current = root;  // Start from the root node
//
//	    while (current != null) {
//	        if (current.data.getStartTime().isAfter(datePath) || current.data.getStartTime().isEqual(datePath)) {  // If current node's date is after, move to the left subtree
//	            count+=1;
//	        	current = current.left;
//	        } else {  // Handle cases where current node's date is before or equal
//	        	
//	        	    count += 1 + countRecords(current.right);  // Count current node and recursively count right subtree
//	        	    current = current.left;
//	        	
//	        }
//	    }
//
//	    System.out.println(count);  // Print the final count
//	}
/**
 * getState - getter for BST state
 * @return BST state
 */
public String getState() {
	return this.state;
}
/**
 * setBST - sets state for BST
 * @param state to set BST to
 */
public void setState(String state) {
	this.state = state;
}
}
	
