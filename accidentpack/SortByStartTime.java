package accidentpack;

import java.util.Comparator;
/**
 * SortByStartTime - comparator method to compare by Start Times
 * @author Domenic Mancuso
 * @version 3/4/2024
 */
public class SortByStartTime implements Comparator<Report>{

	public int compare(Report r1, Report r2) {
		
			return r1.getStartTime().compareTo(r2.getStartTime());
			}

}
