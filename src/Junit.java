import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import Geom.Point3D;
import TheGame.Map;

class Junit {

	@Test
	void GPS2Pixeltest() {
		Point3D p1= new Point3D(35.212418,32.105765);
		Point3D ans= new Point3D(1433,0);
		Map m= new Map("Ariel1.png");
		Point3D p2=m.GPS2Pixel(p1, 1433, 642);
		System.out.println(p2);
		if(p2.x()!=446708.1062008713||p2.y()!=-512585.76869753737) {
			fail("the GPS2Pixel function dosent work well");
		}
		
	}

}
