package com.mcflyboy.newPong.entity.entities;

import com.mcflyboy.newPong.entity.properties.appearances.ModelAppearance;

public class ModelEntity extends AppearanceEntity {
	public ModelEntity() {
		super();
		super.setAppearance(new ModelAppearance());
	}
	public ModelAppearance getModelAppearance() {
		return (ModelAppearance)super.getAppearance();
	}
	public void setModelAppearance(ModelAppearance ma) {
		super.setAppearance(ma);
	}
}
