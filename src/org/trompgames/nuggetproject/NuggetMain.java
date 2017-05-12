package org.trompgames.nuggetproject;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.trompgames.utils.Vector2;

public class NuggetMain {

	
	public static void main(String[] args) {
		
		NuggetHandler handler = new NuggetHandler();
		handler.setImageOffset(new Vector2(-738, -560)); //-1121, -832
		
		//String s = handler.getAllPixels();
		//System.out.println(s);
		
		//new File("F:/Users/Thomas/Downloads/IMG_20170410_161851.jpg");
		
		
		/*
		File file = new File("C:/Users/Thomas/Desktop/Nuggets");
		
		for(File folder : file.listFiles()){
			System.out.println(folder.getName());
			
			for(File img : folder.listFiles()){
				System.out.println(getPixles(img));
			}
			
		}
		*/
	}
	
	public static int getPixles(File file){
		BufferedImage image = null;
		try{
			image = ImageIO.read(file);
		}catch(IOException e){
			e.printStackTrace();
		}	
		
		//System.out.println(image.getHeight());
		//System.out.println(image.getWidth());
		int x1 = 700;
		int y1 = 300;
		
		int x2 = x1 + 2200;
		int y2 = y1 + 1700;
		
		if(x2 > image.getWidth()) x2 = image.getWidth();
		if(y2 > image.getHeight()) y2 = image.getHeight();
		
		
		
		Color color = new Color(image.getRGB(x1, y1));
		double r = color.getRed();
		double g = color.getGreen();
		double b = color.getBlue();
		
		for(int j = y1 + 1; j < y2; j++){
			for(int i = x1; i < x2; i++){
				Color c = new Color(image.getRGB(i, j));
				r = (r + c.getRed())/2;
				g = (g + c.getGreen())/2;
				b = (b + c.getBlue())/2;
			}
		}
		//115, 51, 13

		double avgR = 136;
		double avgG = 87;
		double avgB = 44;
		
		
		double rPercent = 5;
		double gPercent = 5;
		double bPercent = 1.3;
		
		double lowR = r - (avgR * rPercent);
		double lowG = g - (avgG * gPercent);
		double lowB = b - (avgB * bPercent);
		
		double highR = r + (avgR * rPercent);
		double highG = g + (avgG * gPercent);
		double highB = b + (avgB * bPercent);
		
		
		ArrayList<Color> colors = new ArrayList<Color>();
		int pixels = 0;
		for(int j = y1; j < y2; j++){
			for(int i = x1; i < x2; i++){			

				
				Color c = new Color(image.getRGB(i, j));
				
				if(!(        c.getRed()   > lowR && c.getRed()   < highR
						&& c.getGreen() > lowG && c.getGreen() < highG
						&& c.getBlue()  > lowB && c.getBlue()  < highB)){
					
					colors.add(c);
					pixels++;
				}				
			}
		}
			
		//System.out.println("Avg R: " + r + " G: " + g + " B: " + b);
		
		//System.out.println("Low R: " + lowR + " G: " + lowG + " B: " + lowB);
		//System.out.println("High R: " + highR + " G: " + highG + " B: " + highB);

		//System.out.println("Pixels: " + pixels);
		return pixels;
	}
	
}
