package com.cook.talk.controller;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cook.talk.model.chat.ChatMessage;
import com.cook.talk.model.chat.LoginInfo;
import com.cook.talk.model.service.ChatRoomRepository;
import com.cook.talk.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatController {
	private final SimpMessageSendingOperations messagingTemplate;
	private final JwtTokenProvider jwtTokenProvider;
	private final ChatRoomRepository chatRoomRepository;

	@MessageMapping("/chat/message")
	public void message(ChatMessage message, @Header("token") String token) {
		String nickname = jwtTokenProvider.getUserNameFromJwt(token);
		// 로그인 회원 정보로 대화명 설정
		message.setSender(nickname);
		// 채팅방 입장시에는 대화명과 메시지를 자동으로 세팅한다.
		if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
			message.setSender("[알림]");
			message.setMessage(nickname + "님이 입장하셨습니다.");
			chatRoomRepository.plusUserCount(message.getRoomId());
		} else if (ChatMessage.MessageType.QUIT.equals(message.getType())) {
			message.setMessage(message.getSender() + "님이 방에서 나갔습니다.");
			chatRoomRepository.minusUserCount(message.getRoomId());
			message.setSender("[알림]");
		}
		messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
	}

	@GetMapping("/user")
	@ResponseBody
	public LoginInfo getUserInfo() {
		// comVo.setUserId(princi b pal.getName())
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		return LoginInfo.builder().name(name).token(jwtTokenProvider.generateToken(name)).build();
	}
}
