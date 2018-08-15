package com.mcflyboy.newPong.entity.properties;

import com.mcflyboy.newPong.math.Color3f;
import com.mcflyboy.newPong.math.Vector2f;

public abstract class Appearance {
	private boolean visible;
	private Color3f color;
	private Vector2f scale;
	private float angle;
	public Appearance() {
		visible = true;
		color = new Color3f();
		scale = new Vector2f(1f, 1f);
		angle = 0f;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Color3f getColor() {
		return color;
	}
	public void setColor(Color3f color) {
		this.color = color;
	}
	public Vector2f getScale() {
		return scale;
	}
	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
	public void setScale(float scale) {
		this.scale = new Vector2f(scale, scale);
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	protected abstract float getUnscaledWidth();
	public float getWidth() {
		return getUnscaledWidth() * scale.x;
	}
	protected abstract float getUnscaledHeight();
	public float getHeight() {
		return getUnscaledHeight() * scale.y;
	}
}
