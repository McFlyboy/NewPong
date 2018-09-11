package com.mcflyboy.newPong.entity.entities.gameplay;

import com.mcflyboy.newPong.entity.entities.GameEntity;
import com.mcflyboy.newPong.math.Vector2f;

public class Ball extends GameEntity {
	private Vector2f acceleration;
	public Ball() {
		this(new Vector2f());
	}
	public Ball(Vector2f startPosition) {
		super();
		acceleration = new Vector2f();
		resetBall(startPosition);
	}
	public Vector2f getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(Vector2f acceleration) {
		this.acceleration = acceleration;
	}
	public void updateVelocity(float deltaTime) {
		acceleration = super.getVelocity().getNegate().getMul(0.4f);
		super.getVelocity().add(acceleration.getMul(deltaTime));
	}
	public void resetBall(Vector2f position) {
		super.setPosition(new Vector2f(position));
		super.setVelocity(new Vector2f(-2f, -0.5f));
	}
}
