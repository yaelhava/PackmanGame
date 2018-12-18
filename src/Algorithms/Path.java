package Algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import File_format.TimeRunComperator;
import TheGame.Fruit;
import TheGame.Map;
import TheGame.Packman;

public class Path {

	private ArrayList<Fruit> fruitList;
	private Packman packman;
	private PriorityQueue<nextFruit> packmanPrio; 
	private Map map;


	public Path() {
		packmanPrio = new PriorityQueue<nextFruit>(fruitList.size(), new TimeRunComperator());
	}

	public Path(Packman p, ArrayList<Fruit> fruitList) {
		this.packman= p;
		this.fruitList= fruitList;
		packmanPrio = new PriorityQueue<nextFruit>(fruitList.size(), new TimeRunComperator());


	}

	public void BuildPath(double pTime) {
		double time;
		for (Fruit F : fruitList) {
			if(pTime == 0) {
			time = runTime(packman, F);
			}
			else {
				time = pTime + runTime(packman, F);
			}
			nextFruit next = new nextFruit(packman, F, time);
			packmanPrio.add(next); 
		}

	}

	public nextFruit next() {
		return packmanPrio.poll();
	}

	public double runTime(Packman p, Fruit f) {
		double dis= map.PixelDistance(p.getPoint3D(), f.getPoint3D());
		double radius= p.getPackmanData().getRadius();
		double speed= p.getPackmanData().getMoveAbility();

		return (dis-radius)/speed;
	}
	
	public PriorityQueue<nextFruit> getPackmanPrio() {
		return packmanPrio;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}