package GIS;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import Geom.Point3D;

/**
 * this class saves the data of a layer
 * @author yael hava and naama hartuv
 */

public class LayerMetaData implements Meta_data{

	private String name;

	/**
	 * constructor
	 * @param name - the name of the layer
	 */

	public LayerMetaData(String name) {
		this.name = name;
	}

	/**
	 * getter for the name
	 * @return - the name
	 */

	public String getName() {
		return this.name;
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
