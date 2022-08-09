package main;
import javax.swing.JFrame;

import main.InjectPanel  ;


public class InjectApp extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public InjectApp(String title) {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		InjectPanel ijPanel = new InjectPanel(this);
		this.add(ijPanel); 
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true); 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InjectApp("InjectApp");
	}
	
}
