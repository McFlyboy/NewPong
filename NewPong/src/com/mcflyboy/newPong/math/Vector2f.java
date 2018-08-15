package com.mcflyboy.newPong.math;

public class Vector2f {
	public float x, y;
	public Vector2f() {
		this(0f, 0f);
	}
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public Vector2f(Vector2f vec) {
		x = vec.x;
		y = vec.y;
	}
	public Vector2f getAdd(Vector2f vec) {
		float x = this.x + vec.x;
		float y = this.y + vec.y;
		return new Vector2f(x, y);
	}
	public void add(Vector2f vec) {
		x += vec.x;
		y += vec.y;
	}
	public Vector2f getSub(Vector2f vec) {
		float x = this.x - vec.x;
		float y = this.y - vec.y;
		return new Vector2f(x, y);
	}
	public void sub(Vector2f vec) {
		x -= vec.x;
		y -= vec.y;
	}
	public Vector2f getMul(float scalar) {
		float x = this.x * scalar;
		float y = this.y * scalar;
		return new Vector2f(x, y);
	}
	public void mul(float scalar) {
		x *= scalar;
		y *= scalar;
	}
	public Vector2f getDiv(float scalar) {
		if(scalar == 0f) {
			System.err.println("Tried to divide by zero!");
			return new Vector2f(0f, 0f);
		}
		float x = this.x / scalar;
		float y = this.y / scalar;
		return new Vector2f(x, y);
	}
	public void div(float scalar) {
		if(scalar == 0f) {
			System.err.println("Tried to divide by zero!");
			x = 0f;
			y = 0f;
			scalar = 1f;
		}
		x /= scalar;
		y /= scalar;
	}
	public float getLength() {
		return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	public Vector2f getNormalize() {
		float length = getLength();
		if(length == 0f) {
			System.err.println("Tried to divide by zero!");
			return new Vector2f(0f, 0f);
		}
		float x = this.x / length;
		float y = this.y / length;
		return new Vector2f(x, y);
	}
	public void normalize() {
		float length = getLength();
		if(length == 0f) {
			System.err.println("Tried to divide by zero!");
			x = 0f;
			y = 0f;
			length = 1f;
		}
		x /= length;
		y /= length;
	}
	public float getDot(Vector2f vec) {
		return x * vec.x + y * vec.y;
	}
	public float getAngleBetween(Vector2f vec) {
		float dot = getDot(vec);
		float cosTheta = dot / (getLength() * vec.getLength());
		double theta = Math.acos(cosTheta);
		return (float)Math.toDegrees(theta);
	}
	public float getAngle() {
		float angle = getAngleBetween(new Vector2f(1f, 0f));
		if(getAngleBetween(new Vector2f(0f, 1f)) > 90f) {
			angle *= -1f;
		}
		return angle;
	}
	public static Vector2f createVector(float angle, float length) {
		double radians = Math.toRadians(angle);
		float cos = (float)Math.cos(radians);
		float sin = (float)Math.sin(radians);
		return new Vector2f(cos * length, sin * length);
	}
	public static Vector2f createVector(float angle) {
		return createVector(angle, 1f);
	}
	public Vector2f getRotate(float angle) {
		double radians = Math.toRadians(angle);
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		float x = (float)(this.x * cos - this.y * sin);
		float y = (float)(this.x * sin + this.y * cos);
		return new Vector2f(x, y);
	}
	public void rotate(float angle) {
		double radians = Math.toRadians(angle);
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		float x = (float)(this.x * cos - this.y * sin);
		float y = (float)(this.x * sin + this.y * cos);
		this.x = x;
		this.y = y;
	}
	public Vector2f getNegate() {
		return new Vector2f(-x, -y);
	}
	public void negate() {
		x *= -1f;
		y *= -1f;
	}
	@Override
	public String toString() {
		return String.format("[%f, %f]", x, y);
	}
}
