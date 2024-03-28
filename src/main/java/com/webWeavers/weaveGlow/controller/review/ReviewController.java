package com.webWeavers.weaveGlow.controller.review;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductDTO;
import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductService;
import com.webWeavers.weaveGlow.biz.imageupload.ImageService;
import com.webWeavers.weaveGlow.biz.review.ReviewDTO;
import com.webWeavers.weaveGlow.biz.review.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	@Autowired
	BuyProductService buyProductService;
	@Autowired
	ImageService imageService;

	@RequestMapping("/reviewWrite")
	public String reviewWrite(BuyProductDTO buyProductDTO, Model model) {
		buyProductDTO = buyProductService.selectOne(buyProductDTO); // bDTO를 사용하여 bDAO의 검색기능인 R(selectOne)기능을 사용하여 해당 구매한 상품이 존재하는지 확인
		if(buyProductDTO == null) { // 만약 해당 구매한 상품이 존재하지 않는다면
			return "redirect:/error"; // 에러페이지로 경로를 설정하며 return으로 아래의 코드를 즉시 종료
		}
		model.addAttribute("buyProductDTO", buyProductDTO);	//해당 data객체를 request에 저장
		return "user/reviewWrite";
	}
	
	@RequestMapping("/reviewEdit")
	public String reviewEdit(ReviewDTO reviewDTO, Model model) {
		System.out.println(reviewDTO);
		reviewDTO = reviewService.selectOne(reviewDTO);
		if(reviewDTO == null) {
			return "redirect:/error";
		}
		model.addAttribute("reviewDTO", reviewDTO);
		
		return "user/reviewEdit";
	}
	
	@RequestMapping("/reviewList")
	public String reviewList(ReviewDTO reviewDTO, HttpSession session, Model model) {
		reviewDTO.setMemberID((String)session.getAttribute("sessionMid"));
		reviewDTO.setSearchCondition("myReview");
		
		model.addAttribute("rdatas", reviewService.selectAll(reviewDTO));
		return "user/reviewList";
	}
	
	
	@RequestMapping("/reviewInsert")
	public String reviewInsert(ReviewDTO reviewDTO, @RequestParam("reviewImgFile") MultipartFile multipartFile) {
		reviewDTO.setReviewImg(imageService.imageInsert(multipartFile, "review"));
		if(!reviewService.insert(reviewDTO)) {
			return "redirect:/error";
		}
		return "redirect:/reviewList";
	}
	
	@RequestMapping("/reviewUpdate")
	public String reviewUpdate(ReviewDTO reviewDTO, @RequestParam("reviewImgFile") MultipartFile multipartFile) {
		reviewDTO.setReviewImg(imageService.imageInsert(multipartFile, "review"));
		System.out.println(reviewDTO);
		if(!reviewService.update(reviewDTO)) {
			return "redirect:/error";
		}
		return "redirect:/reviewList";
	}
	
}
	
