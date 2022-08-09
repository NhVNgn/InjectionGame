package room;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.*;

import main.InjectPanel;

public class Tree extends BaseBt {

	public Tree(double xPos, double yPos, double scale, String file) {
		super(xPos, yPos, scale, file);
		// TODO Auto-generated constructor stub
		
	}

	public void drawBranchHelper(Graphics2D g2, float len) {
		g2.setStroke(new BasicStroke(2));
		g2.draw(new Line2D.Float(0, 0, 0, -len)); // Draw the branch itself
		g2.translate(0, -len); // Move to the end of that line

		len *= 0.66; 		// Shrink the length of the branch
		
		if (len > 2) { // Base case: exit when the length of the branch is 2
						// pixels or less
			AffineTransform at = g2.getTransform();
			g2.rotate(Math.PI / 3); // Rotate to the right
			drawBranchHelper(g2, len); // Call itself to draw two shorter branches!!
			g2.setTransform(at);

			
			at = g2.getTransform();
			g2.rotate(-Math.PI / 4);
			drawBranchHelper(g2, len/2);
			g2.setTransform(at);
			
			// Repeat the same thing, only branch off to the "left" this time!
			at = g2.getTransform();
			g2.rotate(-Math.PI / 10);
			drawBranchHelper(g2, len);
			g2.setTransform(at);
			
			
			
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		
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

	public void drawBranch(Graphics2D g2, int i) {
		// TODO Auto-generated method stub
		AffineTransform at = g2.getTransform();
		g2.translate(InjectPanel.initW / 3.1, InjectPanel.initH/1.73);
		this.drawBranchHelper(g2, i);
		g2.setTransform(at);
		
	}

}
