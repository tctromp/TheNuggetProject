package org.trompgames.nuggetproject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.trompgames.utils.GameHandler;
import org.trompgames.utils.Vector2;

public class NuggetHandler extends GameHandler{

	private Vector2 imageOffset = new Vector2(0,0);
	
	private NuggetFrame nuggetFrame;
	private NuggetPanel nuggetPanel;
	
	private BufferedImage image;
	private Vector2 imageScale;
	
	private File folder;
	private ArrayList<File> images = new ArrayList<File>();
	private int imageIndex = 0;
	
	public String getFolderName(){
		return folder.getName();
	}
	
	public int getImageIndex(){
		return imageIndex;
	}
	
	public String getAllPixels(){
		
		String s = "";
		
		int i = 0;
		int last = -101010;
		while(i < images.size() + 1){
			
			if(!nuggetPanel.isPainting() && nuggetPanel.getPixels() != last){
			
				s += nuggetPanel.getPixels() + "\n";
				nextPicture();
				i++;
				System.out.println(i);
			}		
			System.out.println(i);
		}
		
		return s;
	}
	
	public NuggetHandler() {
		super(60, 20, true);		
		
		folder = new File("C:\\Users\\Thomas\\Desktop\\Nuggets\\Monterey");
		
		
		for(File file : folder.listFiles()){
			images.add(file);
		}
		
		this.LoadFile(images.get(0));
		//this.scaleImage(new Vector2(image.getWidth()/4, image.getHeight()/4));
		
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
	
	public void nextPicture(){
		imageIndex++;
		if(imageIndex >= images.size()) imageIndex = images.size()-1;
				
		this.LoadFile(images.get(imageIndex));
		
		nuggetFrame.repaint();
		
	}
	
	public void prevPicture(){
		imageIndex--;
		if(imageIndex < 0 ) imageIndex = 0;
				
		this.LoadFile(images.get(imageIndex));
		
		nuggetFrame.repaint();
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
		nuggetPanel.repaint();

		
	}
	
	public void mousePressEvent(Vector2 loc){
		
	}
	
	public void mouseReleaseEvent(Vector2 loc){
		
	}
	
	
	public Vector2 getImageOffset(){
		return imageOffset;
	}
	
	public void setImageOffset(Vector2 v){
		this.imageOffset = v;
	}

	@Override
	public void update(double deltaTime) {
		if(nuggetPanel == null) return;
		//nuggetPanel.repaint();
	}

	@Override
	public void fixedUpdate(double deltaTime) {

	}
	
	
}
