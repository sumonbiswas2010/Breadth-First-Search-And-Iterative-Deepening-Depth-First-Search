package spate292;

/* Name: Sujay Patel, Spate292@uic.edu
 * Homework 2 - CS 411
 * File 1 of 2: Breadth First Search Algorithm Implementation
 * 
 * List of references used for this assignment:
 * 
 * 1. http://aima.cs.berkeley.edu/
 * 2. http://www.brian-borowski.com/software/puzzle/ 
 * 3. http://stackoverflow.com/questions/3094925/trying-to-solve-15-puzzle-outofmemoryerror
 * 
 */

//ok changed again

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Bfs {
	
	private static class branch {
		public branch parent = null;       // parent branch
		public branch right = null;
		public branch left = null;
		public branch up = null;
		public branch down = null;

		public int dimension [][] = new int [4][4];   // 4x4 matrices for 15-puzzle game 
		public int tree = 0;
		public int horizontal, vertical;
	}
	
    public static int[][] createTree(int markCurrent [][], char nextMove, int x, int y){	// Creates a 4x4 matrix using the directions i.e. Right, Left, Up, Down
      
    	int newBranch [][] = new int [4][4];
        
    	for(int i = 0; i < 4; i++)
            newBranch [i] = markCurrent [i].clone();
        
        if(nextMove=='R'){
        	newBranch [x][y] = newBranch [x][y+1];
            newBranch [x][y+1] = 0;
        }
        else if(nextMove=='L'){
            newBranch [x][y] = newBranch [x][y-1];
            newBranch [x][y-1] = 0;
        }
        else if(nextMove=='U'){
            newBranch [x][y] = newBranch [x-1][y];
            newBranch [x-1][y] = 0;
        }
        else{
        	newBranch[x][y] = newBranch[x+1][y];
            newBranch[x+1][y] = 0;
        }
        
        return newBranch;
    }

    public static void puzzleView(branch current_branch){	// The first half of the puzzle solution
        
    	if(current_branch == null){
            return;
    	}

        for(int i = 0; i < 4; i++){
            
        	for(int j: current_branch.dimension[i]){
        		
                System.out.println(j);
            }
        }
    } 
   
    public static void searchBranches(branch currentBranch){  // check to see if the moves are legible
        
    	if(currentBranch.horizontal < 3){
    		currentBranch.down = new branch();
    		currentBranch.down.tree = currentBranch.tree+1;
           
    		currentBranch.down.vertical = currentBranch.vertical;
            currentBranch.down.horizontal = currentBranch.horizontal+1;
            
            currentBranch.down.parent = currentBranch;
            currentBranch.down.dimension = createTree(currentBranch.dimension, 'D', currentBranch.horizontal, currentBranch.vertical);
         }  
    	
    	if(currentBranch.horizontal > 0){
    		 currentBranch.up = new branch();
    		 currentBranch.up.tree = currentBranch.tree+1;
    		
    		 currentBranch.up.vertical = currentBranch.vertical;
             currentBranch.up.horizontal = currentBranch.horizontal-1;
             
             currentBranch.up.parent = currentBranch;
             currentBranch.up.dimension = createTree(currentBranch.dimension, 'U', currentBranch.horizontal, currentBranch.vertical);
    	 }
    	 
    	 if(currentBranch.vertical < 3){
    		 currentBranch.right = new branch();
    		 currentBranch.right.tree = currentBranch.tree+1;
    		 
    		 currentBranch.right.vertical = currentBranch.vertical+1;
    		 currentBranch.right.horizontal = currentBranch.horizontal;
    		 
    		 currentBranch.right.parent = currentBranch;
    		 currentBranch.right.dimension = createTree(currentBranch.dimension, 'R', currentBranch.horizontal, currentBranch.vertical);
    	 }
    	 
    	 if(currentBranch.vertical > 0){
    		 currentBranch.left = new branch();
    		 currentBranch.left.tree = currentBranch.tree+1;
    		 
    		 currentBranch.left.vertical = currentBranch.vertical-1;
    		 currentBranch.left.horizontal = currentBranch.horizontal;
    		 
    		 currentBranch.left.parent = currentBranch;
    		 currentBranch.left.dimension = createTree(currentBranch.dimension, 'L', currentBranch.horizontal, currentBranch.vertical); 
    	 }
    }

	public static boolean goalCheck(branch solved_branch){  
    	
        int puzzle_solved [][]  = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};         // solution set for each row x columns
        
        return Arrays.deepEquals(solved_branch.dimension, puzzle_solved);   // we use deepEquals here because we want the accurate search for our arrays
    }
    
    public static String markDown (branch visited_branch){  // This function will mark down the visited branches
    	String mark = " ";
    	
    	for(int i = 0; i < 4; i++){
    
    		for (int j = 0; j < 4; j++)
    			
    			mark = mark + ((char) (65 + visited_branch.dimension[i][j]));
        }
        return mark;
    }
    
	public static branch BreadthFirstSearch(branch start){     // Breadth-First-Search implementation - Example from the book
		
		LinkedList<branch> list = new LinkedList<branch>();
		ArrayList<String> visited_branches = new ArrayList<String>();
		
		list.add(start);
		 while(!list.isEmpty()){
			 
			 branch current = list.removeFirst();
			 String current_branch = markDown(current);
			 
			 if(goalCheck(current)){
				 return current;
			 }
	
			 else if(!visited_branches.contains(current_branch)){
		
				 visited_branches.add(current_branch);
				searchBranches(current);
				
				if(current.up!=null){
					list.add(current.up);
				}
				
				if(current.down!=null){
					list.add(current.down);
				}
				
				if(current.right!=null){
					list.add(current.right);
				}
				
				if(current.left!=null){
					list.add(current.left);
				}
		
			 }
		 }
	return null;	 
	}

    public static void puzzleView1(branch current_branch){	// The other half of the puzzle solution
        if(current_branch == null)
            return;

        for(int i = 0; i < 4; i++){
        	
            for(int j: current_branch.dimension[i]){
            
                System.out.printf(" %2d", j);
            }
        }
        
        System.out.println(" \n");
    }

    public static void puzzleSolution(branch output, boolean display){
    	
        LinkedList<branch> outputOrder = new LinkedList<branch>();
        branch currentOrder = output;
        
        while(currentOrder != null){
           
        	outputOrder.addFirst(currentOrder);
            currentOrder = currentOrder.parent;
        }

        System.out.println("The Breadth First Search solution for the puzzle is: \n");

        while(!outputOrder.isEmpty()){
       
        	if(!display)
            
        		puzzleView(outputOrder.removeFirst());
            	puzzleView1(outputOrder.removeFirst());
       
        }
    }
	
	public static void main(String[] args) { // take the input from the user and handle any errors that occur
		
        if(args.length <= 0){	
           return;
       }
       else if(args.length != 16){            // error message if displayed when less than 16 digits are entered 
    	   
           System.out.println("Please enter the correct number of the puzzle (15-Puzzle & 1-Blank Space)"); 
           return;
       }

       branch startBranch = new branch();
       
       for (int i =0; i < 4; i++) {
    	   
           for (int j = 0; j < 4; j++) {
           
        	   startBranch.dimension[i][j] = Integer.parseInt(args[4*i + j]);
        	   
               if(startBranch.dimension[i][j] == 0){
            	
            	   startBranch.horizontal = i;
            	   startBranch.vertical = j;
 
               }
           }
       }
       
       System.out.println("Welcome to the 15-Puzzle Game!\n");
       System.out.println("Breadth First Search is being implemented: \n\nPlease wait while the search is in process...\n"); 
       
       try{						// Try to find the solution for the puzzle, if runs out of memory, error message is displayed 
    	   
           branch solution = BreadthFirstSearch(startBranch);
       
           if(solution != null){
           
        	   puzzleSolution(solution, true);	// If the puzzle is solvable, search is continued
           }
       }
       
       catch(OutOfMemoryError e){
    	   
           System.out.println("\nError: Breadth First Search ran out of memory -> Cannot find the solution!\n");
       }
       
	}
}
