package edu.damago.java.fx.hello;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * GUI application class.
 */
public class TTTGame extends Application {
	static private final String APP_ICON_PATH = "edu/damago/java/fx/hello/globe.png";
	static private final int WINDOW_WIDTH = 320;
	static private final int WINDOW_HEIGHT = 320;

	@SuppressWarnings("unused")
	private TTTController TTTController;


	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 */
	static public void main (final String[] args) {
		// creates a new instance of both HelloWorldApp and Stage.
		// uses both to call instance method start(Stage).
		launch(args);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start (final Stage window) throws Exception {
		// open resources: none

		// initialize GUI components
		final BorderPane rootPane = newRootPane();
		this.TTTController = new TTTController(rootPane);

		final Scene sceneGraph = new Scene(rootPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setScene(sceneGraph);
		window.setTitle("TTT GAME");
		window.getIcons().add(new Image(APP_ICON_PATH));
		window.show();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop () {
		// close resources: none
	}


	/**
	 * Returns a new root pane.
	 * @return the root pane
	 */
	static private BorderPane newRootPane () {		
		final GridPane gameGrid = new GridPane();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				final Button button = new Button("-");
				button.setFont(new Font(25));
				button.setPrefWidth(Integer.MAX_VALUE);
				button.setPrefHeight(Integer.MAX_VALUE);
				button.setPadding(new Insets(1, 1, 1, 1));
				gameGrid.add(button, i, j);
			}
		}
		
		final Button resetButton = new Button("NEW GAME");
		resetButton.setFont(new Font(15));
		resetButton.setPrefWidth(Integer.MAX_VALUE);
		
		final Label playerX = new Label("");
		final Label playerO = new Label("");
		playerX.setPrefWidth(Integer.MAX_VALUE);
		playerX.setAlignment(Pos.BASELINE_CENTER);
		playerO.setPrefWidth(Integer.MAX_VALUE);
		playerO.setAlignment(Pos.CENTER);
		
		final HBox topBox = new HBox(playerX, resetButton, playerO);
			
		final Label messageLabel = new Label("RESULT:");
		messageLabel.setFont(new Font(15));
		messageLabel.setPrefWidth(Integer.MAX_VALUE);
		messageLabel.setAlignment(Pos.BASELINE_CENTER);
		final Label messageOutput = new Label("");
		messageOutput.setFont(new Font(15));
		messageOutput.setPrefWidth(Integer.MAX_VALUE);
		messageOutput.setAlignment(Pos.BASELINE_CENTER);
		final HBox resultMessage = new HBox(messageLabel, messageOutput);
		
		final BorderPane rootPane = new BorderPane();
		rootPane.setTop(topBox);
		rootPane.setCenter(gameGrid);
		rootPane.setBottom(resultMessage);

		return rootPane;
	}
}