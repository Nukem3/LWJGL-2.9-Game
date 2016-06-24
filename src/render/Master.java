package render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import engine.Window;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entityShader.Shader;
import models.TexturedModel;
import terrain.Terrain;
import terrain.TerrainRenderer;
import terrain.TerrainShader;

public class Master {

	// FOV/NEAR_PLANE/FAR_PLANE
	private static final float FOV = 70;
	private static final float NEAR = 0.1f;
	private static final float FAR = 1000;

	private Matrix4f projMat;

	private Shader shader = new Shader();
	private EntityRenderer re;

	private TerrainRenderer tRe;
	private TerrainShader tS = new TerrainShader();

	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	private List<Terrain> terrs = new ArrayList<Terrain>();

	public Master() {
		enableCulling();
		createProjMat();
		re = new EntityRenderer(shader, projMat);
		tRe = new TerrainRenderer(tS, projMat);
	}

	public static void enableCulling() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}

	public static void disableCulling() {
		GL11.glDisable(GL11.GL_CULL_FACE);
	}

	public void render(Light sun, Camera cam) {
		prepare();
		shader.start();
		shader.loadLight(sun);
		shader.loadViewMat(cam);
		re.render(entities);
		shader.stop();
		tS.start();
		tS.loadLight(sun);
		tS.loadViewMat(cam);
		tRe.render(terrs);
		tS.stop();
		entities.clear();
		terrs.clear();
	}

	public void processTerrain(Terrain terrain) {
		terrs.add(terrain);
	}

	public void processEntity(Entity entity) {
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if (batch != null) {
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}

	public void clean() {
		shader.clean();
		tS.clean();
	}

	// Clears the color every second
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.0125f, 0.25f, 0.5f, 1);
	}

	// Create Projection Matrix
	private void createProjMat() {
		float aspectRatio = (float) Window.getWidth() / (float) Window.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR - NEAR;

		projMat = new Matrix4f();
		projMat.m00 = x_scale;
		projMat.m11 = y_scale;
		projMat.m22 = -((FAR + NEAR) / frustum_length);
		projMat.m23 = -1;
		projMat.m32 = -((2 * NEAR * FAR) / frustum_length);
		projMat.m33 = 0;
	}

}
