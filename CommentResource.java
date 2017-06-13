package org.mohan.javabrains.messenger.resources;

import java.util.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.mohan.javabrains.messenger.model.Comment;
import org.mohan.javabrains.messenger.service.CommentService;
@Path("/")
public class CommentResource {
	
	private CommentService commentService = new CommentService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@PathParam("messageId") long messageId) {
		return commentService.getComments(messageId);
	}
	
	@Path("/{commentId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.getComment(messageId, commentId);
	}
	
	@POST
	public boolean addComment(@PathParam("messageId") long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}
	
	@Path("/{commentId}")
	@PUT
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment) {
		return commentService.updateComment(messageId, commentId, comment);
	}
	
	@Path("/{commentId}")
	@DELETE
	public Comment deleteComment(@PathParam("messageId") long messageId, long commentId) {
		return commentService.deleteComment(messageId, commentId);
	}
}
