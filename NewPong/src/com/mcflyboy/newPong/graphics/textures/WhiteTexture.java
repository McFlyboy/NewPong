package com.mcflyboy.newPong.graphics.textures;

import java.awt.image.BufferedImage;

import com.mcflyboy.newPong.graphics.Texture;

public class WhiteTexture {
	private static Texture instance;
	public static Texture getInstance() {
		if(instance == null) {
			instance = create();
		}
		return instance;
	}
	private static Texture create() {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		img.setRGB(0, 0, 0xffffff);
		return new Texture(img);
	}
}
