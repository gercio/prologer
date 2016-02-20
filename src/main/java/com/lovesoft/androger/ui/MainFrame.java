package com.lovesoft.androger.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.lovesoft.androger.ApplicationInput;
import com.lovesoft.androger.Setup;
import com.lovesoft.androger.core.GUIView;
import com.lovesoft.androger.core.LogID;
import com.lovesoft.androger.core.LogView;
import com.lovesoft.androger.core.storage.ApplicationState;
import com.lovesoft.androger.core.storage.GUIState;
import com.lovesoft.androger.ui.ImageHolder.ImageEnum;

public class MainFrame extends JFrame implements GUIView, WindowListener {
	private static final long serialVersionUID = 5313752195641623721L;
	private ApplicationInput input;
	private ArrayList<LogPanel> panels;
	private JTabbedPane tabbedPane;
	private String  previousFolder;
	private LastTabKeeper tabKeeper;
	private StartingScreen ss; // It is NOT Hitler SS :)

	public MainFrame(ApplicationInput logic) {
		this.input = logic;
	}
	
	public void init() {
		// Show splash screen when creating
		ss = new StartingScreen(this);
		// THis one eats memory resources!
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.panels = new ArrayList<LogPanel>();
		tabbedPane = new JTabbedPane();
		setTitle(Setup.getApplicationTitle());
		setLayout(new BorderLayout());
		add(tabbedPane, BorderLayout.CENTER);
		tabKeeper = new LastTabKeeper(tabbedPane, new AboutPanel(), new NewTabPanel(new ActionListnerNewTabButton()));
		setSize(500, 400);
		addWindowListener(this);
	}
	
	public void showView() {
		if(ss != null) {
			ss.setVisible(false);
			ss.dispose();
			ss = null;			
		}
		super.setVisible(true);
	}

	@Override
	public LogView createNewTextView(String name, LogID id) {
		final LogPanel panel = new LogPanel(input, id);
		JPanel tabTitlePane = new JPanel();
		tabTitlePane.add(new JLabel(name));
		JButton closeButton = new JButton();
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				input.closeLogWatch(panel.getLogID());
			}
		});
		ButtonIconDecorator decorator = new ButtonIconDecorator();
		decorator.decorate(ImageEnum.CLOSE, closeButton);
		tabTitlePane.add(closeButton);
		tabTitlePane.setOpaque(false);
		tabbedPane.addTab(null, panel);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tabTitlePane);
		panels.add(panel);
		getRootPane().setDefaultButton(panel.getDefaultButton());
		tabKeeper.checkLastTab();
		tabbedPane.setSelectedComponent(panel); // Select recently added log view
		return panel;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		input.GUIViewClosed();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		input.GUIViewClosed();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}


	@Override
	public String getNewLogFileName() {
		FileChooserDialog folderChoose = new FileChooserDialog(this,false,previousFolder);
		folderChoose.setVisible(true);
		previousFolder = folderChoose.getSelected(); 
		return folderChoose.getSelected();
	}

	@Override
	public void removeTextView(LogView logView) {
		boolean removed = false;
		for(int i = 0; i < tabbedPane.getTabCount(); ++i) {
			Component comp = tabbedPane.getComponentAt(i);
			if(comp.equals(logView)) {
				tabbedPane.remove(i);
				removed = true;
			}
		}
		if(!removed){
			throw new RuntimeException("Can't remove tab with logView " + logView);
		}
	}

	@Override
	public void saveTo(ApplicationState as) {
		GUIState guiState = new GUIState();
		guiState.setSize(getSize());
		guiState.setLocationOnScreen(getLocationOnScreen());
		as.setGuiState(guiState);
	}

	@Override
	public void loadFrom(ApplicationState as) {
		GUIState guiState = as.getGuiState();
		if(guiState.getLocationOnScreen() != null) {
			// Validate if application will be visible
			// TODO add support for multiple screens.
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Point savedLocation = new Point(guiState.getLocationOnScreen());
			
			if(savedLocation.getX() < 0 || savedLocation.getX() > screenSize.getWidth() - 50) {
				savedLocation.setLocation(0, savedLocation.getY());
			}
			if(savedLocation.getY() < 0 || savedLocation.getY() > screenSize.getHeight() - 50) {
				savedLocation.setLocation(savedLocation.getX(), 0);
			}
			setLocation(savedLocation);
			setSize(guiState.getSize());
		}
	}	
	
}
