package xOxGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class FC2016400348 {
	public static final Scanner console = new Scanner(System.in);
	public static final Random rand = new Random();
	public static String sumOfPosition = "";
	public static int positionX;
	public static int positionY;
	public static int userWin = 0;
	public static int comWin = 0;
	public static String userSymbol;
	public static String comSymbol;
	public static boolean isDraw = false;
	public static boolean won;
	public static boolean playAgain = true;
	public static String board = "";
	public static String actualSymbol;
	public static void main(String[] args) throws FileNotFoundException {
		
				
		gameStart();
		
		do {
		
		do {
			System.out.println();
			move(actualSymbol);
			gameControl(board);
			
			
			if(won == true && actualSymbol == userSymbol) {
				
				System.out.println("You win.");
				userWin++;
				
			}else if(won == true && actualSymbol == comSymbol) {
				System.out.println("Computer win.");
				comWin++;
			}
			else if(isDraw) {
				System.out.println("It's Draw.!");
				
			}
			
			
			actualSymbol = (actualSymbol == userSymbol) ? comSymbol : userSymbol ;
		}while(won == false && isDraw == false);
		
			System.out.print("Do you want play again ?(Y or N)");
			playAgain();
			
			
		}while(playAgain == true);
		
			
		}
	
	
		
		
	public static void gameStart() throws FileNotFoundException {
		
		System.out.println("Welcome to the XOX Game.");
		
		System.out.print("Would you like to load the board from file or create a new one? (L or C)"); // taking input from user to start the game with creating new game board or loading board from file.
		
		String in = console.nextLine().toUpperCase();
		
		
		 while(!(in.equals("L") || (in.equals("C")))) {  // check for true input
			 
			 System.out.print("Wrong input :(L OR C) ");
			 in = console.nextLine().toUpperCase();
	 }
		
		
		if(in.equals("C")) {
			
			System.out.print("Enter your symbol :(X OR O) ");
			
			 userSymbol = console.nextLine().toUpperCase(); // input to define the symbol which that user will use(x or o) for game. 
			 
			 
			 while(!(userSymbol.equals("X") || (userSymbol.equals("O")))) { // check for wrong input
				  
				 System.out.print("Wrong input:(X OR O) ");
				 userSymbol = console.nextLine().toUpperCase();
		 }
			 
			 if(userSymbol.equals("X")) {
				 
				 comSymbol = "O";
			 }else {
				 comSymbol = "X";
			 }
			 
			 System.out.println("Your are player "+ userSymbol + " and the computer player is player "+ comSymbol);
			
			
			if(rand.nextInt(10) > 5) {		// to determine who will start the game randomly.
				
				System.out.println("You will start:");
				
				actualSymbol = userSymbol;
				System.out.println(emptyBoard());
				board = emptyBoard();
				
			}else {
				
				System.out.println("Computer will start:");
				
				actualSymbol = comSymbol;
				System.out.println(emptyBoard());
				board = emptyBoard();
			}
			
			
		}else {
			
			loadGame();	//Loading game from file.
		}
		
		
		
	}
	
	public static void move(String symbol) {		// to put the symbol into the game board one by one.
		
		boolean input = false;
		
		do {
			
			if(symbol.equals(userSymbol)) {
				
				
				System.out.print("Enter the coordinate: ");	// to get input from 1 to 4 for user.
				
			 positionX = console.nextInt();
			 positionY = console.nextInt();
			if((positionX >= 1 && positionX <= 4 ) && (positionY >= 1 && positionY <= 4 ) && !(checkPosition(Integer.toString(positionX),Integer.toString(positionY)))) { 
				
				board = updateBoard(board,symbol,positionX,positionY);	// to update the board with putting actual symbol in the right position.
				
				input = true;
				
				
			}else {
				
				System.out.println("Wrong input ! Try again");	// to check wrong input or not
			}
			}else {
				
				
				 int getX = rand.nextInt(4) + 1;
				 int getY = rand.nextInt(4) + 1;	// to get input randomly from 1 to 4 for computer
				
				if(!checkPosition(Integer.toString(getX),Integer.toString(getY))) {	// to check for position whether it was used or not.
					
					board = updateBoard(board,symbol,getX,getY); // to update the board with putting actual symbol in the right position.
					
					input = true;
				}
		
			}
		}while(!input);	 // to exit loop if input is right .
		
	}
	
	//Load the game
	
	public static void loadGame() throws FileNotFoundException { // to load the board from file.
		
		
		System.out.print("Please enter the file name: ");
		
		String filename = console.next();
		
		File f = new File("src/xOxGame/" + filename);
		
		while(!f.exists()) {		// to exit loop if f exist.
			
			System.out.print("File is not found.Please enter the right file name:");
			filename = console.next();
			f = new File("src/xOxGame/" +filename);
		}
		
		System.out.println("Load successful.");
		
		Scanner read = new Scanner(f);
		
		while(read.hasNextLine()) {		// to read the board from file.
			
			board += read.nextLine();
			board += "\n";
			
		}
		
		System.out.print("Enter your symbol :(X OR O) ");
		
		 userSymbol = console.next().toUpperCase(); // input to define the symbol which that user will use(x or o) for game. 
		 
		 
		 while(!(userSymbol.equals("X") || (userSymbol.equals("O")))) { // check for wrong input
			 
			 System.out.print("Wrong input:(X OR O) ");
			 userSymbol = console.nextLine().toUpperCase();
	 }
		 
		 if(userSymbol.equals("X")) {
			 
			 comSymbol = "O";
		 }else {
			 comSymbol = "X";
		 }
		 
		 System.out.println("Your are player "+ userSymbol + " and the computer player is player "+ comSymbol);
		 actualSymbol = userSymbol;
		 sumOfPosition += "1-1/3-2/4-2/4-4/";		// to update the position which is used from file
		 System.out.println(board);
	}


	//Update the board
	
	public static String updateBoard(String board,String side, int x, int y) {	// to update the board.
		
		Scanner scan = new Scanner(board);
		
		String updateBoard = "";
		
		int countLine = 0;
	
		while(scan.hasNextLine()) {
			
			String line = scan.nextLine();
			
			Scanner sc = new Scanner(line);
			
			countLine++;
			
			int index = 0;
			
			while(sc.hasNext()) {
				
				String in = sc.next();
				
				if(in.equals("E") || in.equals("X") || in.equals("O")){					// after the position is found in the board,we add the symbol the right position and update the board.
					
					index++;
					
				}
				
				if(countLine == x && index == y) {
					
					System.out.print(side + " ");
					updateBoard += side + " ";
					
					
					index = 1000;
					
					
				}else {
					
					System.out.print(in + " ");
					updateBoard += in + " ";
				}
				
			}
			
			
			System.out.println();
			updateBoard += "\n";
		}
		
		return updateBoard;
	
	}
	

	//Set the game control statements.(Vertically,horizontally,diagonally)
	
	public static boolean gameControl(String board) {		// to check in order to determine who won the game.
		
	
		 won = false;
		
		//Vertically
		
		String vertical = "";
		
		for(int line = 1; line<= 4; line++) {
			
			for(int k = 0; k < board.length() ; k++) {
				
				if(k % 19 == line* 4 - 2) {
					
					vertical += board.charAt(k);
				}
			}
				
				if(contains(vertical)) {
					
					won = true;
					break;
					
				}else {
				
				vertical = "" ;
				}
		}
		
		//Horizontally
		
		String horizontal = "";
		
		int indexH = 2;
		
		int length = (board.length() / 4 - 1); //length of each line;
		
		for(int line = 0; line< 4; line++) {
			
			for(int k = line*length; k < (length) * (line + 1); k++) {
				
					if(indexH <0) {
						indexH= 3;
					}
					if(k % 4 == indexH) {
					
						horizontal += board.charAt(k);
					}
			}
			
				if(contains(horizontal)) {
					
					won = true;
					break;
				}else {
					
					horizontal = "";
					indexH--;
				}
			
		}
		
		//Diagonally
		int indexDR = 2;
		int indexDL = 10;
		
		String diagonalLeft = "";
		String diagonalRight = "";
	
		
		
		for(int line = 1; line <= 3;) {

			for(int k = 0; k < board.length() ; k++) {
				
					
					if(k % 23 == indexDR) {
					
						diagonalRight += board.charAt(k);
					}
					
					if(k % 15 == indexDL) {
						
						diagonalLeft += board.charAt(k);
					}
			}
			
			
			
				if(contains(diagonalRight) || contains(diagonalLeft)) {
					
					won = true;
					break;
				}else {
					
					diagonalLeft = "";
					diagonalRight = "";
					line++;
					indexDR = indexDR * line + line;
					
					if(line == 2) {
						indexDL = 14;
					}							// Find a good solution !!;;
					if(line == 3) { 
						indexDL = 3;
					}
				
					
					}
					
				}
		
		if(board.indexOf("E") == -1) {		// for draw part.
			
			isDraw = true;
			
			
		}
				
		return won;
		
	}
	
	public static void playAgain() {	// to ask the user whether user want to play again or not.
		
		String answer = console.next();
		
		if(answer.equalsIgnoreCase("N")) {	// if the answer is N, game is finished.
			
			playAgain = false;
			
			System.out.print("You: " + userWin + " Computer: " + comWin + "\nThanks for playing");	// output number of wins of user and computer.
			
			
			
		}else {																// if the answer is Y , game is going on.
			won = false;
			board = emptyBoard();
			sumOfPosition = "";
			
			if(rand.nextInt(10) > 5) {
				
				System.out.println("You will start:");
				System.out.println(emptyBoard());
				actualSymbol = userSymbol;
				
				
			}else {
				
				System.out.println("Computer will start:");
				System.out.println(emptyBoard());
				actualSymbol = comSymbol;
				
			}
			
			
		}
		
		
	}
	
	//Set the empty board.
	
		public static String emptyBoard() {		// to create a new empty board .
			
			String string ="";
			
			for(int i = 1 ; i<=4;i++) {
				
				for(int k = 1; k<=4; k++) {
					string +="| E ";
				}
				
				string += "|\n";
				
			}
			
			return string;
			
		}
	
	//Contains part of the control game.
	
	public static boolean contains(String string) {		// to return if the string contains XXX or OOO which is a rule for determining who won.
		
		boolean result = false;
		
		if(string.contains("XXX") || string.contains("OOO")) {
			
			result = true;
		}
		
		return result;
		
	}
	
	//Check the position
	
	
	public static boolean checkPosition(String x ,String y) {		// to check position whether it is used or not and add to the  sumOfPosition variable.
		
		boolean result = false;
		
		for(int i = 0; i<sumOfPosition.length() - 1; i+=4) {			// (example)1-3/1-4/1-2/
			
			if(sumOfPosition.substring(i, i + 4).equals(x+"-"+y+"/")) {
				
				result = true;
				break;
			}
		}
		
		sumOfPosition += (x +"-"+y+"/");	//(example)1-3/
		
		return result;
		
		
	}
	

}
