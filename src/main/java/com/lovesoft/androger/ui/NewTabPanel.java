package com.lovesoft.androger.ui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.lovesoft.androger.ui.ImageHolder.ImageEnum;

public class NewTabPanel extends JPanel {
	private static final long serialVersionUID = -4077643355433521655L;

	public NewTabPanel(ActionListener newButtonListener) {
		JButton newTabButton = new JButton();
		ButtonIconDecorator decorator = new ButtonIconDecorator();
		decorator.decorate(ImageEnum.ADD, newTabButton);
		newTabButton.addActionListener(newButtonListener);
		setOpaque(false);
		add(newTabButton);
	}
}
