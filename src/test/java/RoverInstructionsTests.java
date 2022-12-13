import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rover.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverInstructionsTests {

	private Plateau plateau;
	private Rover rover;

	@BeforeEach
	void beforeEach() {
		plateau = new Plateau(5, 5);
		rover = new Rover("Lionel");
	}

	@Test
	@DisplayName("1st mission : move rover Lionel : End position : 1 5 N")
	void first_mission_should_be_ok() {
		rover.initRoverPosition(plateau, "1 2 N");

		rover.processInstructions(convertCommandToInstructions("LMLMLMLMMMM"));

		assertEquals(" Starting from : 1 5 N", rover.getInitPositionInfos());
	}
	
	@Test
	@DisplayName("2nd mission : move rover Lionel : End position : 3 1 E")
	public void second_mission_should_be_ok() {
		rover.initRoverPosition(plateau, "3 3 E");
		rover.processInstructions(convertCommandToInstructions("MMRMMRMMMRRM"));

		assertEquals(" Starting from : 3 1 E", rover.getInitPositionInfos());
	}
	
	@Test
	@DisplayName("3rd mission : move rover Lionel outside of plateau")
	public void moving_rover_out_of_bounds_should_throw_exception() {
		rover.initRoverPosition(plateau, "2 2 N");
		Exception exception = assertThrows(IllegalMovementException.class, () ->
				rover.processInstructions(convertCommandToInstructions("MMMMMMMMMMMMMM")));
		assertEquals("Outside of the plateau", exception.getMessage());
	}
	
	@Test
	@DisplayName("4th mission : place rover Lionel outside of plateau")
	public void placing_rover_out_of_bounds_should_throw_exception() {
		Exception exception = assertThrows(IllegalMovementException.class, () ->
			rover.initRoverPosition(plateau, "12 6 N"));
		assertEquals("Outside of the plateau", exception.getMessage());
	}

	@Test
	@DisplayName("5th mission : initialize rover position with a letter")
	void wrong_position_instruction_should_throw_exception() {
		Exception exception = assertThrows(NumberFormatException.class, () ->
		rover.initRoverPosition(plateau, "M 3 E"));
		assertEquals("For input string: \"M\"", exception.getMessage());
	}

	@Test
	@DisplayName("6th mission : initialize rover direction with a wrong letter")
	void wrong_position_instruction_should_throw_exceptionn() {
		Exception exception = assertThrows(RuntimeException.class, () ->
				rover.initRoverPosition(plateau, "3 3 Z"));
		assertEquals("Unsupported direction character 'Z'. Only cardinal points are allowed", exception.getMessage());
	}


	@Test
	@DisplayName("7th mission : uninitialized rover")
	public void uninit_rover_should_report_wrong_state() {
		String report = rover.getInitPositionInfos();
		assertEquals("No init position yet", report);
	}
	
	@Test
	@DisplayName("7th mission : move uninit rover")
	public void moving_an_uninit_rover_should_throw_exception() {
		Exception exception = assertThrows(UnInitializedException.class, () -> rover.processInstructions(convertCommandToInstructions("MMMMMM")));
		assertEquals("org.rover.UnInitializedException : place rover on the plateau", exception.getMessage());
	}
	
	@Test
	@DisplayName("8th mission : invalid command line")
	public void invalid_instruction_should_throw() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> rover.processInstructions(convertCommandToInstructions("blabla")));
		assertEquals("Invalid instruction : b. Only L, M, R is allowed !", exception.getMessage());
	}
	
	@Test
	@DisplayName("9th mission : two rovers on the same spot")
	public void init_rover_over_another_rover_should_throw() {
		Rover greg = new Rover("Gregoire");
		
		rover.initRoverPosition(plateau, "3 5 E");
		Exception exception = assertThrows(RuntimeException.class, () -> greg.initRoverPosition(plateau, "3 5 N"));
		assertEquals("Position already occupied by a rover", exception.getMessage());
	}
	
	@Test
	@DisplayName("10th mission : two rovers on the plateau")
	public void dropping_two_rovers_next_to_another_should_succeed() {
		Rover greg = new Rover("Gregoire");
		rover.initRoverPosition(plateau, "2 5 E");
		greg.initRoverPosition(plateau, "1 5 N");
	}

	private List<Instruction> convertCommandToInstructions(String instructions) {
		return new Instructions(instructions).getInstructions();
	}
}