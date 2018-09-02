package com.mcflyboy.newPong.input;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFWJoystickCallback;

public class Gamepads {
	private static GLFWJoystickCallback joystickCallback;
	private static List<Integer> connectedJIDs;
	public static void create() {
		connectedJIDs = new ArrayList<Integer>();
		for(int i = GLFW_JOYSTICK_1; i < GLFW_JOYSTICK_16 + 1; i++) {
			if(glfwJoystickPresent(i)) {
				connectedJIDs.add(i);
			}
		}
		glfwSetJoystickCallback(joystickCallback = new GLFWJoystickCallback() {
			@Override
			public void invoke(int jid, int event) {
				if(event == GLFW_CONNECTED) {
					connectedJIDs.add(jid);
				}
				else {
					for(int i = 0; i < connectedJIDs.size(); i++) {
						if(jid == connectedJIDs.get(i)) {
							connectedJIDs.remove(i);
							return;
						}
					}
				}
			}
		});
	}
	public static void destroy() {
		joystickCallback.close();
	}
}
