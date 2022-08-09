package patientdec;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import room.BaseBt;

public class BandDecor  extends ArmDecorator {
	
	public BandDecor(ArmInterface patient ,double xPos, double yPos, double scale, String file) {
		super(patient ,xPos, yPos, scale, file);
		// TODO Auto-generated constructor stub
	}
	
	public void decorate(Graphics2D g2) {
		super.decorate(g2);
		decorateWithSyringe(g2);
		
	}
	
	
	private void decorateWithSyringe(Graphics2D g2) {
		// TODO Auto-generated method stub
		AffineTransform at = g2.getTransform();
//		System.out.println("swap-called");
		g2.translate(x, y);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(at);
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		
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
		
	}
	
}