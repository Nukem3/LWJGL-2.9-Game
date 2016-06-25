package terrain;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import models.RawModel;
import textures.ModelTexture;
import textures.TerrainPack;
import tools.Maths;

public class TerrainRenderer {

	private TerrainShader shader;

	public TerrainRenderer(TerrainShader shader, Matrix4f projMat) {
		this.shader = shader;
		shader.start();
		shader.loadProjMat(projMat);
		shader.connectTextureUnits();
		shader.stop();
	}

	public void render(List<Terrain> terrs) {
		for (Terrain terr : terrs) {
			prepareTerr(terr);
			prepareMat(terr);
			GL11.glDrawElements(GL11.GL_TRIANGLES, terr.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			unbindTexModel();
		}
	}

	// Bind/Load Textures
	private void prepareTerr(Terrain terr) {
		RawModel rawModel = terr.getModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		bindTexture(terr);
		shader.loadShine(1, 0);
	}

	private void bindTexture(Terrain terrain) {
		TerrainPack texPack = terrain.getTexPack();

		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texPack.getBack().getTextureID());

		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texPack.getR().getTextureID());

		GL13.glActiveTexture(GL13.GL_TEXTURE2);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texPack.getG().getTextureID());

		GL13.glActiveTexture(GL13.GL_TEXTURE3);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texPack.getB().getTextureID());

		GL13.glActiveTexture(GL13.GL_TEXTURE4);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, terrain.getBlend().getTextureID());

	}

	// Unbind
	private void unbindTexModel() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}

	// Set up the Projection Matrix
	private void prepareMat(Terrain terr) {
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(new Vector3f(terr.getX(), 0, terr.getZ()), 0,
				0, 0, 1);
		shader.loadTransformationMatrix(transformationMatrix);
	}
}
