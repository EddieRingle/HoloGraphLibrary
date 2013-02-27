package com.echo.holographlibrary;

import java.util.ArrayList;

public class Line {
	private ArrayList<LinePoint> points = new ArrayList<LinePoint>();
	private int color;
	private boolean showPoints = true;
	
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public ArrayList<LinePoint> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<LinePoint> points) {
		this.points = points;
	}
	public void addPoint(LinePoint point){
		points.add(point);
	}
	public LinePoint getPoint(int index){
		return points.get(index);
	}
	public int getSize(){
		return points.size();
	}
	public boolean isShowingPoints() {
		return showPoints;
	}
	public void setShowingPoints(boolean showPoints) {
		this.showPoints = showPoints;
	}
	
}
