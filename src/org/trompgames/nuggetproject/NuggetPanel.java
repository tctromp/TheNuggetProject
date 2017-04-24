package org.trompgames.nuggetproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.trompgames.utils.Vector2;

public class NuggetPanel extends JPanel{

	private NuggetHandler handler;
	
	public NuggetPanel(NuggetHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public void paintComponent(Graphics gr) {
		
		Graphics2D g2d = (Graphics2D) gr;		
		
		g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		
		BufferedImage image = handler.getImage();
		
		
		
		
		
		Vector2 offset = handler.getImageOffset();
		
		Vector2 imageScale = handler.getImageScale();
		
		//g2d.drawImage(image, (int) offset.getX(), (int) offset.getY(), (int) imageScale.getX(), (int) imageScale.getY(), null);
		
		int xOffset = (int) offset.getX();
		int yOffset = (int) offset.getY();
		
		g2d.drawImage(image, null, xOffset, yOffset);
		
		
		
		
		int x1 = 700;
		int y1 = 300;
		
		int x2 = x1 + 2200;
		int y2 = y1 + 1700;
		
		g2d.drawRect(x1 + xOffset, y1 + yOffset, x2 - x1, y2 - y1);

		
		
		
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
		
		System.out.println("Avg R: " + r + " G: " + g + " B: " + b);
		
		
		//g2d.setColor(new Color((int) r, (int) g, (int) b));
		//g2d.fillRect(20, 20, 10, 10);
		
		
		
		//A4 paper size: 210 x 297 mm

		int height = image.getHeight(); //Height of image
		int width = image.getWidth();   //Width of image
		
		g2d.drawString("Pixels per mm: " + height/210, 5, 15);
		
		
		
		
		
	}
}
