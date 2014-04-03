/*
v * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		if(message != null){
			remove(message);
		}
		message = new GLabel(msg, getWidth()/2, getHeight() - BOTTOM_MESSAGE_MARGIN);
		message.setFont(MESSAGE_FONT);
		add(message);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		if(profile != null){
			removeAll();
			
			GLabel name = new GLabel(profile.getName(), LEFT_MARGIN, TOP_MARGIN);
			name.setFont(PROFILE_NAME_FONT);
			name.setColor(Color.blue);
			add(name);
			
			GRect placeholder_image = new GRect(LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + name.getHeight(), IMAGE_WIDTH, IMAGE_HEIGHT);
			add(placeholder_image);
			GLabel no_image_text = new GLabel("NO IMAGE", LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + name.getHeight());
			no_image_text.move(placeholder_image.getWidth()/2 -  no_image_text.getWidth(), (placeholder_image.getHeight() +  no_image_text.getHeight())/2);
			no_image_text.setFont(PROFILE_IMAGE_FONT);
			add(no_image_text);
			
			if(profile.getImage() != null){
				GImage image = profile.getImage();
				image.scale(image.getWidth() / IMAGE_WIDTH, image.getHeight() / IMAGE_HEIGHT);
				add(image, LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + name.getHeight());
			}
			
			if(!profile.getStatus().equals("")){
				GLabel status = new GLabel(profile.getName() + " is " + profile.getStatus(), LEFT_MARGIN, STATUS_MARGIN + IMAGE_MARGIN + TOP_MARGIN + name.getHeight() + IMAGE_HEIGHT);
				status.setFont(PROFILE_STATUS_FONT);
				add(status);
			}
			
			GLabel friends = new GLabel("Friends", getWidth()/2, IMAGE_MARGIN + TOP_MARGIN + name.getHeight());
			friends.setFont(PROFILE_FRIEND_LABEL_FONT);
			add(friends);
			Iterator <String> i = profile.getFriends();
			int counter = 0;
			while(i.hasNext()){
				counter ++;
				GLabel friend = new GLabel(i.next(), getWidth()/2, IMAGE_MARGIN + TOP_MARGIN + name.getHeight());
				friend.move(0, friend.getHeight() * counter + 5);
				friend.setFont(PROFILE_FRIEND_FONT);
				add(friend);
			}
		}
		else{
			removeAll();
		}
	}
	
	//Instance variables
	GLabel message = null;
}
