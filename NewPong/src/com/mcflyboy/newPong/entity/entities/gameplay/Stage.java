package com.mcflyboy.newPong.entity.entities.gameplay;

import com.mcflyboy.newPong.Window;
import com.mcflyboy.newPong.entity.entities.ModelEntity;
import com.mcflyboy.newPong.graphics.models.StageModel;
import com.mcflyboy.newPong.graphics.textures.WhiteTexture;
import com.mcflyboy.newPong.math.Vector2f;
import com.mcflyboy.newPong.util.ColorScheme;

public class Stage extends ModelEntity {
	public Stage() {
		super();
		super.getModelAppearance().setModel(StageModel.getInstance());
		super.getModelAppearance().setTexture(WhiteTexture.getInstance());
		super.getModelAppearance().setColor(ColorScheme.getNormal());
		super.getModelAppearance().setScale(new Vector2f(Window.ASPECT_RATIO, Window.ASPECT_RATIO * 9f / 21f).getMul(0.9f));
		super.getPosition().y = -0.2f;
	}
}
