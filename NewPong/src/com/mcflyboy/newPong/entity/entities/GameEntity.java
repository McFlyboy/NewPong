package com.mcflyboy.newPong.entity.entities;

import com.mcflyboy.newPong.entity.properties.appearances.ModelAppearance;
import com.mcflyboy.newPong.graphics.model.models.SquareModel;
import com.mcflyboy.newPong.graphics.texture.textures.WhiteTexture;
import com.mcflyboy.newPong.math.Color3f;
import com.mcflyboy.newPong.math.Vector2f;

public abstract class GameEntity extends ModelEntity {
	private int score;
	private Vector2f direction;
	public GameEntity() {
		super();
		ModelAppearance ma = super.getModelAppearance();
		ma.setScale(0.03f);
		ma.setColor(new Color3f(0.25f, 1f, 0f));
		ma.setModel(SquareModel.getInstance());
		ma.setTexture(WhiteTexture.getInstance());
		score = 0;
		direction = new Vector2f();
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void changeScore(int points) {
		score += points;
	}
	public Vector2f getDirection() {
		return direction;
	}
	public void setDirection(Vector2f direction) {
		this.direction = direction;
	}
}
