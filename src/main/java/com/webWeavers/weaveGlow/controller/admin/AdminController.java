package com.webWeavers.weaveGlow.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;
import com.webWeavers.weaveGlow.biz.serial.SerialDTO;
import com.webWeavers.weaveGlow.biz.serial.SerialService;
import com.webWeavers.weaveGlow.biz.subcategory.SubCategoryDTO;

@Controller
public class AdminController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	SerialService serialService;
	@Autowired
	ProductService productService;
	
	@RequestMapping("/adminDashboard")
	public String adminDashboard() {
		return "admin/adminDashboard";
	}
	
	
	@RequestMapping("/adminMemberStatus")
	public String adminMemberStatus(MemberDTO memberDTO, Model model) {
		memberDTO.setSearchCondition("allMemberInfo");
		model.addAttribute("memberDatas", memberService.selectAll(memberDTO));
		return "admin/adminMemberStatus";
	}
	
	
	@RequestMapping("/adminMemberStatusChange")
	public String adminMemberStatusChange(MemberDTO memberDTO, Model model) {
		memberDTO.setSearchCondition("idCheck");
		memberDTO = memberService.selectOne(memberDTO);		
		model.addAttribute("memberID", memberDTO.getMemberID());
		model.addAttribute("memberPassword", memberDTO.getMemberPassword());
		model.addAttribute("memberName", memberDTO.getMemberName());
		model.addAttribute("memberBirth", memberDTO.getMemberBirth());
		model.addAttribute("memberPhone", memberDTO.getMemberPhone());
		model.addAttribute("memberNickname", memberDTO.getMemberNickname());
		model.addAttribute("memberEmail", memberDTO.getMemberEmail());
		model.addAttribute("gradePK", memberDTO.getGradePK());
		return "admin/adminMemberStatusChange";
	}
	
	@RequestMapping("/adminMemberUpdate")
	public String adminMemberUpdate(MemberDTO memberDTO) {
		memberDTO.setSearchCondition("adminUpdateMember");
		System.out.println(memberDTO);
		if(!memberService.update(memberDTO)) {
			return "redirect:/error";
		}
		return "redirect:/adminMemberStatus";
	}
	
	@RequestMapping("/adminMemberSearch")
	public @ResponseBody String adminMemberSearch(MemberDTO memberDTO) {
		memberDTO.setSearchCondition("userSearch");
		System.out.println(memberDTO);
		List<MemberDTO> datas = memberService.selectAll(memberDTO);
		System.out.println(datas);
		String json;
		if (datas == null) { // 만약 주소목록이 비어있다면
			json = "[]"; // 빈 배열을 반환
		}
		Gson gson = new Gson(); // Gson 객체 생성
		json = gson.toJson(datas);
		System.out.println(json);
		return json;
	}
	
	@RequestMapping("/adminProductStatus")
	public String adminProductStatus(ProductDTO productDTO, Model model) {
		productDTO.setSearchCondition("adminProductList");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		return "admin/adminProductStatus";
	}
	
	@RequestMapping("/adminProductRegistration")
	public String adminProductRegistration() {
		return "admin/adminProductRegistration";
	}
	
	@RequestMapping("/adminProductInsert")
	public String adminProductInsert(SubCategoryDTO subCategoryDTO, ProductDTO productDTO) {
		
		
		return "adminProductStatus";
	}
	
	@RequestMapping("/adminProductStatusChange")
	public String adminProductStatusChange() {
		
		return "admin/adminProductStatusChange";
	}
	
	@RequestMapping("categoryCheckBox")
	public @ResponseBody String categoryCheckBox(ProductDTO productDTO, Gson gson, SubCategoryDTO subCategoryDTO) {
		// 서브카테고리 selectAll해서 해당 상품의 카테고리개수들 전부 가져와서 전달;
		return "";
	}
	
	
	
	
	@RequestMapping("/adminOrderStatus")
	public String adminOrderStatus(SerialDTO serialDTO, Model model) {
		serialDTO.setSearchCondition("orderList");
		model.addAttribute("serialDatas", serialService.selectAll(serialDTO));
		return "admin/adminOrderStatus";
	}
	
	
	@RequestMapping("/salesStatus")
	public String salesStatus() {
		return "admin/salesStatus";
	}
	
	@RequestMapping("/daliySalesStatus")
	public String daliySalesStatus() {
		return "admin/daliySalesStatus";
	}
	
	@RequestMapping("/monthlySalesStatus")
	public String monthlySalesStatus() {
		return "admin/monthlySalesStatus";
	}
	
	
	
}
