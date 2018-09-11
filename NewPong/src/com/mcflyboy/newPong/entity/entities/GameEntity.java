package com.mcflyboy.newPong.entity.entities;

import com.mcflyboy.newPong.entity.properties.appearances.ModelAppearance;
import com.mcflyboy.newPong.graphics.models.SquareModel;
import com.mcflyboy.newPong.graphics.textures.WhiteTexture;
import com.mcflyboy.newPong.math.Vector2f;
import com.mcflyboy.newPong.util.ColorScheme;

public abstract class GameEntity extends ModelEntity {
	private int score;
	private Vector2f velocity;
	public GameEntity() {
		super();
		ModelAppearance ma = super.getModelAppearance();
		ma.setScale(0.03f);
		ma.setColor(ColorScheme.getNormal());
		ma.setModel(SquareModel.getInstance());
		ma.setTexture(WhiteTexture.getInstance());
		score = 0;
		velocity = new Vector2f();
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void addToScore(int points) {
		score += points;
	}
	public Vector2f getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}
}
