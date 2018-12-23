package Algorithms;


import TheGame.Fruit;

import TheGame.Packman;

/**
 * keep the packman, the fruit it's gonna eat, and the time between them
 * @author yael hava and naama hartuv
 *
 */

public class nextFruit {

	private Packman packman;
	private Fruit fruit;
	private double time;

	/**
	 * constructor
	 * @param packman - the packman
	 * @param fruit - the fruit
	 * @param time - the time between them
	 */
	
	public nextFruit(Packman packman, Fruit fruit, double time) {
		this.packman=packman;
		this.fruit=fruit;
		this.time=time;
	}

	/**
	 * packman getter
	 * @return - packman
	 */
	
	public Packman getPackman() {
		return packman;
	}

	/**
	 * fruit getter 
	 * @return- fruit
	 */

	public Fruit getFruit() {
		return fruit;
	}

	/**
	 * time getter
	 * @return - time between them
	 */
	
	public double getTime() {
		return time;
	}
	
	
}