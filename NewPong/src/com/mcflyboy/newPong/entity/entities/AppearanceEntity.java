package com.mcflyboy.newPong.entity.entities;

import com.mcflyboy.newPong.entity.Entity;
import com.mcflyboy.newPong.entity.properties.Appearance;

public class AppearanceEntity extends Entity {
	private Appearance appearance;
	public AppearanceEntity() {
		super();
		appearance = null;
	}
	public Appearance getAppearance() {
		return appearance;
	}
	public void setAppearance(Appearance appearance) {
		this.appearance = appearance;
	}
}
