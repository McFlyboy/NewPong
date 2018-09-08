package com.mcflyboy.newPong.input.devices;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFWGamepadState;
import org.lwjgl.glfw.GLFWJoystickCallback;

public class Gamepads {
	private static GLFWJoystickCallback joystickCallback;
	private static GLFWGamepadState stateContainer;
	private static List<Integer> unusedConnectedGamepadJIDs;
	private static boolean gamepadListChanged;
	public static void create() {
		stateContainer = GLFWGamepadState.create();
		unusedConnectedGamepadJIDs = new ArrayList<Integer>();
		gamepadListChanged = false;
		for(int i = GLFW_JOYSTICK_1; i < GLFW_JOYSTICK_16 + 1; i++) {
			if(glfwJoystickPresent(i) && glfwJoystickIsGamepad(i)) {
				unusedConnectedGamepadJIDs.add(i);
				gamepadListChanged = true;
			}
		}
		glfwSetJoystickCallback(joystickCallback = new GLFWJoystickCallback() {
			@Override
			public void invoke(int jid, int event) {
				if(event == GLFW_CONNECTED) {
					if(glfwJoystickIsGamepad(jid)) {
						unusedConnectedGamepadJIDs.add(jid);
						gamepadListChanged = true;
					}
				}
				else {
					for(int i = 0; i < unusedConnectedGamepadJIDs.size(); i++) {
						if(jid == unusedConnectedGamepadJIDs.get(i)) {
							unusedConnectedGamepadJIDs.remove(i);
							gamepadListChanged = true;
							return;
						}
					}
				}
			}
		});
	}
	public static boolean isGamepadListChanged() {
		boolean changed = gamepadListChanged;
		gamepadListChanged = false;
		return changed;
	}
	public static int getUnusedGamepadCount() {
		return unusedConnectedGamepadJIDs.size();
	}
	public static String[] getUnusedGamepadNames() {
		String[] names = new String[unusedConnectedGamepadJIDs.size()];
		for(int i = 0; i < names.length; i++) {
			names[i] = glfwGetGamepadName(unusedConnectedGamepadJIDs.get(i));
		}
		return names;
	}
	public static GLFWGamepadState getStateContainer() {
		return stateContainer;
	}
	public static Gamepad createGamePad(int index) {
		if(index < 0 || index >= unusedConnectedGamepadJIDs.size()) {
			return null;
		}
		gamepadListChanged = true;
		return new Gamepad(unusedConnectedGamepadJIDs.remove(index));
	}
	public static void destroy() {
		joystickCallback.free();
	}
}
