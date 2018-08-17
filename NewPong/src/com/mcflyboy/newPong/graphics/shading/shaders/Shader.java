package com.mcflyboy.newPong.graphics.shading.shaders;

import com.mcflyboy.newPong.entity.properties.Appearance;
import com.mcflyboy.newPong.graphics.shading.ShaderProgram;
import com.mcflyboy.newPong.math.Vector2f;

public class Shader extends ShaderProgram {
	private int positionLocation;
	private int colorLocation;
	private int scaleLocation;
	private int angleLocation;
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
		colorLocation = super.getUniformLocation("color");
		scaleLocation = super.getUniformLocation("scale");
		angleLocation = super.getUniformLocation("angle");
	}
	public void loadPosition(Vector2f position) {
		super.loadVector2f(positionLocation, position);
	}
	public void loadAppearace(Appearance appearance) {
		super.loadColor3f(colorLocation, appearance.getColor());
		super.loadVector2f(scaleLocation, appearance.getScale());
		super.loadFloat(angleLocation, appearance.getAngle());
	}
}