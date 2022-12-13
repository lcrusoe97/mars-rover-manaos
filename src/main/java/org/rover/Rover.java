package org.rover;

import java.util.List;

public class Rover {
	
	private Plateau plateau;
	private PositionRover positionRover;
	private Direction direction;
	private String name;
	
	public Rover(String name) {
		this.name = name;
	}
	
	public void initRoverPosition(Plateau plateau, String args) {
		String[] initInfos = args.split(" ");
		int x = Integer.parseInt(initInfos[0]);
		int y = Integer.parseInt(initInfos[1]);
		Direction direction = Direction.valueToHeading(initInfos[2]);
		addInitializedRoverToPlateau(plateau, new PositionRover(x, y), direction);
	}
	
	public void addInitializedRoverToPlateau(Plateau plateau, PositionRover positionRover, Direction direction) {
		if (!positionRover.isInsidePlateau(plateau)) {
			throw new IllegalMovementException(plateau, positionRover);
		}

		if (plateau.isPositionOccupied(positionRover)) {
			throw new RuntimeException("Position already occupied by a rover");
		}

		this.plateau = plateau;
		this.positionRover = positionRover;
		this.direction = direction;

		plateau.addRover(this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("org.rover.Rover ");
		sb.append(name);
		sb.append(" is heading to the ").append(direction.name());
		sb.append(getInitPositionInfos());
		return sb.toString();
	}

	public String getFinalPositionInfos() {
		StringBuilder sb = new StringBuilder("org.rover.Rover ");
		sb.append(name);
		sb.append(" arrived @ ").append(positionRover);
		sb.append(" and facing ").append(Direction.valueFromHeading(direction));
		return sb.toString();
	}

	public String getInitPositionInfos() {
		if (positionRover == null || direction == null) {
			return "No init position yet";
		}
		StringBuilder sb = new StringBuilder(" Starting from : ");
		sb.append(positionRover).append( " ").append(Direction.valueFromHeading(direction));
		return sb.toString();
	}

	public boolean hasPosition(PositionRover pos) {
		return positionRover.equals(pos);
	}
	
	public void processInstructions(List<Instruction> instructions) {
		instructions.forEach(instruction -> processInstruction(instruction));
	}
	
	private void processInstruction(Instruction instruction) {
		if (positionRover == null || direction == null) {
			throw new UnInitializedException();
		}
		
		switch (instruction) {
			case LEFT -> turnLeft();
			case MOVE -> moveForward();
			case RIGHT -> turnRight();
			default -> throw new RuntimeException("Wrong character inside instruction list");
		}
	}
	
	private void turnLeft() {		
		direction = switch (direction) {
			case EAST -> Direction.NORTH;
			case NORTH -> Direction.WEST;
			case SOUTH -> Direction.EAST;
			case WEST -> Direction.SOUTH;
		};
	}
	
	private void turnRight() {
		direction = switch (direction) {
			case EAST -> Direction.SOUTH;
			case NORTH -> Direction.EAST;
			case SOUTH -> Direction.WEST;
			case WEST -> Direction.NORTH;
		};
	}
	
	private void moveForward() {		
		PositionRover newPositionRover = positionRover.moveForward(direction);
		if (!newPositionRover.isInsidePlateau(plateau)) {
			throw new IllegalMovementException(plateau, newPositionRover);
		}
		positionRover = newPositionRover;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public PositionRover getPositionRover() {
		return positionRover;
	}

	public void setPositionRover(PositionRover positionRover) {
		this.positionRover = positionRover;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
