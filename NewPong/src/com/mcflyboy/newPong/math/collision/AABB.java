package com.mcflyboy.newPong.math.collision;

import com.mcflyboy.newPong.entity.entities.AppearanceEntity;
import com.mcflyboy.newPong.entity.properties.Appearance;
import com.mcflyboy.newPong.math.Vector2f;

public class AABB {
	public static boolean checkIntersection(AppearanceEntity entity1, AppearanceEntity entity2) {
		Vector2f vec1 = entity1.getPosition();
		Vector2f vec2 = entity2.getPosition();
		Appearance a1 = entity1.getAppearance();
		Appearance a2 = entity2.getAppearance();
		if(Math.abs(vec1.x - vec2.x) > a1.getWidth() / 2f + a2.getWidth() / 2f) {
			return false;
		}
		if(Math.abs(vec1.y - vec2.y) > a1.getHeight() / 2f + a2.getHeight() / 2f) {
			return false;
		}
		return true;
	}
}
