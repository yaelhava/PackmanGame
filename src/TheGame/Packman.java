package TheGame;

import java.util.ArrayList;

import Geom.Point3D;

public class Packman {

	private PackmanMetaData data;
	private Point3D point3D;
	//private Pixel pixel;
	private ArrayList<Fruit> packmanRoad; 
	private double time = 0;
	
	
	



	public Packman(Point3D point, PackmanMetaData data) {
		this.point3D = point;
		this.data = data;
		packmanRoad = new ArrayList<Fruit>();
	}
	
	

	public Packman(Point3D point) {
		this.point3D = point;
		packmanRoad = new ArrayList<Fruit>();
	//	this.pixel = pixel;
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
//	public Pixel getPixel() {
//		return pixel;
//	}

	
	
	
}
