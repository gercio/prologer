package com.lovesoft.androger.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.lovesoft.androger.ApplicationInput;
import com.lovesoft.androger.Setup;
import com.lovesoft.androger.Translation;
import com.lovesoft.androger.core.LogID;
import com.lovesoft.androger.core.LogView;

public class LogPanel extends JPanel implements LogView, ActionListener, KeyListener {
	private static final long serialVersionUID = -8162679910096596418L;
	private JTextField filterTextField;
	private JButton filterBtn;
	private JButton pauseBtn;
	private JButton clearBtn;
	private TextArea ta;
	private ApplicationInput applicationInput;
	private boolean filterEnabled = false;
	private int lineHeight;
	private LogID logID;
	private boolean paused = false;
	private OnOffButtonDecorator buttonDecorator = new OnOffButtonDecorator();

	public LogPanel(ApplicationInput applicationInput, LogID logID) {
		this.applicationInput = applicationInput;
		this.logID = logID;
		ta = new TextArea();
		ta.setEditable(false);
		filterTextField = new JTextField(20);
		filterTextField.addKeyListener(this);
		Font font = new Font("Monospaced", Font.PLAIN, 13);
		ta.setFont(font);
		ta.setForeground(Color.BLACK);
		ta.setBackground(Color.WHITE);
		filterBtn = new JButton(Translation.translate("Filter"));
		pauseBtn = new JButton(Translation.translate("Pause"));
		clearBtn = new JButton(Translation.translate("Clear"));
		JPanel toolsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		toolsPanel.add(filterTextField);
		toolsPanel.add(filterBtn);
		JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
		sep.setPreferredSize(new Dimension(sep.getPreferredSize().width,30));
		JSeparator sep2 = new JSeparator(SwingConstants.VERTICAL);
		sep2.setPreferredSize(new Dimension(sep.getPreferredSize().width,30));
		toolsPanel.add(sep);
		toolsPanel.add(pauseBtn);
		toolsPanel.add(sep2);
		toolsPanel.add(clearBtn);
		filterBtn.addActionListener(this);
		pauseBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		checkWidgetsState();
		setLayout(new BorderLayout());
		add(ta, BorderLayout.CENTER);
		add(toolsPanel, BorderLayout.NORTH);
		
		FontMetrics m = ta.getFontMetrics(ta.getFont());
		lineHeight = m.getAscent() + m.getDescent();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == filterBtn) {
			filterEnabled = !filterEnabled;
			if(filterEnabled) {
				String filterText = filterTextField.getText();
				applicationInput.textFilterEnabled(this, filterText);
			} else {
				applicationInput.textFilterDisabled(this);
			}
			
		}
		if (e.getSource() == pauseBtn) {
			applicationInput.togglePauseLogView(logID);
			paused = !paused;
		}
		if (e.getSource() == clearBtn) {
			applicationInput.clearLogView(logID);
		}
		checkWidgetsState();
	}

	@Override
	public String getText() {
		return ta.getText();
	}

	@Override
	public int getMaxTextFieldSize() {
		return Setup.getMaxTextFieldSize();
	}

	@Override
	public void appendText(String newText) {
		ta.append(newText);

	}

	@Override
	public void setViewAtPosition(int i) {
		ta.setCaretPosition(i);
	}

	@Override
	public void setText(String newText) {
		ta.setText(newText);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private void checkWidgetsState() {
		String filterText = filterTextField.getText();
		if (filterText != null && filterText.length() > 0) {
			filterBtn.setEnabled(true);
		} else {
			filterBtn.setEnabled(false);
		}
		
		buttonDecorator.decorate(new OnOff() {
			@Override
			public boolean isOn() {
				return paused;
			}
		}, pauseBtn);
		
		buttonDecorator.decorate(new OnOff() {
			@Override
			public boolean isOn() {
				return filterEnabled;
			}
		}, filterBtn);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		checkWidgetsState();
	}

	public JButton getDefaultButton() {
		return filterBtn;
	}

	@Override
	public int getNumberOfVisibleRows() {
		int lines = ta.getSize().height / lineHeight;
		return lines;
	}

	@Override
	public LogID getLogID() {
		return logID;
	}
}
