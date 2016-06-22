package com.nukem.engine;

import org.lwjgl.opengl.Display;

import com.nukem.entityShader.Shader;
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
		Shader shader = new Shader();

		float vv = 1;
		// Fills the entire viewport
		float[] vertices = { -vv, -vv, -vv, vv, -vv, -vv, vv, vv, -vv, -vv, vv, -vv };

		// Indices' position
		int[] indices = { 0, 1, 3, 3, 1, 2 };

		// Create a RawModel
		RawModel model = loader.loadToVAO(vertices, indices);

		// While Loop (Update)
		while (!Display.isCloseRequested()) {
			
			// Render
			re.prepare();
			shader.start();
			re.render(model);
			shader.stop();

			// Updates the screen
			Window.updateDisplay();
		}

		// Deletes all data after closing
		shader.clean();
		loader.clean();
		Window.closeDisplay();
	}

}
