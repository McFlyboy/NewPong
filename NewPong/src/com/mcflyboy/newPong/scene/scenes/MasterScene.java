package com.mcflyboy.newPong.scene.scenes;

import com.mcflyboy.newPong.scene.Scene;
import com.mcflyboy.newPong.timing.Timer;

public class MasterScene extends Scene {
	private GameplayScene gameplay;
	public MasterScene(Timer baseTimer) {
		super(baseTimer);
		gameplay = new GameplayScene(super.getTimer());
		gameplay.start();
	}
	@Override
	protected void update(float deltaTime) {
		gameplay.update();
	}
	@Override
	public void render() {
		gameplay.render();
	}
	@Override
	public void dispose() {
		gameplay.dispose();
	}
}
