package com.mcflyboy.newPong.scene.scenes;

import com.mcflyboy.newPong.Window;
import com.mcflyboy.newPong.entity.entities.gameplay.Ball;
import com.mcflyboy.newPong.entity.entities.gameplay.Player;
import com.mcflyboy.newPong.entity.entities.gameplay.Stage;
import com.mcflyboy.newPong.graphics.Render;
import com.mcflyboy.newPong.input.devices.Gamepad;
import com.mcflyboy.newPong.input.devices.Gamepads;
import com.mcflyboy.newPong.input.devices.Keyboard;
import com.mcflyboy.newPong.math.collision.AABB;
import com.mcflyboy.newPong.scene.Scene;
import com.mcflyboy.newPong.timing.Timer;

public class GameplayScene extends Scene {
	private Gamepad gamepad;
	private Player player;
	private Ball ball;
	private Stage stage;
	public GameplayScene(Timer baseTimer) {
		super(baseTimer);
		gamepad = Gamepads.createGamepad(0);
		stage = new Stage();
		player = new Player(stage.getPosition().y);
		ball = new Ball(stage.getPosition());
	}
	@Override
	protected void update(float deltaTime) {
		if(Keyboard.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			Window.close();
		}
		player.getVelocity().x = 0f;
		player.getVelocity().y = 0f; //gamepad.getAxisState(Gamepad.AXIS_LEFT_Y);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player.getVelocity().y += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player.getVelocity().y -= 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.getVelocity().x += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.getVelocity().x -= 1f;
		}
		player.getVelocity().mul(1.5f);
		ball.updateVelocity(deltaTime);
		
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
		}
		ball.getPosition().x += ball.getVelocity().x * deltaTime;
		player.getPosition().x += player.getVelocity().x * deltaTime;
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
		}
		ball.getPosition().y += ball.getVelocity().y * deltaTime;
		player.getPosition().y += player.getVelocity().y * deltaTime;
		if(AABB.checkIntersection(player, ball)) {
			if(ball.getPosition().y - player.getPosition().y > 0f) {
				ball.getPosition().y = player.getPosition().y + player.getAppearance().getHeight() / 2f + ball.getAppearance().getHeight() / 2f + 0.001f;
			}
			else {
				ball.getPosition().y = player.getPosition().y - player.getAppearance().getHeight() / 2f - ball.getAppearance().getHeight() / 2f - 0.001f;
			}
		}
		
		//Ball to stage collision
		if(Math.abs(ball.getPosition().x) >= stage.getModelAppearance().getWidth() / 2f - ball.getAppearance().getWidth() / 2f) {
			ball.getVelocity().x *= -1f;
			ball.getPosition().x *= (stage.getModelAppearance().getWidth() / 2f  - ball.getAppearance().getWidth() / 2f) / Math.abs(ball.getPosition().x);
		}
		float stageTop = stage.getPosition().y + stage.getModelAppearance().getHeight() / 2f - ball.getModelAppearance().getHeight() / 2f;
		if(ball.getPosition().y >= stageTop) {
			ball.getVelocity().y *= -1;
			ball.getPosition().y = stageTop;
		}
		float stageBottom = stage.getPosition().y - stage.getModelAppearance().getHeight() / 2f + ball.getModelAppearance().getHeight() / 2f;
		if(ball.getPosition().y <= stageBottom) {
			ball.getVelocity().y *= -1;
			ball.getPosition().y = stageBottom;
		}
		
		//Check if ball is being crushed
		if(AABB.checkIntersection(player, ball)) {
			ball.getPosition().x = 0;
			ball.getPosition().y = stage.getPosition().y;
			ball.reset(stage.getPosition());
		}
	}
	@Override
	public void render() {
		Render.render(stage);
		Render.render(player);
		Render.render(ball);
	}
	@Override
	public void dispose() {
		stage.getModelAppearance().getModel().dispose();
	}
}
