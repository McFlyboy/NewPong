package com.mcflyboy.newPong.entity;

import com.mcflyboy.newPong.math.Vector2f;

public class Entity {
	private Vector2f position;
	public Entity() {
		position = new Vector2f();
	}
	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
}
