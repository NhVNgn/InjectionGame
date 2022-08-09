package room;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import util.ImageLoader;

public class Machine extends BaseBt{
	
	private boolean on = false;
	public Machine(double xPos, double yPos, double scale, String file) {
		super(xPos, yPos, scale, file);
		// TODO Auto-generated constructor stub
	}
	
	public void setOn(boolean value) {
		this.on = value;
	}
	public boolean isOn() {
		return on;
	}
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		AffineTransform transform = g2.getTransform(); // save(x~y)
		g2.translate(x, y);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		
		
		g2.setTransform(transform);
		
	}

	@Override
	public boolean hit(BaseBt b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setImg(int state) {
		// TODO Auto-generated method stub
		 if (state == 0)
			 img = ImageLoader.loadImage("assets/machine.png");  //closed oven
		 else if (state == 1)
			 img = ImageLoader.loadImage("assets/making.png");   //opened oven
		 else if (state == 2)
			 img = ImageLoader.loadImage("assets/machineOn.png");
	}
	
}
