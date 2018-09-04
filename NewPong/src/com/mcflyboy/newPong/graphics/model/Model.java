package com.mcflyboy.newPong.graphics.model;

import static org.lwjgl.opengl.GL30.*;

import java.util.ArrayList;
import java.util.List;

public class Model {
	private int vao;
	private List<Integer> vbos;
	private int vertexCount;
	private boolean lines;
	private float width;
	private float height;
	public Model() {
		vao = glGenVertexArrays();
		bind();
		vbos = new ArrayList<Integer>();
		vertexCount = 0;
		lines = false;
		width = 0f;
		height = 0f;
	}
	public int getVertexCount() {
		return vertexCount;
	}
	public boolean isLines() {
		return lines;
	}
	public void setLines(boolean lines) {
		this.lines = lines;
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	public void bind() {
		glBindVertexArray(vao);
	}
	public static void unbind() {
		glBindVertexArray(0);
	}
	public void setVertices(float[] vertices) {
		addAttrib(0, 2, vertices);
		vertexCount = vertices.length / 2;
		float highestX = 0f, lowestX = 0f, highestY = 0f, lowestY = 0f;
		for(int i = 0; i < vertices.length; i++) {
			if(i % 2 == 0) {
				highestX = Math.max(highestX, vertices[i]);
				lowestX = Math.min(lowestX, vertices[i]);
			}
			else {
				highestY = Math.max(highestY, vertices[i]);
				lowestY = Math.min(lowestY, vertices[i]);
			}
		}
		width = highestX - lowestX;
		height = highestY - lowestY;
	}
	public void setTexturecoords(float[] texturecoords) {
		addAttrib(1, 2, texturecoords);
	}
	private void addAttrib(int index, int size, float[] data) {
		int vbo = glGenBuffers();
		vbos.add(vbo);
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0L);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	public void dispose() {
		for(int vbo : vbos) {
			glDeleteBuffers(vbo);
		}
		glDeleteVertexArrays(vao);
	}
}
