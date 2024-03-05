package accidentpack;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
/**
 * program5 - runner class for Assignment 5
 * creates an arrayList that has Binary Search Trees (BST) 
 * added, number of BSTs are determined by how many different states
 * are included in accident Reports CSV file
 * @author Domenic Mancuso
 * @version 3/4/2024
 */
public class program5 {
	
	static String filePath;
	static String statePath;
	static String dateString;
	static LocalDate datePath;
	static ArrayList<myBST> arrayList;
	static myBST foundBST;

	

	
	/**
	 * findBST - finds BST of specified statePath to be iterated over 
	 * @param statePath
	 * @param arrayList
	 * @return foundBST as asked
	 */
	public static myBST findBST(String statePath, ArrayList<myBST> arrayList) {
	    myBST foundBST = null;  // Initialize to null to indicate not found

	    for (myBST BST : arrayList) {
	    if (BST.getState().equals(statePath)) {
	    foundBST = BST;
	    break;  // Exit loop once the matching BST is found
	        }
	    }
	    //test print statement
//	    System.out.println(foundBST.getState());
	    return foundBST;  // Might be null if not found
	}

	
	
	/**
	 * input - menu interface 
	 * utilizes scanner in void method to give desired reports
	 * @param filePath, statePath, datePath 
	 * it is a void method so it does not take parameters, rather arguments
	 * @see stolen from a StackOverflow submission I do not have the link to.
	 * Kudos to whoever it was
	 */
	public static void input() {
	    Scanner myScanner = new Scanner(System.in);
//input method is fine, fix other deliverables tonight
	    System.out.print("Please input a filePath: ");
	    filePath = myScanner.nextLine();  // Use nextLine() for entire line

	    System.out.print("Enter the state (e.g. 'IL'): ");
	    statePath = myScanner.nextLine();

	    System.out.print("Enter the date (e.g., 2022-09-08 00:00:00): ");
	    dateString = myScanner.nextLine();

	    // Validate date format and parse (if valid)
	    if (isValidDateFormat(dateString)) {
	    dateString = dateString.split("\\.")[0];
	    datePath = ReportTree.dateConvert(dateString);
	    myScanner.close();
	    } else {
	    System.err.println("Error: Invalid date format. Please try again.");
	    input(); // Recursively call input to get valid date
	    }}
	/**
	 * isValidDateFormat - tests if inputted date is usable
	 * @param dateString
	 * @return true if date is of applicable format
	 * @see Gemini AI for the complex code you see below
	 */
	private static boolean isValidDateFormat(String dateString) {
	    String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01]) ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])(\\.\\d+)?$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(dateString);
	    return matcher.matches();
		}
		
	/**
	 * findSeconds - quick method to convert from nanoseconds to seconds
	 * @param start - current time in nanoseconds
	 * @param stop - current time in nanoseconds
	 * @return difference of stop and start divided by 1*10^9
	 */
	public static long findSeconds(long start, long stop) {
		return ((stop-start)/1000000000);
		}

		public static void main(String[] args) {
long start;
long stop;
ArrayList<myBST> arrayList = new ArrayList<>();
myBST foundBST = new myBST();

		//input interface
	input();
	
		try {
		
		//build binary search trees
		start = System.nanoTime();
			arrayList = ReportTree.ReadCSVFile(filePath);
		stop = System.nanoTime();
	System.out.println(findSeconds(start,stop) + " seconds to build the binary search trees");
				
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	
	
//		start = System.nanoTime();
			foundBST = findBST(statePath, arrayList);
//		stop = System.nanoTime();
//	System.out.println(findSeconds(start,stop) +" seconds to find state BST");
	
	
//		start = System.nanoTime();
//			foundBST.inOrder();
//		stop = System.nanoTime();
//	System.out.println(findSeconds(start,stop)+" seconds for inOrder traversal");
		
	//count children of specified date
		start = System.nanoTime();
			int numChildren = foundBST.countChildren();
		stop = System.nanoTime();
	System.out.println(numChildren+ " reports are available for " + statePath +" on and after the date " + dateString);
	System.out.println(findSeconds(start,stop) + " seconds to count this using children count fields");
	
	//count records of specified date
		start = System.nanoTime();
			int numRecords=foundBST.countRecords();
		stop  = System.nanoTime();
		System.out.println(numRecords + " reports are available for " +statePath + " on and after the date " +dateString);
	System.out.println(findSeconds(start,stop)+" seconds to calculate this using recursive method");

		
		//iterative attempt
	
//			 foundBST.iterativeSearch(datePath);
//		System.out.print(" reports are available using iterative search");
		}
//	Deliverables:
//		1. All the necessary Java files with the README file for running the program
//		2. There should be a program5.java file with a main method that takes in the CSV file path
//		as the input argument and prints the answer to the following questions (by running your
//		algorithm). For each sub-task, you need to print the run time (by simply considering the
//		time difference before and after running your program)
//		Expected run commands and outputs:
//		command: java program5 accidents.csv
//		outputs:
//		xx seconds to build the binary search trees
	
	
	
//		Enter the state (e.g., IL): IL
//		Enter the date (e.g., 2022-09-08): 2022-09-08
	
	
//		xx reports are available for IL on and after the date 2022-09-08
//		xx seconds to calculate this using children count fields
//		xx reports are available for IL on and after the date 2022-09-08
//		xx seconds to calculate this using recursive method
	
	
//		Note that the underlined text shows input from the console.
//		You should have a myBST.java file, that has the following methods (and more if needed):
//		● insert (Report sr): boolean – You might have a different class name than Report
//		● search(Date date):int
//		● recursiveSearch(Date date):int
//		You are allowed LocalDate, or other formats representing a Date object.
//		3. A PDF file showing the run time for each sub-task (in seconds). For each sub-task,
//		mention the complexity order of your algorithm and justify it
//
//		




}
