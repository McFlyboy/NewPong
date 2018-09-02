package com.mcflyboy.newPong.scene;

import com.mcflyboy.newPong.timing.DeltaTimer;
import com.mcflyboy.newPong.timing.Timer;

public abstract class Scene {
	private boolean running;
	private Timer timer;
	private DeltaTimer deltaTimer;
	public Scene(Timer baseTimer) {
		running = false;
		timer = new Timer(baseTimer);
		deltaTimer = new DeltaTimer(timer);
	}
	public boolean isRunning() {
		return running;
	}
	public Timer getTimer() {
		return timer;
	}
	public void start() {
		if(running) {
			return;
		}
		timer.start();
		running = true;
	}
	public void pause() {
		if(!running) {
			return;
		}
		timer.pause();
		running = false;
	}
	public void update() {
		update(deltaTimer.getDeltaTime());
	}
	protected abstract void update(float deltaTime);
	public abstract void render();
	public abstract void dispose();
}
