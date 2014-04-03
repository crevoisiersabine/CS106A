/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
		count = 0;
		Iterator <NameSurferEntry> i = together.keySet().iterator();
		while (i.hasNext()){
			remove(together.get(i.next()));
		}
		together.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		count += 1;
		tog = new GCompound();
		// You fill this in //
		for(int i = 0; i < NDECADES-1; i++){
			level0 = entry.getRank(i);
			level1 = entry.getRank(i+1);
			if(level0 == 0){
				level0 = 3*getHeight() - 6*GRAPH_MARGIN_SIZE;
			}
			if(level1 == 0){
				level1 = 3*getHeight() - 6*GRAPH_MARGIN_SIZE;
			}
			line = new GLine(getWidth()*(i)/NDECADES, level0/3 + GRAPH_MARGIN_SIZE, getWidth()*(i+1)/NDECADES, level1/3 + GRAPH_MARGIN_SIZE);
			
			tog.add(line);
		}
		for(int j = 0; j < NDECADES; j++){
			if(entry.getRank(j) == 0){
				label = new GLabel(entry.getName() + " *", getWidth()*(j)/NDECADES, getHeight() - GRAPH_MARGIN_SIZE - 5);
				tog.add(label);
			}
			else{
				label = new GLabel(entry.getName() +  " " + String.valueOf(entry.getRank(j)), getWidth()*(j)/NDECADES, entry.getRank(j)/3 + GRAPH_MARGIN_SIZE - 5);
				tog.add(label);
			}
		}
		if(count%4 == 1){
			tog.setColor(Color.BLACK);
		}
		else if(count%4 == 2){
			tog.setColor(Color.RED);
		}
		else if(count%4 == 3){
			tog.setColor(Color.BLUE);
		}
		else{
			tog.setColor(Color.MAGENTA);
		}

		together.put(entry, tog);
		
		Iterator <NameSurferEntry> i = together.keySet().iterator();
		while (i.hasNext()){
			add(together.get(i.next()));
		}
	}
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		//	 You fill this in //
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
		years[0] = "1900";
		years[1] = "1910";
		years[2] = "1920";
		years[3] = "1930";
		years[4] = "1940";
		years[5] = "1950";
		years[6] = "1960"; 
		years[7] = "1970"; 
		years[8] = "1980";
		years[9] = "1990";
		years[10] = "2000";
		for(int i = 1; i <= NDECADES; i++){
			add(new GLine(getWidth()*i/NDECADES, 0, getWidth()*i/NDECADES, getHeight()));
			add(new GLabel(years[i-1], getWidth()*(i-1)/NDECADES, getHeight()));
		}
		for(NameSurferEntry name: together.keySet()){
			tog = together.get(name);
			tog.removeAll();
			for(int i = 0; i < NDECADES-1; i++){
				level0 = name.getRank(i);
				level1 = name.getRank(i+1);
				if(level0 == 0){
					level0 = 3*getHeight() - 6*GRAPH_MARGIN_SIZE;
				}
				if(level1 == 0){
					level1 = 3*getHeight() - 6*GRAPH_MARGIN_SIZE;
				}
				line = new GLine(getWidth()*(i)/NDECADES, level0/3 + GRAPH_MARGIN_SIZE, getWidth()*(i+1)/NDECADES, level1/3 + GRAPH_MARGIN_SIZE);
				
				tog.add(line);
			}
			for(int j = 0; j < NDECADES; j++){
				if(name.getRank(j) == 0){
					label = new GLabel(name.getName() + " *", getWidth()*(j)/NDECADES, getHeight() - GRAPH_MARGIN_SIZE - 5);
					tog.add(label);
				}
				else{
					label = new GLabel(name.getName() +  " " + String.valueOf(name.getRank(j)), getWidth()*(j)/NDECADES, name.getRank(j)/3 + GRAPH_MARGIN_SIZE - 5);
					tog.add(label);
				}
			}
		add(tog);
		}
	}
	
	//Instance variables
	private String[] years = new String[11];
	private int level0 = 0;
	private int level1 = 0;
	private int count = 0;
	private HashMap<NameSurferEntry, GCompound> together = new HashMap<NameSurferEntry, GCompound>();
	private GLine line;
	private GLabel label;
	private GCompound tog;
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
