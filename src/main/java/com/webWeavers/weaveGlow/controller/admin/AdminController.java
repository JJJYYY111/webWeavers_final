package com.webWeavers.weaveGlow.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;

@Controller
public class AdminController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/adminDashboard")
	public String adminDashboard() {
		return "admin/adminDashboard";
	}
	
	
	@RequestMapping("/adminMemberStatus")
	public String adminMemberStatus(MemberDTO memberDTO, Model model) {
		memberDTO.setSearchCondition("allMemberInfo");
		List<MemberDTO> memberDatas = memberService.selectAll(memberDTO);
		model.addAttribute("memberDatas", memberDatas);
		return "admin/adminMemberStatus";
	}
	
	
	@RequestMapping("/adminMemberUpdate")
	public String adminMemberUpdate(MemberDTO memberDTO, Model model) {
		memberDTO.setSearchCondition("idCheck");
		memberDTO = memberService.selectOne(memberDTO);
		model.addAttribute("memberPassword", memberDTO.getMemberPassword());
		model.addAttribute("memberName", memberDTO.getMemberName());
		model.addAttribute("memberBirth", memberDTO.getMemberBirth());
		model.addAttribute("memberPhone", memberDTO.getMemberPhone());
		model.addAttribute("memberNickname", memberDTO.getMemberNickname());
		model.addAttribute("memberEmail", memberDTO.getMemberEmail());
		model.addAttribute("gradePK", memberDTO.getGradePK());
		return "admin/adminMemberUpdate";
	}
	
	
	@RequestMapping("/adminProductStatus")
	public String adminProductStatus() {
		return "admin/adminProductStatus";
	}
	
	
	@RequestMapping("/adminProductRegistration")
	public String adminProductRegistration() {
		return "admin/adminProductRegistration";
	}
	
	
	@RequestMapping("/adminOrderStatus")
	public String adminOrderStatus() {
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
