package back_end.commands.commandLibrary.math;
import java.util.function.DoubleUnaryOperator;

import back_end.interfaces.CommandInterface;

public class NaturalLog extends OneInputMathCommand implements CommandInterface{

	@Override
	protected DoubleUnaryOperator getOperation() {
		return input -> Math.log(input);
	}


}
