package com.lovesoft.androger.core.storage;

import java.awt.Dimension;
import java.awt.Point;

public class GUIState {
	private Dimension size;
	private Point locationOnScreen;
	
	public void setSize(Dimension size) {
		this.size = size;
	}

	public void setLocationOnScreen(Point locationOnScreen) {
		this.locationOnScreen = locationOnScreen;
	}

	public Dimension getSize() {
		return size;
	}

	public Point getLocationOnScreen() {
		return locationOnScreen;
	}
}
