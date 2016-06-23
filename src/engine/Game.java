package engine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entityShader.Shader;
import models.RawModel;
import models.TexturedModel;
import render.Loader;
import render.OBJ;
import render.Renderer;
import textures.ModelTexture;

public class Game {

	public static void main(String[] args) {
		// Initialize Game Window
		Window.initWindow();
		// Initialize Engine
		Loader loader = new Loader();
		Shader shader = new Shader();
		Renderer re = new Renderer(shader);

		/*
		 * Fills the entire viewport float vv = 1; float[] vertices = { -vv,
		 * -vv, -vv, vv, -vv, -vv, vv, vv, -vv, -vv, vv, -vv };
		 */

		// Create a RawModel
		RawModel model = OBJ.loadOBJ("sphere", loader);
		TexturedModel texturedModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("/textures/texture")));
		ModelTexture tex = texturedModel.getTexture();
		tex.setShineDamper(10);
		tex.setReflectivity(1);

		Entity e0 = new Entity(texturedModel, new Vector3f(0, 0, -25f), 0, 0, 0, 1);

		Light light = new Light(new Vector3f(10, 10, -20), new Vector3f(1, 1, 1));

		Camera cam = new Camera();

		// While Loop (Update)

		while (!Display.isCloseRequested()) {

			float xx = 0;
			float yy = 0;
			float zz = 0;

			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				zz += 1;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				zz -= 1;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
				xx -= 1;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
				xx += 1;
			}

			e0.increaseRot(xx, yy, zz);

			cam.camInput();
			// Render
			re.prepare();
			shader.start();
			shader.loadLight(light);
			shader.loadViewMat(cam);
			re.render(e0, shader);
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
