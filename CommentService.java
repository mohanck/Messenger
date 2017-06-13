package org.mohan.javabrains.messenger.service;

import java.util.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.mohan.javabrains.messenger.database.DatabaseResource;
import org.mohan.javabrains.messenger.model.Comment;
import org.mohan.javabrains.messenger.model.Message;

public class CommentService {	
	
	private Map<Long, Message> messages;	
	
	public CommentService() {
		messages = DatabaseResource.getAllMessages();		
	}

	public List<Comment> getComments(long messageId) {		
	    return new ArrayList<>(messages.get(messageId).getComments().values());		
	}
	
	public Comment getComment(long messageId, long commentId) {
		Message message = messages.get(messageId);
		if(message == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		Map<Long,Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if(comment == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return comment;			   
	} 
	
	public Comment updateComment(long messageId, long commentId, Comment comment) {
		Message message = messages.get(messageId);
		comment.setCommentId(commentId);
		comment.setCreated(new Date());
		return message.getComments().replace(commentId,comment);
	}
	
	public boolean addComment(long messageId, Comment comment) {
		Message message = messages.get(messageId);		
		comment.setCommentId(message.getComments().size()+1);
		comment.setCreated(new Date());
		return message.getComments().put(comment.getCommentId(), comment)==null;
	}
	
	public Comment deleteComment(long messageId, long commentId) {
		return messages.get(messageId).getComments().remove(commentId);
	}
	
}
