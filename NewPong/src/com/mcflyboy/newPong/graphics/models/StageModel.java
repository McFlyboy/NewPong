package com.mcflyboy.newPong.graphics.models;

import com.mcflyboy.newPong.graphics.Model;

public class StageModel {
	private static Model instance;
	public static Model getInstance() {
		if(instance == null) {
			instance = create();
		}
		return instance;
	}
	private static Model create() {
		int dashes = 15;
		Model level = new Model();
		float[] vertexData = new float[16 + dashes * 4];
		vertexData[0] = -1f;
		vertexData[1] = -1f;
		vertexData[2] = 1f;
		vertexData[3] = -1f;
		vertexData[4] = 1f;
		vertexData[5] = -1f;
		vertexData[6] = 1f;
		vertexData[7] = 1f;
		vertexData[8] = 1f;
		vertexData[9] = 1f;
		vertexData[10] = -1f;
		vertexData[11] = 1f;
		vertexData[12] = -1f;
		vertexData[13] = 1f;
		vertexData[14] = -1f;
		vertexData[15] = -1f;
		float dashLength = 2f / ((float)dashes * 2f + 1f);
		for(int i = 16; i < vertexData.length; i++) {
			if(i % 2 == 1) {
				vertexData[i] = -1f + dashLength + dashLength * (float)((i - 16 - 1) / 2);
			}
		}
		level.setVertices(vertexData);
		level.setTexturecoords(new float[vertexData.length]);
		level.setLines(true);
		Model.unbind();
		return level;
	}
}
