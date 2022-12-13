package org.rover;

import java.util.Objects;

public class PositionRover {

	public int x;
	public int y;
	
	public PositionRover(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public PositionRover(PositionRover other) {
		this.x = other.x;
		this.y = other.y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PositionRover that = (PositionRover) o;
		return x == that.x && y == that.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return x + " " + y;
	}
	
	public boolean isInsidePlateau(Plateau p) {
		if (x < 0 || x > p.longueurPlateau) {
			return false;
		}
		
		if (y < 0 || y > p.largeurPlateau) {
			return false;
		}
		
		return true;
	}
	
	public PositionRover moveForward(Direction direction) {
		return switch (direction) {
			case EAST -> new PositionRover(x + 1, y);
			case NORTH -> new PositionRover(x, y + 1);
			case SOUTH -> new PositionRover(x, y - 1);
			case WEST -> new PositionRover(x - 1, y);
		};
	}
}
