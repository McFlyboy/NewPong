package com.mcflyboy.newPong.timing;

public class Timer {
	private Timer baseTimer;
	private boolean running;
	private double startTime;
	private double recordedTime;
	public Timer() {
		this(null);
	}
	public Timer(Timer baseTimer) {
		this(baseTimer, false);
	}
	public Timer(boolean running) {
		this(null, running);
	}
	public Timer(Timer baseTimer, boolean running) {
		this.baseTimer = baseTimer;
		if(running) {
			start();
		}
		else {
			this.running = false;
			startTime = 0.0;
		}
		recordedTime = 0.0;
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
	public double getTime() {
		if(running) {
			return getBaseTime() - startTime;
		}
		else {
			return recordedTime;
		}
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
