package com.mcflyboy.newPong.input.devices;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.glfw.GLFWGamepadState;

public class Gamepad {
	
	/** Gamepad buttons. */
	public static final int
		BUTTON_A            = 0,
		BUTTON_B            = 1,
		BUTTON_X            = 2,
		BUTTON_Y            = 3,
		BUTTON_LEFT_BUMPER  = 4,
		BUTTON_RIGHT_BUMPER = 5,
		BUTTON_BACK	        = 6,
		BUTTON_START        = 7,
		BUTTON_GUIDE        = 8,
		BUTTON_LEFT_THUMB   = 9,
		BUTTON_RIGHT_THUMB  = 10,
		BUTTON_DPAD_UP      = 11,
		BUTTON_DPAD_RIGHT   = 12,
		BUTTON_DPAD_DOWN    = 13,
		BUTTON_DPAD_LEFT    = 14,
		BUTTON_LAST         = BUTTON_DPAD_LEFT,
		BUTTON_CROSS        = BUTTON_A,
		BUTTON_CIRCLE       = BUTTON_B,
		BUTTON_SQUARE       = BUTTON_X,
		BUTTON_TRIANGLE     = BUTTON_Y;
	
	/** Gamepad axes. */
	public static final int
		AXIS_LEFT_X        = 0,
		AXIS_LEFT_Y        = 1,
		AXIS_RIGHT_X       = 2,
		AXIS_RIGHT_Y       = 3,
		AXIS_LEFT_TRIGGER  = 4,
		AXIS_RIGHT_TRIGGER = 5,
		AXIS_LAST          = AXIS_RIGHT_TRIGGER;
	
	/** Value changes. */
	private static final int
		CHANGED   = 2,
		UNCHANGED = 1;
	
	private int jid;
	private int[] buttonStates;
	private float[] axisStates;
	public Gamepad(int jid) {
		this.jid = jid;
		buttonStates = new int[15];
		axisStates = new float[6];
	}
	public String getName() {
		return glfwGetGamepadName(jid);
	}
	public boolean isPresent() {
		return glfwJoystickPresent(jid);
	}
	public boolean isButtonPressed(int button) {
		return getButtonState(button) == (GLFW_PRESS | CHANGED);
	}
	public boolean isButtonDown(int button) {
		return (getButtonState(button) & UNCHANGED) == GLFW_PRESS;
	}
	private int getButtonState(int button) {
		int buttonState = buttonStates[button];
		buttonStates[button] &= UNCHANGED;
		return buttonState;
	}
	public float getAxisState(int axis) {
		return axisStates[axis];
	}
	//TODO: Fix axis-values. Add more methods to Gamepad. Drop all inputs when Window is not focused. Handle deletion of gamepads.
	public void update() {
		GLFWGamepadState stateContainer = Gamepads.getStateContainer();
		glfwGetGamepadState(jid, stateContainer);
		ByteBuffer buttonBuffer = stateContainer.buttons();
		int button = 0;
		while(buttonBuffer.hasRemaining()) {
			int buttonState = (int)buttonBuffer.get();
			if((buttonStates[button] & UNCHANGED) == GLFW_RELEASE && buttonState == GLFW_PRESS) {
				buttonStates[button] = buttonState | CHANGED;
			}
			else if((buttonStates[button] & UNCHANGED) == GLFW_PRESS && buttonState == GLFW_RELEASE) {
				buttonStates[button] = buttonState | CHANGED;
			}
			button++;
		}
		FloatBuffer axesBuffer = stateContainer.axes();
		int axis = 0;
		while(axesBuffer.hasRemaining()) {
			axisStates[axis] = axesBuffer.get();
			axis++;
		}
	}
}
