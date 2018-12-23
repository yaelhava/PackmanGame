package TheGame;

import java.io.PrintWriter;
import java.util.ArrayList;
import File_format.CSV2elements;
import File_format.CSVWriter;


public class Game {

	private ArrayList<Fruit> fruitList;
	private ArrayList<Packman> packmanList;
	private Packman packman;
	private Fruit fruit;
	private CSVWriter csvWriter;
	CSV2elements c;


	public Game() {
		fruitList = new ArrayList<Fruit>();
		packmanList = new ArrayList<Packman>();
	}

	public Game(ArrayList<Packman> packmanList, ArrayList<Fruit> fruitList) {
		this.fruitList = fruitList;
		this.packmanList = packmanList;
	}
	
	
	public void add(Object o) {
		if(o == packman) {
			packmanList.add((Packman)o);
		}
		else if(o == fruit) {
			fruitList.add((Fruit)o);
		}
	}

	public void setFruitList(ArrayList<Fruit> fruitList) {
		this.fruitList = fruitList;
	}


	public void setPackmanList(ArrayList<Packman> packmanList) {
		this.packmanList = packmanList;
	}


	public ArrayList<Fruit> getFruitList() {
		return fruitList;
	}


	public ArrayList<Packman> getPackmanList() {
		return packmanList;
	}


}
