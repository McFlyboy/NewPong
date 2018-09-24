package com.mcflyboy.newPong.scene.scenes;

import com.mcflyboy.newPong.graphics.Render;
import com.mcflyboy.newPong.graphics.models.SquareModel;
import com.mcflyboy.newPong.graphics.textures.WhiteTexture;
import com.mcflyboy.newPong.scene.Scene;
import com.mcflyboy.newPong.timing.Timer;
import com.mcflyboy.newPong.util.ColorScheme;

public class MasterScene extends Scene {
	private GameplayScene gameplay;
	public MasterScene(Timer baseTimer) {
		super(baseTimer);
		Render.setClearColor(ColorScheme.background());
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
		SquareModel.getInstance().dispose();
		WhiteTexture.getInstance().dispose();
	}
}
