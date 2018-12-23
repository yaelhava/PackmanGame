package TheGame;

import Geom.Point3D;

public class Fruit {
	
//	private ElementGeom point;
	private FruitMetaData data;
	private Point3D point3D;
	//private Pixel pixel;
	private int ID;
	private double weight;
	
	
	public Fruit(Point3D point, FruitMetaData data) {
		this.point3D = point;
		this.data = data;
	
	}
	
	public Fruit(Point3D point, int ID) {
		this.point3D = point;
		//this.pixel = pixel;
		this.ID = ID;
	}

	public Fruit(Point3D point) {
		this.point3D = point;
	
	}
	
//	public ElementGeom getPoint() {
//		return point;
//	}

	public FruitMetaData getFruitData() {
		return data;
	}

	public Point3D getPoint3D() {
		return point3D;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	

}
