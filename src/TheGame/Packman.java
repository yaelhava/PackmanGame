package TheGame;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;

import Algorithms.Path;
import Geom.Point3D;

/**
 * lishol et atara meifo hafunczia
 * @author yaelh
 *
 */


public class Packman {

	private PackmanMetaData data;
	private Point3D point3D;
	//private Pixel pixel;
	private ArrayList<Fruit> packmanRoad; 
	private double time = 0;
	private double radius, moveAbility;
	private int ID;



	public Packman(Point3D point, PackmanMetaData data) {
		this.point3D = point;
		this.data = data;
		packmanRoad = new ArrayList<Fruit>();
		Fruit f = new Fruit(point3D);
		packmanRoad.add(f);
	}
	
	

	public Packman(Point3D point, double radius, double moveAbility, int ID) {
		this.point3D = point;
		packmanRoad = new ArrayList<Fruit>();
		this.radius = radius;
		this.moveAbility = moveAbility;
		this.ID = ID;
		Fruit f = new Fruit(point3D);
		packmanRoad.add(f);
		
	//	this.pixel = pixel;
	}
	
	
	public double getRadius() {
		return radius;
	}


	public double getMoveAbility() {
		return moveAbility;
	}



	public PackmanMetaData getPackmanData() {
		return data;
	}

	public Point3D getPoint3D() {
		return point3D;
	}

	public void setPoint3D(Point3D point3d) {
		point3D = point3d;
	}
	
	
	public ArrayList<Fruit> getPackmanRoad() {
		return packmanRoad;
	}
	
	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}



	public void setRadius(double radius) {
		this.radius = radius;
	}



	public void setMoveAbility(double moveAbility) {
		this.moveAbility = moveAbility;
	}



	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}

	
	
}
