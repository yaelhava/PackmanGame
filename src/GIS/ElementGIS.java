package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

	/**
	 * this class represents the lines from the csv file, one line is element.
	 * @author yael hava and naama hartuv
	 */

public class ElementGIS implements GIS_element {

	private metaDataGIS data;
	private Point3D point;
	private MyCoords coords;
	
	/**
	 * constructor
	 * @param data - the data in one line
	 * @param point	- the coordinate of one line
	 */
	
	public ElementGIS(metaDataGIS data,Point3D point) {
		this.data = data;
		this.point = point;
	}
	
	/**
	 * @return point - the coordinate
	 */
	
	@Override
	public Geom_element getGeom() {
		return point;
	}

	/**
	 * @return data - the data of one line
	 */
	
	@Override
	public Meta_data getData() {
		return data;
	}

	/**
	 * computes a point which is the element point transformed by a 3D vector (in meters)
	 * @param vec - the 3D vector in meters
	 */
	
	@Override
	public void translate(Point3D vec) { //if we need it, we need to check if it works!!!!!!!!!!!!!!
		Point3D p = new Point3D(point);
		p = coords.add(p, vec);
	}

}
