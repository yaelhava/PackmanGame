package File_format;


import java.util.Comparator;

import Algorithms.nextFruit;


public class TimeRunComperator implements Comparator<nextFruit> {

	public int compare(nextFruit f1, nextFruit f2) {
		if(f1.getTime()>f2.getTime()) return 1;
		if(f1.getTime()<f2.getTime()) return -1;
		else
			return 0;
	}

	
}