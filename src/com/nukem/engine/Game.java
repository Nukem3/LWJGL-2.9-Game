package com.nukem.engine;

import org.lwjgl.opengl.Display;

import com.nukem.entityShader.Shader;

import models.RawModel;
import models.TexturedModel;
import render.Loader;
import render.Renderer;
import textures.ModelTexture;

public class Game {

	public static void main(String[] args) {
		// Initialize Game Window
		Window.initWindow();
		// Initialize Loader & Renderer
		Loader loader = new Loader();
		Renderer re = new Renderer();
		Shader shader = new Shader();

		/*
		 * Fills the entire viewport
		 * float vv = 1;
		 * float[] vertices = { -vv,
		 * -vv, -vv, vv, -vv, -vv, vv, vv, -vv, -vv, vv, -vv };
		 */
		
		// Vertices for a quad
		float[] vertices = { -.4f, .4f, 0, -.4f, -.4f, 0, .4f, -.4f, 0, .4f, .4f, 0 };

		// Indices' position
		int[] indices = { 0, 1, 3, 3, 1, 2 };

		// Texture Coordinate
		float[] textureCoords = { 0, 0, 0, 1, 1, 1, 1, 0 };

		// Create a RawModel
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		// Create the texture
		ModelTexture texture = new ModelTexture(loader.loadTexture("texture"));
		// Create the texturedModel(RawModel + texture)
		TexturedModel texturedModel = new TexturedModel(model, texture);

		// While Loop (Update)
		while (!Display.isCloseRequested()) {

			// Render
			re.prepare();
			shader.start();
			re.render(texturedModel);
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
