package back_end.commands.commandLibrary.math;

import java.util.function.BinaryOperator;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.commands.commandLibrary.TwoParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public class Sum extends TwoInputMathCommand implements CommandInterface{

	@Override
	protected BinaryOperator<Double> getOperation() {
		return (a,b) -> a + b;
	}

}
