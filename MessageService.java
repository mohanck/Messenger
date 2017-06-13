package org.mohan.javabrains.messenger.service;

import java.util.*;

import org.mohan.javabrains.messenger.database.DatabaseResource;
import org.mohan.javabrains.messenger.exception.DataNotFoundException;
import org.mohan.javabrains.messenger.model.Message;

public class MessageService {	
	
	private Map<Long, Message> messages;
	
	public MessageService() {
		messages = DatabaseResource.getAllMessages();
	}

	public List<Message> getMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long messageId) {
		Message message = messages.get(messageId);
		if(message == null) {			
			throw new DataNotFoundException("Message with Message Id : "+messageId+" not found"); 
		}
		return message;
	}
	
	public boolean updateMessage(long messageId, Message message) {
		if(messages.get(messageId) != null) {
			message.setId(messageId);
			message.setCreated(new Date());
			messages.put(messageId, message);
			return true;
		}
		return false;	
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		message.setCreated(new Date());
		
		messages.put(message.getId(), message);
		return message;
	}
	
	public boolean deleteMessage(long messageId) {
		return messages.remove(messageId)!=null;
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesforYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year) {
				messagesforYear.add(message);
			}
		}
		return messagesforYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> messagesPaginated = new ArrayList<>(messages.values());
		if(start + size > messagesPaginated.size()) {
			return messagesPaginated.subList(start, messagesPaginated.size());
		}
		return messagesPaginated.subList(start, start+size);
	}
}
