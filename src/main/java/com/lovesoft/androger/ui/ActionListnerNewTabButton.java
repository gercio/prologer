package com.lovesoft.androger.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.lovesoft.androger.core.Androger;

public class ActionListnerNewTabButton implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Androger.getApplicationInput().addNewLogWatch();
	}

}
