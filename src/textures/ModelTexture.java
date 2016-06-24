package textures;

public class ModelTexture {

	private int textureID;

	private float shineDamper = 1;
	private float reflectivity = 0;

	private boolean hasTransparency = false;
	private boolean useFakeLight = false;

	public int getTextureID() {
		return textureID;
	}

	public boolean isUseFakeLight() {
		return useFakeLight;
	}

	public void setUseFakeLight(boolean useFakeLight) {
		this.useFakeLight = useFakeLight;
	}

	public boolean isHasTransparency() {
		return hasTransparency;
	}

	public void setHasTransparency(boolean hasTransparency) {
		this.hasTransparency = hasTransparency;
	}

	public void setTextureID(int textureID) {
		this.textureID = textureID;
	}

	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflect) {
		this.reflectivity = reflect;
	}

	public ModelTexture(int id) {
		this.textureID = id;
	}

	public int getID() {
		return this.textureID;
	}
}
