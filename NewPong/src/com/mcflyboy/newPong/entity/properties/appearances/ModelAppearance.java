package com.mcflyboy.newPong.entity.properties.appearances;

import com.mcflyboy.newPong.entity.properties.Appearance;
import com.mcflyboy.newPong.graphics.Model;
import com.mcflyboy.newPong.graphics.Texture;

public class ModelAppearance extends Appearance {
	private Model model;
	private Texture texture;
	public ModelAppearance() {
		super();
		model = null;
		texture = null;
	}
	@Override
	public float getUnscaledWidth() {
		return model.getWidth();
	}
	@Override
	public float getUnscaledHeight() {
		return model.getHeight();
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
