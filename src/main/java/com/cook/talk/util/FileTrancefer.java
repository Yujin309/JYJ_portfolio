package com.cook.talk.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cook.talk.model.dto.RecipeDTO;

public class FileTrancefer {
	
	public static File isDirectory(String baseDir) {

		File dir = new File(baseDir);
		if(!dir.isDirectory()) {
			dir.mkdir();
		}
		return dir;
	}
	public static String requestFileTrancefer(MultipartFile file, String path) {
		//rcpPic, cookPic 업로드
		
		try {
			//basedir 주소 프로젝트 주소로 하면 배포 시 오류 생길 수 있음
			
			file.transferTo(
					new File(
							isDirectory("C:/Pictures/"+path) + file.getOriginalFilename()
					)
			);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return file.getOriginalFilename();
	}
	public static void requestMultiFilesTrancefer(MultipartHttpServletRequest multi, RecipeDTO recipeDTO) {
		//rcpPic, cookPic 업로드
		
		//String fileName = "/";
		
		
		
		Iterator<String> files = multi.getFileNames();
		List<String> list = new ArrayList<>();
		files.forEachRemaining(list::add);
		System.out.println(list);
		/*
		String rcpPic = FileTrancefer.requestFileTrancefer(file, "recipe/"); 
		String cookPic = FileTrancefer.requestFileTrancefer(file, "rcporder/");
		*/
		if(list.size()!=0) {
			if(list.get(0)!=null) { //  저장버튼 클릭시 레시피 그림 아이디의 값이 없으면 기본 값으로 셋팅 그러면 이 if문 없어도 됨
				MultipartFile mFile = multi.getFile(list.get(0));
			
				recipeDTO.getRecipeVO().setRcpPic(	requestFileTrancefer(mFile ,"recipe/"));
			}else {
				//  레시피 이미지가 안들어 왔을 때  디폴트 하나 잡으면 됨
			}
			for(int i =1 ; i< list.size(); i++) {
				MultipartFile mFile = multi.getFile(list.get(i));
				
				recipeDTO.getRcpOrderVO().setCookPic(requestFileTrancefer(mFile ,"rcporder/"));
			}
		}
	
			
			
		
		
		/*
		 * String rcpPic = FileTrancefer.requestFileTrancefer(files, "recipe/"); String
		 * cookPic = FileTrancefer.requestFileTrancefer(files, "rcporder/");
		 * 
		 * 
		 * ;
		 */
	}
}