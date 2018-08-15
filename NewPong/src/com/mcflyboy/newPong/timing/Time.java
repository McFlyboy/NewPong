package com.mcflyboy.newPong.timing;

import org.lwjgl.glfw.GLFW;

public class Time {
	private static double lastFPSUpdate;
	private static int frameCount;
	private static int fps;
	public static double getTime() {
		return GLFW.glfwGetTime();
	}
	public static int getFPS() {
		return fps;
	}
	public static void updateFPS() {
		frameCount++;
		double currentTime = getTime();
		if(currentTime - lastFPSUpdate >= 1.0) {
			lastFPSUpdate = currentTime;
			fps = frameCount;
			frameCount = 0;
		}
	}
}
