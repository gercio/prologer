package com.lovesoft.androger.core.storage;

import java.awt.Dimension;
import java.awt.Point;

public class GUIStateDTO {
	private PointDTO size;
	private PointDTO locationOnScreen;
	
	public GUIStateDTO() {
	}
	
	public GUIStateDTO(GUIState guiState) {
		size = new PointDTO(guiState.getSize().height, guiState.getSize().width);
		locationOnScreen = new PointDTO(guiState.getLocationOnScreen().x, guiState.getLocationOnScreen().y);
	}
	
	public GUIState toGUIState() {
		GUIState guiState = new GUIState();
		guiState.setSize(new Dimension((int)size.getY(), (int)size.getX()));
		guiState.setLocationOnScreen(new Point((int)locationOnScreen.getX(), (int)locationOnScreen.getY()));
		return guiState;
	}
	
	public PointDTO getSize() {
		return size;
	}
	public void setSize(PointDTO size) {
		this.size = size;
	}
	public PointDTO getLocationOnScreen() {
		return locationOnScreen;
	}
	public void setLocationOnScreen(PointDTO locationOnScreen) {
		this.locationOnScreen = locationOnScreen;
	}
}
