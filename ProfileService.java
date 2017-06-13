package org.mohan.javabrains.messenger.service;

import java.util.*;

import org.mohan.javabrains.messenger.database.DatabaseResource;
import org.mohan.javabrains.messenger.model.Profile;

public class ProfileService {	
	
	private Map<String, Profile> profiles;
	
	public ProfileService() {
		profiles = DatabaseResource.getAllProfiles();
	}

	public List<Profile> getProfiles() {			
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public boolean updateProfile(String profileName, Profile profile) {			
		if(profiles.get(profileName) != null) {
			profile.setId(profiles.get(profileName).getId());
			profile.setCreated(new Date());
			
			profiles.put(profileName, profile);
			return true;
		}
		return false;	
	}
	
	public boolean addProfile(Profile profile) {
		profile.setId(profiles.size()+1);
		profile.setCreated(new Date());
		
		return (profiles.put(profile.getProfileName(), profile)==null);
	}
	
	public boolean deleteProfile(String profileName) {
		return profiles.remove(profileName)!=null;
	}
}
