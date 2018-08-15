package com.mcflyboy.newPong.graphics;

public class Models {
	public static Model createSquare() {
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
