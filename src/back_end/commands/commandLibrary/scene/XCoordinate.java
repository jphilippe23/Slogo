package back_end.commands.commandLibrary.scene;

import back_end.commands.commandLibrary.PresetCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public class XCoordinate extends PresetCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		return model.getHome().getX() + model.getX(0);
	}

}
