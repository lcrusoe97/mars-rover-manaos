package org.rover;

import java.util.*;

public class Plateau {
	
	public int longueurPlateau;
	public int largeurPlateau;
	
	private List<Rover> rovers = new ArrayList<>();
	
	public Plateau(int longueurPlateau, int largeurPlateau) {
		this.longueurPlateau = longueurPlateau;
		this.largeurPlateau = largeurPlateau;
	}
	
	public void addRover(Rover rover) {
		rovers.add(rover);
	}
	
	public boolean isPositionOccupied(PositionRover positionRover) {
		return rovers.stream().anyMatch(rover -> rover.hasPosition(positionRover));
	}
}
