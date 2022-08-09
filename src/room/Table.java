package room;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Table extends BaseBt{

	public Table(double xPos, double yPos, double scale, String file) {
		super(xPos, yPos, scale, file);
		orgW = -img.getWidth()/2;
		orgH = -img.getHeight()/2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		AffineTransform transform = g2.getTransform();
		g2.translate(x, y);
		g2.scale(scale, scale);
	
		g2.drawImage(img, (int) orgW, (int) orgH, null);
	
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

}
