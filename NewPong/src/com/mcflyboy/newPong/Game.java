package com.mcflyboy.newPong;

import com.mcflyboy.newPong.graphics.Render;
import com.mcflyboy.newPong.input.devices.Gamepads;
import com.mcflyboy.newPong.input.devices.Keyboard;
import com.mcflyboy.newPong.input.devices.Mouse;
import com.mcflyboy.newPong.scene.scenes.MasterScene;
import com.mcflyboy.newPong.timing.DeltaTimer;
import com.mcflyboy.newPong.timing.Time;

public class Game {
	public static final String TITLE = "NewPong";
	private DeltaTimer systemDelta;
	private MasterScene scene;
	public void start() {
		try {
			Framework.init();
			Window.create(1280, 720, TITLE, false);
			Window.setVSync(false);
			Mouse.hideCursor(true);
			Keyboard.init();
			Gamepads.create();
			Render.init();
			systemDelta = new DeltaTimer();
			scene = new MasterScene(null);
			scene.start();
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
				update();
				if(renderTimeRemaining <= 0.0) {
					renderReady = true;
					renderTimeRemaining += targetFrameTime;
				}
				renderTimeRemaining -= (double)systemDelta.getDeltaTime();
				if(renderReady) {
					render();
				}
				else {
					Thread.sleep(1L);
				}
				Time.updatePerSecondCounters();
			}
		}
		catch(Exception e) {
			ErrorHandler.println("-- Failed while running! --\n");
			e.printStackTrace(ErrorHandler.getPrintStream());
			ErrorHandler.println("\nAttemting to close the game...\n");
		}
		stop();
	}
	private void update() {
		Gamepads.update();
		scene.update();
		Time.addToUPS();
	}
	private void render() {
		Render.clear();
		scene.render();
		Window.update();
		Time.addToFPS();
	}
	private void stop() {
		try {
			scene.dispose();
			Render.terminate();
			Gamepads.destroy();
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
