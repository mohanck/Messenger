package org.mohan.javabrains.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {
	private long commentId;
	private String content;
	private String firstName;
	private String lastName;
	private Date created;
		
	public Comment() {
		
	}

	public Comment(String content, String firstName, String lastName) {
		this.content = content;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", content=" + content + ", firstName=" + firstName + ", lastName="
				+ lastName + ", created=" + created + "]";
	}
		
}
