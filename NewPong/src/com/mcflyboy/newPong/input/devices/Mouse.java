package com.mcflyboy.newPong.input.devices;

import static org.lwjgl.glfw.GLFW.*;

import com.mcflyboy.newPong.Window;

public class Mouse {
	public static void hideCursor(boolean show) {
		glfwSetInputMode(Window.getHandle(), GLFW_CURSOR, show ? GLFW_CURSOR_HIDDEN : GLFW_CURSOR_NORMAL);
	}
}
