package myCalculator;

import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;
import java.util.Stack;

import javafx.scene.control.Label;

public class Calculator {
	private Label _outputView;
	private String _output;
	private Stack<String> _undo;
	private Stack<String> _redo;
//	private Queue<String> _operations;
	private Button _undoButton;
	private Button _redoButton;
	private Button _zero;
	private Button _decimal;
	private Button _opposite;
	private Button _divide;
	private Button _multiply;
	private Button _add;
	private Button _subtract;
	private Button _equals;
	private ArrayList<Button> _buttons;
	private ArrayList<String> _symbols;
	private int _numParentheses;
	private PaneOrganizer _organizer;
	private String _temp;
	private String _lastOp;
	
	public Calculator(PaneOrganizer organizer, Label outputView) {
		_organizer = organizer;
		_outputView = outputView;
		_output = "0";
		_temp = "";
		_undo = new Stack<String>();
		_redo = new Stack<String>();
//		_operations = new LinkedList<String>();
		_outputView.setOpacity(0.3);
		_numParentheses = 0;
	}
	
	public void compute(Button clicked) {
		if (clicked.isActive()) {
			
			String type = clicked.getType();
			String text = clicked.getText();
			if (clicked != _multiply && clicked != _divide && clicked != _add && clicked != _subtract) {
				clicked.flash();
			}
			
			_undoButton.activate();
			_redoButton.activate();
			_multiply.activate();
			_divide.activate();
			_add.activate();
			_subtract.activate();
			_equals.activate();
			clicked.deselect();
			
			if (type.equals(Constants.NUMPAD)) {
				_undo.push(_output);
				_redo.clear();
				
				if (!text.equals(".") && !text.equals("+/-")) {
					_outputView.setOpacity(1.0);
					if (_output.equals("0")) {
						_output = "";
					}
					_output += clicked.getNumber();
				} else if (text.equals(".")) {
					_outputView.setOpacity(1.0);
					if (!_output.contains(".")) {
						_output += clicked.getNumber();
						clicked.deactivate();
					}
				} else if (text.equals("+/-")) {
					if (_output.startsWith("-")) {
						_output = _output.replace("-", "");
					} else {
						_output = "-" + _output;
					}
				}
			} else if (type.equals(Constants.GENERAL)) {
				//Clear all
				if (text.equals("AC")) {
					_output = "0";
					_outputView.setText("0");
					_temp = "";
					_undo.clear();
					_redo.clear();
				}
				if (text.equals("<--")) {
					if (!_undo.isEmpty()) {
						if (!_output.equals("")) {
							_redo.push(_output);
						}
						_output = _undo.pop();
						_outputView.setText(_output);
						this.updateUndoRedo();
						this.updateZero();
						this.updateDecimal();
						this.updateParentheses();
						return;
					}
				}				
				if (text.equals("-->")) {
					if (!_redo.isEmpty()) {
						if (!_output.equals("")) {
							_undo.push(_output);
						}
						_output = _redo.pop();
						_outputView.setText(_output);
						this.updateUndoRedo();
						this.updateZero();
						this.updateDecimal();
						this.updateParentheses();
						return;
					}
				} 
				if (text.equals("(") || text.equals(")")) {
					_outputView.setOpacity(1.0);
					if (!_output.contains("(")) {
						if (text.equals("(")) {
							_undo.push(_output);
							_redo.clear();
							if (_output.equals("0")) {
								_output = "";
							}
							_output = text + _output;
						}
					}
					if (text.equals(")") && this.isExpressionBalanced()) {
						_undo.push(_output);
						_redo.clear();
						if (_output.equals("0")) {
							_output = "";
						}
						_output = _output + text;
					}
					_outputView.setText(_output);
					_numParentheses++;
					return;
				}
			} else if (type.equals(Constants.OPERATOR)) {
				_multiply.deselect();
				_divide.deselect();
				_add.deselect();
				_subtract.deselect();
				_equals.deselect();
				if (clicked != _equals) {
					clicked.select();
				}
				
				if (!_temp.equals("")) {
					double arg1 = Double.parseDouble(_temp);
					double arg2 = Double.parseDouble(_output);
					if (_lastOp.equals(Constants.M)) {
						_output = Double.toString(arg1 * arg2);
					} else if (_lastOp.equals(Constants.D)) {
						_output = Double.toString(arg1 / arg2);
					} else if (_lastOp.equals(Constants.A)) {
						_output = Double.toString(arg1 + arg2);
					} else if (_lastOp.equals(Constants.S)) {
						_output = Double.toString(arg1 - arg2);
					}
				}
				
				if (text.equals("=")) {
//					_operations.add(_output);
//					while (!_operations.isEmpty()) {
//						System.out.println(_operations.remove());
//					}
					_outputView.setText(_output);
					_organizer.playDisplay();
				} else if (text.equals("X")) {
					_redo.clear();
					_undo.push(_output);
//					_operations.add(_output);
//					_operations.add("*");
					_lastOp = Constants.M;
					_temp = _output;
					_outputView.setText(_output);
					_output = "";
					this.updateUndoRedo();
					this.updateZero();
					this.updateDecimal();
					return;
				} else if (text.equals("/")) {
					_redo.clear();
					_undo.push(_output);
//					_operations.add(_output);
//					_operations.add("/");
					_lastOp = Constants.D;
					_temp = _output;
					_outputView.setText(_output);
					_output = "";
					this.updateUndoRedo();
					this.updateZero();
					this.updateDecimal();
					return;
				} else if (text.equals("+")) {
					_redo.clear();
					_undo.push(_output);
//					_operations.add(_output);
//					_operations.add("+");
					_lastOp = Constants.A;
					_temp = _output;
					_outputView.setText(_output);
					_output = "";
					this.updateUndoRedo();
					this.updateZero();
					this.updateDecimal();
					return;
				} else if (text.equals("_")) {
					_redo.clear();
					_undo.push(_output);
//					_operations.add(_output);
//					_operations.add("-");
					_lastOp = Constants.S;
					_temp = _output;
					_outputView.setText(_output);
					_output = "";
					this.updateUndoRedo();
					this.updateZero();
					this.updateDecimal();
					return;
				} 
			} else {
				
			}
			
			int counter = 3;
			int numCommas = 0;
			int negOffset = 0;
			if (!_output.contains(".")) {
				_output = _output.replace(",", "");
				if (_output.contains("-")) {
					negOffset++;
				}
				while (counter + numCommas < _output.length() - negOffset - _numParentheses) {
					_output = _output.substring(0, _output.length() - counter - numCommas) + "," + _output.substring(_output.length() - counter - numCommas);
					numCommas++;
					counter += 3;
				}
			}
			
			//SCIENTIFIC NOTATION
			if (_output.length() > 31) {
				//Switch to scientific notation
			}
			
			_outputView.setText(_output);
			
			this.updateUndoRedo();
			this.updateZero();
			this.updateDecimal();
		}
	}

