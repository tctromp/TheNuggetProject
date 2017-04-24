package org.trompgames.nuggetproject;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.trompgames.utils.GameHandler;
import org.trompgames.utils.Vector2;

public class NuggetHandler extends GameHandler{

	private Vector2 imageOffset = new Vector2(0,0);
	
	private NuggetFrame nuggetFrame;
	private NuggetPanel nuggetPanel;
	
	private BufferedImage image;
	private Vector2 imageScale;
	
	public NuggetHandler() {
		super(60, 20, true);		
		
		this.LoadFile(new File("F:/Users/Thomas/Downloads/IMG_20170410_161851.jpg"));
		this.scaleImage(new Vector2(image.getWidth()/4, image.getHeight()/4));
		
		nuggetFrame = new NuggetFrame(this);
		nuggetPanel = nuggetFrame.getNuggetPanel();	
	}
	
	public boolean LoadFile(File file){		
		try{
			image = ImageIO.read(file);
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}	
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public void scaleImage(Vector2 imageScale){
		this.imageScale = imageScale;
	}
	
	public Vector2 getImageScale(){
		return imageScale;
	}
	
	
	public void mouseDragEvent(Vector2 prevLoc, Vector2 currLoc){
		
		Vector2 v = currLoc.sub(prevLoc);
		imageOffset = imageOffset.add(v);
		
		
	}
	
	public void mousePressEvent(Vector2 loc){
		
	}
	
	public void mouseReleaseEvent(Vector2 loc){
		
	}
	
	
	public Vector2 getImageOffset(){
		return imageOffset;
	}

	@Override
	public void update(double deltaTime) {
		if(nuggetPanel == null) return;
		nuggetPanel.repaint();
	}

	@Override
	public void fixedUpdate(double deltaTime) {

	}
	
	
}
