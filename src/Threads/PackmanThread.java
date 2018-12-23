package Threads;

import java.util.ArrayList;

import GUI.MyFrame;
import Geom.Point3D;
import TheGame.Packman;

public class PackmanThread extends Thread{
	
	private MyFrame myFrame;
	private Packman packman;
	
	
	public PackmanThread(Packman packman,MyFrame myFrame) {
		this.packman = packman;
		this.myFrame = myFrame;
	}
	
	@Override
	public void run() {
		ArrayList<Point3D> p = myFrame.packmanSteps(this.packman);
	//	Point3D packmanPoint = new Point3D(packman.getPoint3D());
		for (int i = 0; i < p.size(); i++) {
			this.packman.setPoint3D(p.get(i));
			this.myFrame.synchronizedPaint();
			try {
				this.sleep(500);
				
			} catch (Exception e) {
				
			}
		}
	//	packman.setPoint3D(packmanPoint);
		
	

	}
}