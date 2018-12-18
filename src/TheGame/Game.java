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

	
	public ArrayList<Fruit> getFruitList() {
		return fruitList;
	}


	public ArrayList<Packman> getPackmanList() {
		return packmanList;
	}


	

	public Game() {
		fruitList = new ArrayList<Fruit>();
		packmanList = new ArrayList<Packman>();
	}

	
	public Game(String path) {
		c = new CSV2elements(path);
	}
	
	
	public void add(Object o) {
		if(o == packman) {
			packmanList.add((Packman)o);
		}
		else if(o == fruit) {
			fruitList.add((Fruit)o);
		}
	}


//	public void exportCsvFile(String fileName, PrintWriter pw) {
//        csvWriter.writeCSV(fileName, pw, getPackmanList(), getFruitList());
//
//	}




}
