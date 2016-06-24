package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import terrain.Terrain;
import textures.ModelTexture;

public class Game {

	public static void main(String[] args) {
		// Initialize Display & Render Engine
		Window.initWindow();
		Loader loader = new Loader();
		Master r = new Master();

		List<Entity> entities = new ArrayList<Entity>();

		// Crate
		RawModel crateModel = OBJ.loadOBJ("crate", loader);
		TexturedModel crate = new TexturedModel(crateModel, new ModelTexture(loader.loadTexture("/crate")));
		ModelTexture mt_crate = crate.getTexture();
		mt_crate.setShineDamper(10);
		mt_crate.setReflectivity(0.5f);

		TexturedModel grass = new TexturedModel(OBJ.loadOBJ("grass", loader),
				new ModelTexture(loader.loadTexture("/grass")));
		grass.getTexture().setHasTransparency(true);
		grass.getTexture().setUseFakeLight(true);

		// Entities
		Entity e0 = new Entity(crate, new Vector3f(0, 0, -25f), 0, 0, 0, 1);

		Camera cam = new Camera();

		// Sun
		Light light = new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1, 1, 1));

		// Terrain
		Terrain t = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("terrain_grass")));

		Random rand = new Random();
		for (int i = 0; i < 500; i++) {
			entities.add(
					new Entity(grass, new Vector3f(rand.nextFloat() * 752, 0, rand.nextFloat() * - 1000), 0, 0, 0, 3));
		}

		entities.add(e0);

		// While Loop (Update)
		while (!Display.isCloseRequested()) {
			cam.camInput();

			// Render
			r.render(light, cam);
			r.processTerrain(t);

			for (Entity entity : entities) {
				r.processEntity(entity);
			}
			// Updates the screen
			Window.updateDisplay();
		}

		// Deletes all data after closing
		r.clean();
		loader.clean();
		Window.closeDisplay();
	}

}
