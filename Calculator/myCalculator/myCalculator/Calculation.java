package myCalculator;

public class Calculation {
	private double _arg1;
	private double _arg2;
	
	public Calculation(String arg1, String arg2) {
		_arg1 = Double.parseDouble(arg1);
		_arg2 = Double.parseDouble(arg2);
	}
	
	public String solve(String func) {
		if (func.equals(Constants.M)) {
			return Double.toString(_arg1 * _arg2);
		} else if (func.equals(Constants.D)) {
			return Double.toString(_arg1 / _arg2);
		} else if (func.equals(Constants.A)) {
			return Double.toString(_arg1 + _arg2);
		} else if (func.equals(Constants.S)) {
			return Double.toString(_arg1 - _arg2);
		} else {
			return "0";
		}
	}
	
}