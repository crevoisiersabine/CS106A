/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		
		add(new JLabel("Name"), NORTH);
		add(add_text, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		
		add(status_text, WEST);
		status_text.setActionCommand("Change Status");
		status_text.addActionListener(this);
		add(new JButton("Change Status"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(picture_text, WEST);
		picture_text.setActionCommand("Change Picture");
		picture_text.addActionListener(this);
		add(new JButton("Change Picture"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friend_text, WEST);
		friend_text.setActionCommand("Add Friend");
		friend_text.addActionListener(this);
		add(new JButton("Add Friend"), WEST);
		
		addActionListeners();
		
		plotwindow = new FacePamphletCanvas(); 
		add(plotwindow);
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	if(e.getActionCommand().equals("Add Friend") && !friend_text.getText().equals("")){
    		if(profile == null){
    			plotwindow.showMessage("Please select a profile to add a friend");
    		}
    		else{
    			if(DB.containsProfile(friend_text.getText()) == false){
    				plotwindow.showMessage(friend_text.getText() + " does not exist.");
    			}
    			else{
    				boolean test = profile.addFriend(friend_text.getText());
    				DB.addProfile(profile);
    				if(test == false){plotwindow.showMessage(profile.getName() + " already has " + friend_text.getText() + " as a friend.");}
    				else{
    					FacePamphletProfile friend_profile = DB.getProfile(friend_text.getText());
    					friend_profile.addFriend(profile.getName());
    					DB.addProfile(friend_profile);
    					plotwindow.displayProfile(profile);
    					plotwindow.showMessage(friend_text.getText() + " added as a friend");
    				}
    			}
    		}
		}
    	
    	if(e.getActionCommand().equals("Change Picture") && !picture_text.getText().equals("")){
    		if(profile == null){
    			plotwindow.showMessage("Please select a profile to change photo");
    		}
    		else{
    			GImage image = null; 
    			try { 
    				image = new GImage(picture_text.getText()); 
    			} catch (ErrorException ex) { 
    				plotwindow.showMessage("Unable to open image file: " + picture_text.getText());
    			}
    			if(image != null) {
    				profile.setImage(image);
    				DB.addProfile(profile);
    				plotwindow.displayProfile(profile);
    				plotwindow.showMessage("Picture updated");
    			}
    		}
		}
    	
    	if(e.getActionCommand().equals("Change Status") && !status_text.getText().equals("")){
    		if(profile == null){
    			plotwindow.showMessage("Please select a profile to change status");
    		}
    		else{
    			profile.setStatus(status_text.getText());
    			DB.addProfile(profile);
    			plotwindow.displayProfile(DB.getProfile(profile.getName()));
    			plotwindow.showMessage("Status updated.");
    		}
		}
    	
    	if(e.getActionCommand().equals("Add") && !add_text.getText().equals("")){
    		profile = new FacePamphletProfile(add_text.getText());
			if(DB.containsProfile(profile.getName()) == false){
				DB.addProfile(profile);
				plotwindow.displayProfile(profile);
				plotwindow.showMessage("New Profile added.");
			}
			else{
				plotwindow.displayProfile(DB.getProfile(profile.getName()));
				plotwindow.showMessage("This profile already exists!");
			}
		}
    	
    	if(e.getActionCommand().equals("Delete")){
			if(DB.containsProfile(add_text.getText()) == true){
				DB.deleteProfile(add_text.getText());
				profile = null;
				plotwindow.displayProfile(profile);
				plotwindow.showMessage("Deleted profile: " + add_text.getText());
			}
			else{
				plotwindow.showMessage("A profile with the name " + add_text.getText() + " does not exist!");
			}
		}
    	
    	if(e.getActionCommand().equals("Lookup")){
			if(DB.containsProfile(add_text.getText()) == true){
				profile = DB.getProfile(add_text.getText());
				plotwindow.displayProfile(profile);
				plotwindow.showMessage("Displaying: " + profile.getName());
			}
			else{
				profile = null;
				plotwindow.displayProfile(profile);
				plotwindow.showMessage("A profile with name " + add_text.getText() + " does not exist!");
			}
		}
	}
    
    //Instance variables
    private FacePamphletCanvas plotwindow;
    private FacePamphletDatabase DB = new FacePamphletDatabase();
    private JTextField add_text = new JTextField(TEXT_FIELD_SIZE);
    private JTextField status_text = new JTextField(TEXT_FIELD_SIZE);
    private JTextField picture_text = new JTextField(TEXT_FIELD_SIZE);
    private JTextField friend_text = new JTextField(TEXT_FIELD_SIZE);
    FacePamphletProfile profile = null;
}
