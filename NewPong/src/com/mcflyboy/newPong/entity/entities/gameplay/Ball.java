package com.mcflyboy.newPong.entity.entities.gameplay;

import com.mcflyboy.newPong.entity.entities.GameEntity;
import com.mcflyboy.newPong.math.Vector2f;

public class Ball extends GameEntity {
	private static final float MIN_SPEED = 0.75f;
	private static final float MAX_SPEED = 5f;
	private static final float DECCELERATION = 0.2f;
	private Vector2f acceleration;
	public Ball() {
		this(new Vector2f());
	}
	public Ball(Vector2f startPosition) {
		super();
		acceleration = new Vector2f();
		reset(startPosition);
	}
	public Vector2f getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(Vector2f acceleration) {
		this.acceleration = acceleration;
	}
	public void updateVelocity(float deltaTime) {
		acceleration = super.getVelocity().getNegate().getMul(DECCELERATION);
		super.getVelocity().add(acceleration.getMul(deltaTime));
		if(super.getVelocity().getLength() < MIN_SPEED) {
			adjustSpeed(MIN_SPEED);
		}
		else if(super.getVelocity().getLength() > MAX_SPEED) {
			adjustSpeed(MAX_SPEED);
		}
	}
	public void reset(Vector2f position) {
		super.setPosition(new Vector2f(position));
		super.setVelocity(new Vector2f(-2f, -0.5f));
	}
	
	/**
	 * Adjusts the speed of the ball.
	 * Will only work if the current speed is zero in value.
	 * @param speed The ball's new speed
	 */
	private void adjustSpeed(float speed) {
		super.getVelocity().normalize();
		super.getVelocity().mul(speed);
	}
}
