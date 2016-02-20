package com.lovesoft.androger.ui;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;

import com.lovesoft.androger.Setup;
import com.lovesoft.androger.ui.ImageHolder.ImageEnum;

public class ButtonIconDecorator {
	
	public void decorate(ImageEnum image, JButton button) {
		boolean noText = false;
		if(button.getText() == null || button.getText() == "" ) {
			noText = true;
		}
		
		Icon i = ImageHolder.getInstance().getImageSmall(image);
		button.setIcon(i);
		if(noText) {
			int off = Setup.getGuiOffset();
			button.setPreferredSize(new Dimension(i.getIconWidth()+off, i.getIconHeight()+off));
			button.setMinimumSize(button.getPreferredSize());
			button.setMaximumSize(button.getPreferredSize());			
		}
	}
	
	
}
