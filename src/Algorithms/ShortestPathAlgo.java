package Algorithms;

import java.util.ArrayList;

import TheGame.Fruit;
import TheGame.Game;
import TheGame.Packman;
import Geom.Point3D;

public class ShortestPathAlgo  {

	private Game game;

	public ShortestPathAlgo(Game game) {
		this.game = game;
	}

	public void ShortestPath() {
		while(game.getFruitList() != null) {
			Path fastestPaths = new Path();

			for(Packman p : game.getPackmanList()) {
				Path path = new Path(p, game.getFruitList());
				path.BuildPath(p.getTime());
				fastestPaths.getPackmanPrio().add(path.next());
			}
			//set the point of the packman to the point of the fruit
			fastestPaths.next().getPackman().setPoint3D(fastestPaths.next().getFruit().getPoint3D());
			//add to array list of any packman its fruits
			fastestPaths.next().getPackman().getPackmanRoad().add(fastestPaths.next().getFruit());
			//sets the time of the packman
			fastestPaths.next().getPackman().setTime(fastestPaths.next().getPackman().getTime() + 
					fastestPaths.next().getTime());
			//erase the fruit that got eatten from the list
			game.getFruitList().remove(fastestPaths.next().getFruit());	
		}
	}




}