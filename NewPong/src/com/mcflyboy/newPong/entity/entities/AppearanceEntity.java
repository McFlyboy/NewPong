package com.mcflyboy.newPong.entity.entities;

import com.mcflyboy.newPong.entity.Entity;
import com.mcflyboy.newPong.entity.properties.Appearance;

public abstract class AppearanceEntity extends Entity {
	private Appearance appearance;
	public AppearanceEntity() {
		super();
		appearance = null;
	}
	protected Appearance getAppearance() {
		return appearance;
	}
	protected void setAppearance(Appearance appearance) {
		this.appearance = appearance;
	}
}
