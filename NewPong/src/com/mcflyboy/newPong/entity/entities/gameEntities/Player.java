package com.mcflyboy.newPong.entity.entities.gameEntities;

import com.mcflyboy.newPong.entity.entities.GameEntity;

public class Player extends GameEntity {
	public Player() {
		super();
		super.getAppearance().getScale().y = 0.1f;
	}
}
