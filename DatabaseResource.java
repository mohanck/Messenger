package org.mohan.javabrains.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.mohan.javabrains.messenger.model.Message;
import org.mohan.javabrains.messenger.model.Profile;

public class DatabaseResource {
	
	public static Map<Long,Message> messages = new HashMap<>();
	public static Map<String,Profile> profiles = new HashMap<>();	
	
	public static Map<Long, Message> getAllMessages() {
		return messages;
	}
	
	public static Map<String, Profile> getAllProfiles() {
		return profiles;
	}
	
}
