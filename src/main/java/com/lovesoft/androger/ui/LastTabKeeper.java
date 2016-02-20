package com.lovesoft.androger.ui;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class LastTabKeeper {
	private JTabbedPane pane;
	private JComponent lastTabRenderedComponent;
	private JComponent lastTabRenderedComponentTitle;

	public LastTabKeeper(JTabbedPane pane, JComponent lastTabRenderedComponent, JComponent lastTabRenderedComponenttitle) {
		super();
		this.pane = pane;
		this.lastTabRenderedComponent = lastTabRenderedComponent;
		this.lastTabRenderedComponentTitle = lastTabRenderedComponenttitle;
		
	}

	/**
	 * Check if last tab component is last in JTabbedPane if not then, make it last. 
	 * Just simple as that.
	 */
	public void checkLastTab() {
		boolean found = false;
		int i = 0;
		for (; i < pane.getTabCount(); i++) {
			JComponent componentOnTab = (JComponent) pane.getTabComponentAt(i);
			if(componentOnTab == lastTabRenderedComponent) {
				found = true;
				break;
			}
		}
		
		if(found) {
			if(i < pane.getTabCount() - 1) {
				// Tab exist but is not last, move it then!
				pane.removeTabAt(i-1);
				addTabAtEnd();
			}
		} else {
			addTabAtEnd();
		}
	}

	private void addTabAtEnd() {
		pane.addTab(null, lastTabRenderedComponent);
		pane.setTabComponentAt(pane.getTabCount() - 1, lastTabRenderedComponentTitle);
	}
}
