package com.mcflyboy.newPong.graphics.model.models;

import com.mcflyboy.newPong.graphics.model.Model;

public class SquareModel {
	private static Model instance;
	public static Model getInstance() {
		if(instance == null) {
			instance = create();
		}
		return instance;
	}
	private static Model create() {
		Model square = new Model();
		square.setVertices(new float[] {
				-1f, -1f,
				1f, -1f,
				1f, 1f,
				-1f, 1f
		});
		square.setTexturecoords(new float[] {
				0f, 0f,
				1f, 0f,
				1f, 1f,
				0f, 1f
		});
		Model.unbind();
		return square;
	}
}
