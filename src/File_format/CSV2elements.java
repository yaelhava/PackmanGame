package File_format;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.text.html.HTMLDocument.Iterator;

import Geom.Point3D;
import TheGame.Fruit;
import TheGame.FruitMetaData;
import TheGame.Game;
import TheGame.Packman;
import TheGame.PackmanMetaData;

/**
 * this class takes a csv file and inserts every line to an element object.
 * @author yael hava and naama hartuv
 */

public class CSV2elements 
{
	private String path;
	private Game game;

	/**
	 * constructor
	 * @param path - the given cvs file's path
	 */

	public CSV2elements(String path) {
		this.path = path;
		game = new Game();
		System.out.println("hii");
		toElem();
	}

	/**
	 * create an array list with elements and add any element to a layer.
	 */

	private void toElem() {
		CSVreader r = new CSVreader(path);
		ArrayList<String[]> arr = r.CSVReader();

		for(int i=1; i<arr.size(); i++) {
			if(arr.get(i)[0].equals("P")) {
				game.add(toPackmanElem(arr.get(i)));
			}
			else if(arr.get(i)[0].equals("F")) {
				game.add(toFruitElem(arr.get(i)));
			}
		}
	}

	/**
	 * enters data and coordinate to a created element.
	 * @param arr - the line
	 * @return e - the element with the data and the coordinate
	 */

	private Packman toPackmanElem(String arr[]) {
		System.out.println("hiipacmkan");

		PackmanMetaData data = new PackmanMetaData(arr);
		Point3D point = new Point3D(arr);
		Packman p = new Packman(point, data);
		return p;
	}

	private Fruit toFruitElem(String arr[]) {
		System.out.println("hiifruit");

		FruitMetaData data = new FruitMetaData(arr);
		Point3D point = new Point3D(arr);
		Fruit f = new Fruit(point, data);
		return f;
	}

	/**
	 * getter for the layer created at this class
	 * @return l - the layer
	 */

	public Game getGame() {
		return game;
	}

}




