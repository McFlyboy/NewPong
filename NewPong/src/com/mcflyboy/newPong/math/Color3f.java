package com.mcflyboy.newPong.math;

public class Color3f {
	public float r, g, b;
	public Color3f() {
		this(1f, 1f, 1f);
	}
	public Color3f(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	public Color3f(int rgbCode) {
		r = (float)((rgbCode & 0xff0000) >> 16) / 255f;
		g = (float)((rgbCode & 0xff00) >> 8) / 255f;
		b = (float)(rgbCode & 0xff) / 255f;
	}
	public Color3f(Color3f color) {
		r = color.r;
		g = color.g;
		b = color.b;
	}
	public float getUsableRed() {
		return getUsableValue(r);
	}
	public float getUsableGreen() {
		return getUsableValue(g);
	}
	public float getUsableBlue() {
		return getUsableValue(b);
	}
	public float getUsableBrightness() {
		return (getUsableRed() + getUsableGreen() + getUsableBlue()) / 3f;
	}
	public float getBrightness() {
		return (r + g + b) / 3f;
	}
	public void setBrightness(float targetBrightness) {
		targetBrightness = getUsableValue(targetBrightness);
		float currentBrightness = getBrightness();
		if(currentBrightness == 0f) {
			r = targetBrightness;
			g = targetBrightness;
			b = targetBrightness;
			return;
		}
		float adjustment = targetBrightness / currentBrightness;
		mul(adjustment);
	}
	public Color3f getAsMonochrome() {
		float brightness = getUsableBrightness();
		return new Color3f(brightness, brightness, brightness);
	}
	public Color3f getMul(float scalar) {
		return new Color3f(r * scalar, g * scalar, b * scalar);
	}
	public void mul(float scalar) {
		r *= scalar;
		g *= scalar;
		b *= scalar;
	}
	public Color3f getNegate() {
		return new Color3f(0.5f - (r - 0.5f), 0.5f - (g - 0.5f), 0.5f - (b - 0.5f));
	}
	public void negate() {
		r = 0.5f - (r - 0.5f);
		g = 0.5f - (g - 0.5f);
		b = 0.5f - (b - 0.5f);
	}
	@Override
	public String toString() {
		return String.format("(%f, %f, %f)", r, g, b);
	}
	private float getUsableValue(float value) {
		return Math.max(Math.min(value, 1f), 0f);
	}
}
