package com.mcflyboy.newPong.graphics.texture.textures;

import java.awt.image.BufferedImage;

import com.mcflyboy.newPong.graphics.texture.Texture;

public class WhiteTexture extends Texture {
	private static Texture instance;
	private WhiteTexture() {
		super();
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		img.setRGB(0, 0, 0xffffff);
		super.setImage(img);
	}
	public static Texture getInstance() {
		if(instance == null) {
			instance = new WhiteTexture();
		}
		return instance;
	}
}
