package com.mcflyboy.newPong;

import com.mcflyboy.newPong.entity.entities.gameEntities.Ball;
import com.mcflyboy.newPong.entity.entities.gameEntities.Player;
import com.mcflyboy.newPong.graphics.Render;
import com.mcflyboy.newPong.graphics.model.models.SquareModel;
import com.mcflyboy.newPong.graphics.texture.textures.WhiteTexture;
import com.mcflyboy.newPong.input.Keyboard;
import com.mcflyboy.newPong.input.Mouse;
import com.mcflyboy.newPong.math.collision.AABB;
import com.mcflyboy.newPong.timing.DeltaTimer;
import com.mcflyboy.newPong.timing.Time;

public class Game {
	public static final String TITLE = "NewPong";
	private DeltaTimer systemDelta;
	private Player player;
	private Ball ball;
	public void start() {
		try {
			Framework.init();
			Window.create(1280, 720, TITLE, false);
			Window.setVSync(false);
			Mouse.hideCursor(true);
			Keyboard.init();
			Render.init();
			Render.setClearColor(0f, 0.05f, 0f);
			systemDelta = new DeltaTimer();
			player = new Player();
			player.getAppearance().getColor().r = 1f;
			ball = new Ball();
			ball.getPosition().x = -0.5f;
		}
		catch(Exception e) {
			ErrorHandler.println("-- Failed during startup! --\n");
			e.printStackTrace(ErrorHandler.getPrintStream());
			ErrorHandler.println("\nAttemting to close the game...\nMight cause errors!\n");
			stop();
		}
		run();
	}
	private void run() {
		try {
			double targetFrameTime = 1.0 / Window.getMonitorRefreshRate();
			double renderTimeRemaining = 0.0;
			systemDelta.getDeltaTime();
			while(!Window.shouldClose()) {
				boolean renderReady = false;
				float deltaTime = systemDelta.getDeltaTime();
				update(deltaTime);
				if(renderTimeRemaining <= 0.0) {
					renderReady = true;
					renderTimeRemaining += targetFrameTime;
				}
				renderTimeRemaining -= (double)deltaTime;
				if(renderReady) {
					render();
				}
				else {
					Thread.sleep(1L);
				}
			}
		}
		catch(Exception e) {
			ErrorHandler.println("-- Failed while running! --\n");
			e.printStackTrace(ErrorHandler.getPrintStream());
			ErrorHandler.println("\nAttemting to close the game...\n");
		}
		stop();
	}
	private void update(float deltaTime) {
		float deltaX = 0f, deltaY = 0f;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			deltaY += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			deltaY -= 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			deltaX += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			deltaX -= 1f;
		}
		player.getPosition().x += deltaX * deltaTime;
		player.getPosition().y += deltaY * deltaTime;
		System.out.println(AABB.checkIntersection(player, ball));
	}
	private void render() {
		Render.clear();
		Render.render(player);
		Render.render(ball);
		Window.update();
		Time.updateFPS();
	}
	private void stop() {
		try {
			SquareModel.getInstance().dispose();
			WhiteTexture.getInstance().dispose();
			Render.terminate();
			Window.destroy();
			Framework.terminate();
		}
		catch(Exception e) {
			ErrorHandler.println("-- Failed while closing! --\n");
			e.printStackTrace(ErrorHandler.getPrintStream());
		}
		System.exit(0);
	}
}
