package com.mcflyboy.newPong.scene.scenes;

import com.mcflyboy.newPong.Window;
import com.mcflyboy.newPong.entity.entities.gameplay.Ball;
import com.mcflyboy.newPong.entity.entities.gameplay.Player;
import com.mcflyboy.newPong.entity.entities.gameplay.Player.PlayerID;
import com.mcflyboy.newPong.entity.entities.gameplay.Stage;
import com.mcflyboy.newPong.graphics.Render;
import com.mcflyboy.newPong.input.GameController;
import com.mcflyboy.newPong.input.GameController.InputPart;
import com.mcflyboy.newPong.input.devices.Keyboard;
import com.mcflyboy.newPong.math.collision.AABB;
import com.mcflyboy.newPong.scene.Scene;
import com.mcflyboy.newPong.timing.Timer;

public class GameplayScene extends Scene {
	private Player playerLeft;
	private Player playerRight;
	private Ball ball;
	private Stage stage;
	public GameplayScene(Timer baseTimer) {
		super(baseTimer);
		stage = new Stage();
		playerLeft = new Player(PlayerID.PLAYER_LEFT, new GameController(null, InputPart.LEFT_PART), stage.getPosition().y);
		playerRight = new Player(PlayerID.PLAYER_RIGHT, new GameController(null, InputPart.RIGHT_PART), stage.getPosition().y);
		ball = new Ball(stage.getPosition());
	}
	@Override
	protected void update(float deltaTime) {
		if(Keyboard.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			Window.close();
		}
		playerLeft.updateVelocity();
		playerRight.updateVelocity();
		ball.updateVelocity(deltaTime);
		Player nearestPlayerToBall = ball.getPosition().x < 0f ? playerLeft : playerRight;
		updatePlayerToBallPhysics(nearestPlayerToBall, deltaTime);
		
		//Ball to stage collision
		if(Math.abs(ball.getPosition().x) >= stage.getModelAppearance().getWidth() / 2f - ball.getAppearance().getWidth() / 2f) {
			ball.getVelocity().x *= -1f;
			ball.getPosition().x *= (stage.getModelAppearance().getWidth() / 2f  - ball.getAppearance().getWidth() / 2f) / Math.abs(ball.getPosition().x);
		}
		float stageTopForBall = stage.getPosition().y + stage.getModelAppearance().getHeight() / 2f - ball.getModelAppearance().getHeight() / 2f;
		if(ball.getPosition().y >= stageTopForBall) {
			ball.getVelocity().y *= -1;
			ball.getPosition().y = stageTopForBall;
		}
		float stageBottomForBall = stage.getPosition().y - stage.getModelAppearance().getHeight() / 2f + ball.getModelAppearance().getHeight() / 2f;
		if(ball.getPosition().y <= stageBottomForBall) {
			ball.getVelocity().y *= -1;
			ball.getPosition().y = stageBottomForBall;
		}
		
		//Check if ball is being crushed
		if(AABB.checkIntersection(nearestPlayerToBall, ball)) {
			ball.reset(stage.getPosition());
		}
		
		//PlayerLeft to stage collision
		if(Math.abs(playerLeft.getPosition().x) >= stage.getModelAppearance().getWidth() / 2f - playerLeft.getAppearance().getWidth() / 2f) {
			playerLeft.getPosition().x *= (stage.getModelAppearance().getWidth() / 2f  - playerLeft.getAppearance().getWidth() / 2f) / Math.abs(playerLeft.getPosition().x);
		}
		float stageTopForPlayer = stage.getPosition().y + stage.getModelAppearance().getHeight() / 2f - playerLeft.getModelAppearance().getHeight() / 2f;
		if(playerLeft.getPosition().y >= stageTopForPlayer) {
			playerLeft.getPosition().y = stageTopForPlayer;
		}
		float stageBottomForPlayer = stage.getPosition().y - stage.getModelAppearance().getHeight() / 2f + playerLeft.getModelAppearance().getHeight() / 2f;
		if(playerLeft.getPosition().y <= stageBottomForPlayer) {
			playerLeft.getPosition().y = stageBottomForPlayer;
		}
		
		//PlayerRight to stage collision
		if(Math.abs(playerRight.getPosition().x) >= stage.getModelAppearance().getWidth() / 2f - playerRight.getAppearance().getWidth() / 2f) {
			playerRight.getPosition().x *= (stage.getModelAppearance().getWidth() / 2f  - playerRight.getAppearance().getWidth() / 2f) / Math.abs(playerRight.getPosition().x);
		}
		if(playerRight.getPosition().y >= stageTopForPlayer) {
			playerRight.getPosition().y = stageTopForPlayer;
		}
		if(playerRight.getPosition().y <= stageBottomForPlayer) {
			playerRight.getPosition().y = stageBottomForPlayer;
		}
	}
	@Override
	public void render() {
		Render.render(stage);
		Render.render(playerLeft);
		Render.render(playerRight);
		Render.render(ball);
	}
	@Override
	public void dispose() {
		stage.getModelAppearance().getModel().dispose();
	}
	private void updatePlayerToBallPhysics(Player player, float deltaTime) {
		//X-axis collision
		if(AABB.checkMoveXIntersection(player, ball, deltaTime)) {
			if(player.getVelocity().x != 0f) {
				ball.getVelocity().x = player.getVelocity().x;
			}
			else {
				if((player.getPosition().x - ball.getPosition().x) * ball.getVelocity().x > 0f) {
					ball.getVelocity().x *= -1;
				}
			}
			ball.getVelocity().y += player.getVelocity().y * 0.2f;
		}
		ball.getPosition().x += ball.getVelocity().x * deltaTime;
		playerLeft.getPosition().x += playerLeft.getVelocity().x * deltaTime;
		playerRight.getPosition().x += playerRight.getVelocity().x * deltaTime;
		if(AABB.checkIntersection(player, ball)) {
			if(ball.getPosition().x - player.getPosition().x > 0f) {
				ball.getPosition().x = player.getPosition().x + player.getAppearance().getWidth() / 2f + ball.getAppearance().getWidth() / 2f + 0.001f;
			}
			else {
				ball.getPosition().x = player.getPosition().x - player.getAppearance().getWidth() / 2f - ball.getAppearance().getWidth() / 2f - 0.001f;
			}
		}
		
		//Y-axis collision
		if(AABB.checkMoveYIntersection(player, ball, deltaTime)) {
			if(player.getVelocity().y != 0f) {
				ball.getVelocity().y = player.getVelocity().y;
			}
			else {
				if((player.getPosition().y - ball.getPosition().y) * ball.getVelocity().y > 0f) {
					ball.getVelocity().y *= -1;
				}
			}
			ball.getVelocity().x += player.getVelocity().x * 0.2f;
		}
		ball.getPosition().y += ball.getVelocity().y * deltaTime;
		playerLeft.getPosition().y += playerLeft.getVelocity().y * deltaTime;
		playerRight.getPosition().y += playerRight.getVelocity().y * deltaTime;
		if(AABB.checkIntersection(player, ball)) {
			if(ball.getPosition().y - player.getPosition().y > 0f) {
				ball.getPosition().y = player.getPosition().y + player.getAppearance().getHeight() / 2f + ball.getAppearance().getHeight() / 2f + 0.001f;
			}
			else {
				ball.getPosition().y = player.getPosition().y - player.getAppearance().getHeight() / 2f - ball.getAppearance().getHeight() / 2f - 0.001f;
			}
		}
	}
}
