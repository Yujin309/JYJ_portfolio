package com.cook.talk.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.cook.talk.model.chat.ChatRoom;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatRoomRepository {
	    // 구독 처리 서비스
	 private Map<String, ChatRoom> chatRoomMap;
	    @PostConstruct
	    private void init() {
	        chatRoomMap = new LinkedHashMap<>();
	    }
	    public List<Object> findAllRoom() {
	        // 채팅방 생성순서 최근 순으로 반환
	        List<Object> chatRooms = new ArrayList<>(chatRoomMap.values());
	        Collections.reverse(chatRooms);
	        return chatRooms;
	    }
	    public ChatRoom findRoomById(String id) {
	        return chatRoomMap.get(id);
	    }
	    public ChatRoom createChatRoom(String name) {
	        ChatRoom chatRoom = ChatRoom.create(name);
	        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
	        return chatRoom;
	    }
	    public void plusUserCount(String roomId) {
	    	chatRoomMap.get(roomId).setCount(chatRoomMap.get(roomId).getCount()+1);
	    }
	    public void minusUserCount(String roomId) {
	    	chatRoomMap.get(roomId).setCount(chatRoomMap.get(roomId).getCount()-1);
	    }
	    public long getUserCount(String roomId) {
	    	return chatRoomMap.get(roomId).getCount();
	    }
}
