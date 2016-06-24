package engine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import render.Loader;
import render.Master;
import render.OBJ;
import textures.ModelTexture;

public class Game {

	public static void main(String[] args) {
		// Initialize Display & Render Engine
		Window.initWindow();
		Loader loader = new Loader();
		Master r = new Master(); 
		
		// Crate
		RawModel crateModel = OBJ.loadOBJ("crate", loader); // Load the Model
		TexturedModel crate = new TexturedModel(crateModel, new ModelTexture(loader.loadTexture("/textures/crate"))); // Load the texture
		ModelTexture mt_crate = crate.getTexture();
		mt_crate.setShineDamper(10);
		mt_crate.setReflectivity(0.5f);
		
		// Sphere
		RawModel sphereModel = OBJ.loadOBJ("sphere", loader); // Load the Model
		TexturedModel sphere = new TexturedModel(sphereModel, new ModelTexture(loader.loadTexture("/textures/texture"))); // Load the texture
		ModelTexture mt_sphere = sphere.getTexture();
		mt_sphere.setShineDamper(10);
		mt_sphere.setReflectivity(0.5f);
		
		// Dragon
		RawModel dragonModel = OBJ.loadOBJ("dragon", loader); // Load the Model
		TexturedModel dragon = new TexturedModel(dragonModel, new ModelTexture(loader.loadTexture("/textures/texture"))); // Load the texture
		ModelTexture mt_dragon = dragon.getTexture();
		mt_dragon.setShineDamper(10);
		mt_dragon.setReflectivity(0.5f);
	
		// Entities
		Entity e0 = new Entity(crate, new Vector3f(0, 0, -25f), 0, 0, 0, 1);
		Entity e1 = new Entity(sphere, new Vector3f(10, 0, -25f), 0, 0, 0, 1);
		Entity e2 = new Entity(dragon, new Vector3f(-20, 0, -25f), 0, 0, 0, 1);
		
		Camera cam = new Camera();
		
		// Sun
		Light light = new Light(new Vector3f(10, 10, -20), new Vector3f(1, 1, 1));
		
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
			r.render(light, cam);
			r.processEntity(e0);
			r.processEntity(e1);
			r.processEntity(e2);
			// Updates the screen
			Window.updateDisplay();
		}

		// Deletes all data after closing
		r.clean();
		loader.clean();
		Window.closeDisplay();
	}

}
