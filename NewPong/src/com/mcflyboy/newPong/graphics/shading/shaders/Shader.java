package com.mcflyboy.newPong.graphics.shading.shaders;

import com.mcflyboy.newPong.graphics.shading.ShaderProgram;
import com.mcflyboy.newPong.math.Vector2f;

public class Shader extends ShaderProgram {
	private int positionLocation;
	private static Shader shader;
	private Shader() {
		super("shader.vsh", "shader.fsh");
	}
	public static Shader getInstance() {
		if(shader == null) {
			shader = new Shader();
		}
		return shader;
	}
	@Override
	protected void bindAttribs() {
		super.bindAttrib(0, "vertex");
		super.bindAttrib(1, "texturecoord");
	}
	@Override
	protected void registerUniformLocations() {
		positionLocation = super.getUniformLocation("position");
	}
	public void loadPosition(Vector2f position) {
		super.loadVector2f(positionLocation, position);
	}
}
