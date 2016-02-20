package com.lovesoft.androger.core;

import com.lovesoft.androger.ApplicationInput;
import com.lovesoft.androger.ui.MainFrame;

public class Factory {

	public static GUIView buildGUIView(ApplicationInput logic) {
		MainFrame mf = new MainFrame(logic);
		mf.init();
		return mf;
	}

}
