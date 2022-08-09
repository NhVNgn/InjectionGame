package patientdec;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import room.BaseBt;

public class Arm extends BaseBt implements ArmInterface{

	public Arm(double xPos, double yPos, double scale, String file) {
		super(xPos, yPos, scale, file);
		// TODO Auto-generated constructor stub
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
		return false;
		// TODO Auto-generated method stub
	
	}

	@Override
	public void setImg(int state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decorate(Graphics2D g2) {
		// TODO Auto-generated method stub
		AffineTransform transform = g2.getTransform(); // save(x~y)
		g2.translate(x, y);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		
		
		g2.setTransform(transform);
	}

}

