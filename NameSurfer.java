/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.GCompound;
import acm.program.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //

		graph = new NameSurferGraph(); 
		add(graph);
		
		add(new JLabel("Name"), SOUTH);
		text = new JTextField(30);
		add(text, SOUTH);
		add(new JButton("Graph"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		addActionListeners();
		
		database = new NameSurferDataBase("names-data.txt");
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if(e.getActionCommand().equals("Graph")){
			NameSurferEntry entry = database.findEntry(text.getText());
			graph.addEntry(entry);;
		}
		if(e.getActionCommand().equals("Clear")){
			graph.clear();
		}
	}
	
	//Instance Variables
	private JTextField text;
	public NameSurferDataBase database;
	private NameSurferGraph graph; 

}
