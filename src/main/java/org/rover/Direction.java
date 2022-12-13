package org.rover;

public enum Direction {
	NORTH, EAST, SOUTH, WEST;

	public static Direction valueToHeading(String heading) {
		return switch (heading) {
			case "N" -> Direction.NORTH;
			case "W" -> Direction.WEST;
			case "S" -> Direction.SOUTH;
			case "E" -> Direction.EAST;
			default -> throw new RuntimeException("Unsupported direction character '" + heading + "'. Only cardinal points are allowed");
		};
	}

	public static String valueFromHeading(Direction direction) {
		return switch (direction) {
			case NORTH -> "N";
			case WEST -> "W";
			case SOUTH -> "S";
			case EAST -> "E";
		};
	}
}
