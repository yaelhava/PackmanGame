package TheGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Coords.MyCoords;
import Geom.Point3D;

public class Map {

	private Point3D startPoint = new Point3D(32.106046, 35.212405);
	private Point3D endPoint = new Point3D(32.101858, 35.202574);
	private Pixel startPixel;
	private Pixel endPixel;
	private int width,height;
	private BufferedImage map;

	public Map(String path) {
		try {
			map =ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public Point3D gps2Pixel(Point3D point, int width,int height) {
		Point3D ratio = normalize(point,startPoint,endPoint);
		double resultX = Math.abs(ratio.x()*Math.abs(width));
		double resultY = Math.abs(ratio.y()*Math.abs(height));
		return new Point3D(resultX,resultY);
	}

	public Point3D pixel2gps(Point3D point, int width,int height) {
		Point3D ratio = normalize(point,new Point3D(0,0),new Point3D(width,height));
		double resultX = ratio.x()*Math.abs(endPoint.x()-startPoint.x())+startPoint.x();
		double resultY = ratio.y()*Math.abs(endPoint.y()-startPoint.y())+startPoint.y();
		return new Point3D(resultX,resultY);
	}

	private Point3D normalize(Point3D point, Point3D startPoint ,Point3D endPoint) {
		double xPrecent = (point.x()-startPoint.x())/(endPoint.x()-startPoint.x());
		double yPrecent = (point.y()-startPoint.y())/(endPoint.y()-startPoint.y());
		return new Point3D(xPrecent,yPrecent);
	}

	public BufferedImage getMap() {
		return map;
	}





	//		public Map(Point3D start, Point3D end, String path, int width, int height) {
	//			this.startPoint= start;
	//			this.endPoint=end;
	//			this.width=width;
	//			this.height=height;
	//			
	//			try {
	//				map =ImageIO.read(new File(path));
	//			} catch (IOException e) {
	//				e.printStackTrace();
	//			}
	//		}
	//		
	//		
	//	
	//		public double onePixelWidth() {
	//			return Math.abs(endPoint.x()-startPoint.x())/width;
	//		}
	//		
	//		public double onePixelHeight() {
	//			return Math.abs(endPoint.y()-startPoint.y())/height;
	//		}
	//		
	//		public Pixel GPS2Pixel (Point3D point) {
	//			int Pixel_x = (int) ((startPoint.x()-point.x())*onePixelWidth());
	//			int Pixel_y= (int) ((startPoint.y()-point.y())*onePixelHeight());
	//			Pixel p= new Pixel(Pixel_x, Pixel_y);
	//			return p;
	//		}
	//		
	//		
	//		public Point3D Pixel2GPS(Pixel p) {
	//			double GPS_x= (startPixel.getLeftPixel()-endPixel.getLeftPixel())/onePixelWidth();
	//			double GPS_y= (startPixel.getRightPixel()-endPixel.getRightPixel())/onePixelHeight();
	//			Point3D g=  new Point3D(GPS_x, GPS_y);
	//			return g;
	//		}
	public double PixelDistance (Point3D p1, Point3D p2) {

		Point3D startPixel= gps2Pixel(p1, map.getWidth(),map.getHeight());
		Point3D endPixel= gps2Pixel(p2, map.getWidth(),map.getHeight());
		MyCoords m= new MyCoords(0, 0, 0);
		double dis= m.distance3d(startPixel,endPixel);

		return dis;
	}

	//			public double PixelEngel (Pixel p1, Pixel p2) {
	//				Point3D SP= Pixel2GPS(p1);
	//				Point3D EP= Pixel2GPS(p2);
	//				MyCoords m= new MyCoords(0, 0, 0);
	//				double[] AED= m.azimuth_elevation_dist(SP, EP);
	//				return AED[0];
	//			}



}