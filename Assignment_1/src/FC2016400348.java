
//CmpE 150 Introduction to Computing, Fall 2018
//Project 1—Due:02/11/2018, 9am
//
//
//You will write a Java program to implement the ASCII cocktail glasses below using two
//parameters glassSize as the size of the cocktail glass and strawPos as the position of the straw.
//One example run is given below, other example runs are at the bottom of this file. Please read the
//assignment description before beginning to write any code.
//glassSize = 3, strawPos = 4
//
//Please make sure you follow these rules in your implementation:
//1. Your program should have at least two static methods in addition to your main method. Try
//to write your program as modular as possible (without overusing methods).
//2. You are not allowed to generate the entire picture or any single line using printing statements
//such as System.out.print or System.out.println. For example, you cannot have a statement
//that says:
//3. System.out.println(“\******/”);
//You need to generate it using for loops.
//4. Assume that the strawPos will not be greater than glassSize*2. (In other words, strawPos
//<= glassSize*2) Also, strawPos and glassSize will be strictly greater than zero.
//5. You are not allowed to use statements that we haven’t learned in class as of 23/10/18 (such
//as while loops, arrays, and so on).
//6. Try to minimize the number of for loops you use.
//7. We have given you a part of the code for the main method in arguments.java file. This
//code runs your program with arguments given to glassSize and strawPos respectively. Copy
//this code to your own .java file. To test and run your program with arguments, click Run ->
//Run Configurations -> Arguments -> Program Arguments. Enter two integers here, and
//then click run at the bottom of the window. These two integers represent glassSize and
//strawPos respectively.

//Submission: You will submit a project report and your code over Moodle.
//Project report should consist of five sections. These are:
//1. Problem Description: In this section, you should describe the problem in yourwords.
//2. Problem Solution: In this section, you should specify the concepts (methods, for loop, etc.)
//that you use in your program. Explain each one (i.e. why you need it, what you accomplish
//by using it, so on.). Report how many for loops you use.
//3. Implementation: This section will include your whole code with comments. You need to pay
//attention to indentation in order to improve readability.
//• Do not forget to explain each variable that you use (i.e. int count=0; // count is the
//number of items).
//• Before each method, specify what the method does (i.e. /* This method . */)
//4. Output of the program: A screen-shot of your program output should be put in thissection.
//Two example runs are enough.
//5. Conclusion: You should evaluate your work here. State whether you have solved the problem
//correctly. If not, state what is missing, what could have been improved, and so on.
//Your .java file should be named with your initials and your student number together (e.g.,
//OS2013800027). If you have Turkish characters as your initials, please change them to nonTurkish.
//(Example: ÖS2013800027 should be OS2013800027) You will submit these over
//Moodle as a single zip file where the file name is your student number. Your zip file should
//consist of your .java file and your report in .doc or .pdf format. Do not use any Turkish
//characters in your code, class/variable names, or .java file names.
//Partial Submission: If you cannot generate the picture above, you should still submit your
//code as well as your report. Try to generate most of the picture. In your report, explain which parts
//you can generate and which parts you cannot.
//Late Submission: Any submission after the deadline is considered late and will not be accepted.

//FC2016400348.java

public class FC2016400348 {

	public static void main(String[] args) {
	
		int glassSize = Integer.parseInt(args[0]);
		int strawPos  = Integer.parseInt(args[1]);
		
		//Calling the method called cocktail.
		
		cocktail(glassSize,strawPos);
		
		
	}
	
	// this method is for a part of strawpos at upside.
	public static void strawPos(int strawPos) {
		
		for(int i = 0; i<strawPos; i++) {  // to create for strawpos at the beginning.
			for(int k = 0; k<i; k++) {
				
				System.out.print(" ");
			}
			
				System.out.print("o");
			
				System.out.println();
		}
		
		
	}
	
	//this method is for down part of each shape.
	public static void downPart(int glassSize) {
		
		for(int i = 0; i<glassSize ; i++) {
				System.out.print(" ");
		}
		 		System.out.print("--");
		
		for(int i = 0; i<glassSize ; i++) {
			
				System.out.println();
			
		for(int k = 0; k < glassSize ; k++) {
				
				System.out.print(" ");
			}
				
				System.out.print("||");	 
			}
	}
	
	//this method is for a preset method for first shape.For other shapes, i created another method  below.
	public static void slashAndStars(int glassSize) {
		
		for (int i = 0; i < glassSize; i++){
			
			for(int k = 0; k < i; k++) {
				
				System.out.print(" ");
			}
			
				System.out.print("\\");
			for(int l = 0 ; l < glassSize * 2 - i * 2 ; l++) {
				
				System.out.print("*");
				
			}
				System.out.print("/");
			
				System.out.println();
			}
		
	}
	
	
	//This method is for rest of the part.In this method, with many loops , rest of shapes are created.
	public static void cocktail(int glassSize,int strawPos) {
		
		strawPos(strawPos);
		slashAndStars(glassSize);  // In this part, first shape has been created.Then, for other shapes, loops and if statements 
		downPart(glassSize);	  //have been used. 
		
		
		System.out.println();
		
		// There are many loops and if which are practical for shapes in this part. 
		for(int outerloop = 0; outerloop < (glassSize * 2 - strawPos) / 2 + 1; outerloop++) {	
	
			strawPos(strawPos);	
		
		for(int loop = 0; loop < (glassSize); loop++) {
			
			for(int space = 0; space < loop; space++) {
				
				System.out.print(" ");
			}
				
				System.out.print("\\");
			
			
			if(loop <= outerloop) {	
			
			for(int space1 = 0; space1 < strawPos - 1; space1++) {
				 
				 System.out.print(" ");
				 
			}
			
				System.out.print("o");
				
			for(int space2 = 0; space2 < (glassSize * 2 -strawPos) - loop * 2 ;  space2++) {
				
				System.out.print(" ");
					
				}
			
			}else {
			
			for(int stars = 0; stars < glassSize * 2 - 2 * loop; stars++) {
				
				System.out.print("*");
			}
			
			}
			
			
				System.out.print("/");
			
				System.out.println();
			
				
			}
			
				downPart(glassSize);  // this part is for down parts for shapes.
	
				System.out.println();
	}
	}
	
	

}
