package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;

public class Minesweeper {
	public static void main(String[] args) throws Exception {
		Field field = new Field(9, 9, 1);
		ConsoleUI ui = new ConsoleUI(field);
		ui.play();				
	}
}
