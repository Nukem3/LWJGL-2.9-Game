package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f pos = new Vector3f(512, 5, 12);
	private float pitch;
	private float yaw;
	private float roll;

	public void camInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			pos.x += 1f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			pos.x -= 1f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			pos.z -= 1f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			pos.z += 1f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			pos.y += 1f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			pos.y -= 1f;
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
