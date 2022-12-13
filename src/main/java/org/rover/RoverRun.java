package org.rover;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class RoverRun {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(in);
		
		out.print("Type width and lenght for the plateau in the following format : (x y)");
		String dimensions = scanner.nextLine();
		Plateau plateau = buildPlateau(dimensions);
		
		while (true) {
			out.print("Type name for org.rover.Rover : ");
			String name = scanner.nextLine();
			out.print("Type initial rover position for " + name + " in the following format : (x y h)");
			String dropInfo = scanner.nextLine();

			try {
				Rover rover = dropRover(name, plateau, dropInfo);
				out.println("Report: " + rover);

				out.print("Enter instructions for " + name + " (in the form LMRMMMLMM):");
				String instructions = scanner.nextLine();

				List<Instruction> instructionsCollection = new Instructions(instructions).getInstructions();
				rover.processInstructions(instructionsCollection);
				out.println(rover.getFinalPositionInfos());
			} catch (Exception ex) {
				out.println(ex.getMessage());
			}
		}
	}
	
	private static Rover dropRover(String name, Plateau plateau, String dropInfo) {
		Rover initializedRover = new Rover(name);
		initializedRover.initRoverPosition(plateau,  dropInfo);
		return initializedRover;
	}

	private static Plateau buildPlateau(String dimensions) {
		String[] parts = dimensions.split(" ");
		return new Plateau(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
	}
}
