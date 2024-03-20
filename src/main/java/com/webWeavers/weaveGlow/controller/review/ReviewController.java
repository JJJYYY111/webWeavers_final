package com.webWeavers.weaveGlow.controller.review;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductDTO;
import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductService;
import com.webWeavers.weaveGlow.biz.review.ReviewDTO;
import com.webWeavers.weaveGlow.biz.review.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	@Autowired
	BuyProductService buyProductService;

	@RequestMapping("/reviewWrite")
	public String reviewWrite(ReviewDTO reviewDTO, BuyProductDTO buyProductDTO, Model model) {
		
		reviewDTO = reviewService.selectOne(reviewDTO); // rDTO를 사용하여 rDAO의 검색기능인 R(selectOne)기능을 사용하여 해당 상품이 존재하는 확인
		if(reviewService.selectOne(reviewDTO) != null) { // 만약 해당 상품이 없다면 => 리뷰작성버튼으로 현재 action으로 들어왔다면
			return "redirect:/error";
		}

		buyProductDTO = buyProductService.selectOne(buyProductDTO); // bDTO를 사용하여 bDAO의 검색기능인 R(selectOne)기능을 사용하여 해당 구매한 상품이 존재하는지 확인
		if(buyProductDTO == null) { // 만약 해당 구매한 상품이 존재하지 않는다면
			return "redirect:/error"; // 에러페이지로 경로를 설정하며 return으로 아래의 코드를 즉시 종료
		}
		// 구매한 상품이 존재한다면
		reviewDTO.setBuyProductPK(buyProductDTO.getBuyProductPK()); // data객체에 bpk(구매한상품의 PK번호)를 저장
		reviewDTO.setProductPK(buyProductDTO.getProductPK()); // data객체에 bDTO의 ppk(제품의 PK번호)를 저장
		reviewDTO.setProductImg(buyProductDTO.getProductImg()); // data객체에 bDTO의 img(제품의 이미지명)를 저장
		reviewDTO.setProductName(buyProductDTO.getProductName()); // data객체에 bDTO의 pname(제품명)을 저장
		model.addAttribute("reviewDTO", reviewDTO);	//해당 data객체를 request에 저장
		
		return "user/reviewInsert";
	}
	
	@RequestMapping("/reviewModify")
	public String reviewModify(ReviewDTO reviewDTO, Model model) {
		if(reviewService.selectOne(reviewDTO) == null) {
			return "redirect:/error";
		}
		model.addAttribute("reviewDTO", reviewDTO);
		
		return "user/reviewUpdate";
	}
	
	@RequestMapping("/reviewList")
	public String reviewList(ReviewDTO reviewDTO, HttpSession session, Model model) {
		reviewDTO.setMemberID((String)session.getAttribute("sessionMid"));
		reviewDTO.setSearchCondition("myReview");
		
		model.addAttribute("rdatas", reviewService.selectAll(reviewDTO));
		return "reviewList";
	}
	
	
