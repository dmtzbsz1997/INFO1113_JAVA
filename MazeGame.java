import java.io.File;
import java.io.IOException;
import java.io.EOFException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Maze Game
 *
 * INFO1113 Assignment 1
 * 2018 Semester 2
 *
 * The Maze Game.
 * In this assignment you will be designing a maze game.
 * You will have a maze board and a player moving around the board.
 * The player can step left, right, up or down.
 * However, you need to complete the maze within a given number of steps.
 *
 * As in any maze, there are walls that you cannot move through. If you try to
 * move through a wall, you lose a life. You have a limited number of lives.
 * There is also gold on the board that you can collect if you move ontop of it.
 *
 * Please implement the methods provided, as some of the marks are allocated to
 * testing these methods directly.
 *
 * @author YOU :)
 * @date 23 August 2018
 *
 */
public class MazeGame {
    /* You can put variables that you need throughout the class up here.
     * You MUST INITIALISE ALL of these class variables in your initialiseGame
     * method.
     */

    // A sample variable to show you can put variables here.
    // You would initialise it in initialiseGame method.
    // e.g. Have the following line in the initialiseGame method.
    // sampleVariable = 1;
    private static int numberOfLives;
    private static int numberOfSteps;
    private static int amountOfGold;
    private static int numberOfRow;
    private static int[] currentPos=new int[2];
    private static String[][] data=null;
    private static  	String []Saving;



    /**
     * Initialises the game from the given configuration file.
     * This includes the number of lives, the number of steps, the starting gold
     * and the board.
     *
     * If the configuration file name is "DEFAULT", load the default
     * game configuration.
     *
     * NOTE: Please also initialise all of your class variables.
     *
     * @args configFileName The name of the game configuration file to read from.
     * @throws IOException If there was an error reading in the game.
     *         For example, if the input file could not be found.
     */
    public static void initialiseGame(String configFileName) throws IOException {
    	File f=new File(configFileName);
    	Scanner input=new Scanner(f);
    	String[] str=null;
    	int counter=0;
    	while(input.hasNextLine()) {
    		if(counter==0) {
    			str=input.nextLine().split(" ");
    			counter++;
    		}else {
    			for(int i=0;i<Integer.parseInt(str[3]);i++){
    				String[] dataInput=input.nextLine().split("");
    				if(counter==1) {
    					data= new String[Integer.parseInt(str[3])][dataInput.length];
    					counter++;
    				}
    				for(int j=0;j<dataInput.length;j++) {
    					data[i][j]= dataInput[j];
    				}
    			}
    		}
    	}
    	input.close();
    	if(str.length==4) {
    		numberOfLives=Integer.parseInt(str[0]);
    		numberOfSteps=Integer.parseInt(str[1]);
    		amountOfGold=Integer.parseInt(str[2]);
    		numberOfRow=Integer.parseInt(str[3]);
    	}	
    }

    /**
     * Save the current board to the given file name.
     * Note: save it in the same format as you read it in.
     * That is:
     *
     * <number of lives> <number of steps> <amount of gold> <number of rows on the board>
     * <BOARD>
     *
     * @args toFileName The name of the file to save the game configuration to.
     * @throws IOException If there was an error writing the game to the file.
     */

    public static void saveGame(String toFileName) throws IOException {
    	File f=new File(toFileName);
    	PrintWriter p=new PrintWriter(f);
    	p.println(numberOfLives+" "+numberOfSteps+" "+amountOfGold+" "+numberOfRow);
    	for(int i=0;i<numberOfRow;i++) {
    		for(int u=0;u<data[0].length;u++) {
    			p.print(data[i][u]);
    		}
    		p.println();
    	}
        p.close();
    }

    /**
     * Gets the current x position of the player.
     *
     * @return The players current x position.
     */
	 public static int[] getPosition() {
	    	for(int row=0;row<data.length;row++) {
	    		for(int col=0;col<data[0].length;col++) {
	    			if(data[row][col].equals("&")) {
	    				currentPos[0]=row;
	    				 currentPos[1]=col;
	    			}	    			
	    		}
	    	}		 
			 return currentPos;	    	
	    }
	 public static int getCurrentXPosition() {
	    	return getPosition()[1];
	     
	    }


    /**
     * Gets the current y position of the player.
     *
     * @return The players current y position.
     */
    public static int getCurrentYPosition() {
    	return getPosition()[0];
    }

    /**
     * Gets the number of lives the player currently has.
     *
     * @return The number of lives the player currently has.
     */
    public static int numberOfLives() {
        return numberOfLives;
    }

    /**
     * Gets the number of remaining steps that the player can use.
     *
     * @return The number of steps remaining in the game.
     */
    public static int numberOfStepsRemaining() {
        return numberOfSteps;
    }

