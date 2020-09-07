package com.cook.talk.model.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.cook.talk.model.VO.QnAVO;
import com.cook.talk.model.VO.UserVO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Repository
public class UserDTO {


	@NotEmpty(message = "닉네임은 필수 입력 값입니다.")
	private String nickName;

	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식에 맞지 않습니다.")
	private String userId;

	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,16}", message = "영문 +숫자 조합 8~16자리 ")
	private String userPw;

//숫자 적어도 1개, 영문 대문자 중에 하나, 공복제거 
	@Builder
	public UserDTO(String userId, String userPw, String nickName, Date birth, boolean gender) {
		this.userId = userId;
		this.userPw = userPw;
		this.nickName = nickName;
		this.birth = birth;
		this.gender = gender;

	}

	private UserVO user;

	private QnAVO qna;

	private Date birth;

	private boolean gender;

	public static CharSequence getPassword() {
		return null;
	}

	public void setPassword(String encode) {

	}

	public static void userJoin() {

	}

}
//데이터 전달 객체이므로 클라이언트 요청데이터가DTO클래스로 캡슐화 되게 한다 
//즉 컨트롤러에서 서비스 계층간 데이터를 잔달해줌. 
