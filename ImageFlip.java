/*
 * File: Image.java
 * ------------------
 */


import acm.program.*;
import acm.graphics.*;

public class Image extends GraphicsProgram {

	private GImage flipHorizontal(GImage image) {
		
		int[][] array = image.getPixelArray();
		int[][] old_array = image.getPixelArray();
		
		int height = array.length;
		int width = array[0].length;
		
		for(int i = 0; i < height; i ++){
			for(int j = 0; j < width; j++){
				
				array[i][j] = old_array[i][width - 1 - j];

			}
			
		}
		
		return new GImage(array);
		
	}
	
	public void run() {
		
		GImage image = new GImage("test.gif");
		GImage flipped_image = flipHorizontal(image);
		
		add(image, 10, 50);
		
		add(flipped_image, 300, 50);
		
	}
	
}
