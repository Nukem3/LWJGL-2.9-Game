package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f pos = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;

	public void camInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			pos.z -= .02f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			pos.z += .02f;
		}
	}

	public Vector3f getPos() {
		return pos;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

}
