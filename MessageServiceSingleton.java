package org.mohan.javabrains.messenger.service;

import java.util.*;

import org.mohan.javabrains.messenger.model.Message;

public class MessageServiceSingleton {	
	
	private List<Message> messages;
	
	private static volatile MessageServiceSingleton messageServiceSingleton;
	
	private MessageServiceSingleton() {
		Message m1 = new Message(1L, "Hello World!", "Mohan");
		Message m2 = new Message(2L, "Hello Jersey!", "Mohan");
		
		messages = new ArrayList<>();
		
		messages.add(m1);
		messages.add(m2);
	}
	
	public static MessageServiceSingleton getMessageServiceSingleton() {
		if(messageServiceSingleton == null) {
			synchronized (MessageServiceSingleton.class) {
				if(messageServiceSingleton == null) {
					messageServiceSingleton = new MessageServiceSingleton();					
				}
			}
		}
		return messageServiceSingleton;
	}
	
	public List<Message> getMessages() {			
		return messages;
	}
	
	public Message getMessage(long messageId) {
		return messages.stream().filter(m -> m.getId()==messageId).findFirst().orElse(null);
	}
	
	public boolean updateMessage(long messageId, Message message) {		
		for(int index=0; index<messages.size(); index++) {
			if(messages.get(index).getId() == messageId) {
				message.setId(messageId);
				message.setCreated(new Date());
				messages.set(index, message);				
				return true;
			}
		}		
		return false;	
	}
	
	public boolean addMessage(Message message) {
		message.setId(messages.size()+1);
		message.setCreated(new Date());
		
		return messages.add(message);
	}
	
	public boolean deleteMessage(long messageId) {
		return messages.removeIf(m -> m.getId()==messageId);
	}
}
