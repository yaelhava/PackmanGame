package Algorithms;

import java.util.ArrayList;

import TheGame.Fruit;
import TheGame.Game;
import TheGame.Packman;
import Geom.Point3D;

public class ShortestPathAlgo  {

	private Game game;
	private ArrayList<Point3D> packmanPoints;
	
	
	public ShortestPathAlgo(Game game) {
		this.game = game;
		packmanPoints = new ArrayList<Point3D>();
		for (int i = 0; i < game.getPackmanList().size(); i++) {
			packmanPoints.add(game.getPackmanList().get(i).getPoint3D());
		}
	}

	public void ShortestPath() {
		ArrayList<Fruit> newFruitList = new ArrayList<Fruit>();
	//	int i =0;
		for(Fruit f : game.getFruitList()){
			newFruitList.add(new Fruit(f.getPoint3D()));
		}
//		ArrayList<Packman> newPackmanList = new ArrayList<Packman>();
//		for(Packman p : game.getPackmanList()) {
//			newPackmanList.add(p);
//		}
		while(newFruitList.size() != 0) {
			Path fastestPaths = new Path(newFruitList);
			
			for(Packman p : game.getPackmanList()) {
				Path path = new Path(p, newFruitList);
				path.BuildPath(p.getTime());
				fastestPaths.getPackmanPrio().add(path.next());
			}
			//System.out.println(counter++);
			//set the point of the packman to the point of the fruit
			fastestPaths.next().getPackman().setPoint3D(fastestPaths.next().getFruit().getPoint3D());
			//add to array list of any packman its fruits
			fastestPaths.next().getPackman().getPackmanRoad().add(fastestPaths.next().getFruit());
			//sets the time of the packman
			fastestPaths.next().getPackman().setTime(fastestPaths.next().getPackman().getTime() + 
					fastestPaths.next().getTime());
			//erase the fruit that got eatten from the list
			newFruitList.remove(fastestPaths.next().getFruit());
		//	i++;
		}
		for (int i = 0; i < packmanPoints.size(); i++) {
			game.getPackmanList().get(i).setPoint3D(packmanPoints.get(i));
		}
	}

}