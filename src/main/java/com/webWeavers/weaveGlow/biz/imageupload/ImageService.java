package com.webWeavers.weaveGlow.biz.imageupload;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

//	public List<Map<String, String>> imageInsert(List<MultipartFile> multiFileList) {
//		System.out.println("multiFileList : " + multiFileList);
//		
//		String root = "C:/Jiwon/workspaceSpring/weaveGlow_Final/src/main/resources/static/" + "uploadimg";
//		
////		File fileCheck = new File(root);
////		if(!fileCheck.exists()) fileCheck.mkdirs();
//		
//		List<Map<String, String>> fileList = new ArrayList<>();
//		for(int i = 0; i < multiFileList.size(); i++) {
//				String fileName = "";
//				Calendar calendar = Calendar.getInstance();
//				fileName += calendar.get(Calendar.YEAR);
//				fileName += calendar.get(Calendar.MONTH);
//				fileName += calendar.get(Calendar.DATE);
//				fileName += calendar.get(Calendar.HOUR);
//				fileName += calendar.get(Calendar.MINUTE);
//				fileName += calendar.get(Calendar.SECOND);
//				fileName += calendar.get(Calendar.MILLISECOND);
//				String originFile = multiFileList.get(i).getOriginalFilename();
//				fileName += originFile;
//				
//				Map<String, String> map = new HashMap<>();
//				map.put("originFile", originFile);
//				map.put("changeFile", fileName);
//				
//				fileList.add(map);
//			}
//		 System.out.println(fileList);
//		// 파일업로드
//		try {
//			for(int i = 0; i < multiFileList.size(); i++) {
//				File uploadFile = new File(root + "\\" + fileList.get(i).get("changeFile"));
//				multiFileList.get(i).transferTo(uploadFile);
//			}
//			System.out.println("다중 파일 업로드 성공!");
//			return fileList;
//		} catch (Exception e) {
//			System.out.println("다중 파일 업로드 실패 ㅠㅠ");
//			// 만약 업로드 실패하면 파일 삭제
//			for(int i = 0; i < multiFileList.size(); i++) {
//				new File(root + "\\" + fileList.get(i).get("changeFile")).delete();
//			}
//			e.printStackTrace();
//			return null;
//		}
//	}
	public String imageInsert(MultipartFile multipartFile, String folderName) {

		String root = "C:/Jiwon/workspaceSpring/weaveGlow_Final/src/main/resources/static/uploadimg/"+folderName;
//		File fileCheck = new File(root);
//		if(!fileCheck.exists()) fileCheck.mkdirs();

		String changeFileName = "";
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
			File uploadFile = new File(root + "\\" + changeFileName);
			multipartFile.transferTo(uploadFile);
			System.out.println("파일 업로드 성공!");
			return changeFileName;
		} catch (Exception e) {
			System.out.println("파일 업로드 실패 ㅠㅠ");
			// 만약 업로드 실패하면 파일 삭제
			new File(root + "\\" + changeFileName).delete();
			e.printStackTrace();
			return null;
		}
	}

	public String imageUpdate(MultipartFile multipartFile, String prevReviewImg, String folderName) {

		String root = "C:/Jiwon/workspaceSpring/weaveGlow_Final/src/main/resources/static/uploadimg/"+folderName;
//		File fileCheck = new File(root);
//		if(!fileCheck.exists()) fileCheck.mkdirs();
		
		// 파일 삭제
		try {
			new File(root + "\\" + prevReviewImg).delete();
			System.out.println("파일 삭제 성공!");
		} catch (Exception e) {
			System.out.println("파일 삭제 실패 ㅠㅠ");
			e.printStackTrace();
		}

		List<Map<String, String>> fileList = new ArrayList<>();
		String changeFileName = "";
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
			File uploadFile = new File(root + "\\" + changeFileName);
			multipartFile.transferTo(uploadFile);
			System.out.println("파일 업로드 성공!");
			return changeFileName;
		} catch (Exception e) {
			System.out.println("파일 업로드 실패 ㅠㅠ");
			// 만약 업로드 실패하면 파일 삭제
			new File(root + "\\" + changeFileName).delete();
			e.printStackTrace();
			return null;
		}
	}
}
