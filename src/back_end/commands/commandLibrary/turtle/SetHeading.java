package back_end.commands.commandLibrary.turtle;

import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

import java.util.List;
import java.util.function.BiFunction;
import back_end.interfaces.CommandInterface;

public class SetHeading extends OneInputTurtleCommand implements CommandInterface{

	@Override
	protected BiFunction<Turtle, List<Double>, Double> supplyAction(Model model) {
		return (turtle, inputs) -> turtle.setAngle(-1 * inputs.get(0));
	}

}
