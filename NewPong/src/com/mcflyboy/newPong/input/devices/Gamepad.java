package com.mcflyboy.newPong.input.devices;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.glfw.GLFWGamepadState;

import com.mcflyboy.newPong.Window;

public class Gamepad {
	
	/** Gamepad buttons. */
	public static final int
		BUTTON_A            = 0,
		BUTTON_B            = 1,
		BUTTON_X            = 2,
		BUTTON_Y            = 3,
		BUTTON_LEFT_BUMPER  = 4,
		BUTTON_RIGHT_BUMPER = 5,
		BUTTON_BACK         = 6,
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
	
	private static float stickThreshold = 0.1f;
	private static float triggerThreshold = 0f;
	
	private int jid;
	private int[] buttonStates;
	private float[] axisStates;
	private int lastButtonPressed;
	public Gamepad(int jid) {
		this.jid = jid;
		buttonStates = new int[15];
		axisStates = new float[6];
		resetLastButtonPressed();
	}
	public static float getStickThreshold() {
		return stickThreshold;
	}
	public static void setStickThreshold(float stickThreshold) {
		Gamepad.stickThreshold = stickThreshold;
	}
	public static float getTriggerThreshold() {
		return triggerThreshold;
	}
	public static void setTriggerThreshold(float triggerThreshold) {
		Gamepad.triggerThreshold = triggerThreshold;
	}
	public int getJID() {
		return jid;
	}
	public String getName() {
		return glfwGetGamepadName(jid);
	}
	public boolean isPresent() {
		return glfwJoystickPresent(jid);
	}
	public int getLastButtonPressed() {
		return lastButtonPressed;
	}
	public void resetLastButtonPressed() {
		lastButtonPressed = -1;
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
	public void resetButtonStates() {
		for(int i = 0; i < buttonStates.length; i++) {
			buttonStates[i] = GLFW_RELEASE;
		}
	}
	public void resetAxisStates() {
		for(int i = 0; i < axisStates.length; i++) {
			axisStates[i] = 0f;
		}
	}
	public void update(GLFWGamepadState state) {
		if(!Window.isFocused()) {
			resetButtonStates();
			resetAxisStates();
			return;
		}
		glfwGetGamepadState(jid, state);
		ByteBuffer buttonBuffer = state.buttons();
		int button = 0;
		while(buttonBuffer.hasRemaining()) {
			int buttonState = (int)buttonBuffer.get();
			if((buttonStates[button] & UNCHANGED) == GLFW_RELEASE && buttonState == GLFW_PRESS) {
				buttonStates[button] = buttonState | CHANGED;
				lastButtonPressed = button;
			}
			else if((buttonStates[button] & UNCHANGED) == GLFW_PRESS && buttonState == GLFW_RELEASE) {
				buttonStates[button] = buttonState | CHANGED;
			}
			button++;
		}
		FloatBuffer axesBuffer = state.axes();
		int axis = 0;
		while(axesBuffer.hasRemaining()) {
			float axisState = axesBuffer.get();
			if(axis > 3) {
				axisState /= 2f;
				axisState += 0.5f;
				axisStates[axis] = Math.abs(axisState) > triggerThreshold ? axisState : 0f;
			}
			else {
				if(axis == 1 || axis == 3) {
					axisState *= -1f;
				}
				axisStates[axis] = Math.abs(axisState) > stickThreshold ? axisState : 0f;
			}
			axis++;
		}
	}
}