	private boolean isExpressionBalanced() {
		int numOpen = 0;
		int numClosed = 0;
		for (int i = 0; i < _output.length(); i++) {
			String thisChar = Character.toString(_output.charAt(i));
			if (thisChar.equals("(")) {
				numOpen++;
			} else if (thisChar.equals(")")) {
				numClosed++;
			}
		}
		return numClosed == numOpen - 1;
	}

	private void updateDecimal() {
		if (_output.contains(".")) {
			_decimal.deactivate();
		} else {
			_decimal.activate();
		}
	}
	
	private void updateParentheses() {
		int count = 0;
		for (int i = 0; i < _output.length(); i++) {
			String thisChar = Character.toString(_output.charAt(i));
			if (thisChar.equals("(") || thisChar.equals(")")) {
				count++;
			} 
		}
		_numParentheses = count;
	}

	private void updateZero() {
		if (_output.equals("0")) {
			_zero.deactivate();
			_opposite.deactivate();
		} else {
			_zero.activate();
			_opposite.activate();
		}
	}

	private void updateUndoRedo() {
		if (_undo.isEmpty()) {
			_undoButton.deactivate();
		}
		if (_redo.isEmpty()) {
			_redoButton.deactivate();
		}
	}
	
	public void setButtonAssociations(ArrayList<Button> buttons, ArrayList<String> symbols) {
		_buttons = buttons;
		_symbols = symbols;
		_undoButton = this.getButton("<--");
		_redoButton = this.getButton("-->");
		_zero = this.getButton("0");
		_decimal = this.getButton(".");
		_opposite = this.getButton("+/-");
		_multiply = this.getButton("X");
		_divide = this.getButton("/");
		_add = this.getButton("+");
		_subtract = this.getButton("-");
		_equals = this.getButton("=");
	}
	
	private Button getButton(String symbol) {
		return _buttons.get(_symbols.indexOf(symbol));
	}
	
	public void printStack(Stack<String> stack, String name) {
		System.out.println("Printing");
		if (stack.isEmpty()) {
			System.out.printf("%s is empty!\n", name);
		} else {
			System.out.println(name);
			int i = stack.size() - 1;
			while (i >= 0) {
				String item = stack.elementAt(i);
				if (item == "") {
					System.out.println("''");
				} else {
					System.out.println(item);
				}
				i--;
			}
		}
//		System.out.println("Done printing");
	}
	
}