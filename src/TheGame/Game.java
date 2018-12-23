package TheGame;

import java.io.PrintWriter;
import java.util.ArrayList;
import File_format.CSV2elements;
import File_format.CSVWriter;

/**
 * keeps a collection of fruits and packmans
 * @author yael hava and naama hartuv
 *
 */

public class Game {

	private ArrayList<Fruit> fruitList;
	private ArrayList<Packman> packmanList;
	private Packman packman;
	private Fruit fruit;

	/**
	 * constructor
	 */

	public Game() {
		fruitList = new ArrayList<Fruit>();
		packmanList = new ArrayList<Packman>();
	}

	/**
	 * constructor
	 * @param packmanList
	 * @param fruitList
	 */
	
	public Game(ArrayList<Packman> packmanList, ArrayList<Fruit> fruitList) {
		this.fruitList = fruitList;
		this.packmanList = packmanList;
	}
	
	/**
	 * adds fruits and packmans to their lists
	 * @param o
	 */
	
	public void add(Object o) {
		if(o == packman) {
			packmanList.add((Packman)o);
		}
		else if(o == fruit) {
			fruitList.add((Fruit)o);
		}
	}


	public ArrayList<Fruit> getFruitList() {
		return fruitList;
	}


	public ArrayList<Packman> getPackmanList() {
		return packmanList;
	}


}
