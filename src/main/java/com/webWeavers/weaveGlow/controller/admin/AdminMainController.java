package com.webWeavers.weaveGlow.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.webWeavers.weaveGlow.biz.categorization.CategorizationService;
import com.webWeavers.weaveGlow.biz.imageupload.ImageService;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;
import com.webWeavers.weaveGlow.biz.serial.SerialService;
import com.webWeavers.weaveGlow.biz.subcategory.SubCategoryService;

@Controller
public class AdminMainController {

	@Autowired
	MemberService memberService;
	@Autowired
	SerialService serialService;
	@Autowired
	ProductService productService;
	@Autowired
	SubCategoryService subCategoryService;
	@Autowired
	CategorizationService categorizationService;
	@Autowired
	ImageService imageService;

	// 관리자페이지 - 관리자메인페이지로 이동하는 메서드
	@GetMapping("/adminDashboard")
	public String adminDashboard(ProductDTO productDTO, MemberDTO memberDTO, Model model) {
		// 메인페이지에 출력할데이터들을 검색조건을 통해 조회하고 해당 데이터들을 Model에 추가한뒤 페이지이동
		productDTO.setSearchCondition("adminSalesThisMonth");
		model.addAttribute("monthlyTotalSales", productService.selectOne(productDTO));
		productDTO.setSearchCondition("adminTodaySales");
		model.addAttribute("dailyTotalSales", productService.selectOne(productDTO));
		productDTO.setSearchCondition("adminSalesProductTotalNum");
		model.addAttribute("productTotalCount", productService.selectOne(productDTO));
		memberDTO.setSearchCondition("totalMember");
		model.addAttribute("memberTotalCount", memberService.selectOne(memberDTO));
		return "admin/adminDashboard";
	}
}