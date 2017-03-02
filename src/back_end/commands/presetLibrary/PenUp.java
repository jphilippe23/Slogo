package back_end.commands.presetLibrary;

import back_end.commands.abstracts.PresetCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.Model;
import back_end.model.Oxygen;

public class PenUp extends PresetCommand implements CommandInterface<Oxygen<Double>>{
	
	@Override
	public double Execute(Model model) {
		model.setPenUp(0);
		return 0;
	}
	

}