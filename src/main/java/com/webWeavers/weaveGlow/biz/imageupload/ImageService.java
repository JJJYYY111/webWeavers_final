package com.webWeavers.weaveGlow.biz.imageupload;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

	public String imageInsert(MultipartFile multipartFile, String folderName) {

		String root = "C:/Jiwon/workspaceSpring/weaveGlow_Final/src/main/";
//		File fileCheck = new File(root);
//		if(!fileCheck.exists()) fileCheck.mkdirs();

		String changeFileName = "uploadimg/"+folderName+"/";;
		Calendar calendar = Calendar.getInstance();
		changeFileName += calendar.get(Calendar.YEAR);
		changeFileName += calendar.get(Calendar.MONTH);
		changeFileName += calendar.get(Calendar.DATE);
		changeFileName += calendar.get(Calendar.HOUR);
		changeFileName += calendar.get(Calendar.MINUTE);
		changeFileName += calendar.get(Calendar.SECOND);
		changeFileName += calendar.get(Calendar.MILLISECOND);
		String originFileName = multipartFile.getOriginalFilename();
		changeFileName += originFileName;
		// 파일업로드
		try {
			File uploadFile = new File(root + "resources/static/" + changeFileName);
			multipartFile.transferTo(uploadFile);
			System.out.println("파일 업로드 성공!");
			return "resources/" + changeFileName;
		} catch (Exception e) {
			System.out.println("파일 업로드 실패 ㅠㅠ");
			// 만약 업로드 실패하면 파일 삭제
			new File(root + "resources/static/" + changeFileName.replaceFirst("resources/", "")).delete();
			e.printStackTrace();
			return null;
		}
	}

	public String imageUpdate(MultipartFile multipartFile, String prevReviewImg, String folderName) {

		String root = "C:/Jiwon/workspaceSpring/weaveGlow_Final/src/main/";
//		File fileCheck = new File(root);
//		if(!fileCheck.exists()) fileCheck.mkdirs();
		
		// 파일 삭제
		try {
			new File(root + "resources/static/" + prevReviewImg.replaceFirst("resources/", "")).delete();
			System.out.println("이전 파일 삭제 성공!");
		} catch (Exception e) {
			System.out.println("이전 파일 삭제 실패 ㅠㅠ");
			e.printStackTrace();
		}

		List<Map<String, String>> fileList = new ArrayList<>();
		String changeFileName = "uploadimg/"+folderName+"/";
		Calendar calendar = Calendar.getInstance();
		changeFileName += calendar.get(Calendar.YEAR);
		changeFileName += calendar.get(Calendar.MONTH);
		changeFileName += calendar.get(Calendar.DATE);
		changeFileName += calendar.get(Calendar.HOUR);
		changeFileName += calendar.get(Calendar.MINUTE);
		changeFileName += calendar.get(Calendar.SECOND);
		changeFileName += calendar.get(Calendar.MILLISECOND);
		String originFileName = multipartFile.getOriginalFilename();
		changeFileName += originFileName;
		System.out.println(fileList);
		
		// 파일 업로드
		try {
			File uploadFile = new File(root + "resources/static/" + changeFileName);
			multipartFile.transferTo(uploadFile);
			System.out.println("파일 업로드 성공!");
			return "resources/" + changeFileName;
		} catch (Exception e) {
			System.out.println("파일 업로드 실패 ㅠㅠ");
			// 만약 업로드 실패하면 파일 삭제
			new File(root + "resources/static/" + changeFileName.replaceFirst("resources/", "")).delete();
			e.printStackTrace();
			return null;
		}
	}
}
