package org.mohan.javabrains.messenger.resources;

import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.mohan.javabrains.messenger.model.Profile;
import org.mohan.javabrains.messenger.service.ProfileService;

@Path("/profiles")
public class ProfileResource {
	
	private ProfileService profileService = new ProfileService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getProfiles() {
	 	return profileService.getProfiles();
	}
	
	@Path("/{profileName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addProfile(Profile profile) {		
		return profileService.addProfile(profile);
	}
	
	@Path("/{profileName}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateProfile(@PathParam("profileName") String profileName, Profile profile) {
		return profileService.updateProfile(profileName, profile);
	}
	
	@Path("/{profileName}")
	@DELETE
	public boolean deleteProfile(@PathParam("profileName") String profileName) {
		return profileService.deleteProfile(profileName);
	}
	
}
