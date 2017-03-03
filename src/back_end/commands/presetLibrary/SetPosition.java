package back_end.commands.presetLibrary;

import back_end.commands.abstracts.PresetCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.ExpressionTree;
import back_end.model.Model;
import back_end.model.Oxygen;

public class SetPosition extends PresetCommand implements CommandInterface<ExpressionTree>{


	@Override
	public double Execute(Model model) {
		double x = this.getParameterValue()[0];
		double y = this.getParameterValue()[1];
		double ox = model.getX(0);
		double oy = model.getY(0);
		double distance = Math.pow((Math.pow((ox-x), 2) + Math.pow((oy-y), 2)) , 1/2d);
		
		model.setPos(0, model.getHome().getX() + x, model.getHome().getY() - y);
		return distance;
	}

}
