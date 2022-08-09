package room;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.InjectPanel;
import processing.core.PVector;
import util.ImageLoader;

public class Text extends BaseBt {
	private String text;
	private int topPad = 20;
	private int leftPad = 20;
	public Text(String title, int x, int y, int size, float scale) {
		super(x,y, scale, "assets/start.png");
		this.text = title;
		orgW = -img.getWidth()/2;
		orgH = -img.getHeight()/2;
	}
	public void drawEnding(Graphics2D g, boolean win) {
		g.setColor(new Color(0, 50, 0, 100));
		int height = 2;
		g.fillRect(
				0,
				0,
				InjectPanel.initW + 35, 
				InjectPanel.initH
				);
		
		if (win == true) {
			drawText(g, "you win! Injection steps were followed correctly");
			
		}
		else {
			drawText(g, "you lost, injection steps were not followed correctly!");
		}
		
		AffineTransform at = g.getTransform();
		g.translate(x, y);
		g.scale(scale, scale);
		g.drawImage(img, (int) orgW, (int) orgH, null);
		
		Font fstart = new Font("SansSerif", Font.BOLD, 30);
		FontMetrics mstart = g.getFontMetrics(fstart);
		this.text = "REPLAY";
		float textWstart = mstart.stringWidth(text);
		float textHstart = mstart.getHeight();
		g.setFont(fstart);
		g.setColor(Color.white);
		g.drawString(text, 
				0 - textWstart/2, 
				0 + textHstart/5);
		g.setTransform(at);
		
		
	}
	public void drawLongText(Graphics2D g, String description) {
		String[] arrOfStr = description.split("/", 5);
		int currentTopPad = topPad;
		int height = 2;
		g.setColor(new Color(255, 255, 255, 80));
		
		
		AffineTransform at = g.getTransform();
		
		
		
		g.translate(leftPad, currentTopPad);
		
		
		g.fillRect(
				0,
				0,
				450, 
				150
				);
		for (int i = 0; i < arrOfStr.length; i++) {
			
			g.translate(0, currentTopPad);
			g.scale(scale, scale);
			
			
			Font f = new Font("SansSerif", Font.BOLD, 13);
			FontMetrics metrics = g.getFontMetrics(f);
			this.text = description;
			float textWidth = metrics.stringWidth(text);
			float textHeight = metrics.getHeight();
			float margin = 10;
			g.setFont(f);
			g.setColor(new Color(0, 0, 0));
			g.drawString(arrOfStr[i], 
						margin, 
						textHeight);
			
			
			
			
			
			currentTopPad+=0;
			
			
		}
		g.setTransform(at);
	}
	
	public void drawText(Graphics2D g, String description) {
		AffineTransform at = g.getTransform();
		g.translate(topPad, leftPad);
		g.scale(scale, scale);
		g.setColor(new Color(255, 255, 255, 80));
		
		int height = 2;
		g.fillRect(
				0,
				0,
				400, 
				100
				);
		
		Font f = new Font("SansSerif", Font.BOLD, 13);
		FontMetrics metrics = g.getFontMetrics(f);
		this.text = description;
		float textWidth = metrics.stringWidth(text);
		float textHeight = metrics.getHeight();
		float margin = 10;
		g.setFont(f);
		g.setColor(new Color(0, 0, 0));
		g.drawString(text, 
					margin, 
					textHeight);
		
		
		
		
		g.setTransform(at);
	}
	
	
	public void drawIntro(Graphics2D g) {
		BufferedImage coverImg;
		coverImg = ImageLoader.loadImage("assets/cover.png");
		g.drawImage(coverImg, 0, 0, InjectPanel.initW, InjectPanel.initH, null);
		
		
		g.setColor(new Color(0, 0, 0, 230));
		int height = 2;
		g.fillRect(
				0,
				0,
				InjectPanel.initW + 35, 
				InjectPanel.initH
				);
		AffineTransform at = g.getTransform();
		g.translate(x, y);
		g.scale(scale, scale);
		g.drawImage(img, (int) orgW, (int) orgH, null);
		
		Font fstart = new Font("SansSerif", Font.BOLD, 30);
		FontMetrics mstart = g.getFontMetrics(fstart);
		this.text = "START";
		float textWstart = mstart.stringWidth(text);
		float textHstart = mstart.getHeight();
		g.setFont(fstart);
		g.setColor(Color.white);
		g.drawString(text, 
				0 - textWstart/2, 
				0 + textHstart/5);
	
		
		
		
		g.translate(0, -150);
		Font f = new Font("SansSerif", Font.BOLD, 60);
		FontMetrics metrics = g.getFontMetrics(f);
		this.text = "THE INJECTION";
		float textWidth = metrics.stringWidth(text);
//		float textHeight = metrics.getHeight();
		
		g.setFont(f);
		g.setColor(new Color(204, 68, 0));
		g.drawString(text, 
					-textWidth/2, 
					-height);
		
		
		Font f1 = new Font("Monospaced", Font.PLAIN, 14);
		this.text = "In this game, you are a doctor, who is going to give the patient an injection. There are tools on the";
		String text1 = "right shelf that you can use to produce a vaccine. Follow the suggestion and pick the right tool. Have fun!";
		
		g.setFont(f1);
		g.setColor(Color.WHITE);
		g.drawString(text, 
					-textWidth, 
					0+50);
		
		g.drawString(text1, 
				-textWidth, 
				0+70);
		g.setTransform(at);
	
		
	}
	public void drawInfo(Graphics2D g) {
		
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
	
}
