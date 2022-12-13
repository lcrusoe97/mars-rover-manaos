package org.rover;

public class IllegalMovementException extends RuntimeException {

	private Plateau plateau;
	private PositionRover positionRover;
	
	public IllegalMovementException(Plateau plateau, PositionRover positionRover) {
		super("Outside of the plateau");
		
		this.plateau = plateau;
		this.positionRover = positionRover;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public PositionRover getPosition() {
		return positionRover;
	}
}
