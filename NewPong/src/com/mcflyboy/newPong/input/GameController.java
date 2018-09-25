package com.mcflyboy.newPong.input;

import com.mcflyboy.newPong.input.devices.Gamepad;
import com.mcflyboy.newPong.input.devices.Keyboard;

public class GameController {
	private Gamepad gamepad;
	private InputPart part;
	public GameController(Gamepad gamepad, InputPart part) {
		this.gamepad = gamepad;
		this.part = part;
	}
	public float getYAxis() {
		boolean usingLeftPart = part != InputPart.RIGHT_PART;
		if(gamepad != null) {
			return gamepad.getAxisState(usingLeftPart ? Gamepad.AXIS_LEFT_Y : Gamepad.AXIS_RIGHT_Y);
		}
		float y = 0f;
		if(Keyboard.isKeyDown(usingLeftPart ? Keyboard.KEY_W : Keyboard.KEY_UP)) {
			y += 1f;
		}
		if(Keyboard.isKeyDown(usingLeftPart ? Keyboard.KEY_S : Keyboard.KEY_DOWN)) {
			y -= 1f;
		}
		return y;
	}
	public boolean isLeftPressed() {
		if(gamepad != null) {
			return gamepad.isButtonPressed(part != InputPart.LEFT_PART ? Gamepad.BUTTON_X : Gamepad.BUTTON_DPAD_LEFT);
		}
		return Keyboard.isKeyPressed(part != InputPart.RIGHT_PART ? Keyboard.KEY_A : Keyboard.KEY_LEFT);
	}
	public boolean isRightPressed() {
		if(gamepad != null) {
			return gamepad.isButtonPressed(part != InputPart.LEFT_PART ? Gamepad.BUTTON_B : Gamepad.BUTTON_DPAD_RIGHT);
		}
		return Keyboard.isKeyPressed(part != InputPart.RIGHT_PART ? Keyboard.KEY_D : Keyboard.KEY_RIGHT);
	}
	public enum InputPart {
		LEFT_PART, RIGHT_PART, BOTH;
	}
}
