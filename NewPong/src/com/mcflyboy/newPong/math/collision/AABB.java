package com.mcflyboy.newPong.math.collision;

import com.mcflyboy.newPong.entity.entities.AppearanceEntity;
import com.mcflyboy.newPong.entity.entities.GameEntity;
import com.mcflyboy.newPong.entity.properties.Appearance;
import com.mcflyboy.newPong.math.Vector2f;

public class AABB {
	public static boolean checkMoveXIntersection(GameEntity entity1, GameEntity entity2, float deltaTime) {
		return innerCheck(entity1.getPosition().getAdd(new Vector2f(entity1.getVelocity().x * deltaTime, 0f)), entity2.getPosition().getAdd(new Vector2f(entity2.getVelocity().x * deltaTime, 0f)), entity1.getAppearance(), entity2.getAppearance());
	}
	public static boolean checkMoveYIntersection(GameEntity entity1, GameEntity entity2, float deltaTime) {
		return innerCheck(entity1.getPosition().getAdd(new Vector2f(0f, entity1.getVelocity().y * deltaTime)), entity2.getPosition().getAdd(new Vector2f(0f, entity2.getVelocity().y * deltaTime)), entity1.getAppearance(), entity2.getAppearance());
	}
	public static boolean checkMoveIntersection(GameEntity entity1, GameEntity entity2, float deltaTime) {
		return innerCheck(entity1.getPosition().getAdd(entity1.getVelocity().getMul(deltaTime)), entity2.getPosition().getAdd(entity2.getVelocity().getMul(deltaTime)), entity1.getAppearance(), entity2.getAppearance());
	}
	public static boolean checkIntersection(AppearanceEntity entity1, AppearanceEntity entity2) {
		return innerCheck(entity1.getPosition(), entity2.getPosition(), entity1.getAppearance(), entity2.getAppearance());
	}
	public static boolean checkIntersection(AppearanceEntity entity, Vector2f vec) {
		Vector2f position = entity.getPosition();
		Appearance appearance = entity.getAppearance();
		if(Math.abs(position.x - vec.x) > appearance.getWidth() / 2f) {
			return false;
		}
		if(Math.abs(position.y - vec.y) > appearance.getHeight() / 2f) {
			return false;
		}
		return true;
	}
	private static boolean innerCheck(Vector2f pos1, Vector2f pos2, Appearance a1, Appearance a2) {
		if(Math.abs(pos1.x - pos2.x) > a1.getWidth() / 2f + a2.getWidth() / 2f) {
			return false;
		}
		if(Math.abs(pos1.y - pos2.y) > a1.getHeight() / 2f + a2.getHeight() / 2f) {
			return false;
		}
		return true;
	}
}
