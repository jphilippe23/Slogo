package back_end.commands.commandLibrary.math;

import java.util.function.BiPredicate;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.commands.commandLibrary.TwoParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public class Or extends ComparisonCommand implements CommandInterface{

	@Override
	protected BiPredicate<Double, Double> getComparison() {
		return (a, b) -> (a != 0 || b != 0);
	}
}
