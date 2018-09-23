package com.mcflyboy.newPong.entity.entities.gameplay;

import com.mcflyboy.newPong.entity.entities.GameEntity;
import com.mcflyboy.newPong.input.devices.Keyboard;

public class Player extends GameEntity {
	private PlayerID id;
	public Player(PlayerID id) {
		this(id, 0f);
	}
	public Player(PlayerID id, float startYPosition) {
		super();
		super.getAppearance().getScale().y = 0.1f;
		super.getPosition().y = startYPosition;
		this.id = id;
		if(id == PlayerID.PLAYER_1) {
			super.getPosition().x = -1.35f;
		}
		else {
			super.getPosition().x = 1.35f;
		}
	}
	public void updateVelocity() {
		super.getVelocity().x = 0f;
		super.getVelocity().y = 0f;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			super.getVelocity().y += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			super.getVelocity().y -= 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			super.getVelocity().x += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			super.getVelocity().x -= 1f;
		}
		super.getVelocity().mul(3f);
	}
	public enum PlayerID {
		PLAYER_1, PLAYER_2;
	}
}
