package Algorithms;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

import File_format.Csv2kml;
import TheGame.Fruit;
import TheGame.FruitMetaData;
import TheGame.Game;
import TheGame.Packman;
import TheGame.PackmanMetaData;

public class Path2KML {

	//	private Game game;
	private Packman packman;
	private Fruit fruit;
	private StringBuilder content;
	private Path path;
	private Game game;

	public Path2KML(Game game) {
		this.game = game;
	}
	

	public StringBuilder exportKmlFile() {

		content = new StringBuilder();

		String kmlStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"+"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"+
				"<Document>\n"+"<Style id=\"yellow\"><IconStyle><Icon><href>"
				+"http://maps.google.com/mapfiles/kml/paddle/ylw-stars.png</href>"
				+"</Icon></IconStyle></Style><Style id=\"red\"><IconStyle><Icon><href>"
				+"http://maps.google.com/mapfiles/kml/paddle/red-stars.png</href>"
				+ "</Icon></IconStyle></Style>";

//		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"); 
//		sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		content.append(kmlStart);

		String kmlGameStart = "<Document\n" ;///haim zarich?
				

		content.append(kmlGameStart);
		for(Packman p : game.getPackmanList()) {
			long startTime = timeStamp();
			String kmlPackman = 
					//"<Placemark>\n" + 
					"Description\n" +
					"<type>" + "packman" + "</type>\n" +
					"<ID>" + p.getID() +  "</ID>\n" + 
					"<speed>" + p.getMoveAbility() + "</speed>" + 
					"/Description\n" + 
					"<TimeStamp>\n" +
					" <when> " + getTimeStemp(startTime) + " </when>\n" +
					"</TimeStamp>\n" +
					"<styleUrl>#yellow</styleUrl>\n" + 
					"<Point>\n" + 
					"<coordinates>" + p.getPoint3D() + "</coordinates>\n" +
					"</Point>\n" ;
				//	"</Placemark>";
			content.append(kmlPackman);

			for (int i = 1; i < p.getPackmanRoad().size(); i++) {

				//long fruitTime = startTime + (int)(path.runTime(p, p.getPackmanRoad().get(i)) * 1000);
				String kmlFruit = "<Placemark>\n" + 
						"Description\n" +
						"<type>" + "fruit" + "</type>\n" +
						"<ID>" + p.getPackmanRoad().get(i).getID() +  "</ID>\n" + 
						"<weight>" + p.getPackmanRoad().get(i).getWeight() + "</weight>" + 
						"/Description\n" + 
						"<TimeStamp>\n" +
//						" <when> " + 
//						getTimeStemp(fruitTime) +
//						" </when>\n" +
						"</TimeStamp>\n" +
						"<styleUrl>#red</styleUrl>\n" + 
						"<Point>\n" + 
						"<coordinates>"  + p.getPackmanRoad().get(i).getPoint3D() + "</coordinates>\n" +
						"</Point>\n" +
						"</Placemark>\n";

				content.append(kmlFruit);
			}

		}
		
		String kmlGameEnd = "</Document>\n" + 
				"</kml>"; 
		content.append(kmlGameEnd);

		return content;

	}



	public void export() {
		//	csv2kml = new Csv2kml();
		//	Path2KML p2 = new Path2KML();
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

	public long timeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Instant instant = timestamp.toInstant();
		long l = instant.toEpochMilli();
		return l;

	}

	public String getTimeStemp(long timelStamp){
		java.util.Date yourDate = new java.util.Date(timelStamp); //ms
		SimpleDateFormat yyyyMMddTHHmmssSDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String date = yyyyMMddTHHmmssSDF.format(yourDate);
		return date;
	}





}
