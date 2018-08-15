package com.mcflyboy.newPong;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Framework {
	private static boolean initialized = false;
	public static String getLWJGLVersion() {
		return Version.getVersion();
	}
	public static boolean isInitialized() {
		return initialized;
	}
	public static void init() {
		if(initialized) {
			return;
		}
		GLFWErrorCallback.createPrint(ErrorHandler.getPrintStream()).set();
		if(!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		initialized = true;
	}
	public static void terminate() {
		if(!initialized) {
			return;
		}
		glfwTerminate();
		glfwSetErrorCallback(null).free();
		initialized = false;
	}
}
