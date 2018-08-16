package com.mcflyboy.newPong;

import java.awt.image.BufferedImage;

import com.mcflyboy.newPong.entity.entities.ModelEntity;
import com.mcflyboy.newPong.graphics.Model;
import com.mcflyboy.newPong.graphics.Models;
import com.mcflyboy.newPong.graphics.Render;
import com.mcflyboy.newPong.graphics.Texture;
import com.mcflyboy.newPong.timing.Time;

public class Game {
	public static final String TITLE = "NewPong";
	private Model square;
	private Texture texture;
	private ModelEntity test;
	public void start() {
		try {
			Framework.init();
			Window.create(1280, 720, TITLE, true);
			Window.setVSync(true);
			Render.init();
			Render.setClearColor(0f, 0.3f, 0f);
			square = Models.createSquare();
			BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			img.setRGB(0, 0, 0xffffff);
			texture = new Texture(img);
			test = new ModelEntity();
			test.getModelAppearance().setTexture(texture);
			test.getModelAppearance().setModel(square);
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
			while(!Window.shouldClose()) {
				update();
				render();
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
		System.out.println(Time.getFPS());
		test.getPosition().x += 0.001f;
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
			texture.dispose();
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
