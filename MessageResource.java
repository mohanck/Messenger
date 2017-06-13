package org.mohan.javabrains.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.mohan.javabrains.messenger.database.BeanFilter;
import org.mohan.javabrains.messenger.model.Link;
import org.mohan.javabrains.messenger.model.Message;
import org.mohan.javabrains.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {
	
	//private MessageServiceSingleton messageServiceSingleton = MessageServiceSingleton.getMessageServiceSingleton();
	private MessageService messageService = new MessageService();
			
	@GET
	//@Produces(MediaType.APPLICATION_XML)	
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJSONMessages(@BeanParam BeanFilter beanFilter) {
		System.out.println("JSON Method called");
	 // return messageServiceSingleton.getMessages();
		if(beanFilter.getYear() != 0) {
			return messageService.getAllMessagesForYear(beanFilter.getYear());
		}
		else if(beanFilter.getStart() >= 0 && beanFilter.getSize() != 0) { //start is index-0 based
			return messageService.getAllMessagesPaginated(beanFilter.getStart(), beanFilter.getSize());
		}
	 	return messageService.getMessages();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getXMLMessages(@BeanParam BeanFilter beanFilter) {
		System.out.println("XML Method called");
		if(beanFilter.getYear() != 0) {
			return messageService.getAllMessagesForYear(beanFilter.getYear());
		}
		else if(beanFilter.getStart() >= 0 && beanFilter.getSize() != 0) { //start is index-0 based
			return messageService.getAllMessagesPaginated(beanFilter.getStart(), beanFilter.getSize());
		}
	 	return messageService.getMessages();
	}
	
	@Path("/{messageId}")
	@GET
	//@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
	//  return messageServiceSingleton.getMessage(messageId);		
		Message message = messageService.getMessage(messageId);	
		
		message.getLinks().add(new Link(uriInfo.getAbsolutePath()
									    .toString()
							   ,"self"));
		message.getLinks().add(new Link(uriInfo.getBaseUriBuilder()
										.path(ProfileResource.class)
										.path(message.getAuthor())
										.build()
										.toString()
							  ,"profile"));
		message.getLinks().add(new Link(uriInfo.getBaseUriBuilder()
										.path(MessageResource.class)
										.path(MessageResource.class,"getCommentResource")
										.path(CommentResource.class)
										.resolveTemplate("messageId", message.getId())
										.build()
										.toString()
							  ,"comments"));
		return message;
	}
	
	@POST
	//@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
	//  return messageServiceSingleton.addMessage(message);
		message = messageService.addMessage(message);
		
		//Using status and header methods individually
			/*return Response.status(Status.CREATED)
					   .header("location", new URI("/messenger/webapi/messages/"+message.getId())) // Hardcoded shouldn't be used.
					   .entity(message)
					   .build();*/
		
		/*String pathTillHere = uriInfo.getAbsolutePath().toString();
		UriBuilder.fromPath(pathTillHere).path(message.getId()+"");*/
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(message.getId())).build();
		
		//Using created quick-fix method
			return Response.created(uri)
				   .entity(message)
				   .build();
	}
	
	@Path("/{messageId}")
	@PUT
	public boolean updateMessage(@PathParam("messageId") long messageId, Message message) {
	//  return messageServiceSingleton.updateMessage(messageId, message);
		return messageService.updateMessage(messageId, message);
	}
	
	@Path("/{messageId}")
	@DELETE
	public boolean deleteMessage(@PathParam("messageId") long messageId) {
	//  return messageServiceSingleton.deleteMessage(messageId);		
		return messageService.deleteMessage(messageId);
	}	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();		
	}
	
}
