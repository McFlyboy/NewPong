package com.mcflyboy.newPong.entity.entities.gameplay;

import com.mcflyboy.newPong.entity.entities.GameEntity;

public class Player extends GameEntity {
	public Player() {
		this(0f);
	}
	public Player(float startYPosition) {
		super();
		super.getAppearance().getScale().y = 0.1f;
		super.getPosition().y = startYPosition;
		super.getPosition().x = -1.35f;
	}
}
