package com.mcflyboy.newPong.scene.scenes;

import com.mcflyboy.newPong.Window;
import com.mcflyboy.newPong.entity.entities.ModelEntity;
import com.mcflyboy.newPong.entity.entities.gameEntities.Ball;
import com.mcflyboy.newPong.entity.entities.gameEntities.Player;
import com.mcflyboy.newPong.graphics.Render;
import com.mcflyboy.newPong.graphics.model.models.StageModel;
import com.mcflyboy.newPong.graphics.texture.textures.WhiteTexture;
import com.mcflyboy.newPong.input.devices.Gamepad;
import com.mcflyboy.newPong.input.devices.Gamepads;
import com.mcflyboy.newPong.math.Color3f;
import com.mcflyboy.newPong.math.Vector2f;
import com.mcflyboy.newPong.math.collision.AABB;
import com.mcflyboy.newPong.scene.Scene;
import com.mcflyboy.newPong.timing.Timer;

public class GameplayScene extends Scene {
	private Gamepad gamepad;
	private Player player;
	private Ball ball;
	private ModelEntity stage;
	public GameplayScene(Timer baseTimer) {
		super(baseTimer);
		gamepad = Gamepads.createGamepad(0);
		player = new Player();
		player.getPosition().x = -1.35f;
		ball = new Ball();
		ball.getDirection().x = -0.5f;
		ball.getDirection().y = -0.5f;
		stage = new ModelEntity();
		stage.getModelAppearance().setModel(StageModel.getInstance());
		stage.getModelAppearance().setTexture(WhiteTexture.getInstance());
		stage.getModelAppearance().setColor(new Color3f(ball.getAppearance().getColor()));
		stage.getModelAppearance().setScale(new Vector2f(Window.ASPECT_RATIO, Window.ASPECT_RATIO * 9f / 21f).getMul(0.9f));
		stage.getPosition().y = -0.2f;
		ball.getPosition().y = stage.getPosition().y;
		player.getPosition().y = stage.getPosition().y;
	}
	@Override
	protected void update(float deltaTime) {
		if(gamepad.isButtonPressed(Gamepad.BUTTON_START)) {
			Window.close();
		}
		player.getDirection().y = gamepad.getAxisState(Gamepad.AXIS_LEFT_Y) * 2f;
		
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
		
		//Ball to stage collision
		if(Math.abs(ball.getPosition().x) >= stage.getModelAppearance().getWidth() / 2f - ball.getAppearance().getWidth() / 2f) {
			ball.getDirection().x *= -1f;
			ball.getPosition().x *= (stage.getModelAppearance().getWidth() / 2f  - ball.getAppearance().getWidth() / 2f) / Math.abs(ball.getPosition().x);
		}
		float stageTop = stage.getPosition().y + stage.getModelAppearance().getHeight() / 2f - ball.getModelAppearance().getHeight() / 2f;
		if(ball.getPosition().y >= stageTop) {
			ball.getDirection().y *= -1;
			ball.getPosition().y = stageTop;
		}
		float stageBottom = stage.getPosition().y - stage.getModelAppearance().getHeight() / 2f + ball.getModelAppearance().getHeight() / 2f;
		if(ball.getPosition().y <= stageBottom) {
			ball.getDirection().y *= -1;
			ball.getPosition().y = stageBottom;
		}
		
		//Check if ball is being crushed
		if(AABB.checkIntersection(player, ball)) {
			ball.getPosition().x = 0;
			ball.getPosition().y = stage.getPosition().y;
			ball.getDirection().x = -0.5f;
			ball.getDirection().y = -0.5f;
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
