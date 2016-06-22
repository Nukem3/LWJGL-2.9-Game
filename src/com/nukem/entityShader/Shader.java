package com.nukem.entityShader;

public class Shader extends Program {

	private static final String v_file = "src/com/nukem/entityShader/vShader.txt";
	private static final String f_file = "src/com/nukem/entityShader/fShader.txt";
	
	public Shader() {
		super(v_file, f_file);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

}
