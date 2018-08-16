package com.mcflyboy.newPong.timing;

public class DeltaTimer {
	private Timer baseTimer;
	private double startTime;
	public DeltaTimer() {
		this(null);
	}
	public DeltaTimer(Timer baseTimer) {
		this.baseTimer = baseTimer;
		startTime = getBaseTime();
	}
	public Timer getBaseTimer() {
		return baseTimer;
	}
	/**
	 * Resets this timer's time.
	 * 
	 * @param baseTimer
	 */
	public void setBaseTimer(Timer baseTimer) {
		this.baseTimer = baseTimer;
		startTime = getBaseTime();
	}
	public float getDeltaTime() {
		double currentTime = getBaseTime();
		float deltaTime = (float)(currentTime - startTime);
		startTime = currentTime;
		return deltaTime;
	}
	private double getBaseTime() {
		return baseTimer != null ? baseTimer.getTime() : Time.getTime();
	}
}
