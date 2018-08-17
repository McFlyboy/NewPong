package com.mcflyboy.newPong.graphics.texture;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;

public class Texture {
	private int handle;
	private int width;
	private int height;
	/**
	 * 
	 * @param img Must be {@code TYPE_INT_ARGB}
	 */
	protected Texture() {
		
	}
	public Texture(BufferedImage img) {
		setImage(img);
	}
	public int getHandle() {
		return handle;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	protected void setImage(BufferedImage img) {
		width = img.getWidth();
		height = img.getHeight();
		AffineTransform transform = AffineTransform.getScaleInstance(1f, -1f);
		transform.translate(0, -height);
		AffineTransformOp operation = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		img = operation.filter(img, null);
		int[] pixels = new int[width * height];
		img.getRGB(0, 0, width, height, pixels, 0, width);
		ByteBuffer buffer = BufferUtils.createByteBuffer(pixels.length * 4);
		for(int i = 0; i < pixels.length; i++) {
			int pixel = pixels[i];
			buffer.put((byte)((pixel & 0xff0000) >> 16));
			buffer.put((byte)((pixel & 0xff00) >> 8));
			buffer.put((byte)(pixel & 0xff));
			buffer.put((byte)((pixel & 0xff000000) >> 24));
		}
		buffer.flip();
		handle = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, handle);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	public void dispose() {
		glDeleteTextures(handle);
	}
}
