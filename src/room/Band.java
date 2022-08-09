package room;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Band extends BaseBt{

	public Band(double xPos, double yPos, double scale, String file) {
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
