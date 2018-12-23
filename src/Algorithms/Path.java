package Algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import Coords.MyCoords;
import File_format.TimeRunComperator;
import TheGame.Fruit;
import TheGame.Map;
import TheGame.Packman;

/**
 * this class build the path of every packman to the fruits it eats
 * @author yael hava and naama hartuv
 *
 */

public class Path {

	private ArrayList<Fruit> fruitList;
	private Packman packman;
	private PriorityQueue<nextFruit> packmanPrio; 
	
/**
 * connstructor
 * @param fruitList - the fruit list
 */

	public Path(ArrayList<Fruit> fruitList) {
		this.fruitList= fruitList;
		packmanPrio = new PriorityQueue<nextFruit>(fruitList.size(), new TimeRunComperator());
	}

	/**
	 * constructor
	 * @param p - the packman
	 * @param fruitList- the fruit list
	 */
	
	public Path(Packman p, ArrayList<Fruit> fruitList) {
		this.packman= p;
		this.fruitList= fruitList;
		packmanPrio = new PriorityQueue<nextFruit>(fruitList.size(), new TimeRunComperator());

	}
	
	/**
	 * build the path
	 * @param pTime - the packman time till now
	 */

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

	/**
	 * returns the first object in the Priority Queue
	 * @return - the first object in the Priority Queue
	 */
	
	public nextFruit next() {
		return packmanPrio.peek();
	}
	
	/**
	 * calculates the time between packman and fruit
	 * @param p - packman
	 * @param f- fruit
	 * @return the time
	 */

	public double runTime(Packman p, Fruit f) {
		MyCoords coords = new MyCoords(0, 0, 0);
		double dis = coords.distance3d(p.getPoint3D(), f.getPoint3D());
		double radius = p.getRadius();
		double speed = p.getMoveAbility();
		return (dis - radius) / speed;
	}
	
	/**
	 * getter to the Priority Queue
	 * @return
	 */
	
	public PriorityQueue<nextFruit> getPackmanPrio() {
		return packmanPrio;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}