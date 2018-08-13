package myCalculator;

import java.util.ArrayList;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PaneOrganizer {
	private App _app;
	private Stage _stage;
	private Pane _root;
	private Rectangle _displayMatting;
	private ArrayList<Button> _buttons;
	private ArrayList<String> _symbols;
	private Pane _keyPane;
	private FillTransition _equals;
	
	private Calculator _calculator;
	
	public PaneOrganizer(App app, Stage stage) {
		_app = app;
		_stage = stage;
		_root = new Pane();
		_root.setStyle(Constants.BACKGROUND_COLOR);
		_keyPane = new Pane();
		_root.getChildren().add(_keyPane);
		_keyPane.setFocusTraversable(true);
		_keyPane.requestFocus();
		_keyPane.addEventHandler(KeyEvent.KEY_TYPED, new KeyTypedHandler());
		_keyPane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressedHandler());

		this.addDisplayMatting(this.setUpDisplay());
		this.addButtons();
	}
	
	private Label setUpDisplay() {
		Label output = new Label("0");
		output.setFont(Font.font(Constants.FONT, FontWeight.BOLD, Constants.OUTPUT_TEXT_SIZE));
		output.setTextFill(Constants.OUTPUT_TEXT_COLOR);
		output.setAlignment(Pos.BASELINE_RIGHT);
		output.setLayoutX(Constants.DISPLAY_OFFSET);
		output.setLayoutY(Constants.DISPLAY_OFFSET);
		output.setPrefSize(Constants.DISPLAY_WIDTH - 15, Constants.DISPLAY_HEIGHT);
		
		_calculator = new Calculator(this, output);
		
		return output;
	}

	public Pane getRoot() {
		return _root;
	}	

	private void addDisplayMatting(Label output) {
		_displayMatting = new Rectangle(
			Constants.DISPLAY_OFFSET + 1, 
			Constants.DISPLAY_OFFSET + 1, 
			Constants.DISPLAY_WIDTH - 2, 
			Constants.DISPLAY_HEIGHT - 2
		);
		_displayMatting.setFill(Constants.DISPLAY_COLOR);
		_displayMatting.setArcWidth(Constants.ARC);
		_displayMatting.setArcHeight(Constants.ARC);
		_displayMatting.setStroke(Constants.DISPLAY_BORDER_COLOR);
		_displayMatting.setStrokeWidth(Constants.DISPLAY_BORDER_WIDTH);
		_root.getChildren().addAll(_displayMatting, output);
		_equals = new FillTransition(Duration.seconds(2), _displayMatting);
		_equals.setCycleCount(2);
		_equals.setFromValue(Constants.DISPLAY_COLOR);
		_equals.setToValue(Constants.DISPLAY_COLOR_SELECTED);
		_equals.setAutoReverse(true);
	}
	
	public void playDisplay() {
		_equals.play();
	}

	private void addButtons() {
		_buttons = new ArrayList<Button>();
		this.buildSymbolsLibrary();
		
		int counter = -1;
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 10; x++) {
				counter++;
				Button button = new Button(x, y, _symbols.get(counter), counter, _calculator);  
				_buttons.add(button);
				button.addToPane(_root);
			}
		}
		_calculator.setButtonAssociations(_buttons, _symbols);
	}

	private void buildSymbolsLibrary() {
		_symbols = new ArrayList<String>();
		
		_symbols.add("(");
		_symbols.add(")");
		_symbols.add("mc");
		_symbols.add("m+");
		_symbols.add("m-");
		_symbols.add("mr");
		_symbols.add("AC");
		_symbols.add("<--");
		_symbols.add("-->");
		_symbols.add("/");
		
		
		_symbols.add("2nd");
		_symbols.add("x^2");
		_symbols.add("x^3");
		_symbols.add("x^y");
		_symbols.add("e^x");
		_symbols.add("10^x");
		_symbols.add("7");
		_symbols.add("8");
		_symbols.add("9");
		_symbols.add("X");
		
		_symbols.add("1/x");
		_symbols.add("x^(1/2)");
		_symbols.add("x^(1/3)");
		_symbols.add("x^(1/y)");
		_symbols.add("ln");
		_symbols.add("log");
		_symbols.add("4");
		_symbols.add("5");
		_symbols.add("6");
		_symbols.add("-");
		
		_symbols.add("x!");
		_symbols.add("sin");
		_symbols.add("cos");
		_symbols.add("tan");
		_symbols.add("e");
		_symbols.add("EE");
		_symbols.add("1");
		_symbols.add("2");
		_symbols.add("3");
		_symbols.add("+");
		
		_symbols.add("Rad");
		_symbols.add("sinh");
		_symbols.add("cosh");
		_symbols.add("tanh");
		_symbols.add("pi");
		_symbols.add("Rand");
		_symbols.add("0");
		_symbols.add(".");
		_symbols.add("+/-");
		_symbols.add("=");
	}
	
	private class KeyTypedHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			String keyTyped = event.getCharacter();
			System.out.printf("KeyTyped detects %s\n", keyTyped.toString());
			if (keyTyped.equals("q")) {
				System.exit(0);
				return;
			}
			if (keyTyped.equals("r")) {
				_app.start(_stage);
			}
			if (_symbols.contains(keyTyped)) {
				_calculator.compute(_buttons.get(_symbols.indexOf(keyTyped)));
			}
			if (keyTyped.equals("*")) {
				_calculator.compute(_buttons.get(_symbols.indexOf("X")));
			}
			event.consume();
		}
	}
	
	private class KeyPressedHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			KeyCode keyPressed = event.getCode();
			System.out.printf("KeyPressed detects %s\n", keyPressed.toString());
			if (keyPressed == KeyCode.ENTER) {
				_calculator.compute(_buttons.get(_symbols.indexOf("=")));
			} else if (keyPressed == KeyCode.LEFT) {
				_calculator.compute(_buttons.get(_symbols.indexOf("<--")));
			} else if (keyPressed == KeyCode.RIGHT) {
				_calculator.compute(_buttons.get(_symbols.indexOf("-->")));
			} else if (keyPressed == KeyCode.C) {
				_calculator.compute(_buttons.get(_symbols.indexOf("AC")));
			}
			event.consume();
		}
	}
}