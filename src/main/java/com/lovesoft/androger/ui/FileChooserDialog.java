package com.lovesoft.androger.ui;



import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import com.lovesoft.androger.Translation;

public class FileChooserDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = 4977282679566630900L;
	private JFileChooser chooser;
	private String selected;
	public FileChooserDialog(Window owner, boolean onlyDir, String startDir) {
		super(owner, ModalityType.TOOLKIT_MODAL);
		setTitle(Translation.translate("Chose log file"));
		if( startDir != null && startDir.length() > 0) {			
			chooser = new JFileChooser(startDir);
		} else {
			chooser = new JFileChooser();
		}
		chooser.addActionListener(this);
		if(onlyDir) {
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);			
		}
		add(chooser);
		setSize(500, 380);
		setLocationRelativeTo(owner);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand())) {
			selected = chooser.getSelectedFile().getAbsolutePath();
		}
		setVisible(false);
	}
	public String getSelected() {
		return selected;
	}
}

