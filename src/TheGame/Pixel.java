package TheGame;
public class Pixel{
	
		private int leftPixel;
		private int rightPixel;

		
		public Pixel() {
			
		}
		
		public Pixel(int left, int right) {
			this.leftPixel= left;
			this.rightPixel=right;
		}


		public int getLeftPixel() {
			return leftPixel;
		}


		public int getRightPixel() {
			return rightPixel;
		}
		
	}