package com.mcflyboy.newPong.scene.scenes;

import com.mcflyboy.newPong.Window;
import com.mcflyboy.newPong.entity.entities.gameEntities.Ball;
import com.mcflyboy.newPong.entity.entities.gameEntities.Player;
import com.mcflyboy.newPong.graphics.Render;
import com.mcflyboy.newPong.input.Keyboard;
import com.mcflyboy.newPong.math.collision.AABB;
import com.mcflyboy.newPong.scene.Scene;
import com.mcflyboy.newPong.timing.Timer;

public class GameplayScene extends Scene {
	private Player player;
	private Ball ball;
	public GameplayScene(Timer baseTimer) {
		super(baseTimer);
		player = new Player();
		player.getPosition().x = -0.5f;
		ball = new Ball();
		ball.getDirection().x = -0.25f;
		ball.getDirection().y = -0.25f;
	}
	@Override
	protected void update(float deltaTime) {
		player.getDirection().x = 0f;
		player.getDirection().y = 0f;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player.getDirection().y += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player.getDirection().y -= 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.getDirection().x += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.getDirection().x -= 1f;
		}
		
		//X-axis collision
		if(AABB.checkMoveXIntersection(player, ball, deltaTime)) {
			if((player.getPosition().x - ball.getPosition().x) * ball.getDirection().x > 0f) {
				ball.getDirection().x *= -1;
			}
			ball.getPosition().x += player.getDirection().x * deltaTime;
		}
		else {
			ball.getPosition().x += ball.getDirection().x * deltaTime;
		}
		player.getPosition().x += player.getDirection().x * deltaTime;
		
		//Y-axis collision
		if(AABB.checkMoveYIntersection(player, ball, deltaTime)) {
			if((player.getPosition().y - ball.getPosition().y) * ball.getDirection().y > 0f) {
				ball.getDirection().y *= -1;
			}
			ball.getPosition().y += player.getDirection().y * deltaTime;
		}
		else {
			ball.getPosition().y += ball.getDirection().y * deltaTime;
		}
		player.getPosition().y += player.getDirection().y * deltaTime;
		
		//Ball to wall collision
		if(Math.abs(ball.getPosition().x) >= Window.ASPECT_RATIO - ball.getAppearance().getWidth() / 2f) {
			ball.getDirection().x *= -1f;
			ball.getPosition().x *= (Window.ASPECT_RATIO  - ball.getAppearance().getWidth() / 2f) / Math.abs(ball.getPosition().x);
		}
		if(Math.abs(ball.getPosition().y) >= 1f - ball.getAppearance().getHeight() / 2f) {
			ball.getDirection().y *= -1f;
			ball.getPosition().y *= (1f - ball.getAppearance().getHeight() / 2f) / Math.abs(ball.getPosition().y);
		}
		
		//Check if ball is being crushed
		if(AABB.checkIntersection(player, ball)) {
			ball.getPosition().x = 0;
			ball.getPosition().y = 0;
			ball.getDirection().x = -0.125f;
			ball.getDirection().y = -0.125f;
		}
	}
	@Override
	public void render() {
		Render.render(player);
		Render.render(ball);
	}
	@Override
	public void dispose() {
		
	}
}
