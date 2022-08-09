package room;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.InjectPanel;
import util.ImageLoader;
public abstract class BaseBt {
	protected BufferedImage img;
	protected double x;
	protected double y;
	protected double scale;
	protected double orgW;
	protected double orgH;
	protected boolean visible = true;
	public BaseBt(double xPos, double yPos, double scale, String file) {
		this.x = xPos;
		this.y= yPos;
		this.scale = scale;
		
		img = ImageLoader.loadImage(file);
	}
	protected void setVisible(boolean val) {
		this.visible = val;
	}
	abstract public void draw(Graphics2D g2);
	public void setPos(int x, int y) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
	}
	abstract public boolean hit(BaseBt b);
	abstract public void setImg(int state);
	public boolean clicked(double px, double py){
		boolean clicked = false;
		if (px > (x - ((double) img.getWidth()) / 2 * scale) && 
			px < (x + ((double) img.getWidth())/2*scale) && 
			py > (y - ((double) img.getHeight())/2*scale) && 
			py < (y + ((double) img.getHeight())/2*scale)) { 
			clicked = true;
		}
		return clicked;
	}
	public double getPosX() {
		return x;
	}
	public double getPosY() {
		return y;
	}
	public double getImgW() {
		return img.getWidth()*scale;
	}
	public double getImgH() {
		return img.getHeight()*scale;
		
	}
}
