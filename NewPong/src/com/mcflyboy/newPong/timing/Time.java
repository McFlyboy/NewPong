package com.mcflyboy.newPong.timing;

import org.lwjgl.glfw.GLFW;

public class Time {
	private static double lastCountersUpdate;
	private static int frameCount;
	private static int fps;
	private static int updateCount;
	private static int ups;
	public static double getTime() {
		return GLFW.glfwGetTime();
	}
	public static int getFPS() {
		return fps;
	}
	public static int getUPS() {
		return ups;
	}
	public static void addToFPS() {
		frameCount++;
	}
	public static void addToUPS() {
		updateCount++;
	}
	public static void updatePerSecondCounters() {
		double currentTime = getTime();
		if(currentTime - lastCountersUpdate >= 1.0) {
			lastCountersUpdate += 1.0;
			fps = frameCount;
			frameCount = 0;
			ups = updateCount;
			updateCount = 0;
			if(fps < 100 || ups < 600) {
				System.err.println("FPS: " + fps + " - UPS: " + ups);
			}
			else {
				System.out.println("FPS: " + fps + " - UPS: " + ups);
			}
		}
	}
}
