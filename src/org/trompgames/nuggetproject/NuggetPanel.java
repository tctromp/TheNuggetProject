package org.trompgames.nuggetproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.trompgames.utils.Vector2;

public class NuggetPanel extends JPanel{

	private NuggetHandler handler;
	
	public NuggetPanel(NuggetHandler handler) {
		this.handler = handler;
	}
	
	private boolean painting = false;
	private int pixels;
	
	public int getPixels(){
		return pixels;
	}
	
	public boolean isPainting(){
		return painting;
	}
	
	@Override
	public void paintComponent(Graphics gr) {
		
		painting = true;
		
		Graphics2D g2d = (Graphics2D) gr;		
		
		g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		
		BufferedImage image = handler.getImage();
		
		
		
		
		
		Vector2 offset = handler.getImageOffset();
		
		Vector2 imageScale = handler.getImageScale();
		
		//g2d.drawImage(image, (int) offset.getX(), (int) offset.getY(), (int) imageScale.getX(), (int) imageScale.getY(), null);
		
		int xOffset = (int) offset.getX();
		int yOffset = (int) offset.getY();
		
		g2d.drawImage(image, null, xOffset, yOffset);
		
		
		
		
		int x1 = 700; //500
		int y1 = 700; //700
		
		int x2 = x1 + 1900; //1600
		int y2 = y1 + 1300; //1500
		
		g2d.drawRect(x1 + xOffset, y1 + yOffset, x2 - x1, y2 - y1);

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

		double avgR = 136; //136
		double avgG = 87;  //87
		double avgB = 44;  //44
		
		
		double rPercent = .95; //5
		double gPercent = .95; //5
		double bPercent = .95; //1.3
		
		double lowR = avgR - (avgR * rPercent);
		double lowG = avgG - (avgG * gPercent);
		double lowB = avgB - (avgB * bPercent);
		
		double highR = avgR + (avgR * rPercent);
		double highG = avgG + (avgG * gPercent);
		double highB = avgB + (avgB * bPercent);
		
		g2d.setColor(Color.pink);
		
		ArrayList<Color> colors = new ArrayList<Color>();
		int pixels = 0;
		for(int j = y1; j < y2; j++){
			for(int i = x1; i < x2; i++){			

				
				Color c = new Color(image.getRGB(i, j));
				
				if((        c.getRed()   > lowR && c.getRed()   < highR
						&& c.getGreen() > lowG && c.getGreen() < highG
						&& c.getBlue()  > lowB && c.getBlue()  < highB)){
					
					g2d.drawRect(i + xOffset, j + yOffset, 0, 0);
					colors.add(c);
					pixels++;
				}				
			}
		}
		
		//System.out.println("Low R: " + lowR + " G: " + lowG + " B: " + lowB);
		//System.out.println("High R: " + highR + " G: " + highG + " B: " + highB);
		
		System.out.println("Image Index: " + handler.getImageIndex());
		System.out.println("Pixels: " + pixels);
		
		
		if(colors.size() == 0) return;
			
		double rr = colors.get(0).getRed();
		double gg = colors.get(0).getGreen();
		double bb = colors.get(0).getBlue();
		
		
		for(Color c : colors){
			if(c.equals(colors.get(0))) continue;
			
			rr = (rr + c.getRed())/2;
			gg = (gg + c.getGreen())/2;
			bb = (bb + c.getBlue())/2;

			
			
		}
		
		
		
		System.out.println("R: " + rr + " G: " + gg + " B: " + bb);
		
		//System.out.println("Avg R: " + r + " G: " + g + " B: " + b);
		
		
		//g2d.setColor(new Color((int) r, (int) g, (int) b));
		//g2d.fillRect(20, 20, 10, 10);
		
		
		
		//A4 paper size: 210 x 297 mm

		int height = image.getHeight(); //Height of image
		int width = image.getWidth();   //Width of image
		
		g2d.setColor(Color.black);
		
		
		System.out.println("Height: " + height);
		System.out.println("Width: " + width);

		g2d.drawString("Pixels per mm: " + height/210, 5, 15);
		g2d.drawString("Place: " + handler.getFolderName(), 5, 30);
		g2d.drawString("Image #: " + (handler.getImageIndex() + 1), 5, 45);
		g2d.drawString("Pixels: " + pixels, 5, 60);

		StringSelection selection = new StringSelection("" + pixels);
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);
		this.pixels = pixels;
		painting = false;
		
	}
}
