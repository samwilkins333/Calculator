package myCalculator;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Button {
	private Calculator _calculator;
	private String _type;
	private int _x;
	private int _y;
	private double _xLayout;
	private double _yLayout;
	private Rectangle _buttonRect;
	private Label _buttonText;
	private Color _textColor;
	private int _textSize;
	private String _text;
	private int _counter;
	private boolean _active;
	private boolean _selected;
	private FillTransition _flash;
	
	public Button (int x, int y, String text, int counter, Calculator calculator) {
		_x = x;
		_y = y;
		_selected = false;
		_counter = counter;
		_calculator = calculator;
		_xLayout = 15 + Constants.BUTTON_GRID_INC * _x;
		_yLayout = 100 + Constants.BUTTON_GRID_INC * _y;
		_text = text;
		_textSize = Constants.GENERAL_TEXT_SIZE;
		if (_counter > 20 && _counter < 24) {
			_textSize = Constants.GENERAL_SMALL_TEXT_SIZE;
		}
		this.buildRectangle();
		this.buildLabel();
		this.activate();
		if (text == "<--" || 
			text == "-->" || 
			text  == "+/-" ||
			text == "X" ||
			text == "/" ||
			text == "+" ||
			text == "-" ||
			text == "=") {
			this.deactivate();
		}
		this.setupFlash();
	}

	private void buildRectangle() {
		_buttonRect = new Rectangle(
				Constants.BUTTON_DIM, 
				Constants.BUTTON_DIM);
		if (_x == 9) {
			_buttonRect.setFill(Constants.OPERATOR_BUTTON_COLOR);
			_type = Constants.OPERATOR;
		} else if (_x > 5 && _x < 9 && _y > 0) {
			_buttonRect.setFill(Constants.NUMPAD_BUTTON_COLOR);
			_type = Constants.NUMPAD;
		} else {
			_buttonRect.setFill(Constants.GENERAL_BUTTON_COLOR);
			_type = Constants.GENERAL;
		}
		
		_buttonRect.setStroke(Constants.BUTTON_BORDER_COLOR);
		_buttonRect.setStrokeWidth(Constants.BUTTON_BORDER_WIDTH);
		_buttonRect.setArcWidth(Constants.ARC);
		_buttonRect.setArcHeight(Constants.ARC);
		
		this.interpretType();
	}
	
	private void interpretType() {
		if (_type == Constants.GENERAL) {
			_textColor = Constants.GENERAL_TEXT_COLOR;
		} else if (_type == Constants.NUMPAD) {
			_textColor = Constants.NUMPAD_TEXT_COLOR;
		} else if (_type == Constants.OPERATOR) {
			_textColor = Constants.OPERATOR_TEXT_COLOR;
		} else {
			
		}
	}

	private void buildLabel() {
		_buttonText = new Label(_text);
		_buttonText.setTextFill(_textColor);
		_buttonText.setFont(Font.font(Constants.FONT, _textSize));
		_buttonText.setAlignment(Pos.CENTER);
		_buttonText.setPrefSize(Constants.BUTTON_DIM, Constants.BUTTON_DIM);
		_buttonText.setOnMouseClicked(new Reporter());
	}
	
	public void addToPane(Pane root) {
		root.getChildren().addAll(_buttonRect, _buttonText);
		_buttonRect.setLayoutX(_xLayout);
		_buttonRect.setLayoutY(_yLayout);
		_buttonText.setLayoutX(_xLayout);
		_buttonText.setLayoutY(_yLayout);
	}
	
	public String getType() {
		return _type;
	}
	
	public String getText() {
		return _text;
	}
	
	public String getNumber() {
		if (_type != Constants.NUMPAD) {
			System.out.println("WOAH something went wrong with Button.getNumber()");
			return null;
		} else {
			return _text;
		}
	}
	
	private class Reporter implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			_calculator.compute(Button.this);
			event.consume();
		}
	}
	
	public void deactivate() {
		_buttonRect.setOpacity(Constants.GREYED_OUT);
		_buttonText.setOpacity(Constants.GREYED_OUT);
		_active = false;
	}
	
	public void activate() {
		_buttonRect.setOpacity(1.0);
		_buttonText.setOpacity(1.0);
		_active = true;
	}
	
	public Boolean isActive() {
		return _active;
	}
	
	public Boolean isSelected() {
		return _selected;
	}

	public void select() {
		System.out.println("SELECTING!");
		if (_type.equals(Constants.OPERATOR)) {
//			_buttonRect.setStroke(Color.web("FF0000"));
//			_buttonRect.setStrokeWidth(Constants.BORDER_WIDTH_SELECTED);
			_buttonRect.setFill(Constants.OPERATOR_BUTTON_COLOR_SELECTED);
		}
		_selected = true;
	}
	
	public void deselect() {
		if (_type.equals(Constants.OPERATOR)) {
//			_buttonRect.setStroke(Constants.BUTTON_BORDER_COLOR);
//			_buttonRect.setStrokeWidth(Constants.BUTTON_BORDER_WIDTH);
			_buttonRect.setFill(Constants.OPERATOR_BUTTON_COLOR);
		}
		_selected = false;
	}
	
	private void setupFlash() {
		_flash = new FillTransition(Duration.seconds(Constants.BUTTON_FLASH_DURATION), _buttonRect);
		if (_type.equals(Constants.NUMPAD)) {
			_flash.setFromValue(Constants.NUMPAD_BUTTON_COLOR);
			_flash.setToValue(Constants.NUMPAD_BUTTON_COLOR_SELECTED);
		} else if (_type.equals(Constants.OPERATOR)) {
			_flash.setFromValue(Constants.OPERATOR_BUTTON_COLOR);
			_flash.setToValue(Constants.OPERATOR_BUTTON_COLOR_SELECTED);
		} else if (_type.equals(Constants.GENERAL)) {
			_flash.setFromValue(Constants.GENERAL_BUTTON_COLOR);
			_flash.setToValue(Constants.GENERAL_BUTTON_COLOR_SELECTED);
		}
		_flash.setAutoReverse(true);
		_flash.setCycleCount(2);
	}
	
	public void flash() {
		_flash.play();
	}
	
}