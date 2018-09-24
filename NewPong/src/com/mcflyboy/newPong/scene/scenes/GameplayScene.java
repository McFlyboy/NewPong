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
	private Player player1;
	private Player player2;
	private Player currentColCheckPlayer;
	private Ball ball;
	private Stage stage;
	public GameplayScene(Timer baseTimer) {
		super(baseTimer);
		stage = new Stage();
		player1 = new Player(PlayerID.PLAYER_1, new GameController(null, InputPart.LEFT_PART), stage.getPosition().y);
		player2 = new Player(PlayerID.PLAYER_2, new GameController(null, InputPart.RIGHT_PART), stage.getPosition().y);
		ball = new Ball(stage.getPosition());
	}
	@Override
	protected void update(float deltaTime) {
		if(Keyboard.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			Window.close();
		}
		player1.updateVelocity();
		player2.updateVelocity();
		ball.updateVelocity(deltaTime);
		currentColCheckPlayer = ball.getPosition().x < 0f ? player1 : player2;
		
		//X-axis collision
		if(AABB.checkMoveXIntersection(currentColCheckPlayer, ball, deltaTime)) {
			if(currentColCheckPlayer.getVelocity().x != 0f) {
				ball.getVelocity().x = currentColCheckPlayer.getVelocity().x;
			}
			else {
				if((currentColCheckPlayer.getPosition().x - ball.getPosition().x) * ball.getVelocity().x > 0f) {
					ball.getVelocity().x *= -1;
				}
			}
			ball.getVelocity().y += currentColCheckPlayer.getVelocity().y * 0.2f;
		}
		ball.getPosition().x += ball.getVelocity().x * deltaTime;
		player1.getPosition().x += player1.getVelocity().x * deltaTime;
		player2.getPosition().x += player2.getVelocity().x * deltaTime;
		if(AABB.checkIntersection(currentColCheckPlayer, ball)) {
			if(ball.getPosition().x - currentColCheckPlayer.getPosition().x > 0f) {
				ball.getPosition().x = currentColCheckPlayer.getPosition().x + currentColCheckPlayer.getAppearance().getWidth() / 2f + ball.getAppearance().getWidth() / 2f + 0.001f;
			}
			else {
				ball.getPosition().x = currentColCheckPlayer.getPosition().x - currentColCheckPlayer.getAppearance().getWidth() / 2f - ball.getAppearance().getWidth() / 2f - 0.001f;
			}
		}
		
		//Y-axis collision
		if(AABB.checkMoveYIntersection(currentColCheckPlayer, ball, deltaTime)) {
			if(currentColCheckPlayer.getVelocity().y != 0f) {
				ball.getVelocity().y = currentColCheckPlayer.getVelocity().y;
			}
			else {
				if((currentColCheckPlayer.getPosition().y - ball.getPosition().y) * ball.getVelocity().y > 0f) {
					ball.getVelocity().y *= -1;
				}
			}
			ball.getVelocity().x += currentColCheckPlayer.getVelocity().x * 0.2f;
		}
		ball.getPosition().y += ball.getVelocity().y * deltaTime;
		player1.getPosition().y += player1.getVelocity().y * deltaTime;
		player2.getPosition().y += player2.getVelocity().y * deltaTime;
		if(AABB.checkIntersection(currentColCheckPlayer, ball)) {
			if(ball.getPosition().y - currentColCheckPlayer.getPosition().y > 0f) {
				ball.getPosition().y = currentColCheckPlayer.getPosition().y + currentColCheckPlayer.getAppearance().getHeight() / 2f + ball.getAppearance().getHeight() / 2f + 0.001f;
			}
			else {
				ball.getPosition().y = currentColCheckPlayer.getPosition().y - currentColCheckPlayer.getAppearance().getHeight() / 2f - ball.getAppearance().getHeight() / 2f - 0.001f;
			}
		}
		
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
		if(AABB.checkIntersection(currentColCheckPlayer, ball)) {
			ball.reset(stage.getPosition());
		}
		
		//Player1 to stage collision
		if(Math.abs(player1.getPosition().x) >= stage.getModelAppearance().getWidth() / 2f - player1.getAppearance().getWidth() / 2f) {
			player1.getPosition().x *= (stage.getModelAppearance().getWidth() / 2f  - player1.getAppearance().getWidth() / 2f) / Math.abs(player1.getPosition().x);
		}
		float stageTopForPlayer = stage.getPosition().y + stage.getModelAppearance().getHeight() / 2f - player1.getModelAppearance().getHeight() / 2f;
		if(player1.getPosition().y >= stageTopForPlayer) {
			player1.getPosition().y = stageTopForPlayer;
		}
		float stageBottomForPlayer = stage.getPosition().y - stage.getModelAppearance().getHeight() / 2f + player1.getModelAppearance().getHeight() / 2f;
		if(player1.getPosition().y <= stageBottomForPlayer) {
			player1.getPosition().y = stageBottomForPlayer;
		}
		
		//Player2 to stage collision
		if(Math.abs(player2.getPosition().x) >= stage.getModelAppearance().getWidth() / 2f - player2.getAppearance().getWidth() / 2f) {
			player2.getPosition().x *= (stage.getModelAppearance().getWidth() / 2f  - player2.getAppearance().getWidth() / 2f) / Math.abs(player2.getPosition().x);
		}
		if(player2.getPosition().y >= stageTopForPlayer) {
			player2.getPosition().y = stageTopForPlayer;
		}
		if(player2.getPosition().y <= stageBottomForPlayer) {
			player2.getPosition().y = stageBottomForPlayer;
		}
	}
	@Override
	public void render() {
		Render.render(stage);
		Render.render(player1);
		Render.render(player2);
		Render.render(ball);
	}
	@Override
	public void dispose() {
		stage.getModelAppearance().getModel().dispose();
	}
}
