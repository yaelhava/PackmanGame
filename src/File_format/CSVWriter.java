package File_format;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import TheGame.Fruit;
import TheGame.Game;
import TheGame.Packman;

public class CSVWriter {

	Game game;
	int id = 0;

	public CSVWriter(Game game) {
		this.game = game;
	}


	public void writeCSV(String fileName, PrintWriter pw) {
		//		try {
		//			pw = new PrintWriter(new File(fileName));
		//		} 
		//		catch (FileNotFoundException e) {
		//			e.printStackTrace();
		//			return;
		//		}

		StringBuilder sb = new StringBuilder();
		sb.append("Type");
		sb.append(',');
		sb.append("id");
		sb.append(",");
		sb.append("Lat");
		sb.append(",");
		sb.append("Lon");
		sb.append(",");
		sb.append("Alt");
		sb.append(",");
		sb.append("Speed/Weight");
		sb.append(",");
		sb.append("Radius");
		sb.append('\n');



		packman2String(game.getPackmanList(),  sb);
		fruit2String(game.getFruitList(), sb);

		//System.out.println("what i wanted" + packmanList.get(0));

		//		for(int i = 0; i < packmanList.size(); i++) {
		//			System.out.println("point is: " + packmanList.get(i).getPoint3D().x());
		//			readFromString(e2CSV.packman2String(packmanList), sb);

		//	}
		//		for(int i = 0; i < fruitList.size(); i++) {
		//			readFromString(e2CSV.fruit2String(fruitList), sb);
		//}
		//	System.out.println(sb.toString());

		pw.write(sb.toString());
		pw.close();
	}



//	public StringBuilder readFromString(String[] s, StringBuilder sb) {
//		for(int i = 0; i < s.length; i++) {
//			sb.append(s[i]);
//			if(i != s.length - 1) {
//				sb.append(",");
//			}
//		}
//		sb.append('\n');
//		return sb;
//
//	}

	public StringBuilder packman2String(ArrayList<Packman> list, StringBuilder sb) {
		for(Packman p : list) {
			sb.append("P,");
			sb.append("" + id + ",");
			sb.append("" + p.getPoint3D().x() + ",");
			sb.append("" + p.getPoint3D().y() + ",");
			sb.append("" + 0 + ",");
			sb.append("" + 1 + ",");
			sb.append("" + 1);
			sb.append('\n');
			id++;
		}

		return sb;
	}


	public StringBuilder fruit2String(ArrayList<Fruit> list, StringBuilder sb) {
		for(Fruit f : list) {
			sb.append("F,");
			sb.append("" + id + ",");
			sb.append("" + f.getPoint3D().x() + ",");
			sb.append("" + f.getPoint3D().y() + ",");
			sb.append("" + 0 + ",");
			sb.append("" + 1);
			sb.append('\n');
			id++;
		}

		return sb;
	}



}




