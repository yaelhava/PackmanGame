package Algorithms;

import java.io.File;
import java.io.PrintWriter;

import File_format.CSV2elements;
import File_format.Csv2kml;
import GIS.ProjectGIS;

/**
 * this class finds all the csv files in a given path,
 *  and create a project which consist of the csv files.
 *  at the end, it export a kml file.
 * @author yael hava and naama hartuv
 *
 */

public class MultiCSV {
	private ProjectGIS p;
	private String dir;
	private Csv2kml csv2kml;

	/**
	 * constructor
	 * @param dir - the given path
	 */

	public MultiCSV(String dir) { 
		this.dir = dir;
		p = new ProjectGIS();
		scan(dir);
	}

	/**
	 * scan the given path and finds all the csv files in it.
	 * @param path - the given path
	 */

	public void scan(String path) {
		File folder = new File(path);
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				scan(file.getPath());
			}
			else if(file.getName().endsWith(".csv")) {
				CSV2elements e = new CSV2elements(file.getPath());
				p.add(e.getLayer());
			}
		}
	}

	/**
	 * getter for the project created at this class
	 * @return p - the project
	 */
	
	public ProjectGIS getProject() {
		return p;
	}

	/**
	 * export the project consist of csv files to kml file.
	 */

	public void export() {
		csv2kml = new Csv2kml();
		StringBuilder s = csv2kml.exportKmlFile(p);
		try {
			PrintWriter export = new PrintWriter(new File("MyLovelyFile.kml"));
			export.write(s.toString());
			export.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
