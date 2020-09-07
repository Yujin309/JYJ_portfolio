package com.cook.talk.model.chat;

import lombok.Builder;
import lombok.Data;

@Data
public class ChatMessage {

	
	@Builder
    public ChatMessage(MessageType type, String roomId, String sender, String message, long userCount) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.userCount = userCount;
    }
	public enum MessageType{
		ENTER,TALK,JOIN,QUIT
	}
	private MessageType type;
	private String roomId;
	private String sender;
	private String message;
	private long userCount;
}
