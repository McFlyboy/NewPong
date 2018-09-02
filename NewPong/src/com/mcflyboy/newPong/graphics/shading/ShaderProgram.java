package com.mcflyboy.newPong.graphics.shading;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mcflyboy.newPong.ErrorHandler;
import com.mcflyboy.newPong.Window;
import com.mcflyboy.newPong.math.Color3f;
import com.mcflyboy.newPong.math.Vector2f;

public abstract class ShaderProgram {
	private int vertexShader;
	private int fragmentShader;
	private int program;
	public ShaderProgram(String vertexShaderFilename, String fragmentShaderFilename) {
		vertexShader = loadShader(GL_VERTEX_SHADER, vertexShaderFilename);
		fragmentShader = loadShader(GL_FRAGMENT_SHADER, fragmentShaderFilename);
		program = glCreateProgram();
		glAttachShader(program, vertexShader);
		glAttachShader(program, fragmentShader);
		bindAttribs();
		glLinkProgram(program);
		glValidateProgram(program);
		registerUniformLocations();
	}
	public void start() {
		glUseProgram(program);
	}
	public static void stop() {
		glUseProgram(0);
	}
	public void dispose() {
		glDetachShader(program, vertexShader);
		glDetachShader(program, fragmentShader);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		glDeleteProgram(program);
	}
	protected abstract void bindAttribs();
	protected void bindAttrib(int index, String name) {
		glBindAttribLocation(program, index, name);
	}
	protected abstract void registerUniformLocations();
	protected int getUniformLocation(String name) {
		return glGetUniformLocation(program, name);
	}
	protected void loadInt(int location, int i) {
		glUniform1i(location, i);
	}
	protected void loadBoolean(int location, boolean b) {
		glUniform1i(location, b ? GL_TRUE : GL_FALSE);
	}
	protected void loadFloat(int location, float f) {
		glUniform1f(location, f);
	}
	protected void loadVector2f(int location, Vector2f vec) {
		glUniform2f(location, vec.x, vec.y);
	}
	protected void loadColor3f(int location, Color3f color) {
		glUniform3f(location, color.r, color.g, color.b);
	}
	private int loadShader(int type, String filename) {
		String filepath = "/shaders/" + filename;
		StringBuilder shadersource = new StringBuilder();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(ShaderProgram.class.getResourceAsStream(filepath)))) {
			String line;
			while((line = reader.readLine()) != null) {
				shadersource.append(line).append("\n");
			}
		}
		catch (IOException e) {
			ErrorHandler.println("Failed to read the file: " + filepath);
			e.printStackTrace(ErrorHandler.getPrintStream());
			Window.close();
		}
		int shader = glCreateShader(type);
		glShaderSource(shader, shadersource);
		glCompileShader(shader);
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
			ErrorHandler.println("Compile error in shader-file: " + filepath);
			ErrorHandler.println(glGetShaderInfoLog(shader, 500));
			Window.close();
		}
		return shader;
	}
}
