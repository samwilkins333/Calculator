package myCalculator;

import javafx.scene.paint.Color;

public class Constants {
	
	//GLOBAL
	public static final int ARC = 8;
	public static final int BUTTON_COUNT = 50;
	public static final int GENERAL_TEXT_SIZE = 20;
	public static final int GENERAL_SMALL_TEXT_SIZE = 12;
	public static final double OUTPUT_TEXT_SIZE = 43;
	public static final String FONT = "Courier New";
	public static final Color OUTPUT_TEXT_COLOR = Color.BLACK;
	public static final double OUTPUT_X_LAYOUT = 30;
	public static final double OUTPUT_Y_LAYOUT = 25;
	public static final double GREYED_OUT = 0.7;
	public static final double BORDER_WIDTH_SELECTED = 3;
	public static final Color BORDER_COLOR_SELECTED = Color.web("FF0000");
	public static final double BUTTON_FLASH_DURATION = 0.11;
	
	//SCENE INFO
	public static final int SCENE_WIDTH = 865;
	public static final int SCENE_HEIGHT = 525;
	public static final String BACKGROUND_COLOR = "-fx-background-color: #FFFFEE;";
	
	//DISPLAY
	public static final Color DISPLAY_COLOR = Color.BLANCHEDALMOND; 
	public static final Color DISPLAY_COLOR_SELECTED = Color.web("FFEE55");
	public static final int DISPLAY_OFFSET = 15;
	public static final int DISPLAY_WIDTH = SCENE_WIDTH - (2 * DISPLAY_OFFSET);
	public static final int DISPLAY_HEIGHT = 70;
	public static final Color DISPLAY_BORDER_COLOR = Color.BLACK;
	public static final double DISPLAY_BORDER_WIDTH = 2;
	
	//GENERAL BUTTONS
	public static final Color GENERAL_BUTTON_COLOR = Color.web("CCCCCC");
	public static final Color GENERAL_BUTTON_COLOR_SELECTED = Color.web("AAAAAA");
	public static final Color GENERAL_TEXT_COLOR = Color.web("444444");
	public static final Color BUTTON_BORDER_COLOR = DISPLAY_BORDER_COLOR;
	public static final double BUTTON_BORDER_WIDTH = 1;
	public static final double BUTTON_GRID_INC = DISPLAY_OFFSET + DISPLAY_HEIGHT;
	public static final double BUTTON_DIM = DISPLAY_HEIGHT;
	public static final String GENERAL = "GENERAL";
	
	//OPERATOR BUTTONS
	public static final Color OPERATOR_BUTTON_COLOR = Color.ORANGE;
	public static final Color OPERATOR_BUTTON_COLOR_SELECTED = Color.RED;
	public static final Color OPERATOR_TEXT_COLOR = Color.web("FFFFFF");
	public static final String OPERATOR = "OPERATOR";
	
	//NUMPAD BUTTONS
	public static final Color NUMPAD_BUTTON_COLOR = Color.web("CCCDDD");
	public static final Color NUMPAD_BUTTON_COLOR_SELECTED = Color.web("6655CC");
	public static final Color NUMPAD_TEXT_COLOR = Color.web("000000");
	public static final String NUMPAD = "NUMPAD";
	
	//OPERATION TYPES
	public static final String M = "Multiply";
	public static final String D = "Divide";
	public static final String A = "Add";
	public static final String S = "Subtract";
	
}