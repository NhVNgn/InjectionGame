package room;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import util.ImageLoader;

public class Switch extends BaseBt{
	private boolean lightOn = false;
	private Box b;
	public Switch(double xPos, double yPos, double scale,Box b, String file) {
		super(xPos, yPos, scale, file);
		// TODO Auto-generated constructor stub
		this.b = b;
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		AffineTransform transform = g2.getTransform(); // save(x~y)
		g2.translate(x, y);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		
		
		g2.setTransform(transform);
		if (!lightOn){ 
			g2.setColor(new Color(0, 0, 0, 200));
			g2.fill(new Rectangle2D.Double(b.getPosX()-b.getImgW()/2, b.getPosY()-b.getImgH()/2,b.getImgW() , b.getImgH()));
		}
	}

	@Override
	public boolean hit(BaseBt b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setImg(int state) {
		// TODO Auto-generated method stubs
		if (state == 0) {
			img = ImageLoader.loadImage("assets/switchOff.png");
		}
		else if (state == 1) {
			img = ImageLoader.loadImage("assets/switchOn.png");
		}
		
	}
	public void setLightOn(boolean on){
		lightOn = on;
	}

}
