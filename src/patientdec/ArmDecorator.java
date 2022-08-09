package patientdec;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import room.BaseBt;

public abstract class ArmDecorator extends BaseBt implements ArmInterface {
	ArmInterface basePatient;
	public ArmDecorator(ArmInterface patient ,double xPos, double yPos, double scale, String file) {
		super(xPos, yPos, scale, file);
		// TODO Auto-generated constructor stub
		this.basePatient = patient;
	}

	
	
	
	@Override
	public void decorate(Graphics2D g) {
		// TODO Auto-generated method stub
		basePatient.decorate(g);
	}
	

}
