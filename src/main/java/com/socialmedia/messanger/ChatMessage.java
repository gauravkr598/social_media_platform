package com.socialmedia.messanger;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String chatId;
    private MessageType type;
    private String content;
    private String sender;
    private String recipientId;
    private String senderName;
    private Date timestamp;
    public enum MessageStatus{
    	RECEIVED, DELIVERED
    }
    private MessageStatus status;
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
	public ChatMessage(int id, String chatId, MessageType type, String content, String sender, String recipientId,
			String senderName, Date timestamp, MessageStatus status) {
		super();
		this.id = id;
		this.chatId = chatId;
		this.type = type;
		this.content = content;
		this.sender = sender;
		this.recipientId = recipientId;
		this.senderName = senderName;
		this.timestamp = timestamp;
		this.status = status;
	}
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public MessageStatus getStatus() {
		return status;
	}
	public void setStatus(MessageStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", chatId=" + chatId + ", type=" + type + ", content=" + content + ", sender="
				+ sender + ", recipientId=" + recipientId + ", senderName=" + senderName + ", timestamp=" + timestamp
				+ ", status=" + status + "]";
	}

    
}
