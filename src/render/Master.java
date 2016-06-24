package render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entityShader.Shader;
import models.TexturedModel;

public class Master {

	private Shader shader = new Shader();
	private Renderer re = new Renderer(shader);

	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();

	public void render(Light sun, Camera cam) {
		re.prepare();
		shader.start();
		shader.loadLight(sun);
		shader.loadViewMat(cam);
		re.render(entities);
		shader.stop();
		entities.clear();
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
	}

}
