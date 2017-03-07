package back_end.commands.commandLibrary.math;

import back_end.commands.commandLibrary.OneParameterCommand;
import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public class Tangent extends OneParameterCommand{

	@Override
	public double Execute(Model state) {
		getParams();
		return Math.tan(Math.toRadians(A));
	}

}