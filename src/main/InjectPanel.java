package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Timer;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import patientdec.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import room.Band;
import room.Box;
import room.Machine;
import room.Plunger;
import room.Room;
import room.Steam;
import room.Swab;
import room.Switch;
import room.Syringe;
import room.Text;
import room.Tree;
import room.Vial;
import util.MinimHelper;


// Source citation
// Music: close.mp3 , drag.mp3, open.mp3, tap.mp3
// Source: IAT265 - week10-lab-basecode
// Images: All images are drawn in Inkscape

public class InjectPanel extends JPanel implements ActionListener {
	
	public static int initW = 1050;
	public static int initH = 700;
	private Timer timer;
	private Room room;
	private Box box;
	private Machine machine;
	private Syringe syringe;
	private Vial vial;
	
	private Switch swtch;
	private Text text;
	private Steam steam;
	private Swab swab;
	private Band band;
	private Tree tree;
	private ArmInterface arm;
	private Arm armRef;
	private Patient patient;
	private JFrame jframe;
	private final String boxPath = "assets/box0.png";
	private final String roomPath = "assets/room1.png";
	private final String syringePath =  "assets/syringe.png";
	private final String plungerPath = "assets/plunger.png";
	private final String vialPath = "assets/vial.png";
	private final String patientPath = "assets/patient.png";
	private final String swtchPath = "assets/switchOff.png";
	private final String machinePath = "assets/machine.png";
	private final String swabPath = "assets/swab.png";
	private final String bandPath = "assets/bandage.png";
	private final String armPath =  "assets/arm.png";
	private final String swabPath1 = "assets/swab1.png";
	private final String dotPath = "assets/dot.png";
	private int machineTimer = 60;
	private int syringeTimer = 60;
	private final int machineTimerlim = 30*1;
	double boxX = initW/2 + initW/5;
	double boxY = initH/2;
	double swX = initW/2 + initW/2.5;
	double swY = initH/5;
	double syringeX = boxX;
	double syringeY = boxY/2+boxY/6+10;
	double swabX = syringeX-100;
	double swabY = boxY/2+boxY/6+60;
	double bandX = swabX - 70;
	double bandY = boxY/2+boxY/6+60;
	double patientX = 100;
	double patientY = initH-200;
	double armX = patientX + 60;
	double armY = patientY - 40;
	
	double plungerX = syringeX - 30;
	double plungerY = boxY + 30;
	double vialX = boxX - 50;
	double vialY = boxY/2+boxY/3.1;
	double tableX = initW/2 + initW/5;
	double tableY = initH/2 + initH/4;

	double machineX = boxX;
	double machineY = boxY + boxY/2 + boxY/15;
	
	// Fields for state and transitions
	private int state = -1;
	
	
	private String swabDone = "";
	private String syringeDone = "";
	private String bandageDone = "";
	private boolean swabbed = false;
	private boolean injected = false;
	private boolean banded = false;
	private boolean win = true;
	// variables for holding mouse position
	private double mouseX;
	private double mouseY;
	private Minim minim;
	private AudioPlayer bkmusic, click, buttonOn, buttonOff;
	
	

