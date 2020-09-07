package com.cook.talk.Intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cook.talk.model.service.ChatRoomRepository;


@Component
public class DemoCommandLineRunner implements CommandLineRunner {

	@Autowired
	ChatRoomRepository chat;
    @Override
    public void run(String... args) throws Exception {
    	chat.createChatRoom("레시피탐구방");
    	chat.createChatRoom("소통방");
    }


}
