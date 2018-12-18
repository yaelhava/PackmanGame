package Algorithms;


import TheGame.Fruit;

import TheGame.Packman;

public class nextFruit {

	private Packman packman;
	private Fruit fruit;
	private double time;

	
	public nextFruit(Packman packmanp, Fruit fruit, double time) {
		this.packman=packmanp;
		this.fruit=fruit;
		this.time=time;
	}


	public Packman getPackman() {
		return packman;
	}


	public Fruit getFruit() {
		return fruit;
	}


	public double getTime() {
		return time;
	}
	
	
}