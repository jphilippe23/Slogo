package back_end.commands.booleanOps;

import back_end.Input;
import back_end.Model;
import back_end.commands.CommandInterface;
import back_end.constant.Constant;

public class GreaterThan implements CommandInterface, Constant{
    private int mGreater;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mGreater = a > b ? 1 : 0;
		
	}

	@Override
	public double Execute(Model state) { 
		return mGreater;
	}
}