    /**
     * Gets the amount of gold that the player has collected so far.
     *
     * @return The amount of gold the player has collected so far.
     */
    public static int amountOfGold() {
        return amountOfGold;
    }


    /**
     * Checks to see if the player has completed the maze.
     * The player has completed the maze if they have reached the destination.
     *
     * @return True if the player has completed the maze.
     */
    public static boolean isMazeCompleted() {
    	for(int row=0;row<data.length;row++) {
    		for(int col=0;col<data[0].length;col++) {
    			if(data[row][col].equals("@")) {
    				
    				return false;
    			}	    			
    		}
    }
	
    	return true;
    }

    /**
     * Checks to see if it is the end of the game.
     * It is the end of the game if one of the following conditions is true:
     *  - There are no remaining steps.
     *  - The player has no lives.
     *  - The player has completed the maze.
     *
     * @return True if any one of the conditions that end the game is true.
     */
    public static boolean isGameEnd() {
        if(numberOfSteps<=0||numberOfLives<=0||isMazeCompleted()) {
        	return true;
        }   
        return false;
    }

    /**
     * Checks if the coordinates (x, y) are valid.
     * That is, if they are on the board.
     *
     * @args x The x coordinate.
     * @args y The y coordinate.
     * @return True if the given coordinates are valid (on the board),
     *         otherwise, false (the coordinates are out or range).
     */
public static boolean isValidCoordinates(int x, int y) {
	        if(x<0||x>=data[0].length||y<0||y>=data.length) {
	        	return false;
	        }
	        	return true;
	     }

    /**
     * Checks if a move to the given coordinates is valid.
     * A move is invalid if:
     *  - It is move to a coordinate off the board.
     *  - There is a wall at that coordinate.
     *  - The game is ended.
     *
     * @args x The x coordinate to move to.
     * @args y The y coordinate to move to.
     * @return True if the move is valid, otherwise false.
     */
    public static boolean canMoveTo(int x, int y) {
    	if(!isValidCoordinates(x,y)||data[y][x].equals("#")||isGameEnd()) {
    		return false;
    	}
        return true;
    }
    

    /**
     * Move the player to the given coordinates on the board.
     * After a successful move, it prints "Moved to (x, y)."
     * where (x, y) were the coordinates given.
     *
     * If there was gold at the position the player moved to,
     * the gold should be collected and the message "Plus n gold."
     * should also be printed, where n is the amount of gold collected.
     *
     * If it is an invalid move, a life is lost.
     * The method prints: "Invalid move. One life lost."
     *
     * @args x The x coordinate to move to.
     * @args y The y coordinate to move to.
     */
    public static void moveTo(int x, int y) {
	
		if(!isValidCoordinates(x,y)||data[y][x].equals("#")) {
			numberOfLives--;
			numberOfSteps--;
			System.out.println("Invalid move. One life lost.");
		}else {
			data[getCurrentYPosition()][getCurrentXPosition()]=".";
			try {
				amountOfGold=amountOfGold+Integer.parseInt(data[y][x]);
				System.out.println("Moved to ("+x+", "+y+").");
				System.out.println("Plus "+Integer.parseInt(data[y][x])+ " gold.");
				data[y][x]="&";
				numberOfSteps--;
			}catch(Exception e) {
				data[y][x]="&";
				numberOfSteps--;
				System.out.println("Moved to ("+x+", "+y+").");
				}
			}
	    }
    /**
     * Prints out the help message.
     */ 

    /**
     * Prints out the help message.
     */
    public static void printHelp() {
    	
    	System.out.println("Usage: You can type one of the following commands.");
		System.out.println("help         Print this help message.");
		System.out.println("board        Print the current board.");
		System.out.println("status       Print the current status.");
		System.out.println("left         Move the player 1 square to the left.");
		System.out.println("right        Move the player 1 square to the right.");
		System.out.println("up           Move the player 1 square up.");
		System.out.println("down         Move the player 1 square down.");
		System.out.println("save <file>  Save the current game configuration to the given file.");
    	
    	
    }

    /**
     * Prints out the status message.
     */
    public static void printStatus() {
    		System.out.println("Number of live(s): "+numberOfLives);
    		System.out.println("Number of step(s) remaining: "+numberOfSteps);
    		System.out.println("Amount of gold: "+amountOfGold);
    
    }

    /**
     * Prints out the board.
     */
    public static void printBoard() {
      	for(int i=0;i<numberOfRow;i++) {
    		for(int u=0;u<data[0].length;u++) {
    			System.out.print(data[i][u]);
    		}
    		System.out.println();
    	}
    
    }

