package engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class Window {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	private static final int FPS_CAP = 120;

	public static String GAME_NAME = "Lowflow";
	public static String GAME_VERSION = "a0.0.1.5";

	private static long lastFrameTime;
	private static float delta;

	// Initialize Window method
	public static synchronized void initWindow() {
		ContextAttribs attribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);

		try {
			System.out.println("Creating Display...");
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle(GAME_NAME + " " + GAME_VERSION);
			System.out.println("Display - [OK]");
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.err.println("Display - [ERROR] Couldn't initialize display.");
		}

		System.out.println("Setting viewport...");
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		System.out.println("Viewport - OK");
		lastFrameTime = getCurrentTime();

	}

	// Display

	public static void updateDisplay() {
		Display.sync(FPS_CAP);
		Display.update();
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime) / 1000f;
		lastFrameTime = currentFrameTime;
	}

	public static void closeDisplay() {
		System.out.println("Destroying display...");
		Display.destroy();
		System.out.println("Display destroyed");
	}

	// Game timing

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public static float getFrameTimeSeconds() {
		return delta;
	}

	private static long getCurrentTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
}
