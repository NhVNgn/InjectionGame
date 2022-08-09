package room;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import main.InjectPanel;

public class Room extends BaseBt {

	public Room(double x, double y, double scale, String file) {
		super(x, y, scale, file);
		
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2){
		AffineTransform at = g2.getTransform();
		g2.translate(-90, 0);
		g2.scale(1.12, 1);
		g2.drawImage(img, 0, 0, InjectPanel.initW, InjectPanel.initH, null);
		g2.setTransform(at);
	}

	@Override
	public boolean hit(BaseBt b) {
		// TODO Auto-generated method stub
		return false;
		
	}

	@Override
	public void setImg(int state) {
		// TODO Auto-generated method stub
		
	}

}
