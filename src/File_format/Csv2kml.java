package File_format;


import java.util.Iterator;
import Algorithms.MultiCSV;
import GIS.ElementGIS;
import GIS.LayerGIS;
import GIS.ProjectGIS;
import GIS.metaDataGIS;
import Geom.Point3D;

	/**
	 * this class changes the format file from csv to kml.
	 * @author yael hava and naama hartuv
	 */

public class Csv2kml {

	private ElementGIS element;
	private LayerGIS layer;
	private StringBuilder content;

	/**
	 * changes the format file to kml.
	 * @param project - the project consist of layers.
	 * @return - the kml file as a string 
	 */
	
	public StringBuilder exportKmlFile(ProjectGIS project) {
		content = new StringBuilder();
		
		String kmlProjectStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
				"<Document>\n" +
				"<Style id=\"red\"><IconStyle>\r\n" + 
				"<Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>\r\n" + 
				"</Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle>\r\n" + 
				"<Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href>\r\n" +
				"</Icon></IconStyle></Style><Style id=\"green\"><IconStyle>\r\n" +
				"<Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href>\r\n" +
				"</Icon></IconStyle></Style>\n";

		content.append(kmlProjectStart);
		
		Iterator itLayer = project.iterator();
		while(itLayer.hasNext()) {
			String kmlLayerStart = "<Folder>\n";
			content.append(kmlLayerStart);
			layer = (LayerGIS) itLayer.next();
			Iterator itElement = layer.iterator();
			while(itElement.hasNext()) {
				element = (ElementGIS) itElement.next();
				metaDataGIS metaDataElement = (metaDataGIS) element.getData();
				Point3D geom = (Point3D) element.getGeom();
				
				String kmlElement = "<Placemark>\n" +
						"<name>" + metaDataElement.getSSID() +"</name>\n" +
						"<description><![CDATA[" + 
						"" + "Type: " + metaDataElement.getType() + "\n" +
						"Accuracy Meters: " + metaDataElement.getAccuracyMeters()+
						"\n" + "Time: " +  metaDataElement.getFirstSeen() + "\n" +
						"AuthMode: " + metaDataElement.getAuthMode() + "\n" + 
						"Channel: " + metaDataElement.getChannel() + "\n" +
						"]]></description>\n" +
						"<Point>\n" +
						"<coordinates>" + geom.y() + ", " + geom.x() + "</coordinates>" +
						"</Point>\n" +
						"<TimeStamp>\n" + 
						"<when>" + metaDataElement.FirstSeen(metaDataElement.getFirstSeen()) + "</when>" + 
						"</TimeStamp>\n" +
						"</Placemark>\n";
				content.append(kmlElement);
			}
			String kmlLayerEnd = "</Folder>\n";
			content.append(kmlLayerEnd);
		}

		String kmlProjectEnd = "</Document>\n" + 
							   "</kml>"; 
		content.append(kmlProjectEnd);
		return content;
	}
}
