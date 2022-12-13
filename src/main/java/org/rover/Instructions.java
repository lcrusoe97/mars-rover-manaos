package org.rover;

import java.util.ArrayList;
import java.util.List;

public class Instructions {
	private String instructions;
	public Instructions(String instructions) {
		this.instructions = instructions;
	}
	
	public List<Instruction> getInstructions() {
		List<Instruction> result = new ArrayList<>();
		for (char c: instructions.toCharArray()) {
			switch (c) {
				case 'L' -> result.add(Instruction.LEFT);
				case 'M' -> result.add(Instruction.MOVE);
				case 'R' -> result.add(Instruction.RIGHT);
				default -> throw new IllegalArgumentException("Invalid instruction : " + c + ". Only L, M, R is allowed !");
			}
		}
		return result;
	}
}
