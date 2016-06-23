package entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {

	private Vector3f position;
	private Vector3f col;

	public Light(Vector3f position, Vector3f col) {
		super();
		this.position = position;
		this.col = col;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getCol() {
		return col;
	}

	public void setCol(Vector3f col) {
		this.col = col;
	}

}
