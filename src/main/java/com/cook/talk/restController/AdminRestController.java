package com.cook.talk.restController;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cook.talk.model.VO.IngrVO;
import com.cook.talk.model.VO.QnAVO;
import com.cook.talk.model.VO.RecipeVO;
import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.dao.AdIngrDAO;
import com.cook.talk.model.dao.AdRecipeDAO;
import com.cook.talk.model.dao.AdUserDAO;
import com.cook.talk.model.dao.QnADAO;

@RestController
public class AdminRestController {
	@Autowired(required = false)
	private QnADAO qnaDAO;

	@Autowired(required = false)
	private AdUserDAO aduserDAO;

	@Autowired(required = false)
	private AdRecipeDAO adRecipeDAO;

	@Autowired(required = false)
	private AdIngrDAO adIngrDAO;

	@PostMapping("/admin/deleteUserID") // user 정보 삭제
	public String deleteUserID(UserVO userId) {
		System.out.println(userId);
		aduserDAO.deleteUserID(userId);
		System.out.println("아 되자 제발" + userId);

		return "삭제되었습니다";

	}
	@PostMapping("/admin/updateUserNickName") // 닉네임 업데이트
	public String updateUserNickName(@ModelAttribute UserVO userVO) {
		System.out.println(userVO.getUserId() + "userId를 불러오는자리");
		System.out.println(userVO);
		System.out.println(userVO.getNickName() + "userName를 불러오는자리");
		aduserDAO.updateUserNickName(userVO);
		return "수정되었습니다.";
	}

	@PostMapping("/admin/deleteIngr2")//재료 삭제
	public String deleteIngr2(@RequestBody List<String> rcpingrCode) {
		System.out.println(rcpingrCode);
		adIngrDAO.deleteIngr(rcpingrCode);
		System.out.println(rcpingrCode + "삭제되었습니다.");

		return "삭제되었습니다";
	}
	@PostMapping("/admin/deleteRcp")
	public String deleteUser(RecipeVO rcpCode) {
		System.out.println(rcpCode);
		adRecipeDAO.deleteRecipe(rcpCode);
		System.out.println(rcpCode+"가 삭제 되었습니다.");
		return "/delete rcp";
	}

	@PostMapping("/admin/searchUser")
	public List<UserVO> searchUser(@RequestParam("nickName") String nickName) {
		System.out.println(nickName);
		aduserDAO.searchUser(nickName);
		System.out.println(aduserDAO.searchUser(nickName));
		return aduserDAO.searchUser(nickName);
	}

	@PostMapping("/admin/search")
	public List<IngrVO> searchIngr(@RequestParam("ingrName") String ingrVO) {
		System.out.println(ingrVO);
		adIngrDAO.searchIngr(ingrVO);
		System.out.println(adIngrDAO.searchIngr(ingrVO));
		return adIngrDAO.searchIngr(ingrVO);
	}

	@PostMapping("/admin/adRecipe")
	public void adRecipe() {
	}

	@PostMapping("/admin/Ingr")
	public void Ingr() {
	}

	@PostMapping("/admin/qnaAnswer") // qna 답변
	public String insertReply(@ModelAttribute QnAVO qna) {
		System.out.println(qna.getUserId() + "유저 아이디");
		System.out.println(qna.getAnswer() + "답이 입력될거에요 ");
		System.out.println(qna);
		qnaDAO.insertReply(qna);
		return "추가되었습니다.";
	}

	@PostMapping("/admin/searchId") // 아이디 찾아서 레시피 찾기
	public List<RecipeVO> searchRecipeByNickName(@RequestParam("userId") String userId) {
		System.out.println(userId);
		adRecipeDAO.searchRecipeById(userId);
		System.out.println(adRecipeDAO.searchRecipeById(userId));
		System.out.println("여기까지되나요?");
		return adRecipeDAO.searchRecipeById(userId);
	}

	@GetMapping("/admin/IngrPagination")
	public List<IngrVO> ingrPagination(int pageNUM) {

		return adIngrDAO.countPaginationIngrLimit(pageNUM);

	}

	@GetMapping("/admin/UserPagination")
	public List<UserVO> userPagination(int pageNum) {

		return aduserDAO.countPaginationUserLimit((pageNum - 1) * 10);
	}

}