package com.mcflyboy.newPong;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

public class Window {
	private static boolean created = false;
	private static long monitor = 0L;
	private static GLFWVidMode vidmode = null;
	private static long handle = 0L;
	private static boolean vsync = false;
	public static boolean isCreated() {
		return created;
	}
	public static long getHandle() {
		return handle;
	}
	public static boolean isFullscreen() {
		return getFullscreenMonitor() != NULL;
	}
	public static boolean isVSync() {
		return vsync;
	}
	public static int getMonitorWidth() {
		return vidmode.width();
	}
	public static int getMonitorHeight() {
		return vidmode.height();
	}
	public static int getMonitorRefreshRate() {
		return vidmode.refreshRate();
	}
	public static boolean shouldClose() {
		return glfwWindowShouldClose(handle);
	}
	public static void close() {
		glfwSetWindowShouldClose(handle, true);
	}
	public static void create(int width, int height, String title, boolean fullscreen) {
		if(created) {
			return;
		}
		monitor = glfwGetPrimaryMonitor();
		vidmode = glfwGetVideoMode(monitor);
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		if(fullscreen) {
			width = vidmode.width();
			height = vidmode.height();
		}
		handle = glfwCreateWindow(width, height, title, fullscreen ? monitor : NULL, NULL);
		if(handle == NULL) {
			throw new RuntimeException("Failed to create the GLFW window");
		}
		if(!fullscreen) {
			center();
		}
		glfwMakeContextCurrent(handle);
		GL.createCapabilities();
		glfwShowWindow(handle);
		created = true;
	}
	public static void update() {
		glfwSwapBuffers(handle);
		glfwPollEvents();
	}
	public static void center() {
		try(MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer widthBuffer = stack.mallocInt(1);
			IntBuffer heightBuffer = stack.mallocInt(1);
			glfwGetWindowSize(handle, widthBuffer, heightBuffer);
			glfwSetWindowPos(handle, (vidmode.width() - widthBuffer.get()) / 2, (vidmode.height() - heightBuffer.get()) / 2);
		}
	}
	public static void setVSync(boolean vsync) {
		Window.vsync = vsync;
		glfwSwapInterval(vsync ? 1 : 0);
	}
	public static void destroy() {
		if(!created) {
			return;
		}
		glfwFreeCallbacks(handle);
		glfwDestroyWindow(handle);
		created = false;
	}
	private static long getFullscreenMonitor() {
		return glfwGetWindowMonitor(handle);
	}
}
