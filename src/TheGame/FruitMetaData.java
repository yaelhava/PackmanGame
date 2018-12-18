package TheGame;

public class FruitMetaData {

	private double weight;
	private int ID;
	
	public FruitMetaData(String[] s) {
		weight = Double.parseDouble(s[5]);
		ID = Integer.parseInt(s[1]);
	}
	
	public double getWeight() {
		return weight;
	}

	public int getID() {
		return ID;
	}
	
	
	
}
