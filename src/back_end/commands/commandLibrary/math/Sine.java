package back_end.commands.commandLibrary.math;

import java.util.function.DoubleUnaryOperator;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Sine extends OneInputMathCommand implements CommandInterface{

	@Override
	protected DoubleUnaryOperator getOperation() {
		return input -> Math.sin(Math.toRadians(input));
	}


}
