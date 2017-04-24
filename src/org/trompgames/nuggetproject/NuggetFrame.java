package org.trompgames.nuggetproject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import org.trompgames.utils.Vector2;

public class NuggetFrame extends JFrame{

	NuggetHandler handler;
	
	private NuggetPanel nuggetPanel;	
	
	public NuggetFrame(NuggetHandler handler){
		
		this.handler = handler;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();		
		
		this.setSize((int) d.getWidth()/2, (int) d.getHeight()/2);
		this.setLocation((int) d.getWidth()/4, (int) d.getHeight()/4);
	
		
		nuggetPanel = new NuggetPanel(handler);
		this.add(nuggetPanel);		
		
		
		
		addMouseDragListener();
		addMouseListener();
		
		
		this.setVisible(true);			
	}
	
	public void addMouseListener(){
		
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent event) {
				//System.out.println("Click");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent event) {
				mousePressed = true;
				
			}

			@Override
			public void mouseReleased(MouseEvent event) {
				mousePressed = false;
				lastMouseDragLocation = null;
			}		
			
		});	
		
	}
	
	private boolean mousePressed = false;
	
	private Vector2 lastMouseDragLocation;
	
	public void addMouseDragListener(){
		
		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent event) {
				
				if(lastMouseDragLocation == null){
					lastMouseDragLocation = new Vector2(event.getX(), event.getY());
					
					return;
				}
				
				handler.mouseDragEvent(lastMouseDragLocation, new Vector2(event.getX(), event.getY()));				
				lastMouseDragLocation = new Vector2(event.getX(), event.getY());
			}

			@Override
			public void mouseMoved(MouseEvent event) {
				
			}			
		});
		
		
		
	}
	
	public boolean isMousePressed(){
		return mousePressed;
	}
	
	
	
	public NuggetPanel getNuggetPanel(){
		return nuggetPanel;
	}
	
	
	
}
