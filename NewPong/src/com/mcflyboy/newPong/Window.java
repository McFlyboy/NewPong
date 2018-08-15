package com.mcflyboy.newPong;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Window {
	private static boolean created = false;
	private static long handle = 0L;
	public static boolean isCreated() {
		return created;
	}
	public static long getHandle() {
		return handle;
	}
	public static boolean shouldClose() {
		return glfwWindowShouldClose(handle);
	}
	public static void close() {
		glfwSetWindowShouldClose(handle, true);
	}
	public static void create(int width, int height, String title) {
		if(created) {
			return;
		}
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		handle = glfwCreateWindow(width, height, title, NULL, NULL);
		if(handle == NULL) {
			throw new RuntimeException("Failed to create the GLFW window");
		}
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(handle, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		glfwMakeContextCurrent(handle);
		GL.createCapabilities();
		glfwShowWindow(handle);
		created = true;
	}
	public static void update() {
		glfwSwapBuffers(handle);
		glfwPollEvents();
	}
	public static void destroy() {
		if(!created) {
			return;
		}
		glfwFreeCallbacks(handle);
		glfwDestroyWindow(handle);
		created = false;
	}
}
