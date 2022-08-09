package room;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class Syringe extends BaseBt{
	
	private boolean flip = false;
	public Syringe(double xPos, double yPos, double scale, String file) {
		super(xPos, yPos, scale, file);
		orgW = -img.getWidth()/2;
		orgH = -img.getHeight()/2;
		// TODO Auto-generated constructor stub
	}

	public void setFlip(boolean value) {
		this.flip = value;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		AffineTransform transform = g2.getTransform();
		g2.translate(x, y);
		if (flip == true) {
			g2.scale(-scale, -scale);
		}else {
			g2.scale(scale, scale);
		}
	
		g2.drawImage(img, (int) orgW, (int) orgH, null);
	
		g2.setTransform(transform);
	}

	@Override
	public boolean hit(BaseBt b) {
		// TODO Auto-generated method stub
		boolean hit = false;
		if (Math.abs(b.getPosX()-x) < 30 && Math.abs(b.getPosY() - y) < 80) 
			hit = true;
		return hit;
	}
	
	@Override
	public void setImg(int state) {
		// TODO Auto-generated method stub
		if (state == 0)
			 img = ImageLoader.loadImage("assets/syringe.png");  //closed oven
		 else if (state == 1)
			 img = ImageLoader.loadImage("assets/syringe1.png");   //opened oven
		
		
	}

}
