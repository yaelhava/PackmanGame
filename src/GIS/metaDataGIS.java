package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import Geom.Point3D;

/**
 * this class represents the data of an element.
 * @author yael hava and naama hartuv.
 */

public class metaDataGIS implements Meta_data{

	private String MAC, SSID, AuthMode, FirstSeen, Channel, RSSI,AccuracyMeters ,Type ;

	/**
	 * constructor
	 * @param s - a string[] which is the element
	 */
	
	public metaDataGIS (String[]s) {
		MAC = s[0];
		SSID = s[1];
		AuthMode = s[2];
		FirstSeen = s[3];
		Channel = s[4];
		RSSI = s[5];
		Type = s[10];
		AccuracyMeters = s[9];
	}

	/**@return - getMAC*/

	public String getMAC() {
		return MAC;
	}

	/**@return - getSSID*/
	
	public String getSSID() {
		return SSID;
	}

	/**@return - getAuthMode*/

	public String getAuthMode() {
		return AuthMode;
	}

	/**@return - getFirstSeen*/

	public String getFirstSeen() {
		return FirstSeen;
	}

	/**@return - getChannel*/

	public String getChannel() {
		return Channel;
	}

	/**@return - getRSSI*/

	public String getRSSI() {
		return RSSI;
	}

	/**@return - getAccuracyMeters*/

	public String getAccuracyMeters() {
		return AccuracyMeters;
	}

	/**@return - getType*/

	public String getType() {
		return Type;
	}

	/**
	 * add a symbol for date and time
	 * @param firstSeen - the date and time
	 * @return - the date and time with the symbols
	 */
	
	public String FirstSeen(String firstSeen) {
		return firstSeen.substring(0, 10) + "T" + firstSeen.substring(11) + "Z";
	}

	/**
	 *  returns the Universal Time Clock associated with this data
	 *  @return the real time
	 */

	@Override
	public long getUTC() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		format.setTimeZone(TimeZone.getTimeZone("UTC"));

		Date date = null;
		try {
			date = format.parse(this.getFirstSeen());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long millis = date.getTime();

		return millis;
	}

	/**
	 *  return a String representing this data
	 */

	public String toString() {
		return ("MAC:" + MAC + " , SSID: " + SSID +
				" AuthMode: " + AuthMode + "Channel: " + Channel+ "RSSI: " + RSSI
				+ "Type: " + Type);
	}


	@Override
	public Point3D get_Orientation() {
		return null;
	}


}
