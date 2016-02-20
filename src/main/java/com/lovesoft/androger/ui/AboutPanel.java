package com.lovesoft.androger.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lovesoft.androger.Setup;
import com.lovesoft.androger.ui.ImageHolder.ImageEnum;

public class AboutPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3540406002707544289L;

	public AboutPanel() {
		setLayout(new BorderLayout(0, 0));
		add(new JLabel(ImageHolder.getInstance().getImageOriginal(ImageEnum.GALAXY_PICTURE)), BorderLayout.CENTER);
		add(new JLabel(Setup.getApplicationTitle() + " Copyright by Patryk Kałużny 2013-2015"), BorderLayout.SOUTH);
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		
//		Graphics2D g2 = (Graphics2D) g;
//		
//		g2.setColor(new Color(0,200,10, 100));
//		g2.drawLine(0, 0, 100, 100);
		
	}
	
	
	

}
