package GIS;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import Geom.Point3D;

/**
 * this class saves the data of a project
 * @author yael hava and naama hartuv
 */

public class ProjectMetaData implements Meta_data{

	private String name;

	/**
	 * constructor
	 * @param name - the name of the project
	 */

	public ProjectMetaData(String name) {
		this.name = name;
	}

	/**
	 * getter for the name
	 * @return name - the name of the project
	 */

	public String getName() {
		return name;
	}

	/**
	 * computes the time the file was created
	 * @param path - the file locate
	 * @return the time as a string
	 */

	public static String checkTime(String path) {
		File file = new File(path);
		Path filePath = file.toPath();
		BasicFileAttributes attr = null;
		try {
			attr = Files.readAttributes(filePath, BasicFileAttributes.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "" + attr.creationTime();
	}

	/**
	 *  returns the Universal Time Clock associated with this data
	 *  @return the real time
	 */

	@Override
	public long getUTC() {
		return System.currentTimeMillis();
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}

}
