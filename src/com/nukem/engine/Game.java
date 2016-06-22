package com.nukem.engine;

import org.lwjgl.opengl.Display;

import com.nukem.model.RawModel;
import com.nukem.render.Loader;
import com.nukem.render.Renderer;

public class Game {

	public static void main(String[] args) {
		// Initialize Game Window
		Window.initWindow();
		// Initialize Loader & Renderer
		Loader loader = new Loader();
		Renderer re = new Renderer();

		// Vertices' position
		float[] vertices = { -0.5f, 0.5f, 0f, -0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, 0.5f, 0f, -0.5f,
				0.5f, 0f };

		// Create a RawModel
		RawModel model = loader.loadToVAO(vertices);
		
		// While Loop (Update)
		while (!Display.isCloseRequested()) {
			
			// Render
			re.prepare();
			re.render(model);
			
			// Updates the screen
			Window.updateDisplay();
		}

		// Deletes all data after closing
		loader.clean();
		Window.closeDisplay();
	}

}
