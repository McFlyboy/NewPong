package com.mcflyboy.newPong.graphics.model.models;

import com.mcflyboy.newPong.graphics.model.Model;

public class SquareModel extends Model {
	private static Model instance;
	private SquareModel() {
		super();
		super.setVertices(new float[] {
				-1f, -1f,
				1f, -1f,
				1f, 1f,
				-1f, 1f
		});
		super.setTexturecoords(new float[] {
				0f, 0f,
				1f, 0f,
				1f, 1f,
				0f, 1f
		});
		Model.unbind();
	}
	public static Model getInstance() {
		if(instance == null) {
			instance = new SquareModel();
		}
		return instance;
	}
}
