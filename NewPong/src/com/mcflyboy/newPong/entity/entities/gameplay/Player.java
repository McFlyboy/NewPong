package com.mcflyboy.newPong.entity.entities.gameplay;

import com.mcflyboy.newPong.entity.entities.GameEntity;
import com.mcflyboy.newPong.input.GameController;

public class Player extends GameEntity {
	private PlayerID id;
	private GameController controller;
	public Player(PlayerID id, GameController controller) {
		this(id, controller, 0f);
	}
	public Player(PlayerID id, GameController controller, float startYPosition) {
		super();
		super.getAppearance().getScale().y = 0.1f;
		super.getPosition().y = startYPosition;
		this.id = id;
		if(id == PlayerID.PLAYER_LEFT) {
			super.getPosition().x = -1.35f;
		}
		else {
			super.getPosition().x = 1.35f;
		}
		this.controller = controller;
	}
	public void updateVelocity() {
		super.getVelocity().y = controller.getYAxis();
		super.getVelocity().x = 0f;
		if(controller.isRightPressed()) {
			super.getVelocity().x += 1f;
		}
		if(controller.isLeftPressed()) {
			super.getVelocity().x -= 1f;
		}
		super.getVelocity().mul(3f);
	}
	public enum PlayerID {
		PLAYER_LEFT, PLAYER_RIGHT;
	}
}
