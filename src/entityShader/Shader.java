package entityShader;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Light;
import tools.Maths;

public class Shader extends Program {

	private static final String v_file = "src/entityShader/vShader.txt";
	private static final String f_file = "src/entityShader/fShader.txt";

	private int loc_transMat;
	private int loc_projMat;
	private int loc_viewMat;
	private int loc_lightPos;
	private int loc_lightCol;
	private int loc_shine;
	private int loc_reflectivity;
	private int loc_useFakeLight;
	private int loc_skyColour;

	public Shader() {
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
		loc_transMat = super.getUniformLoc("transformationMatrix");
		loc_projMat = super.getUniformLoc("projectionMatrix");
		loc_viewMat = super.getUniformLoc("viewMatrix");
		loc_lightPos = super.getUniformLoc("lightPosition");
		loc_lightCol = super.getUniformLoc("lightColour");
		loc_shine = super.getUniformLoc("shineDamper");
		loc_reflectivity = super.getUniformLoc("reflectivity");
		loc_useFakeLight = super.getUniformLoc("useFakeLight");
		loc_skyColour = super.getUniformLoc("skyColour");
	}

	public void loadSkyColour(float r, float g, float b) {
		super.loadVector(loc_skyColour, new Vector3f(r,g,b));
	}

	public void loadFakeLightVariable(boolean useFake) {
		super.loadBoolean(loc_useFakeLight, useFake);
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
