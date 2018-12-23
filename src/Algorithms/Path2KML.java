package Algorithms;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

import Geom.Point3D;
import TheGame.Fruit;
import TheGame.FruitMetaData;
import TheGame.Game;
import TheGame.Packman;
import TheGame.PackmanMetaData;

/**
 * his class export a kml file that consist of the elements in the game
 * @author yael hava and naama hartuv
 *
 */

public class Path2KML {


	private StringBuilder content;
	private Path path;
	private Game game;

	/**
	 * constructor
	 * @param game - the game
	 */

	public Path2KML(Game game) {
		this.game = game;
	}

	/**
	 * makes the CSV file
	 * @return stringBuilder consist of the the elements
	 */

	public StringBuilder exportKmlFile() {

		content = new StringBuilder();

		String kmlStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"+"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"+
				"<Document>\n"+"<Style id=\"yellow\"><IconStyle><Icon><href>"
				+"http://maps.google.com/mapfiles/kml/paddle/ylw-stars.png</href>"
				+"</Icon></IconStyle></Style><Style id=\"red\"><IconStyle><Icon><href>"
				+"http://maps.google.com/mapfiles/kml/paddle/red-stars.png</href>"
				+ "</Icon></IconStyle></Style><Folder>";


		content.append(kmlStart);

		for(Packman p : game.getPackmanList()) {
			long startTime = timeStamp();
			String kmlPackman =	"<Placemark>\n" + 
					"Description\n" +
					"<type>" + "packman" + "</type>\n" +
					"<ID>" + p.getID() +  "</ID>\n" + 
					"<speed>" + p.getMoveAbility() + "</speed>" + 
					"/Description\n" + 
					"<TimeStamp>\n" +
					" <when> " + getTimeStamp(startTime) + " </when>\n" +
					"</TimeStamp>\n" +
					"<styleUrl>#yellow</styleUrl>\n" + 
					"<Point>\n" + 
					"<coordinates>" + new Point3D(p.getPoint3D().y(), p.getPoint3D().x()) + "</coordinates>\n" +
					"</Point>\n" +
					"</Placemark>";
			content.append(kmlPackman);
			
			for (int i = 1; i < p.getPackmanRoad().size(); i++) {
		
				String kmlFruit = "<Placemark>\n" + 
						"Description\n" +
						"<type>" + "fruit" + "</type>\n" +
						"<ID>" + p.getPackmanRoad().get(i).getID() +  "</ID>\n" + 
						"<weight>" + p.getPackmanRoad().get(i).getWeight() + "</weight>" + 
						"/Description\n" + 
						"<TimeStamp>\n" +
						" <when> " + 
						getTimeStamp(startTime) +
						" </when>\n" +
						"</TimeStamp>\n" +
						"<styleUrl>#red</styleUrl>\n" + 
						"<Point>\n" + 
						"<coordinates>"  + new Point3D(p.getPackmanRoad().get(i).getPoint3D().y(), p.getPoint3D().x()) + "</coordinates>\n" +
						"</Point>\n" +
						"</Placemark>\n";

				content.append(kmlFruit);
			}
		}

		String kmlGameEnd = "</Folder>" + 
				"</Document>\n" + 
				"</kml>"; 
		content.append(kmlGameEnd);

		return content;

	}


	/**
	 * export a KML file
	 */

	public void export() {
		StringBuilder s = exportKmlFile(); 
		try {
			PrintWriter export = new PrintWriter(new File("PackmanLive.kml"));
			export.write(s.toString());
			export.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return - the current time
	 */

	public long timeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Instant instant = timestamp.toInstant();
		long l = instant.toEpochMilli();
		return l;

	}

	/**
	 * chanes the time to simple date format
	 * @param timeStamp - the time in UTC
	 * @return the time in simple date format
	 */

	public String getTimeStamp(long timeStamp){
		java.util.Date yourDate = new java.util.Date(timeStamp); 
		SimpleDateFormat yyyyMMddTHHmmssSDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String date = yyyyMMddTHHmmssSDF.format(yourDate);
		return date;
	}





}