	public InjectPanel(JFrame frame) {
		
		this.setBackground(Color.white); 
		setPreferredSize(new Dimension(initW, initH));
		jframe = frame;
		room = new Room(0,0,1, roomPath);
		box = new Box(boxX , boxY , 0.9, boxPath);
		syringe = new Syringe(syringeX, syringeY, 0.9, syringePath);
		text = new Text("Injection game", initW/2 , initH/2 , 10, (float) 1);
		vial = new Vial(vialX, vialY,1, vialPath);
		swtch = new Switch(swX, swY,1,box, swtchPath);
		machine = new Machine(machineX, machineY, 1, machinePath);
		
		patient = new Patient(patientX, patientY, 0.5, patientPath);
		swab = new Swab(swabX, swabY, 0.4, swabPath);
		steam = new Steam((float) machine.getPosX()+32, (float) machine.getPosY() + 10, 30, 100);
		tree = new Tree(initW, initH, 1, "assets/plunger.png");
		band = new Band(bandX, bandY, 0.5, bandPath);
		arm = new Arm(armX, armY, 0.5, armPath);
		armRef = (Arm) arm;
		minim = new Minim(new MinimHelper());
		click = minim.loadFile("tap.mp3");
		buttonOn = minim.loadFile("open.mp3");
		buttonOff = minim.loadFile("close.mp3");
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
		MyMouseMotionAdapter m2 = new MyMouseMotionAdapter();
		addMouseMotionListener(m2);
		timer = new Timer(30, this);
		timer.start();
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (state == 3) {
			steam.setWidth((int) vial.getImgW()/4);
			steam.setHeight((int) vial.getImgH()/2);
			machineTimer -= 1;
			if (machineTimer <= 0) {
				state = 4;
				vial.setPos((int)(machineX + machine.getImgW()/1.5),
							(int) (machineY+machine.getImgH()/3.7));
				vial.setImg(1);
				buttonOff.play(0);
				machine.setImg(0);
				machine.setOn(false);
				machineTimer = 0;
				
			}
		} else if (state == 5) {
			syringeTimer--;
			if (syringeTimer <= 0) {
				state = 6;
				syringe.setPos((int)syringeX, (int)syringeY-7);
				syringe.setFlip(false);
				syringeTimer = 0;
			}
		}
		
		
		if ((banded || injected) && !swabbed) {
			// when one of two happen before swabbed
			win = false;
		}
		if (swabbed && !injected && banded) {
			win = false;
		}
		if (swabbed && injected && banded) {
			state = 7;
		}
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (state == -1) {
			text.drawIntro(g2);
		}
		if (state == 0) {
			drawObj(g2);
			text.drawText(g2, "Turn on the lightswitch on the medical box");
	
		} else if (state == 1) {
			drawObj(g2);
			text.drawText(g2, "Click on the drug making machine at the bottom to power on");
			
		} else if (state == 2) {
			drawObj(g2);
			if (machine.isOn()) {
				text.drawText(g2, "Drag the empty vial into machine to produce drug");
				
			}
		} else if (state == 3) {
			room.draw(g2);	
//			tree.drawBranch(g2, 40);
			box.draw(g2);
			syringe.draw(g2);
			machine.draw(g2);
//			vial.draw(g2);
			swtch.draw(g2);
			tree.drawBranch(g2, 40);
			band.draw(g2);
			swab.draw(g2);
			text.drawText(g2, "Drug is being produced...");
			steam.drawSteam(g2);
		} else if (state == 4) {
			// set up block
			
			swtch.setImg(1);
			swtch.setLightOn(true);
			vial.setPos((int)(machineX + machine.getImgW()/1.5),
					(int) (machineY+machine.getImgH()/3.7));
			vial.setImg(1);
			machine.setImg(0);
			machine.setOn(false);
			
			// 
			drawObj(g2);
			text.drawText(g2,"Drag the syringe needle pass vial cap to draw up the liquid");
			
		} else if (state == 5) {
			drawObj(g2);
			text.drawText(g2,"Filling the syringe...");
		}else if (state == 6) {
			
			drawObj1(g2);
			text.drawLongText(g2, "A patient has just entered the room/ "
					+ "Steps for injecting the patient:/"
					+ "1.Drag the swab on patient's arm to disinfect " + swabDone + "/"
					+ "2.Drag the syringe on the swabbed area to inject" + syringeDone + "/"
					+ "3.Drag the bandage to cover the small dot after the injection" + bandageDone); 
			
		} else if (state == 7) {
			drawObj1(g2);
			text.drawEnding(g2, win);
		} 
		
	}
	public void drawObj(Graphics2D g2) {
		room.draw(g2);
		box.draw(g2);
		syringe.draw(g2);
		band.draw(g2);
		swab.draw(g2);
		machine.draw(g2);
		vial.draw(g2);
		swtch.draw(g2);
		tree.drawBranch(g2, 40);	
	}
	public void drawObj1(Graphics2D g2) {
		room.draw(g2);
		box.draw(g2);
		tree.drawBranch(g2, 40);
		patient.draw(g2);
		arm.decorate(g2);
		band.draw(g2);
		syringe.draw(g2);
		swab.draw(g2);
		machine.draw(g2);
		vial.draw(g2);
		swtch.draw(g2);
		
		
		
	}
	public class MyMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			
			if (state == -1 && text.clicked(mouseX, mouseY)) {
				state = 0;
			}
			if (state == 0 && swtch.clicked(mouseX, mouseY)) {
				click.play(0);
				swtch.setImg(1);
				swtch.setLightOn(true);
				
				
				state = 1;
			} else if(state == 1 && swtch.clicked(mouseX, mouseY)) {
				
				click.play(0);
				swtch.setLightOn(false);
				swtch.setImg(0);
				
				state = 0;
			} else if (state == 1 && machine.clicked(mouseX, mouseY)) {
				// machine started 
				buttonOn.play(0);
				machine.setOn(true);
				state = 2;
				machine.setImg(2);
			} else if (state == 7 && text.clicked(mouseX, mouseY)) {
				jframe.dispose();
				jframe = new InjectApp("InjectApp");
			}
		}
	}
	
	
	public class MyMouseMotionAdapter extends MouseMotionAdapter{
		public void mouseDragged(MouseEvent e) {
			if (state == 2 && vial.clicked(e.getX(), e.getY())) {
				vial.setPos(e.getX(), e.getY());
				if (vial.hit(machine) && machine.isOn()) {
					state = 3;
					machine.setImg(1);
					machineTimer++;
				}
			}
			if (state == 4 && syringe.clicked(e.getX(), e.getY())) {
				syringe.setFlip(true);
				syringe.setPos(e.getX(), e.getY());
				if (syringe.hit(vial)) {
					state = 5;
					syringe.setImg(1);
					
				}
			}
			
			if (!swabbed && state == 6 && swab.clicked(e.getX(), e.getY()) && !syringe.clicked(e.getX(), e.getY()) && !band.clicked(e.getX(), e.getY())) {
				swab.setPos(e.getX(), e.getY());
				if (swab.hit(armRef)) {
					arm = new SwabDecorator(arm,armX, armY-10, 0.8, swabPath1);
					swab.setPos((int) swabX, (int)swabY);
					swabDone = " : Done!";
					swabbed = true;
				}
			} 
			
			if (!injected && state == 6 && syringe.clicked(e.getX(), e.getY()) && !band.clicked(e.getX(), e.getY()) && !swab.clicked(e.getX(), e.getY())) {
				syringe.setPos(e.getX(), e.getY());
				if (syringe.hit(armRef)) {
				
					arm = new SyringeDecor(arm,armX, armY-10, 0.3, dotPath);
					syringe.setPos((int) syringeX, (int)syringeY);
					syringeDone = " Done!";
					injected = true;
				}
			} 
			if (!banded && state == 6 && band.clicked(e.getX(), e.getY()) && !syringe.clicked(e.getX(), e.getY()) && !swab.clicked(e.getX(), e.getY())) {
				band.setPos(e.getX(), e.getY());
				if (band.hit(armRef)) {
					
					arm = new BandDecor(arm,armX, armY-10, 0.3, bandPath);
					band.setPos((int) bandX, (int)bandY);
					bandageDone = " : Done!";
					banded = true;
				}
			} 
			
			
			
			
			
			
			
			
		}
	}
	
}
