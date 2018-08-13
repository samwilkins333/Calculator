package myCalculator;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Display {
	private Label _label;
	private int _numParentheses;
	
	public Display() {
		_label = new Label("0");
		_numParentheses = 0;
		this.format();
	}
	
	private void format() {
		_label.setFont(Font.font(Constants.FONT, FontWeight.BOLD, Constants.OUTPUT_TEXT_SIZE));
		_label.setTextFill(Constants.OUTPUT_TEXT_COLOR);
		_label.setAlignment(Pos.BASELINE_RIGHT);
		_label.setLayoutX(Constants.DISPLAY_OFFSET);
		_label.setLayoutY(Constants.DISPLAY_OFFSET);
		_label.setPrefSize(Constants.DISPLAY_WIDTH - 15, Constants.DISPLAY_HEIGHT);
	}
	
	public void show(String output) {
		int counter = 3;
		int numCommas = 0;
		int negOffset = 0;
		if (!output.contains(".")) {
			output = output.replace(",", "");
			if (output.contains("-")) {
				negOffset++;
			}
			while (counter + numCommas < output.length() - negOffset - _numParentheses) {
				output = output.substring(0, output.length() - counter - numCommas) + "," + output.substring(output.length() - counter - numCommas);
				numCommas++;
				counter += 3;
			}
		}
		
		//SCIENTIFIC NOTATION
		if (output.length() > 31) {
			//Switch to scientific notation
		}
		
		_label.setText(output);
	}

	public void addToPane(Pane pane) {
		pane.getChildren().add(_label);
	}
	
	public void setOpacity(double opacity) {
		_label.setOpacity(opacity);
	}
	
	public void fullOpacity() {
		_label.setOpacity(1);
	}

	public void incrementParentheses() {
		_numParentheses++;
	}
	
}