//	@RequestMapping("/reviewInsert")
//	public String reviewInsert(ReviewDTO reviewDTO, HttpSession session) {
//		
//		String path = "D:\\JY_java\\workspace\\weaveGlow_ver0.3.6\\src\\main\\webapp\\uploadimg";// 본인 uploadimg 폴더 경로 지정
//		int size = 10 * 1024 * 1024; // 10MB 
//		
//		// 파일을 멀티파트로 인코딩해서 받아오기 때문에 멀티파트 객체를 생성해서 변수로 저장
//		MultipartRequest multipart = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
//		
//		reviewDTO.setMemberID((String)session.getAttribute("sessionMid")); // 세션에서 사용자의 id 값을 받아와서 rDTO에 저장
//		reviewDTO.setBuyProductPK(Integer.parseInt((String)multipart.getParameter("bpk"))); 
//		// multipart객체를 사용하여 bpk(구매한상품의PK번호 <= 리뷰를 남길 상품은 어떤상품인지)를 받아와서 rDTO에 저장
//		
//		String content = multipart.getParameter("content"); // multipart객체를 사용하여 리뷰내용을 받아옴
//		if(content == null) { // 만약 리뷰 내용이 없을 경우
//			content = ""; // 리뷰를 빈칸을 지정
//		}
//		reviewDTO.setContent(content); // rDTO에 위에서 초기화한 리뷰내용을 저장
//      
//		int scope = Integer.parseInt(multipart.getParameter("scope")); // multipart 객체를 사용하여 별점(scope)값을 받아옴
//		if(scope == 0) { // 만약 별점이 0점이라면 (별점의 default=1로 지정, 만약을 위한 유효성검사)
//			scope = 1; // 별점을 1로 설정
//		}
//		reviewDTO.setScope(scope); // rDTO에 초기화한 별점값을 저장
//		String img = multipart.getFilesystemName("file"); // multipart객체를 사용하여 파일명을 받아옴
//		if(img == null) { // 만약 파일명이 존재하지 않을 경우
//			img = ""; // 파일명을 빈칸으로 지정
//		}
//		reviewDTO.setImg(img); // rDTO에 초기화한 파일명을 저장
//		System.out.println(rDTO);
//		boolean flag = reviewService.insert(reviewDTO); // rDTO를 사용하여 rDAO의 추가기능인 C(insert)기능을 사용하여 리뷰테이블에 리뷰데이터를 추가
//		if(!reviewService.insert(reviewDTO)) { // 만약 리뷰테이블에 리뷰데이터를 성공적으로 추가했다면
//			return "error";
//		}
//		return "reviewList";
//	}
//	
//	@RequestMapping("/reviewUpdate")
//	public String reviewUpdate(ReviewDTO reviewDTO) {
//		String path = "D:\\JY_java\\workspace\\weaveGlow_ver0.3.6\\src\\main\\webapp\\uploadimg";// 본인 uploadimg 폴더 경로 지정
//		int size = 10 * 1024 * 1024; // 10MB
//		
//		// 파일을 멀티파트로 인코딩해서 받아오기 때문에 멀티파트 객체를 생성해서 변수로 저장
//		MultipartRequest multipart = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
//		reviewDTO.setRpk(Integer.parseInt(multipart.getParameter("rpk"))); // request 대신 multipart를 사용하여 rpk(리뷰PK)파라미터를 받아와서 rDTO에 저장
//		
//		// 리뷰의 내용(content)을 multipart 객체를 통해서 받음.
//		String content = multipart.getParameter("content");
//		if(content == null) { // 만약 받아온 값이 null이라면 값을 ""로 지정
//			content = "";
//		}
//		reviewDTO.setContent(content); // rDTO에 content값 저장
//		
//		// 리뷰의 별점(scope)를 multipart 객체를 통해서 받음
//		int scope = Integer.parseInt(multipart.getParameter("scope"));
//	      if(scope == 0) { // 만약 받아온 값이 0이라면 값을 "1"로 지정 (scope의 default값은 1, 만약을 대비한 유효성검사)
//	    	  scope = 1;
//	      }
//	      rDTO.setScope(scope); // rDTO에 별점값을 저장
//		
//		String img = multipart.getFilesystemName("file"); // 리뷰에서 새로 업로드된 사진파일의 이름을 multipart객체를 통해 받음
//		String prevImg = multipart.getParameter("prevImg"); // 수정전에 존재하던 사진파일의 이름을 multipart객체를 통해 받음
//		if(img == null) { // 만약 이미지가 새로 업로드 되지 않았다면
//			img = prevImg; // 기존의 이미지를 사용
//		}
//		else { // 이미지가 새로 업로드 되었다면
//			// 수정전의 이미지의 경로를 Path객체에 저장
//			Path filePath = Paths.get(path+"\\"+prevImg);
//			// Path directoryPath = Paths.get("d:\\example"); <= 폴더를 삭제할경우 해당 방식을 사용
//			try {            
//				// 파일 삭제            
//				Files.delete(filePath);  // 파일객체의 delete메소드를 사용하여 위에서 초기화한 이미지경로를 사용          
//				// 디렉토리 삭제            
//				// Files.delete(directoryPath); <= 디렉토리 삭제는 좌측의 방식을 사용
//			} 
//			catch (NoSuchFileException e) { // 삭제하려는 파일이 존재하지 않는다면    
//				System.out.println("삭제하려는 파일이 없습니다"); // 삭제 실패문구 출력하고
//				e.printStackTrace(); // 해당 오류를 출력
//			}
//			catch (IOException e) { // 그외의 입출력과정에서 오류가 발생한다면
//				e.printStackTrace(); // 해당오류를 출력
//			}
//		}
//		reviewDTO.setImg(img); // 위에서 설정된 img의 값을 rDTO에 저장
//		boolean flag = reviewService.update(reviewDTO); // 리뷰관련정보를 담고있는 rDTO를 rDAO에서 수정기능인 U(update)기능을 사용하여 리뷰테이블의 데이터를 수정
//		if(!reviewService.update(reviewDTO)) { // 만약 리뷰테이블의 데이터수정이 정상적으로 작동했으면
//			return "error";
//		}
//		return "reviewList";
//	}
	
}
