package accidentpack;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * ReportTree - file reader class and also contains adder method from BST to arrayList
 * @author Domenic Mancuso
 * @version 3/4/2024
 */
public class ReportTree {
		 
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	/**
	 * DateConvert - converts inputDate to readable LocalDate format
	 * @author Behrooz Mansouri (professor). Thanks!
	 */
		 public static LocalDate dateConvert(String dateTimeString){
	        // for some of the instances the after seconds there are 0s; this line will remove them
	  dateTimeString = dateTimeString.split("\\.")[0];

	        // Parse the string using the formatter
	  LocalDate localDate;
      try {
	  localDate = LocalDate.parse(dateTimeString, formatter);
      } catch (Exception e) {
	            // Handle parsing exception, e.g., invalid format, invalid date
	    System.err.println("Error parsing date-time string: " + e.getMessage());
	    localDate = null;
	          }
	        return localDate;
	    }
		
		 
		 
		 
		 
		/**
		 * createReport - creates report object using Scanner to read file line by line
		 * uses fancy lambda methods 
		 * @param line
		 * @return r Report
		 */
		private static Report createReport(String line) {//a method for creating a report
			String[] items= line.split(",");
			String ID = items[0];
			int Severity = Integer.parseInt(items[1]);	
			LocalDate StartTime = dateConvert(items[2]);
			LocalDate EndTime = dateConvert(items[3]);		
			String Street = items[4];
			String City = items[5];
			String County = items[6];
			String State = items[7];
			double Temperature = Double.parseDouble(items[8]);
			double Humidity = Double.parseDouble(items[9]);
			double Visibility = Double.parseDouble(items[10]);
			String Weather = items[11];
			boolean AtCrossing = items[12].substring(0,1).equals("F")?true: false;
			boolean IsDay = items[13].substring(0,1).equals("N")?false:true;
			Report r = new Report(ID, Severity,StartTime, EndTime, Street, City, County, State, Temperature, Humidity, Visibility, Weather, AtCrossing, IsDay);
			return r;	}
		
		

		/**
		 * ReadCSVFile - reads report object line by 
		 * line and calls arrayListAddMethod while reading reports
		 * to add them to BSTs and add BSTs into arrayList
		 * @param filePath - inputFile of CSV format
		 * @return arrayList of BST
		 */
static ArrayList<myBST> arrayList;
		public static ArrayList<myBST>  ReadCSVFile(String filePath) throws IOException, ParseException
		{		
	Scanner scanner = new Scanner(new File(filePath));
	arrayList = new ArrayList<>();
			System.out.println("Please wait...");
//	int count = 1;
	String line = null;
	scanner.nextLine();
			//create reports
	while(scanner.hasNextLine()) {
	line = scanner.nextLine();
	Report r = createReport(line);
	arrayListAddMethod(r);
//	count+=1;
//	if(count%100000==0) {
//	System.out.println(count + " records added");
//		        }
				}
	scanner.close();
	return arrayList;
	}
		
	/**
	 * arrayListAddMethod - creates BSTs of reports and add them into an arrayList.
	 * If BST with state equal to report state does not exist in arrayList, create a new BST 
	 * with state set to report state. Otherwise, add report to existing BST with same state
	 * @param reports created via ReadCSVFile method
	 * @return arrayList of BSTs separated by state
	 */
		public static ArrayList<myBST> arrayListAddMethod(Report report) {
		    // Handle potential null report
		    if (report == null) {
		    throw new IllegalArgumentException("Report object cannot be null");
		    }
		    // Check if arrayList is null or empty, initialize if needed
		    if (arrayList == null || arrayList.isEmpty()) {
		    arrayList = new ArrayList<>();
		    }
		    boolean reportAdded = false;
		    // Find or create BST based on report state
		    for (myBST bst : arrayList) {
		    if (bst.getState().equals(report.getState())) {
		    bst.insert(report);
		    reportAdded = true;
		    break; // Exit loop if report added
		        }
		    }
		    // If no matching BST found, create a new one and add it to the list
		    if (!reportAdded) {
		    myBST newBST = new myBST();
		    newBST.setState(report.getState());
		    newBST.insert(report);
//		    System.out.println("new BST: " + newBST.getState());
		    arrayList.add(newBST);
		    }
		    return arrayList;
		}
}