package com.nukem.entityShader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class Program {

	private int programID;
	private int vID;
	private int fID;

	public Program(String vFile, String fFile) {
		vID = loadShader(vFile, GL20.GL_VERTEX_SHADER);
		fID = loadShader(fFile, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vID);
		GL20.glAttachShader(programID, fID);
		bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
	}

	public void start() {
		GL20.glUseProgram(programID);
	}

	public void stop() {
		GL20.glUseProgram(0);
	}

	public void clean() {
		stop();
		System.out.println("Detaching Vertex & Fragment Shader...");
		GL20.glDetachShader(programID, vID);
		GL20.glDetachShader(programID, fID);
		System.out.println("Vertex and Fragment Shader have been detached");

		System.out.println("Deleting Vertex & Fragment Shader...");
		GL20.glDeleteShader(vID);
		GL20.glDeleteShader(fID);
		System.out.println("Deleting Shader Program...");
		GL20.glDeleteProgram(programID);
		System.out.println("Shader Program deleted");
	}

	protected abstract void bindAttributes();

	protected void bindAttribute(int attribute, String variableName) {
		GL20.glBindAttribLocation(programID, attribute, variableName);
	}

	// Read/Load the Vertex & Fragment shader
	private static int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader rd = new BufferedReader(new FileReader(file));
			String line;
			while ((line = rd.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}
			rd.close();
		} catch (IOException e) {
			System.err.println("Shader - Error Could not read shader file(s)");
			e.printStackTrace();
			System.exit(-1);
		}
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 300));
			System.err.println("Shader - Could not read shader file(s)");
			System.exit(-1);
		}
		return shaderID;
	}
}
