package com.cook.talk.Mail;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cook.talk.model.dao.MainDAO;
import com.cook.talk.model.dto.MailDTO;

@Controller
@AllArgsConstructor
public class MailController {
	
	private final MailService mailService;
	
	@Autowired
	MainDAO maindao;
	
    @GetMapping("/mail")
    public String dispMail() {
        return "mail/NewFile";
    }

    @PostMapping("/mailSender")
    public String execMail(MailDTO mailDto,Model model) {
        mailService.mailSend(mailDto);
        model.addAttribute("total",maindao.totalSelect());
        return "main/index";
    }
}