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
 * this class takes a csv file and inserts every line to an packman or fruit object.
 * @author yael hava and naama hartuv
 */

public class CSV2elements 
{
	private String path;
	private Game game;


	/**
	 * constructor
	 * @param path - the given cvs file's path
	 * @param game - the game
	 */

	public CSV2elements(String path, Game game) {
		this.path = path;
		this.game = new Game(game.getPackmanList(), game.getFruitList());
		toElem(this.game);
	}

	/**
	 * create an array list with elements and add any element to an array list
	 * of packmans or fruits
	 * @param game - the game
	 */

	public void toElem(Game game) {
		CSVreader r = new CSVreader(path);
		ArrayList<String[]> arr = r.CSVReader();

		for(int i=0; i<arr.size(); i++) {
			if(arr.get(i)[0].equals("P")) {
				game.getPackmanList().add(toPackmanElem(arr.get(i)));
			}
			else if(arr.get(i)[0].equals("F")) {
				game.getFruitList().add(toFruitElem(arr.get(i)));
			}
		}
	}

	/**
	 * enters data and coordinate to a created packman.
	 * @param arr - the line
	 * @return p - the packman with the data and the coordinate
	 */

	private Packman toPackmanElem(String arr[]) {
		PackmanMetaData data = new PackmanMetaData(arr);
		Point3D point = new Point3D(arr);
		Packman p = new Packman(point, data.getMoveAbility(),data.getRadius(), data.getID());
		return p;
	}

	/**
	 * enters data and coordinate to a created fruit.
	 * @param arr - the line
	 * @return f - the fruit with the data and the coordinate
	 */
	
	private Fruit toFruitElem(String arr[]) {
		FruitMetaData data = new FruitMetaData(arr);
		Point3D point = new Point3D(arr);
		Fruit f = new Fruit(point, data);
		return f;
	}

	public Game getGame() {
		return game;
	}

	
	

}




