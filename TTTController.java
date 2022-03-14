package edu.damago.java.fx.hello;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * Controller of the root pane.
 */
public class TTTController {

	private final BorderPane rootPane;
	private GridPane gameGrid;
	private int PLAYER_ONE;
	private HBox topBox;
	private Button resetButton;
	private Label playerX;
	private Label playerO;
	private HBox resultMessage;
	private Label messageOutput;

	/**
	 * Initializes a new instance.
	 * @param rootPane the root pane
	 */
	public TTTController (final BorderPane rootPane) {
		// initialize instance variables with view components
		this.rootPane = requireNonNull(rootPane);
		
		this.gameGrid = (GridPane) this.rootPane.getCenter();
		this.topBox = (HBox) this.rootPane.getTop();
		this.playerX = (Label) topBox.getChildren().get(0);
		this.resetButton = (Button) topBox.getChildren().get(1);
		this.playerO = (Label) topBox.getChildren().get(2);
		this.resultMessage = (HBox) (this.rootPane.getBottom());
		this.messageOutput = (Label) resultMessage.getChildren().get(1);
	
		
		// register event handlers for view component events
		final Button[][] buttons = new Button[3][3];
		for (int rowIndex = 0; rowIndex < buttons.length; ++rowIndex) {
			final Button[] rowButtons = buttons[rowIndex];
			for (int columnIndex = 0; columnIndex < rowButtons.length; ++columnIndex)
				rowButtons[columnIndex] = (Button) gameGrid.getChildren().get(rowIndex * rowButtons.length + columnIndex);
		}
		
		buttons[0][0].setOnAction( event -> this.move(buttons[0][0]) );
		buttons[0][1].setOnAction( event -> this.move(buttons[0][1]) );
		buttons[0][2].setOnAction( event -> this.move(buttons[0][2]) );
		buttons[1][0].setOnAction( event -> this.move(buttons[1][0]) );
		buttons[1][1].setOnAction( event -> this.move(buttons[1][1]) );
		buttons[1][2].setOnAction( event -> this.move(buttons[1][2]) );
		buttons[2][0].setOnAction( event -> this.move(buttons[2][0]) );
		buttons[2][1].setOnAction( event -> this.move(buttons[2][1]) );
		buttons[2][2].setOnAction( event -> this.move(buttons[2][2]) );
		
		//reset button
		this.resetButton.setOnAction(event -> this.reset());
	}
	
	private void reset () {
		gameGrid.setDisable(false);
		for (int i = 0; i < gameGrid.getChildren().size(); i++ ) {
			Button button = (Button) gameGrid.getChildren().get(i);
			button.setText("-");
		}
		PLAYER_ONE += 1;
		this.messageOutput.setText("");
	}
	
	private Button move (Button button) {
		if (PLAYER_ONE % 2 == 0) { 
			button.setText("X"); 
		} else { button.setText("O"); }
		PLAYER_ONE += 1;
			
		if (checkWinner()) {
			this.messageOutput.setText("WIN!");
			gameGrid.setDisable(true);
		}
		return button;		
	}
	
	private boolean checkWinner() {
		List<Character> values = new ArrayList<>();
		boolean won = false;
		for (int i = 0; i < this.gameGrid.getChildren().size(); i++) {
			char value = (((Labeled) this.gameGrid.getChildren().get(i)).getText()).charAt(0);
			values.add(value);
		}
		for (int i = 0; i < 9; i++) {
			if ((i == 0 || i == 1 || i == 2) && (values.get(i) != '-' && values.get(i) == values.get(i + 3) && values.get(i) == values.get(i + 6) )) {
				return true;
			}
			else if((i == 0 || i == 3 || i == 6) && (values.get(i) != '-' && values.get(i) == values.get(i + 1) && values.get(i) == values.get(i + 2) )) {
				return true;
			}
			else if ((i == 0) && (values.get(i) != '-' && values.get(i) == values.get(4) && values.get(i) == values.get(8))) {
				return true;
			}
			else if ((i == 2) && (values.get(i) != '-' && values.get(i) == values.get(4) && values.get(i) == values.get(6))) {
				return true;
			}
		}
		return won;
	}
}
