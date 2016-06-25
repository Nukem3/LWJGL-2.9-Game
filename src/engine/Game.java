package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import obj.ModelData;
import obj.OBJFileLoader;
import render.Loader;
import render.Master;
import terrain.Terrain;
import textures.ModelTexture;
import textures.TerrainPack;
import textures.TerrainTexture;

public class Game {

	public static void main(String[] args) {
		// Initialize Display & Render Engine
		Window.initWindow();
		Loader loader = new Loader();
		Master r = new Master();

		TerrainTexture back = new TerrainTexture(loader.loadTexture("terrain_dirt"));
		TerrainTexture red = new TerrainTexture(loader.loadTexture("terrain_grass"));
		TerrainTexture green = new TerrainTexture(loader.loadTexture("rock"));
		TerrainTexture blue = new TerrainTexture(loader.loadTexture("terrain_grass1"));

		TerrainPack tpack = new TerrainPack(back, red, green, blue);
		TerrainTexture blend = new TerrainTexture(loader.loadTexture("blendMap"));

		List<Entity> entities = new ArrayList<Entity>();

		// Crate
		ModelData crateData = OBJFileLoader.loadOBJ("crate");
		RawModel crateModel = loader.loadToVAO(crateData.getVertices(), crateData.getTextureCoords(),
				crateData.getNormals(), crateData.getIndices());
		TexturedModel crate = new TexturedModel(crateModel, new ModelTexture(loader.loadTexture("crate")));

		// Grass
		ModelData grassData = OBJFileLoader.loadOBJ("grass");
		RawModel grassModel = loader.loadToVAO(grassData.getVertices(), grassData.getTextureCoords(),
				grassData.getNormals(), grassData.getIndices());
		TexturedModel grass = new TexturedModel(grassModel, new ModelTexture(loader.loadTexture("grass")));
		grass.getTexture().setHasTransparency(true);
		grass.getTexture().setUseFakeLight(true);

		// Entities
		Entity e0 = new Entity(crate, new Vector3f(0, 0, 0), 0, 0, 0, 1);

		Camera cam = new Camera();

		// Sun
		Light light = new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1, 1, 1));

		// Terrain
		Terrain t = new Terrain(0, -1, loader, tpack, blend);

		Random rand = new Random();
		for (int i = 0; i < 400; i++) {

			float xx = rand.nextFloat() * 752;
			float zz = rand.nextFloat() * -400;

			if (i % 7 == 0) {
				entities.add(new Entity(grass, new Vector3f(xx, 0, zz), 0, 0, 0, 2));
			}
		}

		entities.add(e0);

		// While Loop (Update)
		while (!Display.isCloseRequested()) {
			cam.camInput();

			e0.increaseRot(0, 0.1f, 0);

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
