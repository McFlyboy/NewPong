package com.mcflyboy.newPong.timing;

public class TargetTimer {
	private Timer baseTimer;
	private boolean running;
	private double startTime;
	private double recordedTime;
	private double targetTime;
	public TargetTimer(double targetTime) {
		this(null, targetTime);
	}
	public TargetTimer(Timer baseTimer, double targetTime) {
		this(baseTimer, false, targetTime);
	}
	public TargetTimer(boolean running, double targetTime) {
		this(null, running, targetTime);
	}
	public TargetTimer(Timer baseTimer, boolean running, double targetTime) {
		this.baseTimer = baseTimer;
		if(running) {
			start();
		}
		else {
			this.running = false;
			startTime = 0.0;
		}
		recordedTime = 0.0;
		this.targetTime = targetTime; 
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
		reset();
	}
	public double getTargetTime() {
		return targetTime;
	}
	public void setTargetTime(double targetTime) {
		this.targetTime = targetTime;
	}
	public double getTime() {
		if(running) {
			return getBaseTime() - startTime;
		}
		else {
			return recordedTime;
		}
	}
	public void setTime(double time) {
		startTime = getBaseTime() - time;
		recordedTime = time;
	}
	public double getTimeRemaining() {
		return targetTime - getTime();
	}
	public boolean isTargetReached() {
		double currentTime = getTime();
		boolean targetReached = currentTime >= targetTime;
		if(targetReached) {
			setTime(currentTime - targetTime);
		}
		return targetReached;
	}
	public void start() {
		if(running) {
			return;
		}
		startTime = getBaseTime() - recordedTime;
		running = true;
	}
	public void pause() {
		if(!running) {
			return;
		}
		recordedTime = getTime();
		running = false;
	}
	public void stop() {
		recordedTime = 0.0;
		running = false;
	}
	public void reset() {
		recordedTime = 0.0;
		startTime = getBaseTime();
	}
	private double getBaseTime() {
		return baseTimer != null ? baseTimer.getTime() : Time.getTime();
	}
}
