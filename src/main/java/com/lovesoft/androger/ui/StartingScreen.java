package com.lovesoft.androger.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JWindow;

import com.lovesoft.androger.ui.ImageHolder.ImageEnum;

class StartingScreen extends JWindow
{
    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public StartingScreen(Frame f)
    {
        super(f);
        JLabel l = new JLabel(ImageHolder.getInstance().getImageOriginal(ImageEnum.SPLASH_SCREEN));
        getContentPane().add(l, BorderLayout.CENTER);
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = l.getPreferredSize();
        setLocation(screenSize.width/2 - (labelSize.width/2),
                    screenSize.height/2 - (labelSize.height/2));
        setVisible(true);
        screenSize = null;
        labelSize = null;
    }
}
