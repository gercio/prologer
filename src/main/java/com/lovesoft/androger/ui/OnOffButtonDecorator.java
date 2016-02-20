package com.lovesoft.androger.ui;

import javax.swing.JButton;

import com.lovesoft.androger.ui.ImageHolder.ImageEnum;

public class OnOffButtonDecorator 
{
	private ButtonIconDecorator bid = new ButtonIconDecorator();
	
	public void decorate(OnOff onOff, JButton button) {
		if(onOff.isOn()) {
			bid.decorate(ImageEnum.TURN_ON, button);
		} else {
			bid.decorate(ImageEnum.TURN_OFF, button);
		}
	}
}
