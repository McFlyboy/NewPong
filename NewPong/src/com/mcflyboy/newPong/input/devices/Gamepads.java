package com.mcflyboy.newPong.input.devices;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFWGamepadState;
import org.lwjgl.glfw.GLFWJoystickCallback;

public class Gamepads {
	private static GLFWJoystickCallback joystickCallback;
	private static GLFWGamepadState state;
	private static List<Integer> unusedConnectedGamepadJIDs;
	private static List<Gamepad> activeGamepads;
	private static boolean unusedGamepadListChanged;
	private static boolean activeGamepadListChanged;
	public static void create() {
		state = GLFWGamepadState.create();
		unusedConnectedGamepadJIDs = new ArrayList<Integer>();
		activeGamepads = new ArrayList<Gamepad>();
		unusedGamepadListChanged = false;
		for(int i = GLFW_JOYSTICK_1; i < GLFW_JOYSTICK_16 + 1; i++) {
			if(glfwJoystickPresent(i) && glfwJoystickIsGamepad(i)) {
				unusedConnectedGamepadJIDs.add(i);
				unusedGamepadListChanged = true;
			}
		}
		glfwSetJoystickCallback(joystickCallback = new GLFWJoystickCallback() {
			@Override
			public void invoke(int jid, int event) {
				if(event == GLFW_CONNECTED) {
					if(glfwJoystickIsGamepad(jid)) {
						unusedConnectedGamepadJIDs.add(jid);
						unusedGamepadListChanged = true;
					}
				}
				else {
					for(int i = 0; i < unusedConnectedGamepadJIDs.size(); i++) {
						if(jid == unusedConnectedGamepadJIDs.get(i)) {
							unusedConnectedGamepadJIDs.remove(i);
							unusedGamepadListChanged = true;
							return;
						}
						if(jid == activeGamepads.get(i).getJID()) {
							activeGamepads.remove(i);
							activeGamepadListChanged = true;
							return;
						}
					}
				}
			}
		});
	}
	public static boolean isUnusedGamepadListChanged() {
		boolean changed = unusedGamepadListChanged;
		unusedGamepadListChanged = false;
		return changed;
	}
	public static boolean isActiveGamepadListChanged() {
		boolean changed = activeGamepadListChanged;
		activeGamepadListChanged = false;
		return changed;
	}
	public static int getUnusedGamepadCount() {
		return unusedConnectedGamepadJIDs.size();
	}
	public static int getActiveGamepadCount() {
		return activeGamepads.size();
	}
	public static String[] getUnusedGamepadNames() {
		String[] names = new String[unusedConnectedGamepadJIDs.size()];
		for(int i = 0; i < names.length; i++) {
			names[i] = glfwGetGamepadName(unusedConnectedGamepadJIDs.get(i));
		}
		return names;
	}
	public static Gamepad createGamepad(int index) {
		if(index < 0 || index >= unusedConnectedGamepadJIDs.size()) {
			return null;
		}
		Gamepad gamepad = new Gamepad(unusedConnectedGamepadJIDs.remove(index));
		activeGamepads.add(gamepad);
		unusedGamepadListChanged = true;
		activeGamepadListChanged = true;
		return gamepad;
	}
	public static void destroyGamepad(Gamepad gamepad) {
		if(gamepad == null) {
			return;
		}
		for(int i = 0; i < activeGamepads.size(); i++) {
			if(activeGamepads.get(i) == gamepad) {
				activeGamepads.remove(i);
				unusedConnectedGamepadJIDs.add(gamepad.getJID());
				unusedGamepadListChanged = true;
				activeGamepadListChanged = true;
				return;
			}
		}
	}
	public static void update() {
		for(Gamepad gamepad : activeGamepads) {
			gamepad.update(state);
		}
	}
	public static void destroy() {
		joystickCallback.free();
	}
}
