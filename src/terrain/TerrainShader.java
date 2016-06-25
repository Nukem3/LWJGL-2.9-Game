package terrain;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Light;
import entityShader.Program;
import tools.Maths;

public class TerrainShader extends Program {

	private static final String v_file = "src/terrain/tVS.txt";
	private static final String f_file = "src/terrain/tFS.txt";

	private int loc_transMat;
	private int loc_projMat;
	private int loc_viewMat;
	private int loc_lightPos;
	private int loc_lightCol;
	private int loc_shine;
	private int loc_reflectivity;
	private int loc_skyColour;
	private int loc_back;
	private int loc_r;
	private int loc_g;
	private int loc_b;
	private int loc_blend;

	public TerrainShader() {
		super(v_file, f_file);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "pos");
		super.bindAttribute(1, "texCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocs() {
		loc_transMat = super.getUniformLoc("transMat");
		loc_projMat = super.getUniformLoc("projMat");
		loc_viewMat = super.getUniformLoc("viewMat");
		loc_lightPos = super.getUniformLoc("lightPos");
		loc_lightCol = super.getUniformLoc("lightCol");
		loc_shine = super.getUniformLoc("shineDamper");
		loc_reflectivity = super.getUniformLoc("reflectivity");
		loc_skyColour = super.getUniformLoc("skyColour");
		loc_back = super.getUniformLoc("backTex");
		loc_r = super.getUniformLoc("rTex");
		loc_g = super.getUniformLoc("gTex");
		loc_b = super.getUniformLoc("bTex");
		loc_blend = super.getUniformLoc("blend");
	}
	
	public void connectTextureUnits() {
		super.loadInt(loc_back, 0);
		super.loadInt(loc_r, 1);
		super.loadInt(loc_g, 2);
		super.loadInt(loc_b, 3);
		super.loadInt(loc_blend, 4);
	}

	public void loadSkyColour(float r, float g, float b) {
		super.loadVector(loc_skyColour, new Vector3f(r, g, b));
	}

	public void loadShine(float damper, float reflectivity) {
		super.loadFloat(loc_shine, damper);
		super.loadFloat(loc_reflectivity, reflectivity);
	}

	public void loadTransformationMatrix(Matrix4f mat) {
		super.loadMatrix(loc_transMat, mat);
	}

	public void loadLight(Light light) {
		super.loadVector(loc_lightPos, light.getPosition());
		super.loadVector(loc_lightCol, light.getCol());
	}

	public void loadViewMat(Camera cam) {
		Matrix4f viewMatrix = Maths.createViewMatrix(cam);
		super.loadMatrix(loc_viewMat, viewMatrix);
	}

	public void loadProjMat(Matrix4f proj) {
		super.loadMatrix(loc_projMat, proj);
	}

}
