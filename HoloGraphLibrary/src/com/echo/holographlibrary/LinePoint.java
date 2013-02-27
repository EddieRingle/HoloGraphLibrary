package com.echo.holographlibrary;

import android.graphics.Path;
import android.graphics.Region;

public class LinePoint {
	private float x = 0;
	private float y = 0;
	private Path path;
	private Region region;

	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	
	
	
}
