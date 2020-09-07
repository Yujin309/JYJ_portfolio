
package com.cook.talk.model.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.dao.UserDAO;
import com.cook.talk.model.dto.UserDTO;
import com.cook.talk.model.service.Role;
import com.cook.talk.model.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	// 회원가입시 유효성 체크
	public static Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();

		for (FieldError error : errors.getFieldErrors()) {
			// 유효성 검사에 실패한 필드 목록을 가져온다.
			String validKeyName = String.format("valid_%s", error.getField());
			// 유효성 검사헤 실패한 필드명을 가져온다.
			validatorResult.put(validKeyName, error.getDefaultMessage());
			// 유효성 검사에 실패한 필드의 정의 된 메세지를 가져온다.
		}
		return validatorResult;
	}

	// 회원가입
	public void join(UserDTO userDTO) {
		// 회원가입 비지니스 로직 구현
	}

	@Transactional
	// joinUser 회원가입 처리 메서드 ,비밀번호 암호화해서 저장
	public int joinUser(UserVO userVO) { // 비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(userVO);
		userVO.setUserPw(passwordEncoder.encode(userVO.getUserPw()));
		System.err.println(userVO);
		return userDAO.login(userVO);
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loadUserByUsername 상세 정보 조회 메서드 , 계정정보와 권한을 갖는 UserDetails 인터페이스를 반환
		// 입력한 아이디 값을 통해 디비에서 값을 읽어오는것. 디비에있으면userDto 객체에 로그인 정보 담는다.
		System.out.println(" 아이디:    " + username);
		UserVO userEntity = userDAO.findUserById(username);
		System.out.println(userEntity);
		List<GrantedAuthority> authorities = new ArrayList<>();
		if (userEntity != null) {
			authorities.add(new SimpleGrantedAuthority(
					userEntity.getAccess() == 1 ? Role.MEMBER.getValue() : Role.ADMIN.getValue()));
			// authorities.add(new SimpleGrantedAuthority 롤 부여코드
		}
		return userEntity == null ? null
				: new org.springframework.security.core.userdetails.User(userEntity.getUserId(), userEntity.getUserPw(),
						authorities);
		// 생성자의 각 매개변수는 순서대로 아이디, 비밀번호, 권한리스트.
	}
}