    /**
     * Performs the given action by calling the appropriate helper methods.
     * [For example, calling the printHelp() method if the action is "help".]
     *
     * The valid actions are "help", "board", "status", "left", "right",
     * "up", "down", and "save".
     * [Note: The actions are case insensitive.]
     * If it is not a valid action, an IllegalArgumentException should be thrown.
     *
     * @args action The action we are performing.
     * @throws IllegalArgumentException If the action given isn't one of the
     *         allowed actions.
     *         if((str.charAt(0)=='h'||str.charAt(0)=='H')&&(str.charAt(1)=='e'||str.charAt(1)=='E')&&(str.charAt(2)=='l'||str.charAt(2)=='L')&&(str.charAt(3)=='p'||str.charAt(0)=='P')) {
     *         
    	}
    	input.close();
    		
     */
    public static int EndingChecker() {
    	int endingChecker=10;
		if(isMazeCompleted()){
			endingChecker=2;
		}else if(numberOfSteps==0&&numberOfLives==0){
			endingChecker=-1;
		}else if(numberOfLives==0){
			endingChecker=1;
		}else if(numberOfSteps==0){
			endingChecker=0;
		}
    	return endingChecker;
    }
     public static void performAction(String action) throws IllegalArgumentException {
    	 Saving=action.split(" ");
    	int x,y;
    	if(action.equalsIgnoreCase("help")) {
    		printHelp();
        }else if(action.equalsIgnoreCase("board")) {
        	printBoard();
        }else if(action.equalsIgnoreCase("status")) {
       		printStatus();
       	}else if(action.equalsIgnoreCase("up")) {
       		x=getCurrentXPosition();
       		y=getCurrentYPosition()-1;    		
        	moveTo(x,y);
        	EndingChecker();
        	}else if(action.equalsIgnoreCase("down")) {
        		x=getCurrentXPosition();
        		y=getCurrentYPosition()+1;
         		moveTo(x,y);
        	}else if(action.equalsIgnoreCase("left")) {
        		x=getCurrentXPosition()-1;
        		y=getCurrentYPosition();
        		moveTo(x,y);
        	}else if(action.equalsIgnoreCase("right")) {
        		x=getCurrentXPosition()+1;
        		y=getCurrentYPosition();
        		moveTo(x,y);
        	}else if(Saving.length==2&&Saving[0].equalsIgnoreCase("save")) {
        		try{
        			saveGame(Saving[1]);
    				System.out.println("Successfully saved the current game configuration to '" + Saving[1] + "'.");
				}catch(Exception e){
					System.out.println("Error: Could not save the current game configuration to '" + Saving[1] + "'.");		
				}
			}else if(action.equals("")){		
				
        	}else {
        		throw new IllegalArgumentException("Error: Could not find command '" + action + "'.\nTo find the list of valid commands, please type 'help'.");
        	}
	 }

    /**
     * The main method of your program.
     * @throws IOException 
     *
     * @args args[0] The game configuration file from which to initialise the
     *       maze game. If it is DEFAULT, load the default configuration.
     */
    public static void main(String[] args)  {
		  	if(args.length==0){
			System.out.println("Error: Too few arguments given. Expected 1 argument, found 0.\r\n" + 
					"Usage: MazeGame [<game configuration file>|DEFAULT]");
			return;
			}
   	if(args.length>1){
			System.out.println("Error: Too many arguments given. Expected 1 argument, found "+args.length+".\r\n" + 
					"Usage: MazeGame [<game configuration file>|DEFAULT]");
			return;
			
		}
	
		try {
			initialiseGame(args[0]);
		}catch(IOException e) {
			System.out.println("Error: Could not load the game configuration from '"+args[0]+"'.");
		}
		Scanner input=new Scanner(System.in);	
		while(input.hasNextLine()) {
			String str=input.nextLine();	
			try {
				performAction(str);
				if(EndingChecker()==-1) {
					System.out.println("Oh no! You have no lives and no steps left.");
					System.out.println("Better luck next time!");
					break;
				}
				else if(EndingChecker()==0){
					System.out.println("Oh no! You have no steps left.");
					System.out.println("Better luck next time!");		
					break;	
				}
				else if(EndingChecker()==1){
					System.out.println("Oh no! You have no lives left.");
					System.out.println("Better luck next time!");
					
					break;	
				}else if(EndingChecker()==2){
					System.out.println("Congratulations! You completed the maze!");
					System.out.println("Your final status is:");
					printStatus();
				
					break;
				}
			}catch (IllegalArgumentException e) {
					System.out.println("Error: Could not find command '" + str.trim() + "'.\nTo find the list of valid commands, please type 'help'.");
				} 
		}
		input.close();	
		if(!isGameEnd()){
			System.out.println("You did not complete the game.");
		}
    }		    		
}
