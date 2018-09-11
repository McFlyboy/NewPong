package com.mcflyboy.newPong.graphics;

import static org.lwjgl.opengl.GL20.*;

import com.mcflyboy.newPong.entity.entities.AppearanceEntity;
import com.mcflyboy.newPong.entity.entities.ModelEntity;
import com.mcflyboy.newPong.entity.properties.appearances.ModelAppearance;
import com.mcflyboy.newPong.graphics.shaders.Shader;
import com.mcflyboy.newPong.math.Color3f;

public class Render {
	private static Shader shader;
	public static void init() {
		shader = Shader.getInstance();
		shader.start();
	}
	public static void clear() {
		glClear(GL_COLOR_BUFFER_BIT);
	}
	public static void setClearColor(Color3f color) {
		setClearColor(color.r, color.g, color.b);
	}
	public static void setClearColor(int color) {
		glClearColor((float)((color >> 16) & 0xff) / 255f, (float)((color >> 8) & 0xff) / 255f, (float)(color & 0xff) / 255f, 1f);
	}
	public static void setClearColor(float red, float green, float blue) {
		glClearColor(red, green, blue, 1f);
	}
	public static void setWireframeMode(boolean wireframe) {
		glPolygonMode(GL_FRONT_AND_BACK, wireframe ? GL_LINE : GL_FILL);
	}
	public static void render(AppearanceEntity entity) {
		if(!entity.getAppearance().isVisible()) {
			return;
		}
		if(entity instanceof ModelEntity) {
			ModelAppearance ma = ((ModelEntity)entity).getModelAppearance();
			Model model = ma.getModel();
			model.bind();
			glEnableVertexAttribArray(0);
			glEnableVertexAttribArray(1);
			shader.loadPosition(entity.getPosition());
			shader.loadAppearace(ma);
			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, ma.getTexture().getHandle());
			glDrawArrays(model.isLines() ? GL_LINES : GL_QUADS, 0, model.getVertexCount());
			glDisableVertexAttribArray(0);
			glDisableVertexAttribArray(1);
			Model.unbind();
		}
	}
	public static void terminate() {
		shader.dispose();
	}
}
