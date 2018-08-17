package com.mcflyboy.newPong;

import com.mcflyboy.newPong.entity.entities.ModelEntity;
import com.mcflyboy.newPong.graphics.Render;
import com.mcflyboy.newPong.graphics.model.Model;
import com.mcflyboy.newPong.graphics.model.models.SquareModel;
import com.mcflyboy.newPong.graphics.texture.Texture;
import com.mcflyboy.newPong.graphics.texture.textures.WhiteTexture;
import com.mcflyboy.newPong.math.Color3f;
import com.mcflyboy.newPong.timing.DeltaTimer;
import com.mcflyboy.newPong.timing.Time;

public class Game {
	public static final String TITLE = "NewPong";
	private DeltaTimer systemDelta;
	private Model square;
	private Texture white;
	private ModelEntity test;
	public void start() {
		try {
			Framework.init();
			Window.create(1280, 720, TITLE, false);
			Window.setVSync(false);
			Render.init();
			systemDelta = new DeltaTimer();
			square = SquareModel.getInstance();
			white = WhiteTexture.getInstance();
			test = new ModelEntity();
			test.getModelAppearance().setTexture(white);
			test.getModelAppearance().setModel(square);
			test.getModelAppearance().setColor(new Color3f(0f, 1f, 0f));
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
		
	}
	private void render() {
		Render.clear();
		Render.render(test);
		Window.update();
		Time.updateFPS();
	}
	private void stop() {
		try {
			square.dispose();
			white.dispose();
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
