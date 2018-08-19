package com.mcflyboy.newPong.entity.entities.gameEntities;

import com.mcflyboy.newPong.entity.entities.GameEntity;
import com.mcflyboy.newPong.math.Vector2f;

public class Ball extends GameEntity {
	private Vector2f direction;
	public Ball() {
		super();
		direction = new Vector2f();
	}
	public Vector2f getDirection() {
		return direction;
	}
	public void setDirection(Vector2f direction) {
		this.direction = direction;
	}
}
