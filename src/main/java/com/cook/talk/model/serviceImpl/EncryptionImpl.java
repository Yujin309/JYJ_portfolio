package com.cook.talk.model.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cook.talk.Mail.MailService;
import com.cook.talk.encryption.Encryption;
import com.cook.talk.model.dao.UserDAO;
import com.cook.talk.model.dto.MailDTO;
import com.cook.talk.model.service.EncryptionService;

@Service
public class EncryptionImpl implements EncryptionService {
	@Autowired
	MailService mail;
	@Autowired
	UserDAO userDAO;
	@Override
	public String encryption(String id) {
		String key = "cooking-talking-key"; // key는 16자 이상
		MailDTO mailDTO = new MailDTO();
		mailDTO.setAddress(id);
		mailDTO.setTitle("쿠킹토킹 메일인증");
		try {
			Encryption aes256 = new Encryption(key);
			URLCodec codec = new URLCodec();
			String encLoginidx = codec.encode(aes256.aesEncode(id));

			mailDTO.setMessage("http://localhost/loginConfirm/" + encLoginidx);
			mail.mailSend(mailDTO);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String Decrypt(String id) {
		String key = "cooking-talking-key";
	
		try {
			String key1 = URLEncoder.encode(id,"UTF-8");
			Encryption aes256 = new Encryption(key);
			URLCodec codec = new URLCodec();
			String decLoginidx = aes256.aesDecode(codec.decode(key1));
			userDAO.updateAccess(decLoginidx);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
