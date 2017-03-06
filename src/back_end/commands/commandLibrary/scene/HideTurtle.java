package back_end.commands.commandLibrary.scene;

import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;
import back_end.commands.commandLibrary.PresetCommand;
import back_end.interfaces.CommandInterface;

public class HideTurtle extends PresetCommand implements CommandInterface{
	
	@Override
	public double Execute(Model model) {
		model.setInVisible(0);
		return 0;
	}
	

}
