package com.nukem.engine;

import org.lwjgl.opengl.Display;

public class Game {

	public static void main(String[] args) {
		// Initialize Game Window
		Window.initWindow();
		
		// While Loop (Update)
		while (!Display.isCloseRequested()) {
			// Updates the screen
			Window.updateDisplay();
		}
	}
	
}
