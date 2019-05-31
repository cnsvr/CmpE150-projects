package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FC2016400348 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File("src/assignment3/input.txt"));
		
		String rowAndColumn = sc.next();
		String[] parts = rowAndColumn.split("x");		// to split two values from "x"
		String part1 = parts[0];
		String part2 = parts[1];
		
		String [][] table = new String[Integer.parseInt(part1)][Integer.parseInt(part2)];		// I created a table to keep values and change them.
		
		
		// Main part and calling all methods in a sequence.
		readTable(sc,table,0,0);
		printTable(table,0,0);
		changeTable(table,0,0);
		System.out.println();
		printTable(table,0,0);
			
	}
	
	public static String [][] readTable(Scanner sc, String [][] table,int rowIndex,int columnIndex) {
		
		
		if(!sc.hasNext()) 		// Base statement . This method will be finished and return table when there is no input to read.
			
			return table;
		
		
		table[rowIndex][columnIndex] = sc.next(); 	// to assign inputs to table.
		
		
		if(columnIndex == table[0].length- 1) {  	// to control the row index if column is finished to read.
			
			rowIndex++;
			columnIndex = -1;
			
		}
		
		return readTable(sc,table,rowIndex,columnIndex + 1);	// recursion part.	
		
	}
	
	public static void printTable(String [][] table,int rowIndex,int columnIndex){	// this method is to print the table..
		
		if(rowIndex >= table.length) 
			
			return;		//base statement.
		
		  System.out.print(table[rowIndex][columnIndex].charAt(1) + " ");
		 	
		
		if(columnIndex == table[0].length - 1) {		// to control the row index if column is finished to read.
			
			rowIndex++;
			columnIndex = -1;
			System.out.println();
			
		}
		
		printTable(table,rowIndex,columnIndex + 1);	//recursion part.
	
	}
	
	public static String[][] changeTable(String [][] table,int rowIndex,int columnIndex) {	// this method is to change and redesign the table with right instructions.
		
		if(rowIndex == table.length	) {		// this is base statement to finish when method is finished.
			
			return table;
		
		}else {
			
			if(table[rowIndex][columnIndex].startsWith("R")) {		// for row label
				
				int labelChange = rowLabel(table,rowIndex,0);		// to determine max value in the row.
				
				table[rowIndex][columnIndex] = "R" + Integer.toString(labelChange);	// to exchange value with max value in the row.
				
			}
			
			if(table[rowIndex][columnIndex].startsWith("C")) {		// for column label.
				
				int median [] = new int[table.length];	// to create array to find median in the column
				
				int labelChange = columnLabel(table,median,0,columnIndex);	// labelChange is the median value of corresponding column.
				
				table[rowIndex][columnIndex] = "C" + Integer.toString(labelChange);		// to exchange value with median value in the column.
				
			}
			
			if(table[rowIndex][columnIndex].startsWith("D")) {	// for diagonal label.
				
				int labelChange = diagonalLabel(table,rowIndex,columnIndex);	// labelChange is the mean value of diagonal numbers.(including itself).
				
				table[rowIndex][columnIndex] = "D" + Integer.toString(labelChange);	// to exchange value with mean value.
				
			}
			
			if(table[rowIndex][columnIndex].startsWith("N")) { // for neighbor label
				
				neighborUp(table,rowIndex,columnIndex);

				neighborDown(table,rowIndex,columnIndex);

				neighborLeft(table,rowIndex,columnIndex);

				neighborRight(table,rowIndex,columnIndex);
				
		}
			
				if(columnIndex == table[0].length- 1) {		// The process will continue with next row when column index come to end of the corresponding row.
						
					rowIndex++;
					columnIndex = -1;
					
				}
					
			return 	changeTable(table,rowIndex,columnIndex + 1);		// recursion part.
			
		}
	}

	
	public static int rowLabel(String[][]table,int rowIndex,int columnIndex) {		// this method is to determine the max value of the row.
		
		
		if(columnIndex == table[rowIndex].length - 1) {
			
			return Integer.parseInt(table[rowIndex][columnIndex].substring(1));		// base statement
			
		}else {
			
			return Math.max(Integer.parseInt(table[rowIndex][columnIndex].substring(1)),	// In this recursion , I used the math class to determine max value in the row.
					rowLabel(table,rowIndex,columnIndex + 1));								
		
		}
	}
	
	public static int columnLabel(String [][]table,int [] median,int rowIndex,int columnIndex) {		// this method is to determine the median value of the column.
		
		if(rowIndex == table.length) {
			
			Arrays.sort(median); // sorting the array
			
			if(median.length % 2 == 0) {			// if the length of the array is even, the small value will be median, otherwise middle value in the array will be median.
				
				return median[median.length / 2 - 1];
				
			}else {
				
				return median[median.length / 2 ];
			}
			
		}else {
		
			median[rowIndex] = Integer.parseInt(table[rowIndex][columnIndex].substring(1));	// to keep the values in an array to find the median.

			return columnLabel(table,median,rowIndex+1,columnIndex);	// recursion part.
		}
	}	
	
	public static void neighborUp(String [][]table,int rowIndex,int columnIndex) {	// This method is to change the number of N's value with the upper N's value.
	
		if(isInBound(table,rowIndex - 1,columnIndex) && table[rowIndex - 1][columnIndex].charAt(0) =='N'){ // to check the N's value whether it is in bound and has string N.
			
			table[rowIndex-1][columnIndex] = "A" + table[rowIndex][columnIndex].charAt(1);	// to assign upper N's new value.Also, i changed the N as A not to encounter  stack over flow.
			rowIndex--;
			neighborUp(table,rowIndex,columnIndex);	// recursion part of upper bound
			neighborRight(table,rowIndex,columnIndex);	// recursion part of right bound
			neighborLeft(table,rowIndex,columnIndex);	// recursion part of left bound
			
		}else {
		
		return;
		}	
		
	}
	
	public static void neighborDown(String [][]table,int rowIndex,int columnIndex) {    // This method is to change the number of N's value with the lower N's value.
		
		
		if(isInBound(table,rowIndex  + 1,columnIndex) && table[rowIndex + 1][columnIndex].charAt(0) =='N'){ // to check the N's value whether it is in bound and has string N.
			
			table[rowIndex + 1][columnIndex] = "A" + table[rowIndex][columnIndex].charAt(1);  // to assign lower N's new value.Also, i changed the N as A not to encounter  stack over flow.
			rowIndex++;
			neighborDown(table,rowIndex,columnIndex);  // recursion part of lower bound
			neighborLeft(table,rowIndex,columnIndex);  // recursion part of left bound
			neighborRight(table,rowIndex ,columnIndex); // recursion part of right bound
			
		}else {
			
			return;
			}		
	}
	
	public static  void neighborLeft(String [][]table,int rowIndex,int columnIndex) {  // This method is to change the number of N's value with the left side N's value.
		
		
		if(isInBound(table,rowIndex,columnIndex - 1) && table[rowIndex][columnIndex - 1].startsWith("N")){  // to check the N's value whether it is in bound and has string N.
			
			
			table[rowIndex][columnIndex - 1] = "A" + table[rowIndex][columnIndex].charAt(1);   // to assign left side N's new value.Also, i changed the N as A not to encounter  stack over flow.
			columnIndex--;
			neighborLeft(table,rowIndex,columnIndex);  // recursion part of left bound
			neighborUp(table,rowIndex,columnIndex);    // recursion part of upper bound
			neighborDown(table,rowIndex,columnIndex);  // recursion part of lower bound
		
		}else {
			
			return;
		}	
	}
	
	public static void neighborRight(String [][]table,int rowIndex,int columnIndex) {  // This method is to change the number of N's value with the right side N's value.
		
		
		if(isInBound(table,rowIndex,columnIndex + 1) && table[rowIndex][columnIndex + 1].charAt(0) =='N'){   // to check the N's value whether it is in bound and has string N.
			 
			table[rowIndex][columnIndex + 1] = "A" + table[rowIndex][columnIndex].charAt(1);   // to assign right side N's new value.Also, i changed the N as A not to encounter  stack over flow.
			columnIndex++;
			neighborRight(table,rowIndex,columnIndex); 	// recursion part of right bound
			neighborUp(table,rowIndex,columnIndex);		// recursion part of upper bound
			neighborDown(table,rowIndex,columnIndex);		// recursion part of left bound
			
		}else {
			
			return;
			}	
	}

		
	public static int diagonalLabel(String [][]table,int rowIndex,int columnIndex) {	// This method is to determine mean of diagonal values.
		
		
		int []diagonalSumAndCountUpLeft;int[]diagonalSumAndCountDownLeft;int[]diagonalSumAndCountUpRight;int[]diagonalSumAndCountDownRight; 
		
		
		diagonalSumAndCountUpLeft = upOfDiagonalLeft(table,rowIndex,columnIndex,0,0);			// to determine sum and count number of the left and up part of diagonal.
		diagonalSumAndCountDownLeft = downOfDiagonalLeft(table,rowIndex,columnIndex,0,0);		// to determine sum and count number of the left and down part of diagonal.
		diagonalSumAndCountUpRight = upOfDiagonalRight(table,rowIndex,columnIndex,0,0);		// to determine sum and count number of the right and up part of diagonal.
		diagonalSumAndCountDownRight = downOfDiagonalRight(table,rowIndex,columnIndex,0,0);	// to determine sum and count number of the right and down part of diagonal.
		
		// I added all of them
		int sumOfAll = diagonalSumAndCountUpLeft[0] + diagonalSumAndCountDownLeft[0]  + diagonalSumAndCountUpRight[0] + diagonalSumAndCountDownRight[0] + Integer.parseInt(table[rowIndex][columnIndex].substring(1));
		// I added all count.
		int countOfAll = diagonalSumAndCountUpLeft[1] + diagonalSumAndCountDownLeft[1]  + diagonalSumAndCountUpRight[1] + diagonalSumAndCountDownRight[1] + 1;
	
		// sum over count equals mean.
		return sumOfAll / countOfAll;
			
	}
	
		
	public static int[] upOfDiagonalLeft(String [][]table,int rowIndex,int columnIndex,int sum,int count) {	// This method is to determine sum and count at left and up part of diagonal.

			rowIndex--;
			columnIndex--;
		
		if(!isInBound(table,rowIndex,columnIndex)) {	// to check whether it is in bound or not.
			
			int diagonal [] = new int[2];	// to keep sum and count in an array.
			diagonal[0] = sum;
			diagonal[1] = count;
			
			return diagonal;
			
		}
		
			sum += Integer.parseInt(table[rowIndex][columnIndex].substring(1));	// to sum the values.
			count++;															// to determine how many values is added up.
			return upOfDiagonalLeft(table,rowIndex,columnIndex,sum,count);		// recursion part.
			
		}
	
	public static int[] downOfDiagonalLeft(String [][]table,int rowIndex,int columnIndex,int sum,int count) {	// This method is to determine sum and count at left and down part of diagonal.

		
			rowIndex++;
			columnIndex++;
			
			if(!isInBound(table,rowIndex,columnIndex)) { // to check whether it is in bound or not.
				
				int diagonal [] = new int[2];  // to keep sum and count in an array.
				diagonal[0] = sum;
				diagonal[1] = count;
				
				return diagonal;
				
			}
	
			sum += Integer.parseInt(table[rowIndex][columnIndex].substring(1));  // to sum the values.
			count++;																// to determine how many values is added up.
			return downOfDiagonalLeft(table,rowIndex,columnIndex,sum,count);		// recursion part.
			
	}
	
	public static int[] upOfDiagonalRight(String [][]table,int rowIndex,int columnIndex,int sum,int count) {		// This method is to determine sum and count at right and up part of diagonal.
		
		rowIndex--;
		columnIndex++;
	
		if(!isInBound(table,rowIndex,columnIndex)) {	// to check whether it is in bound or not.
		
			int diagonal [] = new int[2];	// to keep sum and count in an array.
			diagonal[0] = sum;
			diagonal[1] = count;
			
			return diagonal;
		}	
		
		sum += Integer.parseInt(table[rowIndex][columnIndex].substring(1));	 // to sum the values.
		count++;																// to determine how many values is added up.
		return upOfDiagonalRight(table,rowIndex,columnIndex,sum,count);		// recursion part.
		
}
	
	public static int[] downOfDiagonalRight(String [][]table,int rowIndex,int columnIndex,int sum,int count) {		// This method is to determine sum and count at right and up part of diagonal.
		
		rowIndex++;
		columnIndex--;
		
		if(!isInBound(table,rowIndex,columnIndex)) {		// to check whether it is in bound or not.
			
			int diagonal [] = new int[2];				// to keep sum and count in an array.
			diagonal[0] = sum;
			diagonal[1] = count;
			
			return diagonal;
			
		}
		sum += Integer.parseInt(table[rowIndex][columnIndex].substring(1)); 	  // to sum the values.
		count++;																 // to determine how many values is added up.
		return downOfDiagonalRight(table,rowIndex,columnIndex,sum,count);		// recursion part.
		
}

	
	public static boolean isInBound(String [][]table,int row, int col) {		// This method is to determine whether row and column are is valid or not and return. 
        boolean bol = false;
        if (row < table.length && col < table[0].length && col >= 0 && row >= 0) {
            bol = true;
        }

        return bol;
	}
}
